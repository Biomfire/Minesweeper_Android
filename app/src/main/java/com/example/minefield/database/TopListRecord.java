package com.example.minefield.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "TopList")
public class TopListRecord {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "time")
    public Date time;

    @ColumnInfo(name = "points")
    public Double Points;

}
