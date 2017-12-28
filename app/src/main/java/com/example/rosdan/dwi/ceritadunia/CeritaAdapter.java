package com.example.rosdan.dwi.ceritadunia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cipowela on 28/12/17.
 */

public class CeritaAdapter extends RecyclerView.Adapter<CeritaAdapter.Holder> {

    private Context c;
    private List<Cerita> ceritaList;

    public CeritaAdapter(Context c, List<Cerita> ceritaList) {
        this.c = c;
        this.ceritaList = ceritaList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cerita, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Cerita indexNow = ceritaList.get(position);
        holder.judul.setText(indexNow.judul);
        holder.genre.setText(indexNow.genre);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, DetailCeritaActivity.class);
                intent.putExtra("judul", indexNow.judul);
                intent.putExtra("username", indexNow.penulis);
                intent.putExtra("isi", indexNow.cerita);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ceritaList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView judul, genre;
        LinearLayout layout;

        public Holder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            genre = itemView.findViewById(R.id.genre);
            layout = itemView.findViewById(R.id.layout_cerita_item);
        }
    }
}
