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

                binding.btnColorChangeLight.background = roundDrawable
                var color_hex = Integer.toHexString(defaultColor)

                // 전송할 무드등 색상 값
                Log.d("COLOR_CONVERT", color_hex)
            }
        })
        colorPicker.show()
    }
}