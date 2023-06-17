package com.savan.myroomkoin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import com.savan.myroomkoin.repository.MainRepository
import com.savan.myroomkoin.utils.NetworkHelper
import com.savan.myroomkoin.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val networkHelper: NetworkHelper,
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _users = MutableLiveData<Resource<List<Note>>>()
    val users: LiveData<Resource<List<Note>>>
        get() = _users


    private val _usersPhotos = MutableLiveData<Resource<List<PhotosItem>>>()
    val usersPhotos: LiveData<Resource<List<PhotosItem>>>
        get() = _usersPhotos

    init {

        fetchTodos()
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            _usersPhotos.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()) {

                mainRepository.getPhotos().let {
                    if (it.isSuccessful) {

                        _usersPhotos.postValue(Resource.success(it.body()))
                    } else _usersPhotos.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }else _usersPhotos.postValue(Resource.error("NO Internet Connection", null))
        }
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getTodos().let {
                    if (it.isSuccessful) {

                        Log.d("##Data", it.body().toString())
                        _users.postValue(Resource.success(it.body()))

                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("NO Internet Connection", null))
        }
    }

}
