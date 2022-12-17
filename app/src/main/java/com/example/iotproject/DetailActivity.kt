package com.example.iotproject

//MPAndroidChart import

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iotproject.databinding.ActivityDetailBinding
import com.github.mikephil.charting.charts.LineChart


class DetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var chart: LineChart

        chart = binding.chart

        chart.setDrawGridBackground(true)
        chart.setBackgroundColor(Color.BLACK)
        chart.setGridBackgroundColor(Color.BLACK)


        // description text
        chart.description.isEnabled = true
        val des = chart.description
        des.isEnabled = true
        des.text = "Real-Time DATA"
        des.textSize = 15f
        des.textColor = Color.WHITE


        // touch gestures (false-비활성화)
        chart.setTouchEnabled(false)


        // scaling and dragging (false-비활성화)
        chart.isDragEnabled = false
        chart.setScaleEnabled(false)


        //auto scale
        chart.isAutoScaleMinMaxEnabled = true

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)

        //X축
        chart.xAxis.setDrawGridLines(true)
        chart.xAxis.setDrawAxisLine(false)

        chart.xAxis.isEnabled = true
        chart.xAxis.setDrawGridLines(false)

        //Legend
        val l = chart.legend
        l.isEnabled = true
        l.formSize = 10f // set the size of the legend forms/shapes

        l.textSize = 12f
        l.textColor = Color.WHITE


        //Y축
        val leftAxis = chart.axisLeft
        leftAxis.isEnabled = true
        leftAxis.textColor = resources.getColor(R.color.holo_blue_dark)
        leftAxis.setDrawGridLines(true)
        leftAxis.gridColor = resources.getColor(R.color.holo_orange_dark)

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false


        // don't forget to refresh the drawing
        chart.invalidate()



    }
}