package com.michael.randomnumbersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.michael.randomnumbersapp.adapters.RecycleAdapter
import com.michael.randomnumbersapp.controllers.RetroController
import com.michael.randomnumbersapp.interfaces.RequestListener
import com.michael.randomnumbersapp.models.Response
import com.michael.randomnumbersapp.viewModels.ResponseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    lateinit var adapter: RecycleAdapter
    lateinit var responseViewModel: ResponseViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initRecycleView()

        initRefreshBtn()

    }


    private fun initViewModel() {
        responseViewModel = ViewModelProviders.of(this).get(ResponseViewModel::class.java)

        responseViewModel.response.observe(this, Observer {
            run {
                adapter.updateItems(it)
            }
        })

    }

    private fun initRecycleView()
    {
        val arr =  ArrayList<Int>()
        val value = responseViewModel.response.value
        if(value != null)
        {
            adapter = RecycleAdapter(value,this)

        }else
        {
            adapter = RecycleAdapter(Response(arr),this)
        }

        recyclerview.adapter = adapter

        recyclerview.layoutManager = LinearLayoutManager(this)

        adapter.notifyDataSetChanged()

    }

    private fun initRefreshBtn()
    {
        btn.setOnClickListener {
            responseViewModel.refreshNumbers()
        }
    }

}


