package com.espanol.activity4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.espanol.activity4.databinding.ActivityMainLightModeBinding

class MainActivityLightMode : AppCompatActivity() {

    private lateinit var binding: ActivityMainLightModeBinding
    private val sharedPrefFile = "settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLightModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.emailAddressEditText.setText(sharedPreferences.getString("emailAddress", ""))
        binding.nicknameEditText.setText(sharedPreferences.getString("nickname", ""))
        binding.pushNotificationCheckBox.isChecked = sharedPreferences.getBoolean("pushNotifications", false)
        binding.lightModeRadioButton.isChecked = sharedPreferences.getBoolean("lightMode", true)
        binding.darkModeRadioButton.isChecked = sharedPreferences.getBoolean("darkMode", false)

        if (binding.darkModeRadioButton.isChecked){
            val intent = Intent(this, MainActivityDarkMode::class.java)
            startActivity(intent)
            finish()
        }

        binding.saveButton.setOnClickListener {
            with(sharedPreferences.edit()){
                putString("emailAddress", binding.emailAddressEditText.text.toString())
                putString("nickname", binding.nicknameEditText.text.toString())
                putBoolean("pushNotifications", binding.pushNotificationCheckBox.isChecked)
                putBoolean("lightMode", binding.lightModeRadioButton.isChecked)
                putBoolean("darkMode", binding.darkModeRadioButton.isChecked)
                apply()
            }
            Toast.makeText(this@MainActivityLightMode, "Saved", Toast.LENGTH_SHORT).show()
        }

    }
}