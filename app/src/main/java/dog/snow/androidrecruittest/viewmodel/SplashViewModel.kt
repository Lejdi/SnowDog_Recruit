package dog.snow.androidrecruittest.viewmodel

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.util.NetworkTools
import dog.snow.androidrecruittest.util.SplashState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel
@ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
) : ViewModel()
{
    val state = MutableLiveData<SplashState?>(SplashState.LOADING)

    fun updateCache(context: Context){
        state.value = SplashState.LOADING
        if(!NetworkTools.isInternetAvailable(context)){
            state.value = SplashState.SUCCESS
            return
        }
        viewModelScope.launch {
            state.value = withContext(Dispatchers.IO){
                if(
                    userRepository.getAllFromNetwork() == SplashState.ERROR ||
                    photoRepository.getAllFromNetwork() == SplashState.ERROR ||
                    albumRepository.getAllFromNetwork() == SplashState.ERROR
                ){
                    SplashState.ERROR
                }
                else{
                    SplashState.SUCCESS
                }
            }
        }
    }
}