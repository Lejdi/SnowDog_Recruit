package dog.snow.androidrecruittest.repository.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.repository.cache.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo) : Long

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos() : List<Photo>

    @Query("SELECT * FROM photos WHERE id=:id")
    suspend fun getPhotoById(id : Int) : Photo

    @Query("DELETE FROM photos")
    suspend fun clearData()
}