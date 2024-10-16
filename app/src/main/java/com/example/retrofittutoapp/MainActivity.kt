package com.example.retrofittutoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittutoapp.databinding.ActivityMainBinding
import okio.IOException
import retrofit2.HttpException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            }catch(e: IOException){
                Log.e(TAG, "IO exception: no internet")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "Http exception: unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated //Da fuck does it mean
            }
            if(response.isSuccessful && response.body() != null){
                todoAdapter.todos = response.body()!!
            }else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }

    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter() //Here: to review ()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}