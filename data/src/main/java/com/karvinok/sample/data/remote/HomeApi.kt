package com.karvinok.sample.data.remote

import com.karvinok.domain.entity.NetworkEmployee
import com.karvinok.sample.data.common.BaseResponse
import retrofit2.http.GET

interface HomeApi {
    @GET("employees")
    suspend fun requestEmployees(): BaseResponse<List<NetworkEmployee>>
}
