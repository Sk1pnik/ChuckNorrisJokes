package com.skipnik.petprojects.chucknorrisjokes.main

import com.skipnik.petprojects.chucknorrisjokes.R
import com.skipnik.petprojects.chucknorrisjokes.data.JokeApi
import com.skipnik.petprojects.chucknorrisjokes.data.models.JokeResponse
import com.skipnik.petprojects.chucknorrisjokes.util.Resource

import javax.inject.Inject
import kotlin.Exception

class DefaultMainRepository @Inject constructor(
    private val api: JokeApi


): MainRepository {


    override suspend fun getJoke(): Resource<JokeResponse> {
       return try {
           val response = api.getJoke()
           val result = response.body()
          if(response.isSuccessful && result != null){
              Resource.Success(result)
          }else{
              Resource.Error(response.message())
          }

       }catch (e: Exception){
           Resource.Error(e.message ?: "An error occurred")

       }
    }
}