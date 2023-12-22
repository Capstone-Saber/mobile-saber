package com.example.saber.ui.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saber.common.Resource
import com.example.saber.data.use_case.GetDailyPowerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDailyPowerDataUseCase: GetDailyPowerDataUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state

    init {
        getDailyPowerData()
    }

    private fun getDailyPowerData() {
        getDailyPowerDataUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DashboardState(dataItems = result.data?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = DashboardState(
                        error = result.message?: "An error has occured."
                    )
                }

                is Resource.Loading -> {
                    _state.value = DashboardState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}