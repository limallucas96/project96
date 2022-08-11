package com.limallucas96.core_data.datasource

import retrofit2.http.GET

interface CatDataSource {

    @GET("images/search?limit=5&page=10&order=Desc")
    suspend fun getCats(): List<Unit>

}