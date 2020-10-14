package dog.snow.androidrecruittest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dog.snow.androidrecruittest.repository.cache.CacheDatabase
import dog.snow.androidrecruittest.repository.cache.dao.AlbumDao
import dog.snow.androidrecruittest.repository.cache.dao.PhotoDao
import dog.snow.androidrecruittest.repository.cache.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : CacheDatabase{
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            CacheDatabase.DB_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun providePhotoDao(cacheDatabase: CacheDatabase) : PhotoDao{
        return cacheDatabase.getPhotoDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(cacheDatabase: CacheDatabase) : UserDao {
        return cacheDatabase.getUserDao()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(cacheDatabase: CacheDatabase) : AlbumDao {
        return cacheDatabase.getAlbumDao()
    }
}