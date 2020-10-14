package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.loaders.MockModelLoader
import dog.snow.androidrecruittest.repository.networking.model.Album
import dog.snow.androidrecruittest.repository.networking.service.AlbumRetrofit

class MockAlbumRetrofit(
    private val mockModelLoader: MockModelLoader
) : AlbumRetrofit {

    override suspend fun getAlbums(): List<Album>? {
        return mockModelLoader.loadAlbums(Album::class.java)
    }

    override suspend fun getAlbumById(id: Int): Album? {
        return mockModelLoader.loadAlbums(Album::class.java)?.find { it.id == id }
    }
}