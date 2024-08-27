package com.chaeni__beam.ai_healing.UI

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.chaeni__beam.ai_healing.Adapter.FoodListAdapter
import com.chaeni__beam.ai_healing.Data.foodData
import com.chaeni__beam.ai_healing.DiaryActivity
import com.chaeni__beam.ai_healing.EntranceActivity
import com.chaeni__beam.ai_healing.food.FoodActivity
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.content.BookActivity
import com.chaeni__beam.ai_healing.content.GameActivity
import com.chaeni__beam.ai_healing.content.MovieActivity
import com.chaeni__beam.ai_healing.content.MusicActivity
import com.chaeni__beam.ai_healing.databinding.FragmentHomeBinding
import com.chaeni__beam.ai_healing.food.FoodRcmActivity
import kotlin.random.Random


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var foodAdapter: FoodListAdapter

    val foodData = mutableListOf<foodData>()



    var emotion: String = ""

    var name = "김수원"

    val sadnessMentList = listOf("늘 행복할 수는 없지만\n" +
            "내일은 또 괜찮은 하루가 될 수 있어요.\n", "너무 밝지 않아도 돼요.\n" +
            "환할수록 그림자도 짙은 법이니까요.\n")

    val angerMentList = listOf("화는 순간이지만 그 여파는 오래갑니다.\n" +
            "차분히 숨을 고르고 마음을 가라앉혀 보세요.\n", "당신의 마음이 지치지 않도록,\n" +
            "잠시 멈추고 깊게 호흡해 보세요.\n")

    val gentlenessMentList = listOf("현재의 순간을 즐기세요.\n" +
            "이 평온함은 당신의 내면의 힘을 보여줍니다.\n", "당신의 차분한 마음이\n" +
            "주변에 긍정적인 에너지를 전파하고 있습니다.\n")

    val happinessMentList = listOf("기쁨으로 가득 찬 하루를 보내고 계시네요.\n" +
            "그 웃음이 계속되길 바랄게요.\n", "행복한 순간을 소중히 여기세요.\n" +
            "그 기억이 앞으로도 많은 기쁨을 줄 거예요.\n")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            emotion = it.getString("emotion", "") ?: ""
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setEmotion()

        binding.foodAllBtn.setOnClickListener{
            val intent = Intent(requireContext(), FoodActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.button3.setOnClickListener{
            //짜야함
        }

        binding.diaryBtn.setOnClickListener{
            val intent = Intent(requireContext(), DiaryActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.imageEmotion.setOnClickListener{
            val intent = Intent(requireContext(), EntranceActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.movieRcm.setOnClickListener{
            val intent = Intent(requireContext(), MovieActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.musicRcm.setOnClickListener{
            val intent = Intent(requireContext(), MusicActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.bookRcm.setOnClickListener{
            val intent = Intent(requireContext(), BookActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.gameRcm.setOnClickListener{
            val intent = Intent(requireContext(), GameActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        binding.foodRcm.setOnClickListener{
            val intent = Intent(requireContext(), FoodRcmActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        createNotificationChannel()

        createNotification()


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val CHANNEL_ID = "your_channel_id"
        val channelName = "My Channel"
        val channelDescription = "This is a description for my channel"

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
            description = channelDescription
            // 추가 설정 (소리, 진동 등)
        }

        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    private fun createNotification() {
        val CHANNEL_ID = "your_channel_id"
        val notiId = 1 // 알림 ID 설정
        val title = "알림 타이틀"
        val content = "알림 내용"
        val bitmap = BitmapFactory.decodeResource(requireContext().resources, R.drawable.anger_bg)

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setLargeIcon(bitmap)
            .setShowWhen(true)
            .setColor(ContextCompat.getColor(requireContext(), R.color.black))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(requireContext()).notify(notiId, builder.build())
    }



    override fun onDestroy() {
        super.onDestroy()
    }


    fun setEmotion(){
        when(emotion){
            "sadness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#52BBE3"))
                binding.titleEmotion.setText(" 슬픔, 우울 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#8045496E"))
                binding.imageEmotion.setImageResource(R.drawable.sadness_bg)
                binding.emotionSelectText.visibility = View.GONE
                binding.mentEmotion.setText(sadnessMentList[Random.nextInt(2)] + "${name}님에게 따뜻한 위로가 될 수 있는\n컨텐츠를 추천해드릴게요.")}

            "anger" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#F26154"))
                binding.titleEmotion.setText(" 화남, 스트레스 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80713737"))
                binding.imageEmotion.setImageResource(R.drawable.anger_bg)
                binding.emotionSelectText.visibility = View.GONE
                binding.mentEmotion.setText(angerMentList[Random.nextInt(2)] + "${name}님의 마음을 가라앉힐 수 있는\n컨텐츠를 추천해드릴게요.")}

            "gentleness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#FCD533"))
                binding.titleEmotion.setText(" 평온, 차분 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80806B36"))
                binding.imageEmotion.setImageResource(R.drawable.gentleness_bg)
                binding.emotionSelectText.visibility = View.GONE
                binding.mentEmotion.setText(gentlenessMentList[Random.nextInt(2)] + "${name}님의 차분한 마음을 유지하는 데\n도움이 될 만한 컨텐츠를 추천해드릴게요.")}

            "happiness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#FE999F"))
                binding.titleEmotion.setText(" 행복, 기쁨 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80AF2B59"))
                binding.imageEmotion.setImageResource(R.drawable.happiness_bg)
                binding.emotionSelectText.visibility = View.GONE
                binding.mentEmotion.setText(happinessMentList[Random.nextInt(2)] + "${name}님이 이 행복한 순간을 더 오래 지속할 수 있도록\n맞춤 컨텐츠를 추천해드릴게요.")}

            else -> {
                binding.emotionSelectText.visibility = View.VISIBLE
            }
        }
    }



}