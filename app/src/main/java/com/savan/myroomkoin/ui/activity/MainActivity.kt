package com.savan.myroomkoin.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.savan.myroomkoin.component.EmpComponent
import com.savan.myroomkoin.databinding.ActivityMainBinding
import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem
import com.savan.myroomkoin.ui.adapter.NoteAdapter
import com.savan.myroomkoin.utils.Status
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private var koinComponent: EmpComponent = EmpComponent()
    private var adapter: NoteAdapter = NoteAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        if (koinComponent.isNetworkHelper.isNetworkConnected()) {

//            activityMainBinding.noteRecyclerview.layoutManager = LinearLayoutManager(this)

            activityMainBinding.noteRecyclerview.layoutManager = LinearLayoutManager(this)
            activityMainBinding.noteRecyclerview.adapter =adapter
            setupObserver()
            setupPhotoObserver()
        } else {
            koinComponent.empViewModel.usersData.observe(this) {
                it?.let { notes -> renderList(notes) }
            }
            getupPhotosLocal()
        }


    }

    private fun getupPhotosLocal() {
        koinComponent.empViewModel.usersPhotos.observe(this) {
            it?.let { photo -> renderPhotos(photos = photo) }
        }

        koinComponent.empViewModel.usersData.observe(this, Observer {
            it?.let { notes -> renderList(notes)  }
        })
    }

    private fun setupPhotoObserver() {
        koinComponent.mainViewModel.usersPhotos.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    it.data?.let { photos ->
                        renderPhotos(photos = photos)
                        Log.d("#PHOTOS", Gson().toJson(it.data))
                    }


//                    binding.recyclerView.visibility = View.VISIBLE
                }

                Status.ERROR -> {
//                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.recyclerView.visibility = View.GONE
                }
            }
        })
    }

    private fun renderPhotos(photos: List<PhotosItem>) {

        for (photo in photos) {
            koinComponent.empViewModel.addPhotos(
                PhotosItem(
                    photo.albumId,
                    photo.id,
                    photo.title,
                    photo.url,
                    photo.thumbnailUrl
                )
            )
        }
    }


    private fun setupObserver() {
        koinComponent.mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    it.data?.let { todos ->


                        renderList(todos)


                    }
                    Log.d("##Response", Gson().toJson(it.data))


//                    binding.recyclerView.visibility = View.VISIBLE
                }

                Status.ERROR -> {
//                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.recyclerView.visibility = View.GONE
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<Note>) {
        for (note in users) {
            koinComponent.empViewModel.addEmployee(
                Note(note.id, note.userId, note.title)
            )
        }
        adapter.setNoteList(users)

    }

}