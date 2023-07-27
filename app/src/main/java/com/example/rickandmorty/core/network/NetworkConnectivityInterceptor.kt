package com.example.rickandmorty.core.network

import com.example.rickandmorty.core.throwable.NetworkConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectivityInterceptor @Inject constructor(
    private val networkStatus: NetworkStatus
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkStatus.isOffline)
            throw NetworkConnectivityException()

        return chain.proceed(chain.request())
    }
}