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
import dog.snow.androidrecruittest.repository.cache.model.Album
import dog.snow.androidrecruittest.repository.cache.model.Photo
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel
@ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
) : ViewModel()
{
    val items = MutableLiveData<List<ListItem>>()
    val filterString = MutableLiveData<String>("")
    val displayedItemsCount = MutableLiveData(0)

    fun retrieveData(){
        items.value = mutableListOf()
        viewModelScope.launch {
            val photos = withContext(Dispatchers.IO){
                photoRepository.getAllFromCache()
            }
            val albums = withContext(Dispatchers.IO){
                albumRepository.getAllFromCache()
            }
            val tmpItems = mutableListOf<ListItem>()
            photos.forEach { photo ->
                val album = albums.first { _album -> _album.id == photo.albumId }
                tmpItems.add(ListItem(photo.id, photo.title, album.title, photo.thumbnailUrl))
            }
            items.value = tmpItems
        }
    }

    fun getFirstItemId() : Int?{
        var photo : Photo? = null
        viewModelScope.launch {
            val photos = withContext(Dispatchers.IO){
                photoRepository.getAllFromCache()
            }
            photo = photos.first()
        }
        return photo?.id
    }
}