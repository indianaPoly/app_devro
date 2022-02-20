package com.soongsil.example.data

import androidx.room.*
import com.soongsil.example.model.Training
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTraining(training: Training)

    @Update
    suspend fun updateTraining(training: Training)

    @Delete
    suspend fun deleteTraining(training: Training)

    @Query("SELECT * FROM Training WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun readDateData(year : Int, month : Int, day : Int) : Flow<List<Training>>
}