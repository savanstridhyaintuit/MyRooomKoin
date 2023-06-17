package com.savan.myroomkoin.repository

import com.savan.myroomkoin.api.services.ApiHelper

class MainRepository constructor(private val apihelper: ApiHelper) {

    suspend fun getTodos() = apihelper.getTodos()
    suspend fun getPhotos() = apihelper.getPhotos()

}
