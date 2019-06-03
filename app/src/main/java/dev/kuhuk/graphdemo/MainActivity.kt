package dev.kuhuk.graphdemo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ViewPortHandler
import dev.kuhuk.graphdemo.listviewitems.BarChartItem
import dev.kuhuk.graphdemo.listviewitems.ChartItem
import dev.kuhuk.graphdemo.listviewitems.LineChartItem
import dev.kuhuk.graphdemo.listviewitems.PieChartItem

class MainActivity : DemoBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Layout
        setContentView(R.layout.activity_main)

        // ActionBar title
        title = "MultipleCharts"

        val lv : ListView = findViewById(R.id.listView1)
        val list = arrayListOf<ChartItem>()

        for (i in 0..29) {
            when(i % 3) {
                0 -> list.add(LineChartItem(generateDataLine(i + 1), this))
                1 -> list.add(BarChartItem(generateDataBar(i + 1), this))
                2 -> list.add(PieChartItem(generatedDataPie(), this))
            }
        }

        val cda = ChartDataAdapter(applicationContext, list)
        lv.adapter = cda
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

    private inner class ChartDataAdapter internal constructor(context: Context, objects: List<ChartItem>) :
        ArrayAdapter<ChartItem>(context, 0, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            return getItem(position).getView(position, convertView, context)
        }

        override fun getItemViewType(position: Int): Int {
            val ci = getItem(position)
            return ci?.itemType ?: 0
        }

        override fun getViewTypeCount(): Int {
            return 3 // Because we have 3 different item-types
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.only_github, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.viewGithub -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/ListViewMultiChartActivity.java")
                startActivity(i)
            }
        }
        return true
    }

    override fun saveToGallery() {
        // Intentionally kept empty
    }
}
