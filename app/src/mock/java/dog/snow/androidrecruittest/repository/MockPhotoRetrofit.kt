package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.networking.model.Photo
import dog.snow.androidrecruittest.repository.networking.service.PhotoRetrofit

class MockPhotoRetrofit(
    private val mockModelLoader: MockModelLoader
) : PhotoRetrofit {

    override suspend fun getPhotos(limit: Int): List<Photo>? {
        return mockModelLoader.loadPhotos(Photo::class.java)
    }

    override suspend fun getPhotoById(id: Int): Photo? {
        return mockModelLoader.loadPhotos(Photo::class.java)?.find { it.id == id }
    }
}