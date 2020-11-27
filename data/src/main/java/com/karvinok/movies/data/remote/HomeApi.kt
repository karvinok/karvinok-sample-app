package com.karvinok.movies.data.remote

import com.karvinok.domain.entity.Employee
import com.karvinok.domain.entity.NetworkEmployee
import com.karvinok.movies.data.common.BaseResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApi {
    @GET("employees")
    suspend fun requestEmployees(): BaseResponse<List<NetworkEmployee>>
}
