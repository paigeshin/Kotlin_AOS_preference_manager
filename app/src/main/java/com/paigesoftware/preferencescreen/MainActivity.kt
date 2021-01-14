package com.paigesoftware.preferencescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_information.setOnClickListener {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val username = sharedPreferences.getString("edt_username", "")
            val password = sharedPreferences.getString("edt_password", "")
            val checked = sharedPreferences.getBoolean("chkBox", false)
            val list = sharedPreferences.getString("list", "") //list도 string 으로 가져온다.
            show_text_data.text = "$username, $password $checked $list"
        }

        btn_store_information.setOnClickListener {
            val intent = Intent(this@MainActivity, MyPreferenceActivity::class.java)
            startActivity(intent)
        }

    }




}