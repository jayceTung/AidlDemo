package com.asuper.aidldemo.kotiln

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*

/**
 *
 * @author super
 * @date 2018/6/15
 */
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUi().setContentView(this)
    }
}

class MainActivityUi: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
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