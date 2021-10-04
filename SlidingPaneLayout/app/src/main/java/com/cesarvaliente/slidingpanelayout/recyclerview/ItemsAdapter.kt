package com.cesarvaliente.slidingpanelayout

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(
    private val items: Array<Item>,
    private val sharedVM: SharedVM
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view, sharedVM)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = items[position]
            numberView.text = item.number.toString()
            bodyView.text = item.body
            layout.setOnClickListener {
                selectedItemPosition = position
                sharedVM.setSelectedItem(item)
                notifyDataSetChanged()
            }
            changeItemBackground(position, selectedItemPosition, layout)
        }
    }

    private fun changeItemBackground(
        currentItemPosition: Int,
        selectedItemPosition: Int,
        layout: LinearLayout
    ) {
        layout.isSelected = currentItemPosition == selectedItemPosition
    }

    override fun getItemCount(): Int = items.size

    fun unSelectItem() {
        selectedItemPosition = -1
    }
}