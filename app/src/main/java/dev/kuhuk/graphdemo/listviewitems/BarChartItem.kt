package dev.kuhuk.graphdemo.listviewitems

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.ChartData
import dev.kuhuk.graphdemo.R

class BarChartItem(cd: ChartData<*>, c: Context) : ChartItem(cd) {

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, c: Context): View {
        val holder: ViewHolder
        var view = convertView

        if (convertView == null) {
            holder = ViewHolder()
            view = LayoutInflater.from(c).inflate(R.layout.list_item_barchart, null)
            holder.chart = view.findViewById(R.id.chart)
            view.tag = holder
        } else {
            holder = view?.tag as ViewHolder
        }

        // apply styling
        holder.chart!!.description.isEnabled = false
        holder.chart!!.setDrawGridBackground(false)
        holder.chart!!.setDrawBarShadow(false)

        val xAxis = holder.chart!!.xAxis
        xAxis.position = XAxisPosition.BOTTOM
//        xAxis.typeface = mTf
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        val leftAxis = holder.chart!!.axisLeft
//        leftAxis.typeface = mTf
        leftAxis.setLabelCount(5, false)
        leftAxis.spaceTop = 20f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = holder.chart!!.axisRight
//        rightAxis.typeface = mTf
        rightAxis.setLabelCount(5, false)
        rightAxis.spaceTop = 20f
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

//        mChartData.setValueTypeface(mTf)

        // set data
        holder.chart!!.data = mChartData as BarData
        holder.chart!!.setFitBars(true)

        // do not forget to refresh the chart
        //        holder.chart.invalidate();
        holder.chart!!.animateY(700)

        return view!!
    }

//    private val mTf: Typeface = Typeface.createFromAsset(c.assets, "OpenSans-Regular.ttf")

    override val itemType: Int
        get() = TYPE_BARCHART

    private class ViewHolder {
        internal var chart: BarChart? = null
    }
}
