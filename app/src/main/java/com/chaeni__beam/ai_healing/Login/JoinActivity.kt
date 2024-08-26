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
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityJoinBinding
import org.json.JSONException
import org.json.JSONObject

class JoinActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinBinding

    private var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭 이벤트
        binding.joinBtn.setOnClickListener {
            test()
            finish()
        }

        binding.radioGroupGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonMale -> gender = "남"
                R.id.radioButtonFemale -> gender = "여"
            }
            // 선택된 성별을 로그로 출력하여 확인합니다.
            Log.d("JoinActivity", "선택된 성별: $gender")
        }
    }

    fun test() {
        // 버튼 클릭 시 호출되는 메서드
        Log.d("tttt", "test() 호출됨")
        val id = binding.idInput.text.toString()
        val pw = binding.passwordInput.text.toString()
        val name = binding.nameInput.text.toString()
        val birth = binding.birthInput.text.toString().toInt()
        val gend = gender.toString()


        val responseListener = Response.Listener<String> { response ->
            try {
                Log.d("JoinActivity", "응답 받음")
                val jsonObject = JSONObject(response)
                val success = jsonObject.getBoolean("success")

                if (success) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(this, "예외 1", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "예외 2", Toast.LENGTH_SHORT).show()
            }
        }

        val signupRequestActivity = SignupRequestActivity(id, pw, name, birth, gend, responseListener)
        val queue = Volley.newRequestQueue(this)
        queue.add(signupRequestActivity)
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
}




