package com.mwalasiak.pyramid

import android.content.Context
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.mwalasiak.pyramid.databinding.ActivityLevelsViewBinding


class LevelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLevelsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.saveNumbers.setOnClickListener { saveNumbersToTmpList() }
        binding.addLevels.setOnClickListener { fillAllLevels() }

    }

    private fun fillAllLevels() {
        fillTenthLevel()
        fillNinthLevel()
        fillEighthLevel()
        fillSeventhLevel()
        fillSixthLevel()
        fillFifthLevel()
        fillFourthLevel()
        fillThirdLevel()
        fillSecondLevel()
        fillFirstLevel()
    }

    private fun fillFirstLevel() {
        if (verifySharedPref(TMP_LIST) && getTextView(binding.enterNumber.id).text.isNotEmpty()) {
            val text = getList(TMP_LIST)
            setList(text, FIRST_LEVEL_KEY)
            binding.firstLevel.text = getList(FIRST_LEVEL_KEY)?.replace(",", " ")
            binding.enterNumber.clear()
            binding.addLevels.isEnabled = false
        } else
            binding.firstLevel.text = EMPTY_LIST_TEXT

    }

    private fun fillSecondLevel() {
        fillLevels(FIRST_LEVEL_KEY, SECOND_LEVEL_KEY, binding.secondLevel.id)
    }

    private fun fillThirdLevel() {
        fillLevels(SECOND_LEVEL_KEY, THIRD_LEVEL_KEY, binding.thirdLevel.id)
    }

    private fun fillFourthLevel() {
        fillLevels(THIRD_LEVEL_KEY, FOURTH_LEVEL_KEY, binding.fourthLevel.id)
    }

    private fun fillFifthLevel() {
        fillLevels(FOURTH_LEVEL_KEY, FIFTH_LEVEL_KEY, binding.fifthLevel.id)
    }

    private fun fillSixthLevel() {
        fillLevels(FIFTH_LEVEL_KEY, SIXTH_LEVEL_KEY, binding.sixthLevel.id)
    }

    private fun fillSeventhLevel() {
        fillLevels(SIXTH_LEVEL_KEY, SEVENTH_LEVEL_KEY, binding.seventhLevel.id)
    }

    private fun fillEighthLevel() {
        fillLevels(SEVENTH_LEVEL_KEY, EIGHTH_LEVEL_KEY, binding.eighthLevel.id)
    }

    private fun fillNinthLevel() {
        fillLevels(EIGHTH_LEVEL_KEY, NINTH_LEVEL_KEY, binding.ninethLevel.id)
    }

    private fun fillTenthLevel() {
        fillLevels(NINTH_LEVEL_KEY, TENTH_LEVEL_KEY, binding.tenthLevel.id)
    }

    private fun fillLevels(previousLevel:String, currentLevel:String, id: Int) {
        if (verifySharedPref(previousLevel) && getTextView(binding.enterNumber.id).text.isNotEmpty()){
            val tmpList = compareLevels(getList(previousLevel), getList(TMP_LIST))
            setList(tmpList, currentLevel)
            getTextView(id).text = getList(currentLevel)?.replace(",", " ")
            binding.saveNumbers.isEnabled = true
            binding.addLevels.isEnabled = false
        }else
            getTextView(id).text = EMPTY_LIST_TEXT

    }

    private fun getTextView(id: Int) = binding.root.findViewById<TextView>(id)

    private fun saveNumbersToTmpList() {
        val textFromInput = binding.enterNumber.text.toString()
        if (textFromInput.isNotEmpty()){
            setList(textFromInput, TMP_LIST)
            binding.saveNumbers.isEnabled = false
            binding.addLevels.isEnabled = true
        }else
            setToastMessage()
    }

    private fun setList(stringWithNumbers: String?, listKey: String) {
        val editor = setSharedPreferences().edit()
        editor.putString(listKey, stringWithNumbers)
        editor.apply()
    }

    private fun getList(listName: String): String? {
        return setSharedPreferences().getString(listName, null)
    }

    private fun compareLevels(
        listA: String?,
        listB: String?,
    ): String {

        val level = listA?.split(",")?.map { it.toInt() }?.toMutableList()
        val currentDraw = listB?.split(",")?.map { it.toInt() }?.toList()
        val copyOfLevel = level?.toMutableList()

        for (i in level!!.indices) {
            val tmpValue = level[i]
            for (j in currentDraw!!.indices) {
                if (tmpValue == currentDraw[j]) {
                    copyOfLevel?.remove(tmpValue)
                    break
                }
            }
        }

        return copyOfLevel.toString().replace(" ", "").removeSurrounding("[", "]")
    }

    private fun setSharedPreferences(): SharedPreferences {
        return getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    private fun verifySharedPref(key: String): Boolean {
        val sharedValue = setSharedPreferences()
        val prefKeyName = sharedValue.getString(key, null)
        return prefKeyName != null
    }

    private fun setToastMessage() {
        val emptySaveNumbersInput = Toast.makeText(applicationContext, NO_NUMBERS_INPUT, Toast.LENGTH_SHORT)
        emptySaveNumbersInput.show()
    }

    private fun EditText.clear() {text.clear()}

    companion object {
        const val FILE_NAME = "Levels"
        const val TMP_LIST = "tmp_list"
        const val FIRST_LEVEL_KEY = "first-level"
        const val SECOND_LEVEL_KEY = "second-level"
        const val THIRD_LEVEL_KEY = "third-level"
        const val FOURTH_LEVEL_KEY = "fourth-level"
        const val FIFTH_LEVEL_KEY = "fifth-level"
        const val SIXTH_LEVEL_KEY = "sixth-level"
        const val SEVENTH_LEVEL_KEY = "seventh-level"
        const val EIGHTH_LEVEL_KEY = "eighth-level"
        const val NINTH_LEVEL_KEY = "ninth-level"
        const val TENTH_LEVEL_KEY = "tenth-level"
        const val EMPTY_LIST_TEXT = "all numbers were already drowned"
        const val NO_NUMBERS_INPUT = "please add numbers"
    }
}
