package com.espanol.activity4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.espanol.activity4.databinding.ActivityMainDarkModeBinding
import com.espanol.activity4.databinding.ActivityMainLightModeBinding

class MainActivityDarkMode : AppCompatActivity() {

    private lateinit var binding: ActivityMainDarkModeBinding
    private val sharedPrefFile = "settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.emailAddressEditText.setText(sharedPreferences.getString("emailAddress", ""))
        binding.nicknameEditText.setText(sharedPreferences.getString("nickname", ""))
        binding.pushNotificationCheckBox.isChecked = sharedPreferences.getBoolean("pushNotifications", false)
        binding.lightModeRadioButton.isChecked = sharedPreferences.getBoolean("lightMode", false)
        binding.darkModeRadioButton.isChecked = sharedPreferences.getBoolean("darkMode", true)

        binding.saveButton.setOnClickListener {
            with(sharedPreferences.edit()){
                putString("emailAddress", binding.emailAddressEditText.text.toString())
                putString("nickname", binding.nicknameEditText.text.toString())
                putBoolean("pushNotifications", binding.pushNotificationCheckBox.isChecked)
                putBoolean("lightMode", binding.lightModeRadioButton.isChecked)
                putBoolean("darkMode", binding.darkModeRadioButton.isChecked)
                apply()
            }
            Toast.makeText(this@MainActivityDarkMode, "Saved", Toast.LENGTH_SHORT).show()
        }
    }
}