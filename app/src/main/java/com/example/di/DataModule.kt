package com.example.di

import android.app.Application
import com.example.data.local.database.AppDataBase
import com.example.data.local.database.CharacterDao
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    companion object{
        @Provides
        fun provideCharacterDao(application: Application): CharacterDao{
            return AppDataBase.getInstance(application).characterDao()
        }
    }
}