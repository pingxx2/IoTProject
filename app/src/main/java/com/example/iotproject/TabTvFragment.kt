package com.example.iotproject

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iotproject.databinding.FragmentTabTvBinding

class TabTvFragment : Fragment() {
    private var _binding: FragmentTabTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //전원
        binding.switchTv.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // TV ON
            } else{
                // TV OFF
            }
        }

        //TV 채널 초기 값
        binding.txtChannelTv.setText("01")

        //채널 위로 가는 버튼
        binding.btnChannelUpTv.setOnClickListener{
            val now_channel = binding.txtChannelTv.text.toString().toInt() + 1
            binding.txtChannelTv.setText(now_channel.toString())
        }

        //채널 아래로 가는 버튼
        binding.btnChannelDownTv.setOnClickListener{
            var now_channel = binding.txtChannelTv.text.toString().toInt() - 1
            if(now_channel<=0 ){
                now_channel = 1
            }
            if(now_channel<10){
                binding.txtChannelTv.setText("0"+now_channel.toString())
            }
            else{
                binding.txtChannelTv.setText(now_channel.toString())
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentTabTvBinding.inflate(inflater, container, false)
        return binding.root
    }
}