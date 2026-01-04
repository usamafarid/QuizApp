package com.example.quizapp.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toIcon
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.model.CategoryModel

class CardAdapter(private val context: Context, private var quiz: MutableList<CategoryModel>) : RecyclerView.Adapter<CardAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
//        if(quiz[position].image == -1){
//            holder.imageView.setImageResource(quiz[position].image)
//
//        } else{
//            holder.imageView.setImageURI ( quiz[position].imagePath.toUri())
//
//        }
        holder.imageView.setImageURI ( quiz[position].imagePath.toUri())
        holder.textView1.text = quiz[position].name
        holder.textView2.text = quiz[position].text


        holder.itemView.setOnClickListener {
            Toast.makeText(context, quiz[position].name, Toast.LENGTH_SHORT).show()
//            cardClicked(quiz[position])
            val bundle= Bundle()
            bundle.putInt("categoryID",quiz[position].cid)
            holder.itemView.findNavController().navigate(R.id.action_homeFragment_to_quizFragment,bundle)

        }
    }

    override fun getItemCount(): Int {
        return quiz.size
    }

    fun updateList(categoryModel: List<CategoryModel>){
        quiz.clear()
        quiz.addAll(categoryModel)
//        notifyDataSetChanged()
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)
        val textView1 = itemView.findViewById<TextView>(R.id.tvTitle)
        val textView2 = itemView.findViewById<TextView>(R.id.tvQuestion)
    }
 }