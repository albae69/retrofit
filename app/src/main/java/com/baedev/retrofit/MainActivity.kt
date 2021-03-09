package com.baedev.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baedev.retrofit.adapter.MyAdapter
import com.baedev.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCustomPost(2, "id", "desc")
        viewModel.myCustomPosts.observe(this, Observer { response ->

            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(this, "$response.code()", Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun setupRecyclerView() {
        rvMain.adapter = myAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}