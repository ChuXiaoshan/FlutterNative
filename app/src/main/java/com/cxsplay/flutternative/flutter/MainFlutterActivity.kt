package com.cxsplay.flutternative.flutter

import android.os.Bundle
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.flutternative.R
import com.cxsplay.flutternative.base.BaseActivity
import com.cxsplay.flutternative.databinding.ActivityMainFlutterBinding
import io.flutter.facade.Flutter
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.view.FlutterView

class MainFlutterActivity : BaseActivity() {

    lateinit var bind: ActivityMainFlutterBinding
    lateinit var flutterView: FlutterView
    var myEvent: EventChannel.EventSink? = null
    private var num1: Int = 0
    private var num2: Int = 0
    private var num3: Int = 0

    private val msgChannel by lazy { BasicMessageChannel(flutterView, "com.cxsplay/test1", StandardMessageCodec.INSTANCE) }
    private val methodChannel by lazy { MethodChannel(flutterView, "com.cxsplay/test2") }
    private val eventChannel by lazy { EventChannel(flutterView, "com.cxsplay/test3") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main_flutter)
        flutterView = Flutter.createView(this, lifecycle, "main_flutter")
        val mParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        bind.fl.addView(flutterView, mParams)
        init()
    }

    private fun init() {
        bind.btn1.setOnClickListener {
            num1++
            bind.btn1.text = "msg $num1"
            msgChannel.send(num1) { ToastUtils.showShort(it.toString()) }
        }
        bind.btn2.setOnClickListener {
            num2++
            bind.btn2.text = "method $num2"
            methodChannel.invokeMethod("num2Receiver", num2)
        }
        bind.btn3.setOnClickListener {
            num3++
            bind.btn3.text = "event $num3"
            myEvent?.success(num3)
        }
        msgChannel()
        methodChannel()
        eventChannel()


    }

    private fun msgChannel() {
        msgChannel.setMessageHandler { a, reply ->
            num1 = a as Int
            bind.btn1.text = "msg $num1"
            reply.reply("收到 $num1")
        }
    }

    private fun methodChannel() {
        methodChannel.setMethodCallHandler { call, result ->
            if (call.method == "numberAdd") {
                num2 = call.arguments as Int
                bind.btn2.text = "method $num2"
                result.success("收到 $num2")
            } else {
                result.notImplemented()
            }
        }
    }

    private fun eventChannel() {
        eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(obj: Any?, event: EventChannel.EventSink?) {
                myEvent = event
            }

            override fun onCancel(p0: Any?) {
            }
        })
    }

}
