package com.michael.randomnumbersapp.interfaces

interface RequestListener<T>
{
    fun onComplete(t: T)
    fun onError(msg: String)
}