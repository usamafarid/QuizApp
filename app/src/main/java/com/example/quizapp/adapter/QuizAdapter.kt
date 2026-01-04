//package com.example.quizapp.adapter
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//
//import androidx.recyclerview.widget.RecyclerView
//import com.example.quizapp.R
//import com.example.quizapp.model.QuestionModel

//class QuizAdapter( val context: Context,var question: MutableList<QuestionModel>):
//    RecyclerView.Adapter<QuizAdapter.QuestionViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
//        val inflater= LayoutInflater.from(context)
//        val view= inflater.inflate(R.layout.question_item,parent,false)
//        return QuestionViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
//
//        holder.textView.text = question[position].questionText
//        holder.option1.text = question[position].optionA
//        holder.option2.text = question[position].optionB
//        holder.option3.text = question[position].optionC
//        holder.option4.text = question[position].optionD
//
//        holder.itemView.setOnClickListener {
//
//            val selectedoption = question[position].correct
//            notifyDataSetChanged()
//
//            if (selectedoption == question[position].correct) {
//                Toast.makeText(context, "green border", Toast.LENGTH_SHORT).show()
//            } else
//                Toast.makeText(context, "red border", Toast.LENGTH_SHORT).show()
//        }
//
////        holder.buttonNext.setOnClickListener {
////            holder.textView.text=question[position].questionText
////            holder.option1.text=question[position].optionA
////            holder.option2.text=question[position].optionB
////            holder.option3.text=question[position].optionC
////            holder.option4.text=question[position].optionD
////
////        }
//
////        holder.buttonPrevious.setOnClickListener {
////            holder.textView.text=question[position].questionText
////            holder.option1.text=question[position].optionA
////            holder.option2.text=question[position].optionB
////            holder.option3.text=question[position].optionC
////            holder.option4.text=question[position].optionD
////
////        }
//
//    }
//    fun updateList(questionList: MutableList<QuestionModel>){
//        question.clear()
//
//        question.addAll(questionList)
//        notifyDataSetChanged()
//    }
//
////    private fun questions(index: Int){
////        val currentquestion=index
////        val nextquestion=index+1
////        val previousquestion=index-1
////    }
//    override fun getItemCount(): Int {
//        return  question.size
//    }
//
//    inner class QuestionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val textView: TextView = itemView.findViewById(R.id.tvQuestionText)
//        val option1: TextView = itemView.findViewById(R.id.btnOption1)
//        val option2: TextView = itemView.findViewById(R.id.btnOption2)
//        val option3: TextView = itemView.findViewById(R.id.btnOption3)
//        val option4: TextView = itemView.findViewById(R.id.btnOption4)
//
////        val buttonNext: Button=itemView.findViewById(R.id.btnNext)
////        val buttonPrevious: Button=itemView.findViewById(R.id.btnPrevious)
//    }
//      }