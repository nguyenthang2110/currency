package com.example.currency

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var b1: Button
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var sp1: Spinner
    private lateinit var sp2: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        sp1 = findViewById(R.id.sp1)
        sp2 = findViewById(R.id.sp2)
        setupCustomSpinner()
        b1 = findViewById(R.id.button)
        ed1 = findViewById(R.id.editTextNumber)
        ed2 = findViewById(R.id.editTextNumber2)

        b1.setOnClickListener{convertCurrency()}

    }

    private fun setupCustomSpinner() {
        //khai bao list quoc gia
        val list2=mutableListOf<Outdata>()
        list2.add(Outdata(R.drawable.usa,"USD"))
        list2.add(Outdata(R.drawable.china,"CNY"))
        list2.add(Outdata(R.drawable.russia,"RUB"))
        list2.add(Outdata(R.drawable.korea,"WON"))
        list2.add(Outdata(R.drawable.vietnam,"VND"))

        val customSpinner=CustomSpinner(this,list2)

        sp1.adapter = customSpinner //gan adapter cho spinner
        sp2.adapter = customSpinner //gan adapter cho spinner

    }

    private fun convertCurrency() {
        val fromSp = sp1.selectedItem as Outdata
        val fromCurrency = fromSp.des
        val toSP = sp2.selectedItem as Outdata
        val toCurrency = toSP.des
        if (fromSp == null || toSP == null) {
            // Thông báo người dùng rằng cần chọn giá trị cho cả hai spinner
            Toast.makeText(this, "Please select both currencies.", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("CurrencyConversion", "From: $fromCurrency To: $toCurrency")
        if (ed1.isFocused) {
            val input = ed1.text.toString().toDoubleOrNull() ?: return
            val rate = when {
                fromCurrency == "USD" && toCurrency == "VND" -> 24000.0
                fromCurrency == "VND" && toCurrency == "USD" -> 1 / 24000.0
                else -> 1.0
            }
            val output = input * rate
            ed2.setText(output.toString())
        } else if (ed2.isFocused) {
            val input = ed2.text.toString().toDoubleOrNull() ?: return

            val rate = when {
                toCurrency == "USD" && fromCurrency == "VND" -> 1 / 24000.0
                toCurrency == "VND" && fromCurrency == "USD" -> 24000.0
                else -> 1.0
            }
            val output = input * rate
            ed1.setText(output.toString())
        }
    }

}
