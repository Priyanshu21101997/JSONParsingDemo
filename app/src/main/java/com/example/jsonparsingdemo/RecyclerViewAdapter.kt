package com.example.jsonparsingdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var personNames:ArrayList<String>,var emailIds:ArrayList<String>,
                          var mobileNumbers:ArrayList<String>)
                          :RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        val personName:TextView = itemView.findViewById<TextView>(R.id.name)
        val email:TextView = itemView.findViewById<TextView>(R.id.email)
        val mobileNo:TextView = itemView.findViewById<TextView>(R.id.mobileNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_layout,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.personName.text = personNames[position]
        holder.email.text = emailIds[position]
        holder.mobileNo.text = mobileNumbers[position]
    }

    override fun getItemCount(): Int {
        return personNames.size
    }
}