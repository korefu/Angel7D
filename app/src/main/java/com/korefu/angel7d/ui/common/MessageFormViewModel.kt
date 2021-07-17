package com.korefu.angel7d.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.korefu.angel7d.data.Angel7DRepository
import com.korefu.angel7d.data.FormData
import com.korefu.angel7d.data.Status

class MessageFormViewModel : ViewModel() {
    val loadingStatus = MutableLiveData(Status.WAITING)
    private val repository = Angel7DRepository()
    suspend fun sendMessage(formData: FormData) {
        loadingStatus.value = Status.LOADING
        val result = repository.sendMessage(formData)
        if (result.isSuccess)
            loadingStatus.value = Status.SUCCESS
        else loadingStatus.value = Status.FAILED
    }
}