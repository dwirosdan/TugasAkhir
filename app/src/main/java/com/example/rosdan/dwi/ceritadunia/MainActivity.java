package com.example.rosdan.dwi.ceritadunia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;
    List<Cerita> ceritaList = new ArrayList<>();
    CeritaAdapter adapter;

    DatabaseReference ceritaRef = FirebaseDatabase.getInstance().getReference("cerita");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CeritaAdapter(this, ceritaList);
        view.setAdapter(adapter);


        ceritaRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Cerita now = new Cerita(
                        dataSnapshot.child("judul").getValue().toString(),
                        dataSnapshot.child("genre").getValue().toString(),
                        dataSnapshot.child("cerita").getValue().toString(),
                        dataSnapshot.child("penulis").getValue().toString()
                );

                ceritaList.add(now);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            SharedPreferences preferences = getSharedPreferences(LoginActivity.loginsPref, 0);
            preferences.edit().remove("user").apply();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tambahView(View view) {
        startActivity(new Intent(this, TambahActivity.class));
    }
}
