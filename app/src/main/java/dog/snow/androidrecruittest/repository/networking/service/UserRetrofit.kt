package dog.snow.androidrecruittest.repository.networking.service

import dog.snow.androidrecruittest.repository.networking.model.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserRetrofit {
    @GET("users")
    @Headers("User-agent: Lejdi")
    suspend fun getUsers() : List<User>?

    @GET("users/{id}")
    @Headers("User-agent: Lejdi")
    suspend fun getUserById(@Path("id")id : Int) : User?
}