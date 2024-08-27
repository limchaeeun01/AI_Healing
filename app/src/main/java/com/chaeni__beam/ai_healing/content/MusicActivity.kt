package com.chaeni__beam.ai_healing.content

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.databinding.ActivityMusicBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class MusicActivity : AppCompatActivity() {

    lateinit var binding : ActivityMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#18212E")  // 상태바 색상 변경
        window.navigationBarColor = Color.parseColor("#18212E")  // 네비게이션바 색상 변경

//        lifecycle.addObserver(binding.youtubeVideo)
//        binding.youtubeVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "ExEIv2vEXXE"
//                youTubePlayer.cueVideo(videoId, 0f)
//            }
//
//            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
//                super.onStateChange(youTubePlayer, state)
//                if (state == PlayerConstants.PlayerState.PLAYING) {
//                    // 영상이 재생되기 시작할 때 Toast 메시지 표시
//                    Toast.makeText(applicationContext, "영상이 재생 중입니다!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

    }


}