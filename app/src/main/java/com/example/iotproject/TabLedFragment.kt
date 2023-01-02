package com.example.iotproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iotproject.data.Dummy
import com.example.iotproject.databinding.FragmentTabLedBinding
import com.example.iotproject.service.APISensor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabLedFragment : Fragment() {
    private var _binding: FragmentTabLedBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //전체 조명
        binding.switchEntireLed.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 전체 조명 ON
            } else{
                // 전체 조명 OFF
            }
        }

        //거실 조명
        binding.switchLivingLed.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 거실 조명 ON
            } else{
                // 거실 조명 OFF
            }
        }

        //화장실 조명
        binding.switchBathLed.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 화장실 조명 ON
            } else{
                // 화장실 조명 OFF
            }
        }

        //방1 조명
        binding.switchRoom1Led.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 방1 조명 ON
            } else{
                // 방1 조명 OFF
            }
        }

        //방2 조명
        binding.switchRoom2Led.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 방2 조명 ON
            } else{
                // 방2 조명 OFF
            }
        }

        //방3 조명
        binding.switchRoom3Led.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // 방3 조명 ON
            } else{
                // 방3 조명 OFF
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabLedBinding.inflate(inflater, container, false)
        return binding.root
    }
}