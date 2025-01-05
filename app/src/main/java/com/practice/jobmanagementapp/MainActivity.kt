package com.practice.jobmanagementapp

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.jobmanagementapp.adapter.CategoryAdapter
import com.practice.jobmanagementapp.adapter.JobAdapter
import com.practice.jobmanagementapp.databinding.ActivityMainBinding
import com.practice.jobmanagementapp.model.JobModel
import com.practice.jobmanagementapp.viewModel.MainViewModel
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    //    private val listener:ClickListener
    private lateinit var adapter: CategoryAdapter
    private lateinit var jobAdapter: JobAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initLocation()
        initAdapter()
        initSuggestion()
        init()
        initRecent("0")
    }

    private fun initRecent(cat: String) {
        var data: List<JobModel>
        data = if (cat == "0") {
            mainViewModel.getJobs().sortedBy { it.category }
        } else {
            mainViewModel.getJobs().filter { it.category == cat }
        }
        binding.recyclerViewRecent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewRecent.adapter = JobAdapter(data)
    }

    private fun initSuggestion() {
        binding.progressBarSuggest.visibility = View.VISIBLE
        binding.recyclerViewSuggest.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSuggest.adapter = jobAdapter
        binding.progressBarSuggest.visibility = View.GONE
    }

    private fun initAdapter() {
        val categoryData = mainViewModel.getCategories()
        adapter = CategoryAdapter(categoryData, this)
        val jobData = mainViewModel.getJobs()
        jobAdapter = JobAdapter(jobData)
    }

    private fun init() {
        binding.progressBarCategory.visibility = View.VISIBLE
        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategory.adapter = adapter
        binding.progressBarCategory.visibility = View.GONE

    }

    private fun initLocation() {
        val adapter = ArrayAdapter(this, R.layout.location_item, mainViewModel.loadLocation())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.LocationSp.adapter = adapter
    }

    override fun OnClick(category: String) {
        initRecent(category)
    }
}