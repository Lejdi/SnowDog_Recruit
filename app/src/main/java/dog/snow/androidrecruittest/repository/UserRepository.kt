package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.cache.dao.UserDao
import dog.snow.androidrecruittest.repository.cache.mapper.UserMapper
import dog.snow.androidrecruittest.repository.cache.model.User
import dog.snow.androidrecruittest.repository.networking.service.UserRetrofit
import dog.snow.androidrecruittest.util.SplashState
import java.lang.Exception

class UserRepository
constructor(
    private val userDao: UserDao,
    private val userRetrofit: UserRetrofit,
    private val userCacheMapper: UserMapper,
    private val userNetworkMapper: dog.snow.androidrecruittest.repository.networking.mapper.UserMapper
) {
    suspend fun getAllFromNetwork() : SplashState {
        try{
            val users = userRetrofit.getUsers()
            if(users != null){
                val mappedUsers = userNetworkMapper.mapListFromEntity(users)
                userDao.clearData()
                mappedUsers.forEach {
                    userDao.insert(userCacheMapper.mapToEntity(it))
                }
            }
            else{
                throw Exception("Failed to download users")
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
            val user = userRetrofit.getUserById(id)
            if(user!=null){
                val mappedUser = userNetworkMapper.mapFromEntity(user)
                userDao.insert(userCacheMapper.mapToEntity(mappedUser))
            }
            else{
                throw Exception("Failed to download a user")
            }
        }
        catch(e : Exception){
            e.printStackTrace()
            return SplashState.ERROR
        }
        return SplashState.SUCCESS
    }

    suspend fun getAllFromCache() : List<User> {
        return userDao.getAllUsers()
    }

    suspend fun getFromCacheById(id: Int) : User {
        return userDao.getUserById(id)
    }

    suspend fun clearCache(){
        userDao.clearData()
    }
}