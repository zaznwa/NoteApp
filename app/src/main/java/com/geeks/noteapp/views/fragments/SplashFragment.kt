package com.geeks.noteapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentSplashBinding
import com.geeks.noteapp.model.PreferenceHelper

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(requireContext())

        if (preferenceHelper.shouldShowOnBoard()) {
            findNavController().navigate(R.id.action_splashFragment_to_onBoardFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_authFragment3)
        }
    }
}
