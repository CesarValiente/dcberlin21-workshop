package com.cesarvaliente.slidingpanelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesarvaliente.slidingpanelayout.databinding.ListItemsLayoutBinding
import com.cesarvaliente.slidingpanelayout.recyclerview.ItemsAdapter

class ListItemsFragment : Fragment() {
    private lateinit var myAdapter: ItemsAdapter
    private lateinit var myViewManager: RecyclerView.LayoutManager

    private val sharedVM: SharedVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListItemsLayoutBinding.inflate(inflater, container, false)

        myAdapter = ItemsAdapter(sharedVM.dataset, sharedVM)
        myViewManager = LinearLayoutManager(requireContext())
        binding.listItems.apply {
            setHasFixedSize(true)
            layoutManager = myViewManager
            adapter = myAdapter
        }

        return binding.root
    }
}