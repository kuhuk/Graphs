package dev.kuhuk.graphdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dev.kuhuk.graphdemo.listviewitems.PieChartItem

class PieChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_pie_chart)
//        PieChartItem(generatedDataPie(), this)
    }

    // For Pie Chart
    private fun generatedDataPie() : PieData {
        val entries = arrayListOf<PieEntry>()

        for (i in 0..3)
            entries.add(PieEntry((((Math.random() * 70) + 30).toFloat()), "Quarter " + i+1))
        val d = PieDataSet(entries, "")

        // Space between slices
        d.sliceSpace = 2f
        d.color = (ColorTemplate.VORDIPLOM_COLORS[0])

        return PieData(d)
    }
}
