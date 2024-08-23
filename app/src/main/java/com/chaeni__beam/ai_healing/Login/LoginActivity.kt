package com.chaeni__beam.ai_healing.Login

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.chaeni__beam.ai_healing.MainActivity
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
            finish()
            //test()
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

        // 첫 번째 서버로 요청 (기존 서버)
        val responseListener1 = Response.Listener<String> { response ->
            try {
                Log.d("Server1 Response", response)
                val jsonObject = JSONObject(response)
                val success = jsonObject.getBoolean("success")

                if (success) {
                    Toast.makeText(applicationContext, "기존 서버 로그인 성공.", Toast.LENGTH_SHORT).show()

                    // 두 번째 서버로 요청 보내기
                    sendToSecondaryServer(id, pw)

                } else {
                    Toast.makeText(applicationContext, "기존 서버 로그인 실패.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "기존 서버 응답 처리 중 오류 발생.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "기존 서버에서 알 수 없는 오류 발생.", Toast.LENGTH_SHORT).show()
            }
        }

        val errorListener1 = Response.ErrorListener { error ->
            error.printStackTrace()
            Toast.makeText(applicationContext, "기존 서버에 네트워크 오류 발생.", Toast.LENGTH_SHORT).show()
        }

        val loginRequestActivity = LoginRequestActivity(id, pw, responseListener1, errorListener1)
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(loginRequestActivity)
    }

    private fun sendToSecondaryServer(id: String, pw: String) {
        // 두 번째 서버로 요청
        val responseListener2 = Response.Listener<JSONObject> { response ->
            try {
                Log.d("Server2 Response", response.toString())
                val success = response.getBoolean("success")

                if (success) {
                    val msg = response.getString("ID")
                    Toast.makeText(applicationContext, "새로운 서버 로그인 성공. ID: $msg", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "새로운 서버 로그인 실패.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "새로운 서버 응답 처리 중 오류 발생.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "새로운 서버에서 알 수 없는 오류 발생.", Toast.LENGTH_SHORT).show()
            }
        }

        val errorListener2 = Response.ErrorListener { error ->
            error.printStackTrace()
            Toast.makeText(applicationContext, "새로운 서버에 네트워크 오류 발생.", Toast.LENGTH_SHORT).show()
        }

        val secondaryLoginRequest = SecondaryLoginRequest(id, pw, responseListener2, errorListener2)
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(secondaryLoginRequest)
    }


}