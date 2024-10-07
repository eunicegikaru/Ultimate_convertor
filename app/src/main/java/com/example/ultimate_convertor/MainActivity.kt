package com.example.ultimate_convertor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this is the user interface design....it declares the input space spinner(drop down menu) and the place where the output value will be placed
        val inputNumber = findViewById<EditText>(R.id.inputNumber)
        val convertFrom = findViewById<Spinner>(R.id.convertFrom)
        val convertTo = findViewById<Spinner>(R.id.convertTo)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val outputText = findViewById<TextView>(R.id.outputText)

        // This piece of code is for checking if the convert button on the user interface has been clicked, it also ensures that a valid input has been placed in the input space
        convertButton.setOnClickListener {
            val numberStr = inputNumber.text.toString()
            val fromSystem = convertFrom.selectedItem.toString()
            val toSystem = convertTo.selectedItem.toString()

            if (isValidInput(numberStr, fromSystem)) {
                val result = convertNumber(numberStr, fromSystem, toSystem)
                outputText.text = "Output: $result"
            } else {
                Toast.makeText(this, "Invalid input for $fromSystem system", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // This is a function that declares what kind of input is expected by each number system... and the type of characters expected for each number system.
    private fun isValidInput(numberStr: String, fromSystem: String): Boolean {
        return when (fromSystem) {
            "HEX" -> numberStr.matches(Regex("^[0-9A-Fa-f]+$"))
            "OCT" -> numberStr.matches(Regex("^[0-7]+$"))
            "DEC" -> numberStr.matches(Regex("^[0-9]+$"))
            "BIN" -> numberStr.matches(Regex("^[01]+$"))
            else -> false
        }
    }

    // This is a function to convert between the different number systems. we use long because we need to store large integer values.
    private fun convertNumber(numberStr: String, fromSystem: String, toSystem: String): String {
        val decimalValue = when (fromSystem) {
            "HEX" -> numberStr.toLong(16)
            "OCT" -> numberStr.toLong(8)
            "DEC" -> numberStr.toLong(10)
            "BIN" -> numberStr.toLong(2)
            else -> 0
        }

        return when (toSystem) {
            "HEX" -> decimalValue.toString(16).uppercase()
            "OCT" -> decimalValue.toString(8)
            "DEC" -> decimalValue.toString(10)
            "BIN" -> decimalValue.toString(2)
            else -> ""
        }
    }
}