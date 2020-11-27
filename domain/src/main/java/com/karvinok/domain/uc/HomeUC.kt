package com.karvinok.domain.uc

import com.karvinok.domain.common.BaseError
import com.karvinok.domain.common.BaseUseCase
import com.karvinok.domain.common.Either
import com.karvinok.domain.entity.Employee
import com.karvinok.domain.repository.HomeRepository

//UC если после получения данных с ними нечего делать,
// можно обойтись без UC и инджектить repo прямо в VM

class HomeUC(private val repo : HomeRepository) : BaseUseCase {
    suspend fun requestEmployees(): Either<BaseError, List<Employee>?> {
        return repo.requestEmployees()
    }
}