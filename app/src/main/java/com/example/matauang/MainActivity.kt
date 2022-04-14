package com.example.matauang

import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.matauang.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{ hitung() }

        binding.editText.setOnKeyListener{ view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )}
    }

    private fun hitung(){
        val Input = binding.editText.text.toString()
        val uangInput = Input.toDoubleOrNull()

        if (uangInput == null){
            binding.textView.text = ""
            return
        }

        val pilihanUser = when (binding.radioGroup.checkedRadioButtonId){
            R.id.euro -> 15617
            R.id.dollar -> 14355
            R.id.yen -> 114
            else -> 3827
        }

        var cetakHasil =  uangInput * pilihanUser

        val indonesianLocale = Locale("in", "ID")
        val hasil_format =
            NumberFormat.getCurrencyInstance(indonesianLocale).format(cetakHasil)

        binding.textView.text = getString(R.string.hasil,hasil_format)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}