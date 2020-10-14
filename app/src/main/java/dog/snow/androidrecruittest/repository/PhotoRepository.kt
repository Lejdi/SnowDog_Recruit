package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.cache.dao.PhotoDao
import dog.snow.androidrecruittest.repository.cache.mapper.PhotoMapper
import dog.snow.androidrecruittest.repository.cache.model.Photo
import dog.snow.androidrecruittest.repository.networking.service.PhotoRetrofit
import dog.snow.androidrecruittest.util.SplashState
import java.lang.Exception

class PhotoRepository
constructor(
    private val photoDao: PhotoDao,
    private val photoRetrofit: PhotoRetrofit,
    private val photoCacheMapper: PhotoMapper,
    private val photoNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.PhotoMapper
){
    suspend fun getAllFromNetwork() : SplashState{
        try{
            val photos = photoRetrofit.getPhotos(100)
            if(photos != null){
                val mappedPhotos = photoNetworkMapper.mapListFromEntity(photos)
                photoDao.clearData()
                mappedPhotos.forEach {
                    photoDao.insert(photoCacheMapper.mapToEntity(it))
                }
            }
            else{
                throw Exception("Failed to download photos")
            }
        }
        catch(e : Exception){
            e.printStackTrace()
            return SplashState.ERROR
        }
        return SplashState.SUCCESS
    }

    suspend fun getFromNetworkById(id: Int)  : SplashState{
        try{
            val photo = photoRetrofit.getPhotoById(id)
            if(photo != null){
                val mappedPhoto = photoNetworkMapper.mapFromEntity(photo)
                photoDao.insert(photoCacheMapper.mapToEntity(mappedPhoto))
            }
            else{
                throw Exception("Failed to download a photo")
            }
        }
        catch(e : Exception){
            e.printStackTrace()
            return SplashState.ERROR
        }
        return SplashState.SUCCESS
    }

    suspend fun getAllFromCache() : List<Photo> {
        return photoDao.getAllPhotos()
    }

    suspend fun getFromCacheById(id: Int) : Photo {
        return photoDao.getPhotoById(id)
    }

    suspend fun clearCache(){
        photoDao.clearData()
    }
}