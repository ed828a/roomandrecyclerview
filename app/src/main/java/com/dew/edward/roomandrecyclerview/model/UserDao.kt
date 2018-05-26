package com.dew.edward.roomandrecyclerview.model

/*
 * Created by Edward on 5/25/2018.
 */

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface UserDao {
    //    @get:Query("SELECT * FROM user")
    @get:Query("SELECT * FROM user")
    val allUsers: List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}
