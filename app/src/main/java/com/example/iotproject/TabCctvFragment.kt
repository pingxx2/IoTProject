package com.example.iotproject

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.iotproject.databinding.FragmentTabCctvBinding
import kotlinx.coroutines.NonCancellable.start


class TabCctvFragment : Fragment() {
    private var _binding: FragmentTabCctvBinding? = null
    private val binding get()= _binding!!

    // uri 변수 = 예시링크
    var cctvUri: String = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //cctv 재생 링크 설정
        val videoUri: Uri = Uri.parse(cctvUri)

        //videoView와 연결할 uri 설정
        binding.videoViewCctv.setVideoURI(videoUri)

        //비디오 로딩 준비가 끝났을 때 실행하도록 리스너 설정
        binding.videoViewCctv.setOnPreparedListener {
            //비디오 시작
            binding.videoViewCctv.start()
        }
    }

    //화면에 안보일 때 = 다른 탭으로 이동했을 때
     override fun onPause() {
        super.onPause()
        //비디오 일시 정지
        if (binding.videoViewCctv != null && binding.videoViewCctv.isPlaying) binding.videoViewCctv.pause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabCctvBinding.inflate(inflater, container, false)
        return binding.root
    }

}