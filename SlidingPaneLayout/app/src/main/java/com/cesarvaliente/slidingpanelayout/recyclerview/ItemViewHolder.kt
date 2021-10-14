package com.cesarvaliente.slidingpanelayout.recyclerview

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cesarvaliente.slidingpanelayout.R
import com.cesarvaliente.slidingpanelayout.SharedVM

class ItemViewHolder(view: View, val sharedVM: SharedVM) :
    RecyclerView.ViewHolder(view) {
    val layout: ConstraintLayout = itemView.findViewById(R.id.item_layout)
    val mailItemNumber: TextView = itemView.findViewById(R.id.mail_item_number)
}
