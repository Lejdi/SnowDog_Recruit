package dog.snow.androidrecruittest.repository.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @Embedded(prefix = "address")
    val address: Address,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "website")
    val website: String,

    @Embedded(prefix = "company")
    val company: Company
){
    data class Address(
        @ColumnInfo(name = "street")
        val street: String,

        @ColumnInfo(name = "suite")
        val suite: String,

        @ColumnInfo(name = "city")
        val city: String,

        @ColumnInfo(name = "zipcode")
        val zipcode: String,

        @Embedded(prefix = "geo")
        val geo: Geo
    ){
        data class Geo(
            @ColumnInfo(name = "lat")
            val lat: String,

            @ColumnInfo(name = "lng")
            val lng: String
        )
    }
    data class Company(
        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "catchPhrase")
        val catchPhrase: String,

        @ColumnInfo(name = "bs")
        val bs: String
    )
}