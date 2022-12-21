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

        //초기화
        var lineChart: LineChart = binding.chart

        //1. 데이터셋에 데이터 넣기
        var tempDataSet = LineDataSet(data1(), "temp")
        //1-A. 데이터셋 커스텀
        tempDataSet.setColor(Color.RED) //라인 색상
        tempDataSet.setLineWidth(2f) //라인 굵기
        tempDataSet.setDrawCircleHole(true) //데이터 원 표시 여부
        tempDataSet.setCircleColor(Color.RED) //데이터 원 색상
        tempDataSet.setCircleRadius(2f) // 데이터 원 반지름

        //2. 리스트에 데이터셋 추가
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(tempDataSet)

        /* 차트 스타일 */

        //차트 배경 색상
        //lineChart.setBackgroundColor(Color.BLACK);

        //차트 데이터 없음 표시
        lineChart.setNoDataText("No Data");

        //차트 데이터 없음 텍스트 색상
        lineChart.setNoDataTextColor(Color.BLUE);

        //격자 그리드 적용
        //lineChart.setDrawGridBackground(true);

        //차트 외곽선 진하게
        //lineChart.setDrawBorders(true);

        //차트 외곽선 색상
        //lineChart.setBorderColor(Color.RED);

        //설명
        var description : Description = lineChart.description
        description.setText("") // 설명
        description.setTextSize(10f) //설명 텍스트 크기
        description.setTextColor(Color.BLACK) //텍스트 색상

        //3. 라인데이터에 리스트 추가
        val data = LineData(dataSets)

        //4. 차트에 라인데이터 추가
        lineChart.setData(data);

        //5. 차트 초기화
        lineChart.invalidate();
    }

    //데이터 생성
    private fun data1(): ArrayList<Entry> {
        val dataList: ArrayList<Entry> = ArrayList()
        dataList.add(Entry(0f, 10f))
        dataList.add(Entry(1f, 20f))
        dataList.add(Entry(2f, 30f))
        dataList.add(Entry(3f, 40f))
        return dataList
    }
}
