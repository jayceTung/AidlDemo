package com.asuper.aidldemo.kotiln

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.asuper.aidldemo.kotiln.MainActivity.Companion.req_btn
import org.jetbrains.anko.*

/**
 *
 * @author super
 * @date 2018/6/15
 */
class MainActivity: AppCompatActivity() {
    companion object {                    // 这里定义了控件的ID，方面在Activity中根据ID找控件
        val req_btn = 1
        val showview = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUi().setContentView(this)

        val reqBtn = find<Button>(MainActivity.req_btn)
        reqBtn.setOnClickListener {
            onShowDialog()
        }
    }

    private fun onShowDialog() {
        var dialog: PopDialog = PopDialog(this)
        dialog.show()
    }
}

class MainActivityUi: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                id = req_btn
                setOnClickListener { toast("Hello, ${name.text}!") }
            }.lparams {
                width = matchParent
                height = wrapContent
                leftMargin = dip(35)
                rightMargin = dip(35)
            }
        }
    }
}