package dev.kuhuk.graphdemo

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class BarChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.list_item_barchart)

        val chart = findViewById<BarChart>(R.id.chart)

        // apply styling
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)
        chart.setDrawBarShadow(false)
        chart.setBackgroundColor(Color.WHITE)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)

        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        val leftAxis = chart.axisLeft
        leftAxis.setLabelCount(5, false)
        leftAxis.spaceTop = 20f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = chart.axisRight
        rightAxis.setLabelCount(5, false)
        rightAxis.spaceTop = 20f
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        // set data
        chart.data = generateDataBar(1)
//        chart.data = mChartData as BarData
        chart.setFitBars(true)

        // do not forget to refresh the chart
        chart.invalidate()
        chart.animateY(700)
    }

    // For Bar Chart
    private fun generateDataBar (cnt : Int) : BarData {
        val entries = arrayListOf<BarEntry>()
        for (i in 0..11)
            entries.add(BarEntry(i.toFloat(), i+1.toFloat()))
        val d = BarDataSet(entries, "New DataSet $cnt")
        d.color = (ColorTemplate.VORDIPLOM_COLORS[0])
        d.highLightAlpha = 255

        val cd = BarData(d)
        cd.barWidth = 0.9f

        return cd
    }
}
