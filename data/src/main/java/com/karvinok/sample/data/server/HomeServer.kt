package com.karvinok.sample.data.server

import com.karvinok.domain.common.BaseError
import com.karvinok.domain.common.Either
import com.karvinok.domain.common.proceedResponse
import com.karvinok.domain.entity.Employee
import com.karvinok.domain.repository.HomeRepository
import com.karvinok.sample.data.remote.HomeApi

//todo HomeCache param (db source or CacheManager)
class HomeServer(private val homeApi : HomeApi):
    HomeRepository {

    override suspend fun requestEmployees(): Either<BaseError, List<Employee>?> {
        return proceedResponse {
            val employees = homeApi.requestEmployees().data?.map {
                Employee(
                    it.id,
                    it.employee_name,
                    it.employee_salary.toInt(),
                    it.employee_age.toInt(),
                    it.profile_image
                )
            }
            Either.Success(employees)
        }
    }
}