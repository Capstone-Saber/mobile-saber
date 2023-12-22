package com.example.saber.data.use_case

import com.example.saber.common.Resource
import com.example.saber.data.repository.SaberRepository
import com.example.saber.data.response.DashboardDataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDailyPowerDataUseCase @Inject constructor(
    private val repository: SaberRepository
) {
    operator fun invoke(): Flow<Resource<List<DashboardDataItem>>> = flow {
        try {
            emit(Resource.Loading<List<DashboardDataItem>>())
            val dataItems = repository.getDailyPowerData().data
            emit(Resource.Success<List<DashboardDataItem>>(dataItems))
        } catch(e: HttpException) {
            emit(Resource.Error<List<DashboardDataItem>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<DashboardDataItem>>("Couldn't reach server. Check your internet connection."))
        }
    }
}