package com.example.rosdan.dwi.ceritadunia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailCeritaActivity extends AppCompatActivity {

    TextView judul, penulis, cerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cerita);

        Intent intent = getIntent();
        judul = findViewById(R.id.judul);
        penulis = findViewById(R.id.username);
        cerita = findViewById(R.id.cerita);

        getSupportActionBar().setTitle(intent.getStringExtra("judul"));
        judul.setText(intent.getStringExtra("judul"));
        penulis.setText("ditulis oleh: " + intent.getStringExtra("username"));
        cerita.setText(intent.getStringExtra("isi"));
    }
}
