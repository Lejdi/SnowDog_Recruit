package dog.snow.androidrecruittest.repository.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "userId")
    var userId: Int,

    @ColumnInfo(name = "title")
    var title: String
)