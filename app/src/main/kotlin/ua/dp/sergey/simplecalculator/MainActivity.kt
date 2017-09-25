package ua.dp.sergey.simplecalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val plus: String = "+"
    private val minus: String = "-"
    private val multiply: String = "*"
    private val divide: String = "/"
    private val percent: String = "%"
    private val plusMinus: String = "+/-"
    private val equals: String = "="
    private val point: String = "."

    private var result: TextView? = null
    private var onNumButClick: View.OnClickListener? = null
    private var onMathOperationsButClick: View.OnClickListener? = null
    private var temp: Double = Double.MIN_VALUE
    private var temp2: Double = Double.MIN_VALUE
    private var tempMathOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        result = this.resultTV as TextView
        createClickListeners()
        setOnClickListenerForNumbers()
        setOnClickListenerForMathActions()
    }

    private fun createClickListeners() {
        onNumButClick = View.OnClickListener { view ->
            result!!.text = result!!.text as String + (view as? Button)?.text as String

            if (tempMathOperation == "")
                temp = getCurrentValue()
            else
                temp2 = getCurrentValue()
        }

        onMathOperationsButClick = View.OnClickListener { view ->
            try {
                otherOperations(view)

                mathOperation(view)
            } catch (e: Exception) { //TODO show error
                clean()
            }

        }
    }

    private fun otherOperations(view: View) {
        when ((view as? Button)) {
            butPercent -> {
                result!!.text = result!!.text as String + (percent)
            }
            butPlusMinus -> {
                result!!.text = (getCurrentValue() * -1.0).toString()
            }
            butPoint -> {
                result!!.text = result!!.text as String + (point)
            }
            butEquals -> {
                equals()
            }
            butC -> {
                clean()
            }
            butDel -> {
                if (result!!.text.isNotEmpty())
                    result!!.text = result!!.text.removeRange(result!!.text.length - 1, result!!.text.length)
            }
        }
    }

    private fun clean() {
        temp = Double.MIN_VALUE
        temp2 = Double.MIN_VALUE
        tempMathOperation = ""
        result!!.text = ""
    }

    private fun mathOperation(view: View) {
        var pressed = false

        if (temp == Double.MIN_VALUE) {
            temp = getCurrentValue()
        }
        when ((view as? Button)) {
            butPlus -> {
                tempMathOperation = plus
                pressed = true
            }
            butMinus -> {
                tempMathOperation = minus
                pressed = true
            }
            butDivide -> {
                tempMathOperation = divide
                pressed = true
            }
            butMultiply -> {
                tempMathOperation = multiply
                pressed = true
            }
        }

        if (pressed && temp2 != Double.MIN_VALUE)
            equals()
        else if (pressed)
            result!!.text = ""
    }

    private fun equals() {
        when (tempMathOperation) {
            plus -> result!!.text = (temp + temp2).toString()
            minus -> result!!.text = (temp - temp2).toString()
            divide -> result!!.text = (temp / temp2).toString()
            multiply -> result!!.text = (temp * temp2).toString()
        }
        temp = Double.MIN_VALUE
        temp2 = Double.MIN_VALUE
        tempMathOperation = ""
    }

    private fun getCurrentValue(): Double {
        var a: Double
        try {
            a = (result!!.text as? String)!!.toDouble()
        } catch (e: NumberFormatException) { //TODO show error
            a = Double.MIN_VALUE
            clean()
        }
        return a
    }

    private fun setOnClickListenerForNumbers() {
        this.but0.setOnClickListener(onNumButClick)
        this.but1.setOnClickListener(onNumButClick)
        this.but2.setOnClickListener(onNumButClick)
        this.but3.setOnClickListener(onNumButClick)
        this.but4.setOnClickListener(onNumButClick)
        this.but5.setOnClickListener(onNumButClick)
        this.but6.setOnClickListener(onNumButClick)
        this.but7.setOnClickListener(onNumButClick)
        this.but8.setOnClickListener(onNumButClick)
        this.but9.setOnClickListener(onNumButClick)
    }

    private fun setOnClickListenerForMathActions() {
        this.butPlus.setOnClickListener(onMathOperationsButClick)
        this.butMinus.setOnClickListener(onMathOperationsButClick)
        this.butDivide.setOnClickListener(onMathOperationsButClick)
        this.butMultiply.setOnClickListener(onMathOperationsButClick)
        this.butPercent.setOnClickListener(onMathOperationsButClick)
        this.butPlusMinus.setOnClickListener(onMathOperationsButClick)
        this.butPoint.setOnClickListener(onMathOperationsButClick)
        this.butC.setOnClickListener(onMathOperationsButClick)
        this.butDel.setOnClickListener(onMathOperationsButClick)
        this.butEquals.setOnClickListener(onMathOperationsButClick)
    }

}