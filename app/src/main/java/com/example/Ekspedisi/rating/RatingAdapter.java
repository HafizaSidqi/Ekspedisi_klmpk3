package com.example.Ekspedisi.rating;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.Ekspedisi.R;
import com.example.Ekspedisi.entity.AppDatabase;
import com.example.Ekspedisi.entity.Rating;

import java.util.ArrayList;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

    //Deklarasi Variable
    private ArrayList<Rating> daftarRating;
    private AppDatabase appDatabase;
    private Context context;


    public RatingAdapter(ArrayList<Rating> daftarRating, Context context) {

        //Inisialisasi data
        this.daftarRating = daftarRating;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbRating").allowMainThreadQueries().build();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //Deklarasi View yang akan digunakan
        private TextView nama, rating, komen;
        private  int id;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_item_nama);
            rating = itemView.findViewById(R.id.tv_item_rating);
            komen = itemView.findViewById(R.id.tv_item_komentar);
            item = itemView.findViewById(R.id.rc_main);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rating, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String getNama = daftarRating.get(position).getName();
        String getRating = daftarRating.get(position).getRating();
        String getKomen = daftarRating.get(position).getKomentar();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.nama.setText(getNama);
        holder.rating.setText(getRating);
        holder.komen.setText(getKomen);
    }


    @Override
    public int getItemCount() {

        return daftarRating.size();
    }



}
