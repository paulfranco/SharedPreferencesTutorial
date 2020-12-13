package co.paulfran.sharedpreferencestutorial

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.paulfran.sharedpreferencestutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.btnSave.setOnClickListener {
            // References to our data
            val name = binding.edtName.text.toString()
            val age = binding.edtAge.text.toString().toInt()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", isAdult)

                apply()
            }
        }

        binding.btnLoad.setOnClickListener {
            // Retrieve Data
            val name = sharedPref.getString("name", "Paul")
            val age = sharedPref.getInt("age", 30)
            val isAdult = sharedPref.getBoolean("isAdult", true)

            // Set values to views
            binding.edtName.setText(name)
            binding.edtAge.setText(age.toString())
            binding.cbAdult.isChecked = isAdult
        }
    }
}