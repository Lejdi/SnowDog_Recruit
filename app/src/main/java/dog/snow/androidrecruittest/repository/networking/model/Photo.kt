package dog.snow.androidrecruittest.repository.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("albumId")
    @Expose
    var albumId: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl: String
)