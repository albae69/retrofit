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

        button.setOnClickListener {
            val number = etNumber.text.toString()
            viewModel.getCustomPost(Integer.parseInt(number), "id", "desc")
            viewModel.myCustomPosts.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    tvMessage.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d(TAG, it.userId.toString())
                        Log.d(TAG, it.id.toString())
                        Log.d(TAG, it.title)
                        Log.d(TAG, it.body)
                        Log.d(TAG, "===============")
                    }
                } else {
                    tvMessage.text = response.code().toString()

                }

            })
        }

    }
}