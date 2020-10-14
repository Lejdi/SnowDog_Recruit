package dog.snow.androidrecruittest.repository.util

interface EntityMapper<Entity, RawModel> {

    fun mapFromEntity(entity: Entity) : RawModel
    fun mapToEntity(rawModel: RawModel) : Entity
}