package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddQuestionVM(val repository: QuizRepository) : ViewModel() {

    //changing suggest gemini
    val categories: LiveData< List<CategoryModel>> = repository.getCategory()

    // This is the private mutable LiveData that only the ViewModel can modify.
    //2
//    private val _categories = MutableLiveData<List<CategoryModel>>()

    // This is the public, un-modifiable LiveData that the UI will observe.
    //3
//    val categories: LiveData<List<CategoryModel>>
//        get() = _categories

    /**
     * The init block is called automatically when the ViewModel is first created.
     * We start loading the categories immediately.
     */
    //1
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = repository.getCategory()
//            _categories.postValue(result.value)
//        }
//    }

    /**
     * Inserts a new question into the database via the repository.
     */
    fun insertQuestion(questionModel: QuestionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertQuestion(questionModel)
        }
    }
}
