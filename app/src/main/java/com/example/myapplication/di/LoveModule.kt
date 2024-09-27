package com.example.myapplication.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.myapplication.application.SharedPreferenceHelper
import com.example.myapplication.data.local.HistoryDao
import com.example.myapplication.data.local.HistoryDatabase
import com.example.myapplication.data.network.LoveApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoveModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : HistoryDatabase {
        return Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "app_database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: HistoryDatabase) : HistoryDao {
        return database.historyDao()
    }


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Provides
    fun provideLoveApiService(retrofit: Retrofit): LoveApiService {
        return retrofit.create(LoveApiService::class.java)

    }
    
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun provideSharedPreferenceHelper(sharedPreferences: SharedPreferences) : SharedPreferenceHelper {
        return SharedPreferenceHelper(sharedPreferences)
    }

}