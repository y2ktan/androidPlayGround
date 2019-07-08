package com.example.vcfr67.simpleapplication.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.vcfr67.simpleapplication.R
import android.content.Context
import com.example.vcfr67.simpleapplication.model.Customer

class CustomerAdapter(private val Customers:List<Customer>, val context:Context): RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_item,
                parent, false))
    }

    override fun getItemCount(): Int {
        return Customers.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.customerName.text = Customers[position].name
        holder.customerAge.text = Customers[position].age.toString()
    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var viewHolder: CustomerViewHolder
//        var localConvertView = convertView
//        if(localConvertView != null){
//            localConvertView = LayoutInflater.from(parent?.context).
//                    inflate(R.layout.customer_item,parent,false)
//            viewHolder = CustomerViewHolder(localConvertView)
//            localConvertView.tag = viewHolder
//        } else {
//            viewHolder = localConvertView?.tag as CustomerViewHolder
//        }
//        viewHolder.customerName.text = Customers[position].name
//        viewHolder.customerAge.text = Customers[position].age.toString()
//        return localConvertView!!
//    }

    inner class CustomerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var customerName: TextView = itemView.findViewById(R.id.name)
        var customerAge: TextView = itemView.findViewById(R.id.age)
    }
}