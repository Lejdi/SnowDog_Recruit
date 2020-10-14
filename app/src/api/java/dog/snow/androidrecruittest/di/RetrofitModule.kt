package dog.snow.androidrecruittest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dog.snow.androidrecruittest.repository.networking.service.AlbumRetrofit
import dog.snow.androidrecruittest.repository.networking.service.PhotoRetrofit
import dog.snow.androidrecruittest.repository.networking.service.UserRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun providePhotoRetrofit(retrofit: Retrofit.Builder) : PhotoRetrofit {
        return retrofit.build().create(PhotoRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumRetrofit(retrofit: Retrofit.Builder) : AlbumRetrofit {
        return retrofit.build().create(AlbumRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRetrofit(retrofit: Retrofit.Builder) : UserRetrofit {
        return retrofit.build().create(UserRetrofit::class.java)
    }

}