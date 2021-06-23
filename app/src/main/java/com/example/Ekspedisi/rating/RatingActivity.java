package com.example.Ekspedisi.rating;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.Ekspedisi.R;
import com.example.Ekspedisi.entity.AppDatabase;
import com.example.Ekspedisi.entity.Rating;
import com.google.android.material.textfield.TextInputEditText;

public class RatingActivity extends AppCompatActivity {
    private int time=4000;
    private int id=0;
    private AppDatabase database;

    EditText et_nama, et_rating, et_komen;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_rating);

        et_nama = findViewById(R.id.et_nama);
        et_rating = findViewById(R.id.et_rating);
        et_komen = findViewById(R.id.et_komentar);
        btn_submit = findViewById(R.id.btn_submit);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbRating") //Nama File Database yang akan disimpan
                .build();

        btn_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(et_nama.getText().toString().isEmpty()){
                    Toast.makeText(RatingActivity.this,"Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (et_rating.getText().toString().isEmpty()){
                    Toast.makeText(RatingActivity.this,"Rating boleh kosong!", Toast.LENGTH_SHORT).show();
                }
                Rating rating = new Rating();
                //memasukkan data input ke dalam database
                rating.setId(id);
                rating.setName(et_nama.getText().toString());
                rating.setRating(et_rating.getText().toString());
                rating.setKomentar(et_komen.getText().toString());
                insertRating(rating);
                Intent intent = new Intent(RatingActivity.this, ReadActivity.class);
                startActivity(intent);

            }

            private void insertRating(final Rating rating) {
                new AsyncTask<Void, Void, Long>() {
                    @Override
                    protected Long doInBackground(Void... voids) {
                        //Menjalankan proses insert data
                        long status = database.ratingDAO().insertData(rating);
                        return status;
                    }

                    @Override
                    protected void onPostExecute(Long status) {
                        //Menandakan bahwa data berhasil disimpan
                        Toast.makeText(RatingActivity.this, "Penilaian berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });

    }
}
