package com.example.iotproject

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.iotproject.databinding.FragmentTabLightBinding
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory.toByte
import yuku.ambilwarna.AmbilWarnaDialog
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.math.*

class TabLightFragment : Fragment() {
    private var _binding: FragmentTabLightBinding? = null
    private val binding get() = _binding!!
    var defaultColor : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //색상변경
        binding.btnColorChangeLight.setOnClickListener{
            openColorPicker();
        }

        //밝기변경
        binding.seekBarLight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 값이 변할 때
                binding.txtBrightnessLight.setText(progress.toString())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                // 터치 시작할 때
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // 터치 멈출 때
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentTabLightBinding.inflate(inflater, container, false)
        return binding.root
    }

    //ColorPicker 동작함수
    fun openColorPicker() {
        val colorPicker = AmbilWarnaDialog(requireContext(), defaultColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog) {
                //취소
            }

            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                //선택
                defaultColor = color

                //버튼 색 변경
                val roundDrawable = resources.getDrawable(R.drawable.btn_light_circle)
                roundDrawable.setColorFilter(defaultColor, PorterDuff.Mode.SRC_ATOP)
                Log.d("COLOR", defaultColor.toString())

                binding.btnColorChangeLight.background = roundDrawable
                
                // 전송할 무드등 색상 값
                Log.d("COLOR_CONVERT", colorConvert(defaultColor))

            }
        })
        colorPicker.show()
    }

    private fun colorConvert(color : Int): String{
        // 10진수 음수 값 16진수로 변경하기
        var array_hex = Array<String>(6,{"0"})
        var color_return: String = ""

       // 1. 10진수 절댓값을 16진수로 변경
        var color_hex : String = Integer.toHexString(abs(color))
        Log.d("COLOR_16", color_hex)

        // 1-A. 검정색의 경우 7자리로 변환되므로 강제 변경
        if(color_hex.length==7) {
            color_hex="FFFFFF"
        }

        // 2. 16진수로 변환된 값의 생략된 0을 붙여줌
        for(i in (6 - color_hex.length)..5){
            array_hex[i] = color_hex[i-(6 - color_hex.length)].toString()
        }

        // 3. 16진수를 전체 반전시켜 처음에 있던 음수 값 해결
        for(j in 0..5 ){
            array_hex[j] = Integer.toHexString(abs(0xF - Integer.parseInt(array_hex[j],16))).toString()
            color_return += array_hex[j]
        }
        
        // 4. 알파벳 대문자로 변경 후 리턴
        color_return = color_return.uppercase()
        return color_return
    }

}