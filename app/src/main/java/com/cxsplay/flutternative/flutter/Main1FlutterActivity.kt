package com.cxsplay.flutternative.flutter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxsplay.flutternative.R

class Main1FlutterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1_flutter)
        supportFragmentManager.beginTransaction().add(R.id.fl_container, MyFlutterFragment()).commit()
    }
}
