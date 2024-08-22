package com.chaeni__beam.ai_healing

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class LoginRequestActivity(
    ID: String,
    Password: String,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : StringRequest(Method.POST, URL, listener, errorListener) {

    private val map: MutableMap<String, String> = HashMap()

    init {
        map["ID"] = ID
        map["Password"] = Password
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): MutableMap<String, String> {
        return map
    }

    companion object {
        private const val URL = "http://3.39.196.18/login_test.php"  // 실제 로그인 API 엔드포인트
    }
}
