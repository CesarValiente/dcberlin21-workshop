package com.cesarvaliente.slidingpanelayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cesarvaliente.slidingpanelayout.recyclerview.Item

class SharedVM : ViewModel() {
    val selectedItem = MutableLiveData<Item>()

    val dataset = MutableList(30) { index -> Item(index, "Mail Title $index") }

    fun setSelectedItem(item: Item) {
        selectedItem.value = item
    }
}