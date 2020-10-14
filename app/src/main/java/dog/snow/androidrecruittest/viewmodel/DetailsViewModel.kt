package dog.snow.androidrecruittest.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.ui.model.Detail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel
@ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
) : ViewModel()
{
    val selectedItem = MutableLiveData<Detail?>()

    fun retrieveData(id : Int) {
        viewModelScope.launch {
            selectedItem.value = withContext(Dispatchers.IO){
                val photo = photoRepository.getFromCacheById(id)
                val album = albumRepository.getFromCacheById(photo.albumId)
                val author = userRepository.getFromCacheById(album.userId)
                Detail(
                    photoId = id,
                    photoTitle = photo.title,
                    albumTitle = album.title,
                    username = author.username,
                    email = author.email,
                    phone = author.phone,
                    url = photo.url
                )
            }
        }
    }
}