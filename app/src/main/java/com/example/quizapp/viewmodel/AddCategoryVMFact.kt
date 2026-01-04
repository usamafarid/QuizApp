package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuizRepository

class AddCategoryVMFact( val repository: QuizRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddCategoryVM(repository) as T
    }
}