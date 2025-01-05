package com.practice.jobmanagementapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.practice.jobmanagementapp.ClickListener
import com.practice.jobmanagementapp.R
import com.practice.jobmanagementapp.databinding.ViewHolderCategoryBinding

class CategoryAdapter(private val items: List<String>, val clickListener: ClickListener) :
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    private var selectedPosition = -1
    private var lastSelectedPositioin = -1

    private lateinit var context: Context

    class Viewholder(val binding: ViewHolderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val requiredBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context = parent.context
        val binding =
            ViewHolderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.binding.catTxt.text = item
        holder.binding.root.setOnClickListener {
            lastSelectedPositioin = selectedPosition
            selectedPosition = position
            notifyItemChanged(selectedPosition)
            notifyItemChanged(lastSelectedPositioin)
            clickListener.OnClick(position.toString())
        }

        if (position == selectedPosition) {
            holder.binding.catTxt.setBackgroundResource(R.drawable.purple_full_corner)
            holder.binding.catTxt.setTextColor(context.getColor(R.color.white))
        } else {
            holder.binding.catTxt.setBackgroundResource(R.drawable.grey_full_corner_bg)
            holder.binding.catTxt.setTextColor(context.getColor(R.color.black))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}