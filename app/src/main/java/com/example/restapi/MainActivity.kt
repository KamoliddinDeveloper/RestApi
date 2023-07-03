package com.example.restapi

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restapi.adapters.TodoAllAdapter
import com.example.restapi.databinding.ActivityMainBinding
import com.example.restapi.databinding.ItemDialogBinding
import com.example.restapi.models.MyPostTodoReuest
import com.example.restapi.repozitore.TodoReposirory
import com.example.restapi.retrofit.ApiClient
import com.example.restapi.retrofit.ApiService
import com.example.restapi.utils.Status
import com.example.restapi.viewModel.MyViewModelFactory
import com.example.restapi.viewModel.TodoViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var todoViewModel: TodoViewModel
    private val TAG = "MainActivity"
    private lateinit var todoAllAdapter: TodoAllAdapter
    private lateinit var todoReposirory: TodoReposirory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        todoReposirory = TodoReposirory(ApiClient.getApiService())
        todoViewModel = ViewModelProvider(this, MyViewModelFactory(todoReposirory)).get(TodoViewModel::class.java)
        todoAllAdapter = TodoAllAdapter()
        binding.rv.adapter = todoAllAdapter

        todoViewModel.getAllTodo()
            .observe(this) {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "onCreate: Loading")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "onCreate: Error  ${it.message}")
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCSESS -> {
                        Log.d(TAG, "onCreate: ${it.data}")
                        todoAllAdapter.list = it?.data!!
                        todoAllAdapter.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(itemDialogBinding.root)

            itemDialogBinding.apply {

                save.setOnClickListener {
                    val myPostRequest = MyPostTodoReuest(
                        edtTitle.text.toString().trim(),
                        edtAbout.text.toString().trim(),
                        edtDetline.text.toString().trim(),
                        spinnerStatus.selectedItem.toString().trim(),
                        true
                    )

                    todoViewModel.addMyTodo(myPostRequest).observe(this@MainActivity) {
                        when (it.status) {
                            Status.LOADING -> {
                                progresPost.visibility = View.VISIBLE
                                linerDialog.isEnabled = false
                            }
                            Status.ERROR -> {
                                Toast.makeText(
                                    this@MainActivity, "Xatolik ${it.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progresPost.visibility = View.GONE
                                linerDialog.isEnabled = true
                            }
                            Status.SUCCSESS -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "${it.data?.sarlavha} ${it.data?.id} ga saqlandi",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.cancel()
                            }
                        }
                    }
                }
                dialog.show()
            }
        }
    }
}