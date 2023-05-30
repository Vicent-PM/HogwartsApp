package com.example.hogwarts

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.data.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(val context: Context) {

    private val repository = Repository(context)

    val charactersLiveData = MutableLiveData<List<Characters>?>()

    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacters()
            if(response.isSuccessful) {
                val charactersList = response.body()
                charactersLiveData.postValue(charactersList)
            }
        }
    }

    class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}