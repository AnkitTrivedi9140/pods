package com.example.podsstore.data.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.podsstore.data.local.dao.QuantityDao;
import com.example.podsstore.data.local.entity.ProductEntity;


@Database(entities = { ProductEntity.class}, version = 1, exportSchema = false)
public abstract class DatabaseService extends RoomDatabase {

    private static com.example.podsstore.data.local.DatabaseService instance;
    public abstract QuantityDao quantityDao();


    public static synchronized com.example.podsstore.data.local.DatabaseService getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    com.example.podsstore.data.local.DatabaseService.class, "POD_DB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}


