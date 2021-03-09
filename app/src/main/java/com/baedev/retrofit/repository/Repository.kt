package com.baedev.retrofit.repository

import com.baedev.retrofit.api.RetrofitInstance
import com.baedev.retrofit.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

}