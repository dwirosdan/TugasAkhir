package com.example.rosdan.dwi.ceritadunia;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    EditText judul, genre, cerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        getSupportActionBar().setTitle("Tambah Cerita");
        judul = findViewById(R.id.tambah_judul);
        genre = findViewById(R.id.tambah_genre);
        cerita = findViewById(R.id.tambah_isi);
    }

    public void tambah(View view) {
        String mjudul, mgenre, mcerita, mpenulis;

        SharedPreferences preferences = getSharedPreferences(LoginActivity.loginsPref, 0);

        mjudul = judul.getText().toString();
        mgenre = genre.getText().toString();
        mcerita = cerita.getText().toString();
        mpenulis = preferences.getString("user", "");

        if (mjudul.isEmpty() && mgenre.isEmpty() && mcerita.isEmpty()) {
            Toast.makeText(this, "Ada field kosong", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference ceritaRef = ref.child("cerita");

            ceritaRef.push().setValue(new Cerita(mjudul, mgenre, mcerita, mpenulis));
            Toast.makeText(this, "Cerita di tambahkan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
