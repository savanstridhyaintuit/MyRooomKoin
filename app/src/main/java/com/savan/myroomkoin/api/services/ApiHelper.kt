package com.savan.myroomkoin.api.services

import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import retrofit2.Response

interface ApiHelper {

    suspend fun getTodos(): Response<List<Note>>
    suspend fun getPhotos():Response<List<PhotosItem>>
}