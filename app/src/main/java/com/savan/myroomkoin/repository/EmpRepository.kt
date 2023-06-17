package com.savan.myroomkoin.repository

import com.savan.myroomkoin.dao.EmployeeDao
import com.savan.myroomkoin.dao.PhotosDao
import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem

class EmpRepository constructor(private val dao: EmployeeDao, private val photosDao: PhotosDao) {
    fun insertEmployeeData(employee: Note) = dao.insertEmployee(employee)
    fun getEmployeeList() = dao.getEmployee()

    fun insertPhotos(photosItem: PhotosItem) = photosDao.insertPhotos(photosItem)
    fun getPhotosList() = photosDao.getPhotos()
}