package com.chan.todolist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chan.domain.model.DomainTodo
import com.chan.todolist.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var items: List<DomainTodo> = ArrayList()
//    lateinit var onSaveClick: (DomainCharacterInfo) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
//        holder.btnSave.setOnClickListener {
//            onSaveClick(items[position])
//        }
    }

    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainTodo) {
            binding.tvTodoItem.text = item.name
//            binding.tvSpecies.text = item.species

        }

//        val btnSave = binding.btnSave
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainTodo>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}