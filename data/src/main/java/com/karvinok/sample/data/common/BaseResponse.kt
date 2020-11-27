package com.karvinok.sample.data.common

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("code") val code : Int? = null
)