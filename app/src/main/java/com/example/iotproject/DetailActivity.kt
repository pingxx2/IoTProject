package com.example.iotproject

//MPAndroidChart import

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.iotproject.data.Dummy
import com.example.iotproject.data.SensorData
import com.example.iotproject.databinding.ActivityDetailBinding
import com.example.iotproject.service.APISensor
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer


class DetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}
    val tempList: ArrayList<Entry> = ArrayList()
    val humiList: ArrayList<Entry> = ArrayList()
    val dustList: ArrayList<Entry> = ArrayList()
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //초기화
        for(i in 0..59){
            tempList.add(Entry(i.toFloat(), 0f))
            humiList.add(Entry(i.toFloat(), 0f))
            dustList.add(Entry(i.toFloat(),0f))
        }

        // set_realtime url 요청하기
        setRealTime()

        chart_init(binding.chart1Detail,"온도", Color.RED, tempList)
        chart_init(binding.chart2Detail, "습도", Color.BLUE, humiList)
        chart_init(binding.chart3Detail, "미세먼지", Color.GREEN, dustList)

        timer = timer(period=1000){
            getSensorData()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun onStop() {
        super.onStop()

        //set_normal url 요청하기
        setNormal()
    }

    //chart 설정 (binding할 차트, 차트 이름, 차트 색상, 차트 데이터)
    private fun chart_init(lineChart: LineChart,chartName: String, chartColor: Int, chartData: ArrayList<Entry>){

        //1. 데이터셋에 데이터 넣기
        var tempDataSet = LineDataSet(chartData, chartName)
        //1-A. 데이터셋 커스텀
        tempDataSet.setColor(chartColor) //라인 색상
        tempDataSet.setLineWidth(2f) //라인 굵기
        tempDataSet.setDrawCircleHole(false) //데이터 원 구멍 표시 여부
        tempDataSet.setDrawCircles(false) //데이터 원 표시 여부
        //tempDataSet.setDrawFilled(true)
        //tempDataSet.setCircleColor(chartColor) //데이터 원 색상
        tempDataSet.setCircleRadius(2f) // 데이터 원 반지름
        tempDataSet.setDrawValues(false)

        //1-B. 차트 커스텀
        lineChart.setNoDataText("No Data"); //차트 데이터 없음 표시
        lineChart.setNoDataTextColor(Color.BLACK); //차트 데이터 없음 텍스트 색상
        //lineChart.setBackgsproundColor(Color.BLACK); //차트 배경 색상
        //lineChart.setDrawGridBackground(true); //격자 그리드 적용
        //lineChart.setDrawBorders(true); //차트 외곽선 진하게
        //lineChart.setBorderColor(Color.RED); //차트 외곽선 색상

        lineChart.axisLeft.axisMaximum = 200.0f
        lineChart.axisLeft.axisMinimum = 0.0f

        lineChart.axisRight.axisMaximum = 200.0f
        lineChart.axisRight.axisMinimum = 0.0f


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

    private fun setRealTime() {
        // set_realtime으로 설정
        APISensor.getService()
            .setRealTimeValue()
            .enqueue(object : Callback<Dummy> {
                override fun onResponse(call: Call<Dummy>, response: Response<Dummy>) {

                }
                override fun onFailure(call: Call<Dummy>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
    }

    private fun setNormal() {
        // set_normal 설정
        APISensor.getService()
            .setNormalValue()
            .enqueue(object : Callback<Dummy> {
                override fun onResponse(call: Call<Dummy>, response: Response<Dummy>) {

                }
                override fun onFailure(call: Call<Dummy>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
    }

    private fun getSensorData(){
        // 온도를 가져옴
        APISensor.getService()
            .getSensorValue("TEMP/latest_value")
            .enqueue(object : Callback<SensorData> {
                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
                    if(response.isSuccessful){
                        val data = response.body()

                        for(i in 58 downTo 0){
                            tempList[i+1].y = tempList[i].y
                        }
                        tempList[0].y = data?.value!!.toFloat()

                        runOnUiThread{
                            binding.chart1Detail.notifyDataSetChanged()
                            binding.chart1Detail.invalidate()
                        }
                    }
                }
                override fun onFailure(call: Call<SensorData>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
        // 습도를 가져옴
//        APISensor.getService()
//            .getSensorValue("HUMI/latest_value")
//            .enqueue(object : Callback<SensorData> {
//                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
//                    if(response.isSuccessful){
//                        val data = response.body()
//                        for(i in 0..59){
//                            humiList[i+1]=humiList[i]
//                        }
//                        humiList[0].data = data?.value!!.toFloat()
//                    }
//                }
//                override fun onFailure(call: Call<SensorData>, t: Throwable) {
//                    Log.i("isWorking?", "Fail...")
//                }
//            })

        //미세먼지 가져옴
//        APISensor.getService()
//            .getSensorValue("DUST/latest_value")
//            .enqueue(object : Callback<SensorData>{
//                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
//                    if(response.isSuccessful){
//                        val data = response.body()
//                        for(i in 0..59){
//                            dustList[i+1]=dustList[i]
//                        }
//                        dustList[0].data = data?.value!!.toFloat()
//                    }
//                }
//                override fun onFailure(call: Call<SensorData>, t: Throwable) {
//                    Log.i("isWorking?", "Fail...")
//                }
//            })
    }
}
