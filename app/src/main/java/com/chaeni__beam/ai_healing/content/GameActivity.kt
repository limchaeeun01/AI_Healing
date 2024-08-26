package com.chaeni__beam.ai_healing.content

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaeni__beam.ai_healing.databinding.ActivityGameBinding
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrls = arrayOf(
            "https://i.namu.wiki/i/9vP1RUSbNcozJ9_Bn69pHCoVzrttiYECfTPkiG_DT8xf0opjtspeCSZbUZhBa_hMJIukPpDYmgtEkfy0f2ATbQ.png",
            "https://i.namu.wiki/i/uXUTQHCrevI5xmdNDN5uy1vcUId6Ju0ZpvZfkI6Hu4tIlz2H9jeAvwHZ_KuKfBYHNlh8oECFx2yPVyliCdfa6g.webp"  // 두 번째 이미지 URL
        )

        val imageViews = arrayOf(
            binding.gameImage1,
            binding.gameImage2,
        )

        for (i in imageUrls.indices) {
            val url = imageUrls[i]
            val imageView = imageViews[i]

            val uThread: Thread = object : Thread() {
                var bitmap: Bitmap? = null

                override fun run() {
                    try {
                        val url = URL(imageUrls[i])
                        val conn = url.openConnection() as HttpURLConnection
                        conn.doInput = true
                        conn.connect()
                        val `is` = conn.inputStream
                        bitmap = BitmapFactory.decodeStream(`is`)
                    } catch (e: MalformedURLException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    runOnUiThread {
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
            uThread.start()
        }
    }
}
