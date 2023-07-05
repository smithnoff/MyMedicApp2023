package com.skynoff.mymedicapp.ui.features.singup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skynoff.mymedicapp.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {

    private lateinit var binding:FragmentSingUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(layoutInflater)
        return binding.root
    }
}