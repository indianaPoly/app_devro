package com.soongsil.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.soongsil.example.data.TrainingDatabase
import com.soongsil.example.model.Training
import com.soongsil.example.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrainingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrainingRepository

    init {
        val trainingDao = TrainingDatabase.getDatabase(application)!!.trainingDao()
        repository = TrainingRepository(trainingDao)
    }

    fun addTraining(training: Training) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTraining(training)
        }
    }

    fun updateTraining(training: Training) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTraining(training)
        }
    }

    fun deleteTraining(training: Training) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTraining(training)
        }
    }

    fun readDateData(year : Int, month : Int, day : Int): LiveData<List<Training>> {
        return repository.readDateData(year, month, day).asLiveData()
    }
}