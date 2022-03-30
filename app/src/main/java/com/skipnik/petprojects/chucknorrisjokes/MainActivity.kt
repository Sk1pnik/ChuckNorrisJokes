package com.skipnik.petprojects.chucknorrisjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.skipnik.petprojects.chucknorrisjokes.databinding.ActivityMainBinding
import com.skipnik.petprojects.chucknorrisjokes.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.isVisible = false

        binding.getJoke.setOnClickListener {
            viewModel.joke()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.jokeState.collect { event ->
                when(event){
                    is MainViewModel.JokeEvent.Success ->{
                        binding.progressBar.isVisible = false
                        binding.textView.text = event.resultText
                    }
                    is MainViewModel.JokeEvent.Failure ->{
                        binding.progressBar.isVisible = false
                        binding.textView.text = event.errorText

                    }
                    is MainViewModel.JokeEvent.Loading ->{
                        binding.progressBar.isVisible = true

                    }
                    else -> Unit

                }
            }
        }


    }
}