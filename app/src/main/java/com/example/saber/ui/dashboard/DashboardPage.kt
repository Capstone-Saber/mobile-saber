package com.example.saber.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.marker.Marker

data class LineChartDataPoint(
    val x: Float,
    val y: Float
)

@Composable
fun DashboardPage(
    modifier: Modifier,
) {
    val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)
    LineChartWithTooltip(
        modifier = modifier
    )
}

@Composable
fun LineChartWithTooltip(
    modifier: Modifier = Modifier
) {
    // Remember the tooltip visibility state
    val tooltipVisible by remember { mutableStateOf(false) }

    // Remember the clicked data point state
    val clickedDataPoint by remember { mutableStateOf<LineChartDataPoint?>(null) }

    Chart(
        modifier = modifier,
        chart = lineChart(),
        model = entryModelOf(4f, 12f, 8f, 16f),
        startAxis = rememberStartAxis(),
        bottomAxis = rememberBottomAxis(),
    )

}



