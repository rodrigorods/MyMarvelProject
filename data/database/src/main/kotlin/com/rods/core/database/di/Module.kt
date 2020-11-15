package com.rods.core.database.di

import android.content.Context
import androidx.room.Room
import com.rods.core.database.AppDatabase
import org.koin.dsl.module

private const val databaseName = "MarvelDataBase"

val databaseModule = module {
    single { provideDatabase(get()) }
    factory { provideCharactersDao(get()) }
}

fun provideCharactersDao(database: AppDatabase) = database.charactersLocalDao()

private fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
        .fallbackToDestructiveMigration()
        .build()
}