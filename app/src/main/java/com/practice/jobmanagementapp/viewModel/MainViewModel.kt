package com.practice.jobmanagementapp.viewModel

import androidx.lifecycle.ViewModel
import com.practice.jobmanagementapp.repository.MainRepository

class MainViewModel(val repository: MainRepository) : ViewModel() {
    constructor() : this(MainRepository())

    fun loadLocation() =
        repository.location
    fun getCategories() = repository.categories

    fun getJobs() = repository.items
}