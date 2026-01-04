package com.example.quizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.model.LeaderBoardModel

class LeaderBoardAdapter(val context: Context,private  var userList: List<LeaderBoardModel>): RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardVH>() {

     //=emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardVH {
         val view= LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard,parent,false)
         return  LeaderBoardVH(view)
    }

    override fun onBindViewHolder(holder: LeaderBoardVH, position: Int) {
        holder.rankTV.text = (position+1).toString()
        holder.nameTV.text =userList[position].name
        holder.scoreTV.text =userList[position].score.toString()


    }

    override fun getItemCount(): Int {
    return    userList.size
    }

    fun updateList(newList: List<LeaderBoardModel>){
        userList=newList
        notifyDataSetChanged()
    }

    inner class LeaderBoardVH(itemView: View): RecyclerView.ViewHolder(itemView){
     val rankTV = itemView.findViewById<TextView>(R.id.tvRank)
        val nameTV = itemView.findViewById<TextView>(R.id.tvPlayerName)
        val scoreTV = itemView.findViewById<TextView>(R.id.tvScore)
    }
}