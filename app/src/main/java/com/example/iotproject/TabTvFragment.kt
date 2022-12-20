package com.example.iotproject

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.os.Build.VERSION_CODES.P
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
        var now_channel :Int = 0


        //전원
        binding.switchTv.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // TV ON
            } else{
                // TV OFF
            }
        }

        //저장된 채널 번호 불러오기
        loadData()

        //채널 위로 가는 버튼
        binding.btnChannelUpTv.setOnClickListener{
            now_channel = binding.txtChannelTv.text.toString().toInt() + 1
            if(now_channel<10){
                binding.txtChannelTv.setText("0"+now_channel.toString())
            }
            else{
                binding.txtChannelTv.setText(now_channel.toString())
            }
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

    private fun saveData(){
        //채널 번호 저장하기
        val pref = requireActivity().getPreferences(0)
        val edit = pref.edit()
        edit.putString("channel", binding.txtChannelTv.text.toString())
        edit.apply()
    }
    private fun loadData(){
        //채널 번호 불러오기
        val pref = requireActivity().getPreferences(0)
        binding.txtChannelTv.setText(pref.getString("channel", "01"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentTabTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        //Fragment가 종료되기 직전에 채널 저장
        saveData()
    }
}