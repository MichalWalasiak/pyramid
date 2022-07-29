package com.mwalasiak.pyramid

import android.content.Context
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mwalasiak.pyramid.databinding.ActivityLevelsViewBinding


class LevelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLevelsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.saveNumbers.setOnClickListener { saveNumbersToTmpList() }
        binding.add1Level.setOnClickListener { fillFirstLevel() }
        binding.add2Level.setOnClickListener { fillSecondLevel() }
        binding.add3Level.setOnClickListener { fillThirdLevel() }
        binding.add4Level.setOnClickListener { fillFourthLevel() }
    }

    private fun fillFirstLevel() {
        val firstLevelList: MutableList<String>
        val text = getList("tmpList", "tmpList").toString().replace(",", " ").removeSurrounding("[[", "]]")
        firstLevelList = listOf(text?.split(",").toString()).toMutableList()
        setList(firstLevelList, "1levelFile", "1-level-list")
        binding.firstLevel.text = getList("1levelFile", "1-level-list").toString().replace(",", " ").removeSurrounding("[[", "]]")
    }

    private fun fillSecondLevel() {
        val tmpList = compareLevels(getList("1levelFile", "1-level-list"), getList("tmpList", "tmpList"))
        //setList(tmpList, "2levelFile", "2-level-list")
        val text = tmpList.toString().replace(",", " ").removeSurrounding("[[", "]]")
        val secondLevelList = listOf(text?.split(",").toString()).toMutableList()
        setList(secondLevelList, "2levelFile", "2-level-list")
        binding.secondLevel.text = getList("2levelFile", "2-level-list").toString().replace(",", " ").removeSurrounding("[[[", "]]]")
    }

    private fun fillThirdLevel() {
        val list = getList("2levelFile", "2-level-listzaa`")
        val tmpList = compareLevels(getList("2levelFile", "2-level-list"), getList("tmpList", "tmpList"))
        //setList(tmpList, "3levelFile", "3-level-list")
        val text = tmpList.toString().replace(",", " ").removeSurrounding("[[", "]]")
        val thirdLevelList = listOf(text?.split(",").toString()).toMutableList()
        setList(thirdLevelList, "3levelFile", "3-level-list")
        binding.thirdLevel.text = getList("3levelFile", "3-level-list").toString().replace(",", " ").removeSurrounding("[[", "]]")
    }

    private fun fillFourthLevel() {
        val tmpList = compareLevels(getList("3levelFile", "3-level-list"), getList("tmpList", "tmpList"))
        //setList(tmpList, "4levelFile", "4-level-list")
        val text = tmpList.toString().replace(",", " ").removeSurrounding("[[", "]]")
        val fourthLevelList = listOf(text?.split(",").toString()).toMutableList()
        setList(fourthLevelList, "4levelFile", "4-level-list")
        binding.fourthLevel.text = getList("4levelFile", "4-level-list").toString().replace(",", " ").removeSurrounding("[[[", "]]]")
    }

    private fun saveNumbersToTmpList() {
        val tmpList: MutableList<String>
        val delim = ","
        val text = binding.enterNumber.text
        tmpList = listOf(text?.split(delim).toString()).toMutableList()
        setList(tmpList, "tmpList", "tmpList")

    }

    private fun setList(list: MutableList<String>?, sharedPrefFileName: String, listName: String) {
        var editor = setSharedPreferences(sharedPrefFileName).edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(listName, json)
        editor.commit()
    }

    private fun getList(sharedPrefFileName: String, listName: String): MutableList<String> {
        val gson = Gson()
        val json = setSharedPreferences(sharedPrefFileName).getString(listName, null)
        val type = object :TypeToken<MutableList<String>>(){}.type
        return gson.fromJson(json, type)
    }

    private fun compareLevels(listA: MutableList<String>, listB: MutableList<String>): MutableList<String>? {
        var finalList: MutableList<String>? = null
        val textA = listA.toString().removeSurrounding("[[", "]]")
        var resultA: MutableList<String> = textA.trim().split(" ").filter { it.isNotEmpty() }.toMutableList()
        finalList = resultA.toCollection(mutableListOf())

        val textB = listB.toString().replace(",", " ").removeSurrounding("[[", "]]")
        var resultB: MutableList<String> = textB.trim().split(" ").filter { it.isNotEmpty() }.toMutableList()

        for (i in resultA.indices){
            var tmpValue = resultA[i]
            for (j in resultB.indices){
                if (tmpValue == resultB[j]){
                    finalList.remove(tmpValue)
                    break
                }
            }
        }
        return finalList
    }

    private fun setSharedPreferences(name: String): SharedPreferences {
        return getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}
