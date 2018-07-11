package com.asuper.aidldemo.kotiln

import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import com.asuper.aidldemo.R

/**
 *
 * @author super
 * @date 2018/7/11
 */
class PopDialog: Dialog {
    companion object {
        private val TAG: String = "PopDialog"
    }

    private var mBtn1: Button? = null
    private var mBtn2: Button? = null
    private var mTextPaint: Paint? = null
    private var mContext: Context? = null

    constructor(context: Context): super(context, 0) {
        this.mContext = context
        init(context)
    }

    constructor(context: Context, theme: Int): super(context, theme) {
        this.mContext = context
        init(context)
    }

    private fun init(context: Context) {
        Log.d(TAG, "init")
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_pop, null, false)
        mBtn1 = view.findViewById(R.id.btn1) as Button
        mBtn2 = view.findViewById(R.id.btn2) as Button
        mBtn2?.let {
            it.setOnClickListener {
                v -> Log.i(TAG, "onclick")
            }
        }
        mBtn1?.let {
            it.setOnClickListener {
                v -> Log.i(TAG, "onclick")
            }
        }
        setContentView(view)
        var win: Window = window
        win.setGravity(Gravity.BOTTOM)
//        window.setWindowAnimations()
        var mParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        view.layoutParams = mParams
    }


}