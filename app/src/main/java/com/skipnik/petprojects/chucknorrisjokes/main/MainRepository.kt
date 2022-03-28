package com.skipnik.petprojects.chucknorrisjokes.main

import com.skipnik.petprojects.chucknorrisjokes.data.models.JokeResponse
import com.skipnik.petprojects.chucknorrisjokes.util.Resource

interface MainRepository {

    suspend fun getJoke() : Resource<JokeResponse>
}