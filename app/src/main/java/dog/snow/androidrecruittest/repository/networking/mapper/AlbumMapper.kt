package dog.snow.androidrecruittest.repository.networking.mapper

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.networking.model.Album
import dog.snow.androidrecruittest.repository.util.EntityMapper
import javax.inject.Inject

class AlbumMapper
@Inject
constructor() : EntityMapper<Album, RawAlbum>{
    override fun mapFromEntity(entity: Album): RawAlbum {
        return RawAlbum(
            id = entity.id,
            userId = entity.userId,
            title = entity.title
        )
    }

    override fun mapToEntity(rawModel: RawAlbum): Album {
        return Album(
            id = rawModel.id,
            userId = rawModel.userId,
            title = rawModel.title
        )
    }

    fun mapListFromEntity(entities: List<Album>) : List<RawAlbum>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapListToEntity(rawModels: List<RawAlbum>) : List<Album>{
        return rawModels.map { mapToEntity(it) }
    }

}