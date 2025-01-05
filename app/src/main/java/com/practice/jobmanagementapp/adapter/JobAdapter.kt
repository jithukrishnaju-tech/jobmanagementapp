package com.practice.jobmanagementapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.jobmanagementapp.DetailActivity
import com.practice.jobmanagementapp.databinding.ViewHolderCategoryBinding
import com.practice.jobmanagementapp.databinding.ViewHolderJobBinding
import com.practice.jobmanagementapp.model.JobModel

class JobAdapter(private val items:List<JobModel>) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    private lateinit var context: Context


    class ViewHolder(val binding: ViewHolderJobBinding) : RecyclerView.ViewHolder(binding.root){
        val requiredBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapter.ViewHolder {
        context = parent.context
        val binding = ViewHolderJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.companyTxt.text = item.company
        holder.binding.locationTxt.text = item.location
        holder.binding.timeTxt.text = item.time
        holder.binding.modelTxt.text = item.model
        holder.binding.levelTxt.text = item.level
        holder.binding.salaryTxt.text = item.salary

        val drawableResourceId = holder.itemView.resources
            .getIdentifier(item.picUrl, "drawable", holder.itemView.context.packageName)
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            val intent:Intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", item)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}