package dog.snow.androidrecruittest.repository.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("address")
    @Expose
    val address: Address,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("website")
    @Expose
    val website: String,
    @SerializedName("company")
    @Expose
    val company: Company
){
    data class Address(
        @SerializedName("street")
        @Expose
        val street: String,
        @SerializedName("suite")
        @Expose
        val suite: String,
        @SerializedName("city")
        @Expose
        val city: String,
        @SerializedName("zipcode")
        @Expose
        val zipcode: String,
        @SerializedName("geo")
        @Expose
        val geo: Geo
    ){
        data class Geo(
            @SerializedName("lat")
            @Expose
            val lat: String,
            @SerializedName("lng")
            @Expose
            val lng: String
        )
    }
    data class Company(
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("catchPhrase")
        @Expose
        val catchPhrase: String,
        @SerializedName("bs")
        @Expose
        val bs: String
    )
}