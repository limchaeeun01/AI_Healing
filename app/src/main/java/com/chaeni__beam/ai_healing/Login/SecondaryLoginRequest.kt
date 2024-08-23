package com.chaeni__beam.ai_healing.Login

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class SecondaryLoginRequest(
    ID: String,
    Password: String,
    listener: Response.Listener<JSONObject>,
    errorListener: Response.ErrorListener
) : JsonObjectRequest(Method.POST, URL, null, listener, errorListener) {

    private val map: MutableMap<String, String> = HashMap()

    init {
        map["user_id"] = "user2"
    }

    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        return headers
    }

    override fun getBody(): ByteArray {
        return JSONObject(map as Map<*, *>).toString().toByteArray(Charsets.UTF_8)
    }

    companion object {
        private const val URL = "http://172.20.10.9:5000/login"  // 새로운 로그인 API 엔드포인트
    }
}
