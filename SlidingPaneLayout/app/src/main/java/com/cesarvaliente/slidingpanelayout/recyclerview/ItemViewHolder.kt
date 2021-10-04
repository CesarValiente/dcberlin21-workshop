package com.cesarvaliente.slidingpanelayout

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(view: View, val sharedVM: SharedVM) :
    RecyclerView.ViewHolder(view) {
    val layout: LinearLayout = itemView.findViewById(R.id.item_layout)
    val numberView: TextView = itemView.findViewById(R.id.item_number)
    val bodyView: TextView = itemView.findViewById(R.id.item_body)

}
