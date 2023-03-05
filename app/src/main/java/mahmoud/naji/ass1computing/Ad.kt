package com.mo7ammedtabasi.assignment_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*
import mahmoud.naji.ass1computing.Data
import mahmoud.naji.ass1computing.R


class AdapterData(private var context: Context, private var dataList:ArrayList<Data>)
    : RecyclerView.Adapter<AdapterData.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.tvname
        val num = itemView.tvnum
        val address = itemView.tvaddress


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data= dataList[position]

        holder.name.text = data.name
        holder.num.text = data.phone
        holder.address.text = data.address
    }
}


