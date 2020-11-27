package com.karvinok.domain.repository

import com.karvinok.domain.common.BaseError
import com.karvinok.domain.common.Either
import com.karvinok.domain.entity.Employee
import com.karvinok.domain.entity.NetworkEmployee

interface HomeRepository {
    suspend fun requestEmployees(): Either<BaseError, List<Employee>?>
}