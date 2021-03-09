package com.baedev.retrofit.api

import com.baedev.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {
    @GET("/posts/1")
    suspend fun getPost(): Response<Post>

    @GET("/posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
    ): Response<List<Post>>


    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>

}