package dog.snow.androidrecruittest.repository.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.repository.cache.model.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: Album) : Long

    @Query("SELECT * FROM albums")
    suspend fun getAllAlbums() : List<Album>

    @Query("SELECT * FROM albums WHERE id=:id")
    suspend fun getAlbumById(id : Int) : Album

    @Query("DELETE FROM albums")
    suspend fun clearData()
}