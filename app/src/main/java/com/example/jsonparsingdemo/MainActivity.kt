package com.example.jsonparsingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var personNames = ArrayList<String>()
    private var emailIds = ArrayList<String>()
    private var mobileNumbers = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        try{
            val obj = JSONObject(loadJsonFromAsset())
            val userArray = obj.getJSONArray("users")

            for(i in 0 until userArray.length()){
                val userDetail = userArray.getJSONObject(i)
                personNames.add(userDetail.getString("name"))
                emailIds.add(userDetail.getString("email"))
                mobileNumbers.add(userDetail.getJSONObject("contact").getString("mobile1"))
            }
        }
        catch(e:JSONException){
            e.printStackTrace()
        }

        val recyclerAdapter = RecyclerViewAdapter(personNames,emailIds,mobileNumbers)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter=recyclerAdapter
    }

    fun loadJsonFromAsset():String?{
        var json:String? = null
        val charset = Charsets.UTF_8
        try {
            val `is` = assets.open("example.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer,charset)
        }
        catch (ex:IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }
}