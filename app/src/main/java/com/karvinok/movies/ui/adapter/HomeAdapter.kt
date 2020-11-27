package com.karvinok.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karvinok.domain.entity.Employee
import com.karvinok.movies.databinding.ItemEmployeeBinding

class HomeAdapter(
    private var employees : List<Employee> = arrayListOf(),
    private val itemClick : (Employee) -> Unit
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    fun update(employees : List<Employee>){
        this.employees = employees
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employee : Employee){
            binding.tvTitle.text = employee.name
            binding.root.setOnClickListener { itemClick.invoke(employee) }
        }
    }
}