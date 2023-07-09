package com.example.rickandmorty.core.module

import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterApi
import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterApiDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {
    @Binds
    fun bindCharacterApi(a: CharacterApiDefault): CharacterApi
}
