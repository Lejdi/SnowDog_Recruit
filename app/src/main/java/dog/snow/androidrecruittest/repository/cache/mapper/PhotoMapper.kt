package dog.snow.androidrecruittest.repository.cache.mapper

import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.cache.model.Photo
import dog.snow.androidrecruittest.repository.util.EntityMapper
import javax.inject.Inject

class PhotoMapper
@Inject
constructor() : EntityMapper<Photo, RawPhoto> {
    override fun mapFromEntity(entity: Photo): RawPhoto {
        return RawPhoto(
            id = entity.id,
            albumId = entity.albumId,
            title = entity.title,
            thumbnailUrl = entity.thumbnailUrl,
            url = entity.url
        )
    }

    override fun mapToEntity(rawModel: RawPhoto): Photo {
        return Photo(
            id = rawModel.id,
            albumId = rawModel.albumId,
            title = rawModel.title,
            thumbnailUrl = rawModel.thumbnailUrl,
            url = rawModel.url
        )
    }

    fun mapListFromEntity(entities: List<Photo>) : List<RawPhoto>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapListToEntity(rawModels: List<RawPhoto>) : List<Photo>{
        return rawModels.map { mapToEntity(it) }
    }
}