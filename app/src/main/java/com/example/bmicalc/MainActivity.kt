package com.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight=findViewById<EditText>(R.id.etWeightEnter)
        val height=findViewById<EditText>(R.id.etHeightEnter)
        val calcButton=findViewById<Button>(R.id.button)

        calcButton.setOnClickListener{
            val Weight=weight.text.toString()
            val Height=height.text.toString()
            if (validateInput(Weight,Height)){
                val bmi=Weight.toFloat()/((Height.toFloat()/100 )*(Height.toFloat()/100))
                val bmi2Digit=String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Digit)
            }
        }
    }
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }
    }
    private fun displayResult(bmi:Float){
        val resultIndex=findViewById<TextView>(R.id.answer)
        val resultDescription=findViewById<TextView>(R.id.tvInfo)
        val range=findViewById<TextView>(R.id.range)
        resultIndex.text=bmi.toString()
        range.text="(Normal range is 18.5 - 24.9)"
        var resultText=""
        var color=0

        when{
            bmi<18.50 -> {
                resultText="UnderWeight"
                color=R.color.UnderWeight
            }
            bmi in 18.50..24.99 ->{
                resultText="Normal"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="OverWeight"
                color=R.color.overWeight
            }
            bmi>29.99 -> {
                resultText="Obese"
                color=R.color.Obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText
    }
}