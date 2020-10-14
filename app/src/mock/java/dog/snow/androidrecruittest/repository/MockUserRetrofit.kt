package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.networking.model.User
import dog.snow.androidrecruittest.repository.networking.service.UserRetrofit

class MockUserRetrofit(
    private val mockModelLoader: MockModelLoader
) : UserRetrofit {

    override suspend fun getUsers(): List<User>? {
        return mockModelLoader.loadUsers(User::class.java)
    }

    override suspend fun getUserById(id: Int): User? {
        return mockModelLoader.loadUsers(User::class.java)?.find { it.id == id }
    }
}