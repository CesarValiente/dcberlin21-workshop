package com.cesarvaliente.slidingpanelayout.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cesarvaliente.slidingpanelayout.R
import com.cesarvaliente.slidingpanelayout.SharedVM

class ItemsAdapter(
    private val items: MutableList<Item>,
    private val sharedVM: SharedVM
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var selectedItemPosition = -1

    // TODO: Init selectedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view, sharedVM)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = items[bindingAdapterPosition]
            mailItemNumber.text = item.number.toString()

            layout.setOnClickListener {
                notifyItemChanged(selectedItemPosition)
                selectedItemPosition = bindingAdapterPosition
                sharedVM.setSelectedItem(item)
                changeItemsBackground(bindingAdapterPosition, selectedItemPosition, layout)
            }

            changeItemsBackground(bindingAdapterPosition, selectedItemPosition, layout)
        }
    }

    private fun changeItemsBackground(
        currentItemPosition: Int,
        selectedItemPosition: Int,
        layout: ConstraintLayout
    ) {
        layout.isSelected = currentItemPosition == selectedItemPosition
    }

    override fun getItemCount(): Int = items.size

    // TODO: create updateSelectItem function
}