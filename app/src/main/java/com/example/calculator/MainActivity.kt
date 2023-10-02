package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var textinput: TextView? = null
    private var lastnumeric: Boolean = false
    private var lastdot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textinput = findViewById(R.id.tvinput)
    }

    fun Ondigit(view: View) {
        textinput?.append((view as Button).text)
        lastnumeric = true
        lastdot = false
    }

    fun Onclear(view: View) {
        textinput?.text = ""
    }

    fun OnDecimalpoint(view: View) {
        if (lastnumeric && !lastdot) {

            textinput?.append(".")
            lastnumeric = false
            lastdot = true
        }
    }

    fun Onoperator(view: View) {
        textinput?.text?.let {
            if (lastnumeric && !Isoperationadded(it.toString())) {
                textinput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }
        }
    }

    private fun Isoperationadded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+")
                    || value.contains("-") ||
                    value.contains("*") ||
                    value.contains("/")
        }

    }

    fun Onequal(view: View) {
        if (lastnumeric) {
            var tvValue = textinput?.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitvalue = tvValue.split("-")
                    var one = splitvalue[0]
                    val two = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    textinput?.text = removeZeroafterDot((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {
                    val splitvalue = tvValue.split("+")
                    var one = splitvalue[0]
                    val two = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    textinput?.text =removeZeroafterDot((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("/")) {
                    val splitvalue = tvValue.split("/")
                    var one = splitvalue[0]
                    val two = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    textinput?.text = removeZeroafterDot((one.toDouble() / two.toDouble()).toString())

                } else if (tvValue.contains("*")) {
                    val splitvalue = tvValue.split("*")
                    var one = splitvalue[0]
                    val two = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    textinput?.text =removeZeroafterDot((one.toDouble() * two.toDouble()).toString())
                }



        } catch (e: ArithmeticException) {
            e.printStackTrace()
        }
    }
}
    private fun removeZeroafterDot(result : String): String{
        var value= result
        if(result.contains(".0"))
            value =result.substring(0,result.length -2)
        return value
        }
}
