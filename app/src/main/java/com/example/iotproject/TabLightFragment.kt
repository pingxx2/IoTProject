package com.example.iotproject

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.iotproject.databinding.FragmentTabLightBinding
import yuku.ambilwarna.AmbilWarnaDialog

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
        val colorPicker = AmbilWarnaDialog(context, defaultColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog) {
                //취소
            }

            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                //선택
                defaultColor = color

                //버튼 색 변경
                val roundDrawable = resources.getDrawable(R.drawable.btn_light_circle)
                roundDrawable.setColorFilter(defaultColor, PorterDuff.Mode.SRC_ATOP)
                //Log.d("COLOR", defaultColor.toString())

                binding.btnColorChangeLight.background = roundDrawable

            }
        })
        colorPicker.show()
    }

}