package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class LoginRequestActivity(
    ID: String,
    Password: String,
    listener: Response.Listener<String>
) : StringRequest(Method.POST, URL, listener, null) {

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
        private const val URL = "http://54.180.145.22/login_test.php"
    }
}
