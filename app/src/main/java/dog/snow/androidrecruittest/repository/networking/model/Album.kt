package dog.snow.androidrecruittest.repository.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("userId")
    @Expose
    var userId: Int,
    @SerializedName("title")
    @Expose
    var title: String
)

