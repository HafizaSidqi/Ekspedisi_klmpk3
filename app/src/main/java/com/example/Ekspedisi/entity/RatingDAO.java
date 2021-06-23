package com.example.Ekspedisi.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RatingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertData(Rating rating);

    @Query("Select * from tRating")
    Rating[] readDataRating();

    @Update
    int updateData(Rating rating);

    @Delete
    void deleteData(Rating rating);
}
