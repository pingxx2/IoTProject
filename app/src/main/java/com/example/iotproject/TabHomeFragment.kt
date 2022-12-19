package com.example.iotproject


import android.content.Intent
import android.app.TabActivity
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.iotproject.databinding.FragmentTabHomeBinding
import org.eclipse.paho.client.mqttv3.MqttMessage


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

        //온도 값 변경
        binding.tempHome.setText(temp_value.toString()+"℃")

        //습도 값 변경
        binding.humiHome.setText(humi_value.toString()+"%")

        //미세먼지 값 변경
        binding.dustHome.setText(dust_value.toString()+"㎍/m³")

        //미세먼지 색상 변경
        val roundDrawable = resources.getDrawable(R.drawable.btn_dust_circle)

        var dust_color = if(dust_value in 0..30) -13676321 // 좋음 : 파란색
        else if(dust_value in 31..80) -12213470            // 보통 : 초록색
        else if(dust_value in 81..150) -360658             // 나쁨 : 주황색
        else if(dust_value >151) -1434082                        // 매우나쁨 : 빨간색
        else -16777216                                           // 에러 : 검정색

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


}