package com.example.Ekspedisi.rating;

import android.os.AsyncTask;
import android.util.Log;

import com.example.Ekspedisi.entity.AppDatabase;
import com.example.Ekspedisi.entity.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingPresenter implements RatingContract.presenter {
    private RatingContract.view viewContract;

    public RatingPresenter(RatingContract.view viewContract) {
        this.viewContract = viewContract;
    }


    /***
     * InserData dibutuhkan untuk menambahkan data. Class {@link InsertData}
     * digunakan untuk proses penambahan data ke daabase menggunakan AsyncTask .
     * Sementara fungsi insertData digunaakan untuk memanggil class {@link InsertData}
     */

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private Rating rating;

        public InsertData(AppDatabase database, Rating rating) {
            this.database = database;
            this.rating = rating;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.ratingDAO().insertData(rating);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            viewContract.successAdd();
        }

    }

    @Override
    public void insertData(String nama, String rating, String komentar,
                           final AppDatabase database) {
        final Rating dataDiri = new Rating();
        dataDiri.setRating(rating);
        dataDiri.setKomentar(komentar);
        dataDiri.setName(nama);
        new InsertData(database, dataDiri).execute();
    }


    /***
     * Pada fungsi readData, kita mencoba untuk membaca isi database tanpa menggunakan
     * AsyncTask . Yaitu dengan cara langsung memanggil perintah untuk membaca databaae
     * (database.dao().getData() )
     *
     * Sebenernya disarankan untuk menggunakan AsyncTask seperti pada fungsi InsertData
     */

    @Override
    public void readData(AppDatabase database) {
        Rating[] list;
        list = database.ratingDAO().readDataRating();
        viewContract.getData(Arrays.asList(list));
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private Rating rating;

        public EditData(AppDatabase database, Rating rating) {
            this.database = database;
            this.rating = rating;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.ratingDAO().updateData(rating);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            viewContract.successAdd();
        }
    }

    @Override
    public void editData(String nama, String rating, String komentar, int id,
                         final AppDatabase database) {
        final Rating dataDiri = new Rating();
        dataDiri.setRating(rating);
        dataDiri.setKomentar(komentar);
        dataDiri.setName(nama);
        dataDiri.setId(id);

        new EditData(database, dataDiri).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private Rating rating;

        public DeleteData(AppDatabase database, Rating rating) {
            this.database = database;
            this.rating = rating;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.ratingDAO().deleteData(rating);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewContract.successDelete();
        }

    }

    @Override
    public void deleteData(final Rating rating,
                           final AppDatabase database) {
        new DeleteData(database, rating).execute();
    }

}