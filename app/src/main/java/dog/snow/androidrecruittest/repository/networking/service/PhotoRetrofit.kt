package dog.snow.androidrecruittest.repository.networking.service

import dog.snow.androidrecruittest.repository.networking.model.Photo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoRetrofit {
    @GET("photos")
    @Headers("User-agent: Lejdi")
    suspend fun getPhotos(@Query("_limit") limit : Int) : List<Photo>?

    @GET("photos/{id}")
    @Headers("User-agent: Lejdi")
    suspend fun getPhotoById(@Path("id")id : Int) : Photo?
}