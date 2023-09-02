package com.example.rickandmorty.core.util.extensions

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.core.model.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> resultFlow(
    block: suspend FlowCollector<NetworkResult<T>>.() -> Unit
) = flow {
    block(this)
}
    .onStart { emit(NetworkResult.Loading) }
    .catch {
        emit(NetworkResult.Error("", it))
    }

fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingData(
    transform: (T) -> R
): Flow<PagingData<R>> {
    return map { pagingData ->
        pagingData.map {
            transform(it)
        }
    }
}