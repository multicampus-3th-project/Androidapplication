package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val imageList = mutableListOf<ImageDocument>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageListView.adapter = ImageListAdapter(imageList, ::onItemClick)
        imageListView.layoutManager = GridLayoutManager(this, 3)
        btnSearch.setOnClickListener {
            val keyword = editKeyword.text.toString()
            searchImage(keyword)
            editKeyword.setText("")
        }
    }
    private fun searchImage(keyword: String) {
        KakaoImageSearch.getService()
                .requestSearchImage(keyword = keyword, page = 1)
                .enqueue(object : Callback<Image> {
                    override fun onFailure(call: Call<Image>, t: Throwable) {
                        Log.e("----", t.toString())
                    }
                    override fun onResponse(call: Call<Image>,
                                            response: Response<Image>) {
                        if (response.isSuccessful) {
                            val image = response.body()
                            imageList.addAll(image!!.documents)
                            imageListView.adapter?.notifyDataSetChanged()
                        }
                    }
                })
    }
    fun onItemClick(doc: ImageDocument) {
    }
}