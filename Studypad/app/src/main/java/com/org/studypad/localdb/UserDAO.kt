package com.org.studypad.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.org.studypad.localdb.entity.UserLocalInfo

/**
 * User db dao operation
 */
@Dao
interface UserDAO {
    /**
     * Get all user info list to show
     */
    @Query("SELECT * from user_info")
     suspend fun getAllUsers(): List<UserLocalInfo>

    /**
     * Insert user information into db
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun addUser(userLocalInfo: List<UserLocalInfo>?)
}