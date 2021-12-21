package com.example.retrofitxmlparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private  val BASE_URL = "https://www.reddit.com/r/"
    private var feedsList = ArrayList<String>()
    lateinit var myRV: RecyclerView
    lateinit var rvAdapter: RVAdapter

    lateinit var btnFetch: Button
//    lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//    tvTitle = findViewById(R.id.tvTitle)
        myRV = findViewById(R.id.rvMain)

        btnFetch = findViewById(R.id.btnFetch)
        rvAdapter = RVAdapter(feedsList)
        myRV.adapter = rvAdapter
        myRV.layoutManager = LinearLayoutManager(this)


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
    val feedAPI = retrofit.create(FeedAPI::class.java)
    val call = feedAPI.feed

    btnFetch.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {

            call!!.enqueue(object : Callback<Feed?> {
                override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                    Log.d(TAG, "onResponse: feed: " + response.body().toString())
                    Log.d(TAG, "onResponse: Server Response: $response")
                    val entries = response.body()!!.entrys
                    for (entry in entries!!) {
                        feedsList.add(entry.title.toString())
                        Log.d(TAG, "onResponse: " + feedsList)
                        rvAdapter.notifyDataSetChanged()

//                        var text = tvTitle.text.toString()
//                        tvTitle.text = text + "\n" + entry.title
                    }
                }

                override fun onFailure(call: Call<Feed?>, t: Throwable) {
                    Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                    Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
                }
            })

        }

    })

}
}