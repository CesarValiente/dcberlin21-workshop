package com.cesarvaliente.slidingpanelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.cesarvaliente.slidingpanelayout.databinding.DetailLayoutBinding

class DetailFragment : Fragment() {
    private val sharedVM: SharedVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DetailLayoutBinding.inflate(inflater, container, false)

        sharedVM.selectedItem.observe(viewLifecycleOwner, { selectedItem ->
            selectedItem?.let {
                binding.scrollView.visibility = View.VISIBLE
                binding.mailTitle.text = it.mailTitle
            }
        })

        return binding.root
    }
}