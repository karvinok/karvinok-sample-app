package com.karvinok.domain.entity

import androidx.annotation.Keep

/**
 * @Keep is an proguard exception to keep data layer entities from obfuscation
 */
@Keep
data class NetworkEmployee (
    val id : String,
    val employee_name : String,
    val employee_salary : String,
    val employee_age : String,
    val profile_image : String
)