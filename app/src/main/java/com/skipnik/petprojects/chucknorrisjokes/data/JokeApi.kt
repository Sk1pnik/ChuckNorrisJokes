package com.skipnik.petprojects.chucknorrisjokes.data

import com.skipnik.petprojects.chucknorrisjokes.data.models.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {

    @GET("/jokes/random")
    suspend fun getJoke (): Response<JokeResponse>

}