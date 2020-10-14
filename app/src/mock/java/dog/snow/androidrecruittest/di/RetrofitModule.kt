package dog.snow.androidrecruittest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dog.snow.androidrecruittest.repository.MockAlbumRetrofit
import dog.snow.androidrecruittest.repository.MockPhotoRetrofit
import dog.snow.androidrecruittest.repository.MockUserRetrofit
import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.networking.service.AlbumRetrofit
import dog.snow.androidrecruittest.repository.networking.service.PhotoRetrofit
import dog.snow.androidrecruittest.repository.networking.service.UserRetrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideMockLoader() : MockModelLoader{
        return MockModelLoader()
    }

    @Singleton
    @Provides
    fun providePhotoRetrofit(mockModelLoader: MockModelLoader) : PhotoRetrofit {
        return MockPhotoRetrofit(mockModelLoader)
    }

    @Singleton
    @Provides
    fun provideAlbumRetrofit(mockModelLoader: MockModelLoader) : AlbumRetrofit {
        return MockAlbumRetrofit(mockModelLoader)
    }

    @Singleton
    @Provides
    fun provideUserRetrofit(mockModelLoader: MockModelLoader) : UserRetrofit {
        return MockUserRetrofit(mockModelLoader)
    }

}