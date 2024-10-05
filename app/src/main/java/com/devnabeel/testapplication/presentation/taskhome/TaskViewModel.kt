package com.devnabeel.testapplication.presentation.taskhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnabeel.testapplication.domain.model.Task
import com.devnabeel.testapplication.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val getTasksUseCase: GetTasksUseCase) : ViewModel() {


    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    init {
        _isLoading.value=true
        _tasks.value= emptyList()
        _errorMessage.value=""
        loadTasks()
    }



    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val tasks = getTasksUseCase.execute()
                _tasks.value = tasks ?: emptyList()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}