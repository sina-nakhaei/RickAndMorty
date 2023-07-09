package com.example.rickandmorty.core.util.extensions

import com.example.rickandmorty.core.model.Result
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

fun <T> resultFlow(
    block: suspend FlowCollector<Result<T>>.() -> Unit
) = flow {
    block(this)
}
    .onStart { emit(Result.Loading) }
    .catch {
        //TODO: Handle different types of error
        emit(Result.Error("", ""))
    }