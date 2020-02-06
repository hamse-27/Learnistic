package com.example.learnistic.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnistic.R
import com.example.learnistic.model.Courses

class courses_custom_adapter(val course_List: ArrayList<Courses>) : RecyclerView.Adapter<courses_custom_adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_recycler_view, parent,false)
        val v = LayoutInflater.from(parent.context).inflate(R.layout.courses_cardview_layout, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return course_List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course: Courses= course_List[position]
        holder.coursename.text = course.course_Name


    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val coursename = itemView.findViewById(R.id.coursename1) as TextView

    }

}