package com.example.mehmet_can_gunduz_hw6

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mehmet_can_gunduz_hw6.configs.ApiClient
import com.example.mehmet_can_gunduz_hw6.models.JWTData
import com.example.mehmet_can_gunduz_hw6.models.JWTUser
import com.example.mehmet_can_gunduz_hw6.services.DummyServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var dummyService: DummyServices
    lateinit var edt_username : EditText
    lateinit var edt_password : EditText
    lateinit var btn_login : Button

    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        dummyService = ApiClient.getClient().create(DummyServices::class.java)

        edt_username = findViewById(R.id.edt_username)
        edt_password = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_login.setOnClickListener(btnOnClickListener)
    }

    val btnOnClickListener = View.OnClickListener {


        val email = edt_username.text.toString()
        val pas = edt_password.text.toString()

        val jwtUser = JWTUser(email,pas)
        dummyService.login(jwtUser).enqueue(object  : Callback<JWTData> {
            override fun onResponse(call: Call<JWTData>, response: Response<JWTData>) {
                val jwtUser = response.body()
                if (jwtUser != null){

                    editor.putString("username", jwtUser.username)
                    editor.putString("firstname",jwtUser.firstname)
                    editor.commit()

                    val intent = Intent(this@MainActivity,ProductList::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<JWTData>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Internet or Server Fail", Toast.LENGTH_LONG).show()
            }

        })
    }
}