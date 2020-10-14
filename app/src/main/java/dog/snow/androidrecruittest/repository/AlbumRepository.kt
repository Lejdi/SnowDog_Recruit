package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.cache.dao.AlbumDao
import dog.snow.androidrecruittest.repository.cache.mapper.AlbumMapper
import dog.snow.androidrecruittest.repository.cache.model.Album
import dog.snow.androidrecruittest.repository.networking.service.AlbumRetrofit
import dog.snow.androidrecruittest.util.SplashState
import java.lang.Exception

class AlbumRepository
constructor(
    private val albumDao: AlbumDao,
    private val albumRetrofit: AlbumRetrofit,
    private val albumCacheMapper: AlbumMapper,
    private val albumNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.AlbumMapper
) {
    suspend fun getAllFromNetwork() : SplashState {
        try{
            val albums = albumRetrofit.getAlbums()
            if(albums!=null){
                val mappedAlbums = albumNetworkMapper.mapListFromEntity(albums)
                albumDao.clearData()
                mappedAlbums.forEach {
                    albumDao.insert(albumCacheMapper.mapToEntity(it))
                }
            }
            else{
                throw Exception("Failed to download albums")
            }
        }
        catch(e : Exception){
            e.printStackTrace()
            return SplashState.ERROR
        }
        return SplashState.SUCCESS
    }

    suspend fun getFromNetworkById(id: Int) : SplashState {
        try{
            val album = albumRetrofit.getAlbumById(id)
            if(album != null){
                val mappedAlbum = albumNetworkMapper.mapFromEntity(album)
                albumDao.insert(albumCacheMapper.mapToEntity(mappedAlbum))
            }
            else{
                throw Exception("Failed to download an album")
            }
        }
        catch(e : Exception){
            e.printStackTrace()
            return SplashState.ERROR
        }
        return SplashState.SUCCESS
    }

    suspend fun getAllFromCache() : List<Album> {
        return albumDao.getAllAlbums()
    }

    suspend fun getFromCacheById(id: Int) : Album {
        return albumDao.getAlbumById(id)
    }

    suspend fun clearCache(){
        albumDao.clearData()
    }
}