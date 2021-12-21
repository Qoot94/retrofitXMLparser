package com.example.retrofitxmlparser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitxmlparser.databinding.ItemRowBinding


class RVAdapter(
    private val container: ArrayList<String>
) :
    RecyclerView.Adapter<RVAdapter.CelebViewHolder>() {
    class CelebViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebViewHolder {
        return CelebViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CelebViewHolder, position: Int) {
        val cards = container[position]
        holder.binding.apply {
            tvTitle.text = cards
        }
    }

    override fun getItemCount(): Int = container.size
}
