package com.example.di

import dagger.Module
import dagger.Provides


@Module
interface ApiFactoryModule {

        @Provides
        fun provideApplicationContext(): Application
}