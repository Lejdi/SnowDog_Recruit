package dog.snow.androidrecruittest.repository.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.repository.cache.dao.AlbumDao
import dog.snow.androidrecruittest.repository.cache.dao.PhotoDao
import dog.snow.androidrecruittest.repository.cache.dao.UserDao
import dog.snow.androidrecruittest.repository.cache.model.Album
import dog.snow.androidrecruittest.repository.cache.model.Photo
import dog.snow.androidrecruittest.repository.cache.model.User

@Database(
    entities = [
        Album::class,
        Photo::class,
        User::class],
    version = 1
)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getAlbumDao(): AlbumDao

    companion object{
        val DB_NAME = "cache_db"
    }
}