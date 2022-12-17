package com.example.iotproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iotproject.databinding.FragmentTabLedBinding

class TabLedFragment : Fragment() {
    private var _binding: FragmentTabLedBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //전원
        binding.switchLed.setOnCheckedChangeListener{ p, isChecked ->
            if(isChecked){
                // LED ON
            } else{
                // LED OFF
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