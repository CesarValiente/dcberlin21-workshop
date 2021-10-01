package com.cesarvaliente.slidingpanelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class DetailFragment : Fragment() {
    private val sharedVM: SharedVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_layout, container, false)

        sharedVM.selectedItem.observe(viewLifecycleOwner, Observer<Item> {
            view.findViewById<TextView>(R.id.detail_number).text = it.number.toString()
            view.findViewById<TextView>(R.id.detail_body).text =
                it.body + " " + getString(R.string.body_text_suffix)
        })

        return view
    }
}