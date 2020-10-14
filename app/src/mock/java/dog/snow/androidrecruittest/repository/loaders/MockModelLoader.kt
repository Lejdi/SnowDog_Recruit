package dog.snow.androidrecruittest.repository.loaders

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MockModelLoader {
    private val mockLoader: MockLoader = MockLoader.createResourceLoader()

    fun <T> loadPhotos(classObject: Class<T>): List<T>? {
        val json = mockLoader.loadJson("mocks/photos.json")
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val arry: JsonArray = JsonParser().parse(json).asJsonArray
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, classObject))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun <T> loadAlbums(classObject: Class<T>): List<T>? {
        val json = mockLoader.loadJson("mocks/albums.json")
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val arry: JsonArray = JsonParser().parse(json).asJsonArray
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, classObject))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun <T> loadUsers(classObject: Class<T>): List<T>? {
        val json = mockLoader.loadJson("mocks/users.json")
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val arry: JsonArray = JsonParser().parse(json).asJsonArray
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, classObject))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}