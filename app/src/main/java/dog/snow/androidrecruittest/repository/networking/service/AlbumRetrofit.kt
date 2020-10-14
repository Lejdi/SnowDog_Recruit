package dog.snow.androidrecruittest.repository.networking.service

import dog.snow.androidrecruittest.repository.networking.model.Album
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AlbumRetrofit {
    @GET("albums")
    @Headers("User-agent: Lejdi")
    suspend fun getAlbums() : List<Album>?

    @GET("albums/{id}")
    @Headers("User-agent: Lejdi")
    suspend fun getAlbumById(@Path("id")id : Int) : Album?
}