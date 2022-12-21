package com.example.iotproject

//MPAndroidChart import

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iotproject.databinding.ActivityDetailBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class DetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        chart_init(binding.chart1Detail,"온도", Color.RED, data_temp())
        chart_init(binding.chart2Detail, "습도", Color.BLUE, data_temp())
        chart_init(binding.chart3Detail, "미세먼지", Color.GREEN, data_temp())

    }

    //chart 설정 (binding할 차트, 차트 이름, 차트 색상, 차트 데이터)
    private fun chart_init(lineChart: LineChart,chartName: String, chartColor: Int, chartData: ArrayList<Entry>){

        //1. 데이터셋에 데이터 넣기
        var tempDataSet = LineDataSet(chartData, chartName)
        //1-A. 데이터셋 커스텀
        tempDataSet.setColor(chartColor) //라인 색상
        tempDataSet.setLineWidth(2f) //라인 굵기
        tempDataSet.setDrawCircleHole(true) //데이터 원 표시 여부
        tempDataSet.setCircleColor(chartColor) //데이터 원 색상
        tempDataSet.setCircleRadius(2f) // 데이터 원 반지름

        //1-B. 차트 커스텀
        lineChart.setNoDataText("No Data"); //차트 데이터 없음 표시
        lineChart.setNoDataTextColor(Color.BLACK); //차트 데이터 없음 텍스트 색상
        //lineChart.setBackgroundColor(Color.BLACK); //차트 배경 색상
        //lineChart.setDrawGridBackground(true); //격자 그리드 적용
        //lineChart.setDrawBorders(true); //차트 외곽선 진하게
        //lineChart.setBorderColor(Color.RED); //차트 외곽선 색상

        //2. 리스트에 데이터셋 추가
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(tempDataSet)

        //3. 라인데이터에 리스트 추가
        val data = LineData(dataSets)

        //4. 차트에 라인데이터 추가
        lineChart.setData(data);

        //5. 차트 초기화
        lineChart.invalidate();
    }

    // temp 데이터 생성
    private fun data_temp(): ArrayList<Entry> {
        val dataList: ArrayList<Entry> = ArrayList()
        dataList.add(Entry(0f, 10f))
        dataList.add(Entry(1f, 20f))
        dataList.add(Entry(2f, 30f))
        dataList.add(Entry(3f, 40f))
        return dataList
    }
}
