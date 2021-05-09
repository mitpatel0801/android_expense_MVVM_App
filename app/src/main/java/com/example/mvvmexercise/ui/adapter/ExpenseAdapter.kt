package com.example.mvvmexercise.ui.adapter

import android.graphics.Color
import android.graphics.Color.CYAN
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexercise.Expense
import com.example.mvvmexercise.R
import kotlinx.android.synthetic.main.item_view.view.*

class ExpenseAdapter(
    private val list: MutableList<Expense>,
    private val listener: (expense: Expense) -> Unit
) :
    RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameView: TextView = view.nameView
        val priceView: TextView = view.priceView

        init {
            view.setOnClickListener {
                listener(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_view, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item: Expense = list[p1]

        p0.nameView.text = item.name
        p0.priceView.text = item.cost.toString()

        if (item.isClicked) {
            p0.itemView.setBackgroundColor(CYAN)
        } else {
            p0.itemView.setBackgroundColor(Color.WHITE)
        }

    }

    override fun getItemCount(): Int = list.size
}