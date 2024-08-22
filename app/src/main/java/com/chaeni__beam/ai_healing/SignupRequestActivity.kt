package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class SignupRequestActivity(
    ID: String,
    Password: String,
    Name : String,
    Birth : Int,
    Gender : String,
    listener: Response.Listener<String>
) : StringRequest(Method.POST, URL, listener, null) {
    private val map: MutableMap<String, String> = hashMapOf()

    companion object {
        private const val URL = "http://3.39.196.18/signup_test.php"
    }

    init {
        map["ID"] = ID
        map["Password"] = Password
        map["Name"] = Name
        map["Birthdate"] = Birth.toString()
        map["Gender"] = Gender

    }

    override fun getParams(): MutableMap<String, String> {
        return map
    }
}