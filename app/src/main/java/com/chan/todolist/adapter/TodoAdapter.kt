package com.chan.todolist.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chan.domain.model.DomainTodo
import com.chan.todolist.ItemTouchHelperListener
import com.chan.todolist.R
import com.chan.todolist.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>(), ItemTouchHelperListener {
    private var items: List<DomainTodo> = ArrayList()
    lateinit var onItemDel: (Int) -> Unit
    lateinit var onItemCheck: (DomainTodo) -> Unit
    lateinit var onItemClick: (DomainTodo) -> Unit
    lateinit var onItemMove: (form: Int, to: Int) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
        holder.btnDel.setOnClickListener {
            onItemDel(items[position].id)
        }
        holder.btnCheck.setOnClickListener {
            onItemCheck(items[position])
        }
        holder.back.setOnClickListener {
            onItemClick(items[position])
        }
    }

    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainTodo) {
            binding.tvTodoItem.text = item.name
            if (item.isDone) {
//                binding.tvTodoItem.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvTodoItem.setTextColor(
                    Color.parseColor("#3B3B3B")
                )
                binding.root.setBackgroundColor(Color.parseColor("#2A2A2A"))
            } else {
//                binding.tvTodoItem.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.and(binding.tvTodoItem.paintFlags)
                binding.tvTodoItem.setTextColor(
                    Color.parseColor("#FFFFFFFF")
                )
                binding.root.setBackgroundColor(Color.parseColor("#1C1C1C"))
            }
        }

        val btnDel = binding.btnDel
        val btnCheck = binding.btnCheck
        val back = binding.root
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

    override fun onItemMove(from: Int, to: Int) {
        onItemMove(from, to)
//        val item = items[from]
//        items.removeAt(from)
//        items.add(to, item)
//        notifyItemMoved(from, to)
    }
}