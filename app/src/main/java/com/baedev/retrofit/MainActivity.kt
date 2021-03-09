package com.baedev.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baedev.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d(TAG, response.body()?.userId.toString())
                Log.d(TAG, response.body()?.id.toString())
                tvMessage.text = response.body()?.title
                Log.d(TAG, response.body()?.body!!)
            } else {
                Log.d(TAG, "error response ${response.errorBody().toString()}")
                tvMessage.text = response.code().toString()
            }
        })
    }
}