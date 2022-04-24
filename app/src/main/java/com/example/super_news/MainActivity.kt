package com.example.super_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i))
        }






        val apiInterface = ApiInterface.create().getNews("e80966f3ad4f4dd098dcdd4740ac0180")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Top_business_headlines>> {
            override fun  onResponse(call: Call<List<Top_business_headlines>>?, response: Response<List<Top_business_headlines>>?) {

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body()?.articles)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

               // if(response?.body() != null)
                //    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Top_business_headlines>>?, t: Throwable?) {

            }
        })

    }
}
