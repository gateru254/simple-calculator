package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder


lateinit var one: Button
lateinit var two:Button
lateinit var three: Button
lateinit var four:Button
lateinit var five:Button
lateinit var six:Button
lateinit var seven:Button
lateinit var eight:Button
lateinit var nine:Button
lateinit var zero:Button
lateinit var addition:Button
lateinit var subtraction:Button
lateinit var multiplication:Button
lateinit var division:Button
lateinit var equals:Button
lateinit var backspace:Button
lateinit var output: TextView
lateinit var input: TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one=findViewById(R.id.one)
        one.setOnClickListener { inputDigit(one) }

        two=findViewById(R.id.two)
        two.setOnClickListener { inputDigit(two) }
        three=findViewById(R.id.Three)
        three.setOnClickListener { inputDigit(three) }

        four=findViewById(R.id.Four)
        four.setOnClickListener { inputDigit(four) }
        five=findViewById(R.id.five)
        five.setOnClickListener { inputDigit(five) }

        six=findViewById(R.id.six)
        six.setOnClickListener { inputDigit(six) }

        seven=findViewById(R.id.seven)
        seven.setOnClickListener { inputDigit(seven) }

        eight=findViewById(R.id.eight)
        eight.setOnClickListener { inputDigit(eight) }

        nine=findViewById(R.id.nine)
        nine.setOnClickListener { inputDigit(nine) }

        zero=findViewById(R.id.zero)
        zero.setOnClickListener { inputDigit(zero) }


        division=findViewById(R.id.division)
        division.setOnClickListener { inputOperator(division) }
        multiplication=findViewById(R.id.multiplication)
        multiplication.setOnClickListener { inputOperator(multiplication) }
        addition=findViewById(R.id.addition)
        addition.setOnClickListener { inputOperator(addition) }
        subtraction=findViewById(R.id.subtraction)
        subtraction.setOnClickListener { inputOperator(subtraction) }



        equals=findViewById(R.id.equals)
        equals.setOnClickListener { calculate() }

        backspace=findViewById(R.id.back_space)
        backspace.setOnClickListener{
        val text= input.text.toString()
        if (text.isNotEmpty()){
            input.text.dropLast (1)
        }
            output.text=""
        }



        input=findViewById(R.id.input)
        output=findViewById(R.id.output)
    }

    private fun calculate(){
        val inputText =input.text.toString()
        val expression =ExpressionBuilder(inputText).build()

        if(input.text.last().isDigit()){
            try{
                val result =expression.evaluate()
                output.text =result.toString()
            }catch (e:ArithmeticException){
                output.text="error"
            }
        }
    }

    private fun inputDigit(view: View){
        when{
            output.text !=""->{
                input.text =""
                output.text =""
                input.text = (view as Button).text
            }
            input.text==""->{
                input.text=(view as Button).text
            }
            else ->{
                input.append((view as Button).text)
            }
        }
    }
    private fun inputOperator(view:View){
        when{
            input.text !=""&& !input.text.last().isDigit() ->{
                input.text.drop(1)
            }
            output.text ==""&&input.text !=""->{
                input.append((view as Button).text)
            }
            output.text!=""&&input.text!=""->{
                input.text =output.text
                output.text=""
                input.append((view as Button).text)
            }
            else->
                input.text=""
        }
    }
}
