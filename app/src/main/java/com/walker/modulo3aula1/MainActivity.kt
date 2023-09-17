package com.walker.modulo3aula1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        sharedPreferences = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)

        applySavedTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_theme, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_theme_light -> {
                applyTheme(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
            R.id.menu_theme_dark -> {
                applyTheme(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun applyTheme(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(themeMode)
    }

    private fun saveTheme(themeMode: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("theme_mode", themeMode)
        editor.apply()
    }

    private fun applySavedTheme() {
        val savedThemeMode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_NO)
        applyTheme(savedThemeMode)
    }
}