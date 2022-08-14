package com.limallucas96.core_data.mappers

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

    /*
       TO DATA SOURCE
    */

}