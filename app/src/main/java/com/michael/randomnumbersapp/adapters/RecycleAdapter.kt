package com.michael.randomnumbersapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.michael.randomnumbersapp.R
import com.michael.randomnumbersapp.models.Response

class RecycleAdapter(var items: Response, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val ZERO_TYPE = 1
    private val NON_ZERO_TYPE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        if(viewType == ZERO_TYPE)
        {
            val inflatedView = LayoutInflater.from(context).inflate(R.layout.zero_item, parent, false)
            return ZeroViewHolder(inflatedView)
        }else
        {
            val inflatedView = LayoutInflater.from(context).inflate(R.layout.non_zero_item, parent, false)
            return NonZeroViewHolder(inflatedView)

        }
    }

    override fun getItemCount(): Int {
        return items.random.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) 
    {
        if(holder is NonZeroViewHolder)
        {
           val newHolder =  holder
            newHolder.numberTv.setText(""+items.random[position])
        }else
        {
            val newHolder =  holder as ZeroViewHolder
            newHolder.numberTv.setText(""+items.random[position])
        }
    }

    override fun getItemViewType(position: Int): Int 
    {
        val value = items.random[position]
        
        if(items.random.contains(value*-1))
        {
            return ZERO_TYPE
        }else
        {
            return NON_ZERO_TYPE
        }
    }
    
    inner class NonZeroViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var numberTv : TextView = view.findViewById(R.id.nonZeroTv)
    }

    inner class ZeroViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var numberTv : TextView = view.findViewById(R.id.zeroTv)
    }

    fun updateItems(items: Response)
    {
        this.items = items
        this.notifyDataSetChanged()

    }


}