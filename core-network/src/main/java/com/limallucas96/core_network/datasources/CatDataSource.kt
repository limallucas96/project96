package com.limallucas96.core_network.datasources

import com.limallucas96.data_model.payloads.CatPayload
import retrofit2.http.GET

interface CatDataSource {

    @GET("images/search?limit=5&page=10&order=Desc")
    suspend fun getCats(): List<CatPayload>

}