package com.chaeni__beam.ai_healing

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.chaeni__beam.ai_healing.databinding.ActivityLoginBinding
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinBtn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView = currentFocus
        if (focusView != null && ev != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()

            if (!rect.contains(x, y)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun test() {
        val id = binding.idInput.text.toString()
        val pw = binding.passwordInput.text.toString()

        val responseListener = Response.Listener<String> { response ->
            try {
                // 서버에서 받은 원본 응답을 로그로 출력합니다.
                Log.d("tttt", response)

                val jsonObject = JSONObject(response)
                val success = jsonObject.getBoolean("success")

                if (success) {
                    val msg = jsonObject.getString("ID")
                    Toast.makeText(applicationContext, "로그인 성공. ID: $msg", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // 이전 액티비티를 종료하여 뒤로 가기 방지
                } else {
                    Toast.makeText(applicationContext, "로그인 실패. ID 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "응답 처리 중 오류 발생.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }


        val errorListener = Response.ErrorListener { error ->
            error.printStackTrace()
            Toast.makeText(applicationContext, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }

        // 에러 리스너를 추가하여 LoginRequestActivity를 생성합니다.
        val loginRequestActivity = LoginRequestActivity(id, pw, responseListener, errorListener)
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(loginRequestActivity)
    }

}