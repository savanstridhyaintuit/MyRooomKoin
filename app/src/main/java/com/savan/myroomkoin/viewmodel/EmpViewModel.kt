package com.savan.myroomkoin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import com.savan.myroomkoin.repository.EmpRepository
import com.savan.myroomkoin.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EmpViewModel constructor(private val empRepository: EmpRepository) : ViewModel() {

    private val _users = MutableLiveData<List<Note>>()
    val usersData: LiveData<List<Note>>
        get() = _users

    private val _photos = MutableLiveData<List<PhotosItem>>()
    val usersPhotos: LiveData<List<PhotosItem>>
        get() = _photos


    fun addEmployee(employee: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            empRepository.insertEmployeeData(employee)
        }
    }

    fun addPhotos(photos: PhotosItem){
        viewModelScope.launch {
            empRepository.insertPhotos(photos)
        }
    }

    fun getEmployee(){
        viewModelScope.launch(Dispatchers.IO) {
            _users.postValue(empRepository.getEmployeeList())
        }


    }

}