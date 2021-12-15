package com.example.myapplicationchat.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplicationchat.util.observeEvent

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    abstract val layoutResId: Int
    abstract val viewModel: BaseViewModel

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        _binding.apply {
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
        viewModel.navigationLiveData.observeEvent(viewLifecycleOwner, findNavController())
        return _binding.root
    }
}
