package com.example.quizapp.repository

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import com.example.quizapp.db.CategoryDAO
import com.example.quizapp.db.QuestionDAO
import com.example.quizapp.db.LeaderBoardDAO
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.model.LeaderBoardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class QuizRepository(val categoryDAO: CategoryDAO,val questionDAO: QuestionDAO,val leaderBoardDAO: LeaderBoardDAO) {


      //new professional way coroutine always use in repository.so this reason
    suspend fun insertQuestion(questionModel: QuestionModel ){
        withContext(Dispatchers.IO){
            questionDAO.insertQuestions(questionModel)
        }


    }
//    fun question(qid:Int,questionText: String,optionA: String,optionB: String,optionC: String,optionD: String,correct: String,userSelected: String,cid: Int): QuestionModel{
//        return QuestionModel(qid,questionText=questionText, optionA = optionA, optionB = optionB, optionC = optionC, optionD = optionD, correct = correct,userSelected = userSelected,cid)
//    }

     suspend fun getQuestion(categoryID: Int):  List<QuestionModel>{
         withContext(Dispatchers.IO){
             questionDAO.getQuestions(categoryID)
         }
         return questionDAO.getQuestions(categoryID)
    }
    suspend fun insertCategory(categoryModel: CategoryModel ){
        withContext(Dispatchers.IO){
            categoryDAO.insertCategory(categoryModel)
        }
    }

    fun getCategory(): LiveData< List<CategoryModel>>{
        return  categoryDAO.getCategory()

    }
    suspend fun insertResult(result: LeaderBoardModel){
         leaderBoardDAO.insertResult(result)
    }
    fun getTopScore(): LiveData< List<LeaderBoardModel>>{
        return leaderBoardDAO.getTopScores()
    }

}