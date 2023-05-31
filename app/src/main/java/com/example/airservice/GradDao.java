package com.example.airservice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradDao {

    @Insert
    void insert(Grad grad);

    @Update
    void update(Grad grad);

    @Delete
    void delete(Grad grad);

    @Query("DELETE FROM grad_tabela")
    void deleteAllGrads();

    @Query("SELECT * FROM grad_tabela ORDER BY aqi DESC")
    LiveData<List<Grad>> getAllGrads();
}
