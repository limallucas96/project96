package com.limallucas96.core_data.mappers

import com.limallucas96.core_database.entities.PetEntity
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.data_model.payloads.CatPayload

object CatMapper {

    /*
    FROM DATA SOURCE
    */

    fun List<CatPayload>.toCatEntity(): List<Cat> {
        return map {
            Cat(
                name = it.breeds?.firstOrNull()?.name.orEmpty(),
                description = it.breeds?.firstOrNull()?.description.orEmpty(),
                url = it.url.orEmpty()
            )
        }.filter { it.url.contains(".gif").not() }
    }

    fun List<PetEntity>.toCat(): List<Cat> {
        return map {
            Cat(
                name = it.petName,
                description = it.petName,
                url = it.petProfilePictureUrl
            )
        }
    }

    /*
       TO DATA SOURCE
    */

    fun Cat.toPetEntity() : PetEntity {
        return PetEntity(petName = this.name, petAge = 0, petProfilePictureUrl = this.url)
    }

}