package com.nazrawi.table.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazrawi.table.data.remote.model.Standing
import com.nazrawi.table.data.repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TableViewModel: ViewModel() {
    var repo: APIRepository? = null
    val liveTable = MutableLiveData<List<Standing>>()

    fun updateTable() {
        repo?.let {
            viewModelScope.launch(context = Dispatchers.IO) {
                it.getTable().let {
                    viewModelScope.launch {
                        liveTable.value = it
                    }
                }
            }
        }
    }

}