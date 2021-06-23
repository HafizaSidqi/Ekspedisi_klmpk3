package com.example.Ekspedisi.rating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Ekspedisi.R;
import com.example.Ekspedisi.entity.AppDatabase;
import com.example.Ekspedisi.entity.Rating;
import com.example.Ekspedisi.ui.home.MainActivity;
import com.example.Ekspedisi.rating.RatingAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Rating> daftarRating;
    Button btn_Rating, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        daftarRating = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbRating").allowMainThreadQueries().build();

        daftarRating.addAll(Arrays.asList(database.ratingDAO().readDataRating()));
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RatingAdapter(daftarRating, this);
        recyclerView.setAdapter(adapter);

        //Button pada halaman activity_read
        btn_Rating = findViewById(R.id.btnRating2);
        btn_back = findViewById(R.id.btn_back);

        btn_Rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadActivity.this, MainActivity
                        .class);
                startActivity(intent);
            }
        });
    }
}