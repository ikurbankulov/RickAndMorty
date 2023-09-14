package com.example.di

import android.app.Application
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.favourite.FavouriteFragment
import com.example.presentation.screens.list.CharacterListFragment
import com.example.presentation.screens.search.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(characterList: CharacterListFragment)

    fun inject(characterDetail: CharacterDetailFragment)

    fun inject(favourite: FavouriteFragment)

    fun inject(search: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}