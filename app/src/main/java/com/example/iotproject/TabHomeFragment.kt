package com.example.iotproject


import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iotproject.data.SensorData
import com.example.iotproject.databinding.FragmentTabHomeBinding
import com.example.iotproject.service.APISensor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TabHomeFragment : Fragment() {
    private var _binding: FragmentTabHomeBinding? = null
    private val binding get() = _binding!!

    var temp_value: Float = 0.0f
    var humi_value: Int = 0
    var dust_value: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 기본정보상세보기 -> 그래프 이동
        binding.btnDetailHome.setOnClickListener{
            val intent_base = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent_base)
        }

        //TEST : 버튼 클릭 -> 화재발생 알람 띄우기
        //binding.btnNoti.setOnClickListener{
            //화재가 감지되면 알람 발생
        val intent =   Intent(requireContext(), MyService::class.java)
        requireActivity().startService(intent)
        //}

        // if 화재발생
        //val intent =   Intent(requireContext(), MyService::class.java)
        //requireActivity().startService(intent)

        // else if 화재종료
        //val intent =   Intent(requireContext(), MyService::class.java)
        //requireActivity().stopService(intent)

        //온도, 습도 값 변경
        getSensorData()

        //미세먼지 값 변경
        binding.dustHome.setText(dust_value.toString()+"㎍/m³")

        //미세먼지 색상 변경
        val roundDrawable = resources.getDrawable(R.drawable.btn_dust_circle)

        var dust_color = if(dust_value in 0..30) Color.parseColor("#2F50DF") // 좋음 : 파란색
        else if(dust_value in 31..80) Color.parseColor("#45A322")            // 보통 : 초록색
        else if(dust_value in 81..150) Color.parseColor("#FA7F2E")             // 나쁨 : 주황색
        else if(dust_value >151) Color.parseColor("#EA1E1E")                        // 매우나쁨 : 빨간색
        else Color.parseColor("#000000")                                          // 에러 : 검정색

        roundDrawable.setColorFilter(dust_color, PorterDuff.Mode.SRC_ATOP)
        binding.imgDustHome.background = roundDrawable

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getSensorData(){
        // 온도를 가져옴
        APISensor.getService()
            .getSensorValue("TEMP/latest_value")
            .enqueue(object : Callback<SensorData> {
                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
                    if(response.isSuccessful){
                        val data = response.body()
                        binding.tempHome.text = data?.value + "℃"
                    }
                }
                override fun onFailure(call: Call<SensorData>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
        // 습도를 가져옴
        APISensor.getService()
            .getSensorValue("HUMI/latest_value")
            .enqueue(object : Callback<SensorData> {
                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
                    if(response.isSuccessful){
                        val data = response.body()
                        binding.humiHome.text = data?.value + "%"
                    }
                }
                override fun onFailure(call: Call<SensorData>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
    }
}