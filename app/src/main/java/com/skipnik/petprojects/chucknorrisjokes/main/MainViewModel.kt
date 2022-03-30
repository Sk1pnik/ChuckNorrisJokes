package com.skipnik.petprojects.chucknorrisjokes.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipnik.petprojects.chucknorrisjokes.util.DispatcherProvider
import com.skipnik.petprojects.chucknorrisjokes.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider

) : ViewModel() {

    private val _jokeState = MutableStateFlow<JokeEvent>(JokeEvent.Empty)
    val jokeState: StateFlow<JokeEvent> = _jokeState

    fun joke() {

        viewModelScope.launch(dispatchers.io) {
            _jokeState.value = JokeEvent.Loading
            when (val jokeResponse = repository.getJoke()) {
                is Resource.Error -> _jokeState.value = JokeEvent.Failure(jokeResponse.message!!)
                is Resource.Success -> {
                    val joke = jokeResponse.data!!.value
                    if (joke == null){
                        _jokeState.value = JokeEvent.Failure("Unexpected error")
                    }else{
                        _jokeState.value = JokeEvent.Success(
                            "$joke"
                        )
                    }
                }
            }
        }


    }


    sealed class JokeEvent {
        class Success(val resultText: String) : JokeEvent()
        class Failure(val errorText: String) : JokeEvent()
        object Loading : JokeEvent()
        object Empty : JokeEvent()

    }


}