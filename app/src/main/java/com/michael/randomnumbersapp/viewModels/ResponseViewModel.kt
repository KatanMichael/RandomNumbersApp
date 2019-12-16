package com.michael.randomnumbersapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michael.randomnumbersapp.controllers.RetroController
import com.michael.randomnumbersapp.interfaces.RequestListener
import com.michael.randomnumbersapp.models.Response

class ResponseViewModel: ViewModel()
{
    val controller = RetroController
    var response: MutableLiveData<Response> = MutableLiveData()

    init {
        response = controller.initGetNumbers()
    }

    fun refreshNumbers()
    {

        controller.getRandomNumbers(object : RequestListener<Response>
        {
            override fun onComplete(t: Response) {
                if(t != null)
                {
                    response.postValue(t)
                }
            }

            override fun onError(msg: String)
            {
                response.postValue(null)
            }

        })
    }


}