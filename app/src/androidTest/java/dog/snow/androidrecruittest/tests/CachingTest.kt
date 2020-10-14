package dog.snow.androidrecruittest.tests

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dog.snow.androidrecruittest.repository.*
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CachingTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var albumRepository: AlbumRepository

    @Inject
    lateinit var photoRepository: PhotoRepository

    @Before
    fun init(){
        hiltRule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCachingPhotos(){
        runBlocking {
            withContext(Dispatchers.IO){
                photoRepository.clearCache()
                assert(photoRepository.getAllFromCache().isEmpty())
                photoRepository.getAllFromNetwork()
                assert(photoRepository.getAllFromCache().isNotEmpty())
                assert(photoRepository.getFromCacheById(1).id == 1)
                assert(photoRepository.getFromCacheById(1).albumId == 1)
                assert(photoRepository.getFromCacheById(1).title == "accusamus beatae ad facilis cum similique qui sunt")
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCachingAlbums(){
        runBlocking {
            withContext(Dispatchers.IO){
                albumRepository.clearCache()
                assert(albumRepository.getAllFromCache().isEmpty())
                albumRepository.getAllFromNetwork()
                assert(albumRepository.getAllFromCache().isNotEmpty())
                assert(albumRepository.getFromCacheById(1).id == 1)
                assert(albumRepository.getFromCacheById(1).userId == 1)
                assert(albumRepository.getFromCacheById(1).title == "quidem molestiae enim")
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCachingUsers(){
        runBlocking {
            withContext(Dispatchers.IO){
                userRepository.clearCache()
                assert(userRepository.getAllFromCache().isEmpty())
                userRepository.getAllFromNetwork()
                assert(userRepository.getAllFromCache().isNotEmpty())
                assert(userRepository.getFromCacheById(1).id == 1)
                assert(userRepository.getFromCacheById(1).name == "Leanne Graham")
                assert(userRepository.getFromCacheById(1).phone == "1-770-736-8031 x56442")
            }
        }
    }
}