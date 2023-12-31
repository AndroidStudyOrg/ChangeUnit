package com.example.chageunit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.chageunit.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber: Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tvOutput = binding.tvOutput
        val tvChanged = binding.tvChanged
        val etInput = binding.etInput
        val tvChanging = binding.tvChanging
        val btnSwap = binding.imgbtnSwap

        etInput.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }
            Log.d("inputNumber", inputNumber.toString())

            if (cmToM) {
                tvOutput.text = inputNumber.times(0.01).toString()
            } else {
                tvOutput.text = inputNumber.times(100).toString()
            }
        }

        btnSwap.setOnClickListener {
            cmToM = cmToM.not()
            if (cmToM) {
                tvChanging.text = "cm"
                tvChanged.text = "m"
                tvOutput.text = inputNumber.times(0.01).toString()
            } else {
                tvChanging.text = "m"
                tvChanged.text = "cm"
                tvOutput.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.tvChanging.text = if (cmToM) "cm" else "m"
        binding.tvChanged.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}