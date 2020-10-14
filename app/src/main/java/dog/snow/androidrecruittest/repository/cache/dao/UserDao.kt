package dog.snow.androidrecruittest.repository.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.repository.cache.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User) : Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers() : List<User>

    @Query("SELECT * FROM users WHERE id=:id")
    suspend fun getUserById(id : Int) : User

    @Query("DELETE FROM users")
    suspend fun clearData()
}