package com.example.hogwarts.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.data.models.getSpells.Spells
import com.example.hogwarts.data.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(val context: Context): ViewModel() {

    private val repository = Repository(context)

    val charactersLiveData = MutableLiveData<List<Characters>?>()
    val selectedCharacter = MutableLiveData<Characters>()
    val spellsLiveData = MutableLiveData<List<Spells>?>()
    val houseCharactersLiveData = MutableLiveData<List<Characters>?>()
    val staffCharactersLiveData = MutableLiveData<List<Characters>?>()
    val studentsCharactersLiveData = MutableLiveData<List<Characters>?>()

    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacters()
            if(response.isSuccessful) {
                val charactersList = response.body()
                charactersLiveData.postValue(charactersList?.take(25))
            }
        }
    }

    fun getSpells() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSpells()
            if(response.isSuccessful) {
                val spellsList = response.body()
                spellsLiveData.postValue(spellsList)
            }
        }
    }

    fun getGryffindorCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHouseCharacters("Gryffindor")
            if(response.isSuccessful) {
                val response = response.body()
                houseCharactersLiveData.postValue(response)
            }
        }
    }

    fun getRavenclawCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHouseCharacters("Ravenclaw")
            if(response.isSuccessful) {
                val response = response.body()
                houseCharactersLiveData.postValue(response)
            }
        }
    }

    fun getHufflepuffCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHouseCharacters("Hufflepuff")
            if(response.isSuccessful) {
                val response = response.body()
                houseCharactersLiveData.postValue(response)
            }
        }
    }

    fun getSlytherinCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHouseCharacters("Slytherin")
            if(response.isSuccessful) {
                val response = response.body()
                houseCharactersLiveData.postValue(response)
            }
        }
    }

    fun getStaffCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getStaffCharacters()
            if(response.isSuccessful) {
                val charactersList = response.body()
                staffCharactersLiveData.postValue(charactersList?.take(8))
            }
        }
    }

    fun getSudentsCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getStudentsCharacters()
            if(response.isSuccessful) {
                val charactersList = response.body()
                studentsCharactersLiveData.postValue(charactersList?.take(11))
            }
        }
    }


    class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}