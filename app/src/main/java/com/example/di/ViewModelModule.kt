package com.example.di

import androidx.lifecycle.ViewModel
import com.example.presentation.screens.detail.CharacterDetailViewModel
import com.example.presentation.screens.favourite.FavouriteViewModel
import com.example.presentation.screens.list.CharacterListViewModel
import com.example.presentation.screens.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    fun bindCharacterListViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    fun bindFavouriteViewModel(viewModel: FavouriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}
