package com.movieapp.valorantapp.service

import com.movieapp.valorantapp.agents_buddies_dataclass.Agents
import com.movieapp.valorantapp.agents_buddies_dataclass.BaseModel
import com.movieapp.valorantapp.agents_buddies_dataclass.Buddies
import com.movieapp.valorantapp.bundle.Bundle
import retrofit2.Response
import retrofit2.http.GET

interface ValorantAPI {
    companion object {
        const val BASE_URL = "https://valorant-api.com/v1/";
    }

    @GET("/v1/agents")
    suspend fun agents(): Response<BaseModel<Array<Agents>>>

    @GET("/v1/buddies")
    suspend fun buddies(): Response<BaseModel<Array<Buddies>>>

    @GET("/v1/bundles")
    suspend fun bundles(): Response<BaseModel<Array<Bundle>>>

}