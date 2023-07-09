package com.example.rickandmorty.core.module

import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterRemoteDataSource
import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterRemoteDataSourceDefault
import com.example.rickandmorty.core.data.repository.characters.CharacterRepository
import com.example.rickandmorty.core.data.repository.characters.CharacterRepositoryDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindCharacterRemoteDataSource(d: CharacterRemoteDataSourceDefault): CharacterRemoteDataSource

    @Binds
    fun bindCharacterRepository(r: CharacterRepositoryDefault): CharacterRepository
}