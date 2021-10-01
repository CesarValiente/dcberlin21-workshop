package com.cesarvaliente.slidingpanelayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedVM : ViewModel() {
    val selectedItem = MutableLiveData<Item>()

    fun setSelectedItem(item: Item) {
        selectedItem.value = item
    }
}