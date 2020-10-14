package dog.snow.androidrecruittest.repository.cache.mapper

import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.cache.model.User
import dog.snow.androidrecruittest.repository.util.EntityMapper
import javax.inject.Inject


class UserMapper
@Inject
constructor() : EntityMapper<User, RawUser> {
    override fun mapFromEntity(entity: User): RawUser {
        return RawUser(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            address = RawUser.RawAddress(
                street = entity.address.street,
                city = entity.address.city,
                suite = entity.address.suite,
                zipcode = entity.address.zipcode,
                geo = RawUser.RawAddress.RawGeo(
                    lat = entity.address.geo.lat,
                    lng = entity.address.geo.lng
                )
            ),
            phone = entity.phone,
            company = RawUser.RawCompany(
                name = entity.company.name,
                catchPhrase = entity.company.catchPhrase,
                bs = entity.company.bs
            ),
            website = entity.website
        )
    }

    override fun mapToEntity(rawModel: RawUser): User {
        return User(
            id = rawModel.id,
            name = rawModel.name,
            username = rawModel.username,
            email = rawModel.email,
            address = User.Address(
                street = rawModel.address.street,
                city = rawModel.address.city,
                suite = rawModel.address.suite,
                zipcode = rawModel.address.zipcode,
                geo = User.Address.Geo(
                    lat = rawModel.address.geo.lat,
                    lng = rawModel.address.geo.lng
                )
            ),
            phone = rawModel.phone,
            company = User.Company(
                name = rawModel.company.name,
                catchPhrase = rawModel.company.catchPhrase,
                bs = rawModel.company.bs
            ),
            website = rawModel.website
        )
    }

    fun mapListFromEntity(entities: List<User>) : List<RawUser>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapListToEntity(rawModels: List<RawUser>) : List<User>{
        return rawModels.map { mapToEntity(it) }
    }
}