package dev.kuhuk.graphdemo.listviewitems

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import dev.kuhuk.graphdemo.R

class LineChartItem(cd: ChartData<*>, c: Context) : ChartItem(cd) {

    override fun getView(position: Int, convertView: View?, c: Context): View {
        var convertViewC = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            convertViewC = LayoutInflater.from(c).inflate(R.layout.list_item_linechart, null)
            holder.chart = convertViewC.findViewById(R.id.chart)
            convertViewC.tag = holder

        } else {
            holder = convertViewC!!.tag as ViewHolder
        }

        // apply styling
        // holder.chart.setValueTypeface(mTf);
        holder.chart!!.description.isEnabled = false
        holder.chart!!.setDrawGridBackground(false)

        val xAxis = holder.chart!!.xAxis
        xAxis.position = XAxisPosition.BOTTOM

        val strings = arrayOf("A", "B", "C", "D")

//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
//        chart.setData(new LineData( lines));

        holder.chart!!.xAxis.valueFormatter = IndexAxisValueFormatter(strings)
        holder.chart!!.data = mChartData as LineData


//        xAxis.typeface = mTf
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        val leftAxis = holder.chart!!.axisLeft
//        leftAxis.typeface = mTf
        leftAxis.setLabelCount(5, false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = holder.chart!!.axisRight
//        rightAxis.typeface = mTf
        rightAxis.setLabelCount(5, false)
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        // set data
//        holder.chart!!.data = mChartData as LineData

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart!!.animateX(750)

        return convertViewC!!
    }

//    private val mTf: Typeface = Typeface.createFromAsset(c.assets, "OpenSans-Regular.ttf")

    override val itemType: Int
        get() = ChartItem.TYPE_LINECHART

    private class ViewHolder {
        internal var chart: LineChart? = null
    }
}
