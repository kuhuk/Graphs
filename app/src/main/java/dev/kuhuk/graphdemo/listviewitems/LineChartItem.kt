package dev.kuhuk.graphdemo.listviewitems

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import dev.kuhuk.graphdemo.R

class LineChartItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.list_item_linechart)

        val chart = findViewById<LineChart>(R.id.chart)

        // apply styling
        // holder.chart.setValueTypeface(mTf);
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)

        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM

        val strings = arrayOf("A", "B", "C", "D")

//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
//        chart.setData(new LineData( lines));

        chart.xAxis.valueFormatter = IndexAxisValueFormatter(strings)
        chart.data = generateDataLine(1)

//        xAxis.typeface = mTf
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        val leftAxis = chart.axisLeft
//        leftAxis.typeface = mTf
        leftAxis.setLabelCount(5, false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = chart.axisRight
//        rightAxis.typeface = mTf
        rightAxis.setLabelCount(5, false)
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        // set data
//        holder.chart!!.data = mChartData as LineData

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateX(750)
    }

    // For Line Chart
    private fun generateDataLine(cnt : Int) : LineData {
        val values1 = arrayListOf<Entry>()

        for (i in 0..11) {
            values1.add(Entry(i.toFloat(), ((Math.random()*65)+40).toFloat()))
        }
        val d1 = LineDataSet(values1, "New DataSet $cnt, (1)")
        d1.lineWidth = 2.5f
        d1.circleRadius = 4.5f
        d1.highLightColor = Color.rgb(244,117, 117)
        d1.setDrawValues(false)

        val values2 = arrayListOf<Entry>()
        for (i in 0..11) {
            values2.add(Entry(i.toFloat(), values1[i].y - 30))
        }

        val d2 = LineDataSet(values2, "New DataSet $cnt, (2)")
        d2.lineWidth = 2.5f
        d2.circleRadius = 4.5f
        d2.highLightColor = Color.rgb(244, 117, 117)
        d2.color = ColorTemplate.VORDIPLOM_COLORS[0]
        d2.setDrawValues(false)

        val sets = arrayListOf<ILineDataSet>()
        sets.add(d1)
        sets.add(d2)

        return LineData(sets)
    }
}
