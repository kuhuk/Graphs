package dev.kuhuk.graphdemo.listviewitems

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dev.kuhuk.graphdemo.R

class PieChartItem(cd: ChartData<*>, c: Context) : ChartItem(cd) {
    override fun getView(position: Int, convertView: View?, c: Context): View {
        var convertViewC = convertView

        val holder: ViewHolder

        if (convertViewC != null) {
            holder = convertViewC.tag as ViewHolder
        } else {
            holder = ViewHolder()
            convertViewC = LayoutInflater.from(c).inflate(R.layout.list_item_piechart, null)
            holder.chart = convertViewC.findViewById(R.id.chart)
            convertViewC.tag = holder
        }

        // apply styling
        holder.chart!!.description.isEnabled = false
        holder.chart!!.holeRadius = 52f
        holder.chart!!.transparentCircleRadius = 57f
        holder.chart!!.centerText = mCenterText
//        holder.chart!!.setCenterTextTypeface(mTf)
        holder.chart!!.setCenterTextSize(9f)
        holder.chart!!.setUsePercentValues(true)
        holder.chart!!.setExtraOffsets(5f, 10f, 50f, 10f)

        mChartData.setValueFormatter(PercentFormatter())
//        mChartData.setValueTypeface(mTf)
        mChartData.setValueTextSize(11f)
        mChartData.setValueTextColor(Color.WHITE)
        // set data
        holder.chart!!.data = mChartData as PieData

        val l = holder.chart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart!!.animateY(900)

        return convertViewC!!
    }

//    private val mTf: Typeface = Typeface.createFromAsset(c.assets, "OpenSans-Regular.ttf")
    private val mCenterText: SpannableString

    override val itemType: Int
        get() = TYPE_PIECHART

    init {
        mCenterText = generateCenterText()
    }

    private fun generateCenterText(): SpannableString {
        val s = SpannableString("MPAndroidChart\ncreated by\nPhilipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.6f), 0, 14, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0)
        s.setSpan(RelativeSizeSpan(.9f), 14, 25, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, 25, 0)
        s.setSpan(RelativeSizeSpan(1.4f), 25, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), 25, s.length, 0)
        return s
    }

    private class ViewHolder {
        internal var chart: PieChart? = null
    }
}
