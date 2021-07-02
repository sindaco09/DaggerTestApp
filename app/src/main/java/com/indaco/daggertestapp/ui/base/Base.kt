package com.indaco.daggertestapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class Base<B: ViewBinding> : AppCompatActivity() {

    var _binding: B? = null
    val binding: B get() = _binding!!
    val TAG = this.javaClass.simpleName

    fun setBindingContentView(inflate: B) {
        _binding = inflate.also { setContentView(it.root) }
    }

}
