package sanzlimited.com.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        val btnZero = findViewById<Button>(R.id.btnZero)
        btnZero?.setOnClickListener(){
            onDigit(0)
        }

        val btnOne = findViewById<Button>(R.id.btnOne)
        btnOne?.setOnClickListener(){
            onDigit(1)
        }

        val btnTwo = findViewById<Button>(R.id.btnTwo)
        btnTwo?.setOnClickListener(){
            onDigit(2)
        }

        val btnThree = findViewById<Button>(R.id.btnThree)
        btnThree?.setOnClickListener(){
            onDigit(3)
        }

        val btnFour = findViewById<Button>(R.id.btnFour)
        btnFour?.setOnClickListener(){
            onDigit(4)
        }

        val btnFive = findViewById<Button>(R.id.btnFive)
        btnFive?.setOnClickListener(){
            onDigit(5)
        }

        val btnSix = findViewById<Button>(R.id.btnSix)
        btnSix?.setOnClickListener(){
            onDigit(6)
        }

        val btnSeven = findViewById<Button>(R.id.btnSeven)
        btnSeven?.setOnClickListener(){
            onDigit(7)
        }

        val btnEight = findViewById<Button>(R.id.btnEight)
        btnEight?.setOnClickListener(){
            onDigit(8)
        }

        val btnNine = findViewById<Button>(R.id.btnNine)
        btnNine?.setOnClickListener(){
            onDigit(9)
        }

        val btnDivide = findViewById<Button>(R.id.btnDivide)
        btnDivide?.setOnClickListener(){
            onOperator("/")
        }

        val btnTimes = findViewById<Button>(R.id.btnTimes)
        btnTimes?.setOnClickListener(){
            onOperator("*")
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd?.setOnClickListener(){
            onOperator("+")
        }

        val btnMinus = findViewById<Button>(R.id.btnMinus)
        btnMinus?.setOnClickListener(){
            onOperator("-")
        }

        val btnClear = findViewById<Button>(R.id.btnClear)
        btnClear?.setOnClickListener(){
            onClear()
        }

        val btnEqual = findViewById<Button>(R.id.btnEqual)
        btnEqual?.setOnClickListener() {
            onEqual()
        }

        val btnDecimal = findViewById<Button>(R.id.btnDecimal)
        btnDecimal?.setOnClickListener() {
            onDecimalPoint()
        }
    }

    private fun onEqual(){
        if(lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-").toMutableList()
                    if(prefix.isNotEmpty()){
                        splitValue[0] = prefix + splitValue[0]
                    }
                    tvInput?.text = removeZeroAfterDot((splitValue[0].toDouble() - splitValue[1].toDouble()).toString())
                } else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+").toMutableList()
                    if(prefix.isNotEmpty()){
                        splitValue[0] = prefix + splitValue[0]
                    }
                    tvInput?.text = removeZeroAfterDot((splitValue[0].toDouble() + splitValue[1].toDouble()).toString())
                } else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*").toMutableList()
                    if(prefix.isNotEmpty()){
                        splitValue[0] = prefix + splitValue[0]
                    }
                    tvInput?.text = removeZeroAfterDot((splitValue[0].toDouble() * splitValue[1].toDouble()).toString())
                } else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/").toMutableList()
                    if(prefix.isNotEmpty()){
                        splitValue[0] = prefix + splitValue[0]
                    }
                    tvInput?.text = removeZeroAfterDot((splitValue[0].toDouble() / splitValue[1].toDouble()).toString())
                }

            }catch(e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)

        return value
    }

    private fun onDecimalPoint(){
        if(lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    private fun onClear() {
        tvInput?.text = ""
    }

    private fun onDigit(digit: Int){
        tvInput?.append(digit.toString())
        lastNumeric = true
        lastDot = false
    }

    private fun onOperator(operator: String){
//        this let basically states if tvInput isn't empty and text isn't empty then carry out functionality within.
//        it's a safety check to see if its empty
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append(operator)
                lastDot = false
                lastNumeric = false
            }
        }

    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}