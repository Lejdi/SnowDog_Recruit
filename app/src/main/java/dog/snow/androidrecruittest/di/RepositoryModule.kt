package dog.snow.androidrecruittest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.repository.cache.dao.AlbumDao
import dog.snow.androidrecruittest.repository.cache.dao.PhotoDao
import dog.snow.androidrecruittest.repository.cache.dao.UserDao
import dog.snow.androidrecruittest.repository.cache.mapper.AlbumMapper
import dog.snow.androidrecruittest.repository.cache.mapper.PhotoMapper
import dog.snow.androidrecruittest.repository.cache.mapper.UserMapper
import dog.snow.androidrecruittest.repository.networking.service.AlbumRetrofit
import dog.snow.androidrecruittest.repository.networking.service.PhotoRetrofit
import dog.snow.androidrecruittest.repository.networking.service.UserRetrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        userRetrofit: UserRetrofit,
        userCacheMapper: UserMapper,
        userNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.UserMapper
    ) : UserRepository{
        return UserRepository(userDao, userRetrofit, userCacheMapper, userNetworkMapper)
    }

    @Singleton
    @Provides
    fun providePhotoRepository(
        photoDao: PhotoDao,
        photoRetrofit: PhotoRetrofit,
        photoCacheMapper: PhotoMapper,
        photoNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.PhotoMapper
    ) : PhotoRepository{
        return PhotoRepository(photoDao, photoRetrofit, photoCacheMapper, photoNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumDao: AlbumDao,
        albumRetrofit: AlbumRetrofit,
        albumCacheMapper: AlbumMapper,
        albumNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.AlbumMapper
    ) : AlbumRepository {
        return AlbumRepository(albumDao, albumRetrofit, albumCacheMapper, albumNetworkMapper)
    }
}