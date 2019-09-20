package com.cxsplay.flutternative.flutter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.flutter.facade.Flutter

class MyFlutterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Flutter.createView(activity!!, lifecycle, "f_fragment")
    }
}