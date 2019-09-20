package com.cxsplay.flutternative

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.flutternative.base.BaseActivity
import com.cxsplay.flutternative.databinding.ActivityMainBinding
import com.cxsplay.flutternative.flutter.Main1FlutterActivity
import com.cxsplay.flutternative.flutter.Main2FlutterActivity
import com.cxsplay.flutternative.flutter.MainFlutterActivity

class MainActivity : BaseActivity() {

    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        bind.btn1.setOnClickListener { startActivity(Intent(this, MainFlutterActivity::class.java)) }
        bind.btn2.setOnClickListener { startActivity(Intent(this, Main1FlutterActivity::class.java)) }
        bind.btn3.setOnClickListener {
            val intent = Intent(this, Main2FlutterActivity::class.java)
            intent.action = "android.intent.action.RUN"
            intent.putExtra("route", "f_other_page")
            startActivity(intent)
        }

        bind.tv1.setOnClickListener {
            ToastUtils.showShort(getTextViewByLine(3, bind.tv1))
        }
        bind.tv2.setOnClickListener {
            ToastUtils.showShort(bind.tv2.text)
        }


        bind.tv1.post {
            var s =
                "全球 Android 设备数量突破 10 亿台，Play Store 应用商店提供数千万款应用让用户尽情探索。" +
                        "鉴于硬件与软件的深度普及，想要在整个 Android 生态圈内推动变革绝非易事，但是，" +
                        "无障碍开发者基础架构团队从不畏惧挑战，正在努力推进无障碍功能在全生态圈的进一步发展。"

            var s2 = getTextViewByLine(3, bind.tv1)

            bind.tv2.text = s.replace(s2!!, "")
        }
    }

    private fun getTextViewByLine(lines: Int, tv: TextView): String? {
        val layout = tv.layout
        var str = tv.text.toString()
        var result: String? = ""
        var start: Int
        var end: Int
        repeat(lines) { i ->
            start = layout.getLineStart(i)
            end = layout.getLineEnd(i)
            val lineStr = str.substring(start, end)
            result += lineStr
        }
        return result
    }
}
