package com.soongsil.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.soongsil.example.model.Training

@Database(entities = [Training::class], version = 1, exportSchema = false )
abstract class TrainingDatabase : RoomDatabase(){

    abstract fun trainingDao() : TrainingDao

    companion object{
        /* @Volatile = 접근가능한 변수의 값을 cache를 통해 사용하지 않고
        thread가 직접 main memory에 접근 하게하여 동기화. */
        @Volatile
        private var instance : TrainingDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : TrainingDatabase? {
            if(instance == null){
                synchronized(TrainingDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrainingDatabase::class.java,
                        "Training_database"
                    ).build()
                }
            }
            return instance
        }
    }
}
