package com.savan.myroomkoin.api.services

import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import retrofit2.Response

class Api_Impl constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getTodos(): Response<List<Note>> = apiService.getTodos()
    override suspend fun getPhotos(): Response<List<PhotosItem>> =apiService.getPhotos()
}