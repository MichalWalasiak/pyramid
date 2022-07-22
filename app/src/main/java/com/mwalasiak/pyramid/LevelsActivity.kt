package com.mwalasiak.pyramid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mwalasiak.pyramid.databinding.ActivityLevelsViewBinding

class LevelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLevelsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addNumber.setOnClickListener { readNumbersFromEditText() }
        binding.seeLevel.setOnClickListener { showNumbersFromLevel(R.id.firstLevel) }
    }

    private fun readNumbersFromEditText() {
        val latestDraw: MutableList<String>
        val delim = ","
        val text = binding.enterNumber.text
        writeToSharedPreferences(R.id.firstLevel, text.toString())
        val readed = readFromSharedPreferences(R.id.firstLevel)
        latestDraw = listOf(readed?.split(delim).toString()).toMutableList()
        binding.firstLevel.text = latestDraw.toString().replace(",", " ").removeSurrounding("[[", "]]")
    }

    private fun writeToSharedPreferences(fileKey: Int, text: String) {
        val sharedPref = getSharedPreferences("level", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(getString(fileKey), text)
            apply()
        }


    }

    private fun readFromSharedPreferences(fileKey: Int): String? {
        val sharedPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        return sharedPref.getString(getString(fileKey), null)
    }

    private fun showNumbersFromLevel(level: Int) {
        val readed = readFromSharedPreferences(level)
        val latestDraw = listOf(readed?.split(","))
        binding.firstLevel.text = latestDraw.toString().replace(",", "//").removeSurrounding("[[", "]]")

    }

    private fun compareLevels() {

    }
}
