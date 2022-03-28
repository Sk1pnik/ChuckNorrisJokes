package com.skipnik.petprojects.chucknorrisjokes.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skipnik.petprojects.chucknorrisjokes.util.DispatcherProvider

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider

): ViewModel(){
}