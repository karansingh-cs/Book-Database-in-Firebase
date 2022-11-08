package com.example.bookdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    BookDetail bookDetail;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button sendDatabtn;
    private EditText editBookName, editBookGenre, editBookPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editBookName = findViewById(R.id.idEditBookName);
        editBookGenre = findViewById(R.id.idEditBookGenre);
        editBookPrice = findViewById(R.id.idEditBookPrice);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BookDetail");

        bookDetail = new BookDetail();
        sendDatabtn = findViewById(R.id.idBtnSendData);

        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editBookName.getText().toString();
                String genre = editBookGenre.getText().toString();
                String price = editBookPrice.getText().toString();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(genre) && TextUtils.isEmpty(price)) {
                    Toast.makeText(MainActivity.this, "Please add details about book", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(name, genre, price);
                }
            }
        });
    }

    private void addDatatoFirebase(String name, String genre, String price) {
        bookDetail.setBookName(name);
        bookDetail.setBookGenre(genre);
        bookDetail.setBookPrice(price);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(bookDetail);
                Toast.makeText(MainActivity.this, "Data is added!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to add the Data!" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }




}