package com.example.rickandmorty.core.data.pagingsource.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterRemoteDataSource
import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.NetworkResult
import com.example.rickandmorty.core.model.extractData
import kotlinx.coroutines.flow.last

class CharacterPagingSource(
    private val dataSource: CharacterRemoteDataSource
) : PagingSource<Int, CharacterResponse>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val page = params.key ?: 1

        return when (val result = dataSource.getAllCharacters(page).last()) {
            is NetworkResult.Error -> {
                val ex = result.throwable ?: UnknownError()
                LoadResult.Error(ex)
            }

            is NetworkResult.Success -> {
                val next = result.data?.info?.next
                val prev = result.data?.info?.prev

                LoadResult.Page(
                    result.extractData()?.results ?: emptyList(),
                    prev?.extractPageNumber(),
                    next?.extractPageNumber()
                )
            }
            //TODO: handle errors
            else -> LoadResult.Error(UnknownError())
        }
    }
}

private fun String.extractPageNumber(): Int? {
    val regex = Regex("page=(\\d+)")
    val result = regex.find(this)

    return result?.groupValues?.get(1)?.toIntOrNull()
}