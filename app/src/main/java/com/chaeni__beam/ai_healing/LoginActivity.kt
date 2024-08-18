package com.chaeni__beam.ai_healing

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.loginBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })


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

    fun test(){
        binding.loginBtn.setOnClickListener {
            val id = binding.idInput.text.toString()
            val pw = binding.passwordInput.text.toString()

            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")

                    if (success) {
                        val msg = jsonObject.getString("ID")
                        Toast.makeText(applicationContext, "로그인 성공. ID :" + msg, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "예외 1", Toast.LENGTH_SHORT).show()
                    return@Listener
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val loginRequestActivity = LoginRequestActivity(id, pw, responseListener)
            val queue = Volley.newRequestQueue(applicationContext)
            queue.add(loginRequestActivity)
        }


    }
}