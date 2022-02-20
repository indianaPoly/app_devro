package com.soongsil.example.repository

import com.soongsil.example.data.TrainingDao
import com.soongsil.example.model.Training
import kotlinx.coroutines.flow.Flow

class TrainingRepository(private val trainingDao: TrainingDao){
    suspend fun addTraining(training: Training){
        trainingDao.addTraining(training)
    }

    suspend fun updateTraining(training: Training){
        trainingDao.updateTraining(training)
    }

    suspend fun deleteTraining(training: Training){
        trainingDao.deleteTraining(training)
    }

    fun readDateData(year : Int, month : Int, day : Int): Flow<List<Training>> {
        return trainingDao.readDateData(year, month, day)
    }
}