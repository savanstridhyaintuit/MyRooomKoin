package com.savan.myroomkoin.api.services

import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getTodos(): Response<List<Note>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotosItem>>
}