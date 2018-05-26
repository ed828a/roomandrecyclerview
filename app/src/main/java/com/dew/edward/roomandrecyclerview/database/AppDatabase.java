package com.dew.edward.roomandrecyclerview.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dew.edward.roomandrecyclerview.model.User;
import com.dew.edward.roomandrecyclerview.model.UserDao;

/*
 * Created by Edward on 5/26/2018.
 */





@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
