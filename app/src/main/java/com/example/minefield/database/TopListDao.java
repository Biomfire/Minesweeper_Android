package com.example.minefield.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopListDao{
    @Query("SELECT * FROM TopList ORDER BY points DESC")
    List<TopListRecord> getAll();

    @Query("SELECT * FROM TopList where id IN (:userIds)")
    List<TopListRecord> loadAllByIds(int[] userIds);

    @Insert
    long insert(TopListRecord topListRecord);


    @Delete
    void delete(TopListRecord topListRecord);
}

