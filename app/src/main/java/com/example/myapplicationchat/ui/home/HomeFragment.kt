package com.example.myapplicationchat.ui.home

import androidx.fragment.app.viewModels
import com.example.myapplicationchat.R
import com.example.myapplicationchat.databinding.FragmentHomeBinding
import com.example.myapplicationchat.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteractionListener{
    override val layoutResId = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        val adapter = HomeAdapter(emptyList(),this)
        binding.myRecycleView.adapter = adapter
    }
}