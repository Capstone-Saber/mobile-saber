package com.example.saber.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saber.ui.components.rememberMarker
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun DashboardPage(
    modifier: Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val datasetForModel = remember { mutableListOf(listOf<FloatEntry>()) }
    val modelProducer = remember { ChartEntryModelProducer() }
    val state = viewModel.state.value
    val dailyPowerData = state.dataItems
    val dataPoints = arrayListOf<FloatEntry>()
    for (item in dailyPowerData) {
        val xPos = item.timestamp.toFloat()
        val yValue = item.avgPower.toFloat()
        dataPoints.add(FloatEntry(xPos, yValue))
    }
    datasetForModel.add(dataPoints)
    modelProducer.setEntries(datasetForModel)
    if (state.isLoading) {
        CircularProgressIndicator()
    }
    if (state.error.isNotBlank()) {
        Text(text = state.error, color = MaterialTheme.colorScheme.error)
    }
    if (state.error.isBlank()) {
        LineChartPowerUsage(
            modifier = modifier,
            model = modelProducer
        )
    }
}

@Composable
fun LineChartPowerUsage(
    modifier: Modifier,
    model: ChartEntryModelProducer,
) {
    val marker = rememberMarker()
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = modifier.padding(horizontal = 24.dp)
        ) {
            Text(text = "kWh")
            Chart(
                modifier = modifier.height(360.dp),
                chart = lineChart(
                    spacing = 60.dp,
                    lines = listOf(
                        LineChart.LineSpec(
                            lineColor = Blue.toArgb(),
                            lineBackgroundShader = DynamicShaders.fromBrush(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Blue.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                                        Blue.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                                    )
                                )
                            )
                        )
                    )
                ),
                chartModelProducer = model,
                startAxis = rememberStartAxis(
                    guideline = null,
                    title = "Total kWh usage"
                ),
                bottomAxis = rememberBottomAxis(
                    guideline = null,
                    title = "Time series"
                ),
                marker = marker
            )
        }
    }

}




