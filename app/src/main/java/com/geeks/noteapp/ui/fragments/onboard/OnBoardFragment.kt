package com.geeks.noteapp.ui.fragments.onboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentOnBoardBinding
import com.geeks.noteapp.ui.adapters.OnBoardAdapter
import com.geeks.noteapp.utils.PreferenceHelper


class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(requireContext())

        initialize()
        setupListeners()
    }


    private fun initialize() {
        binding.viewPager.adapter = OnBoardAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                with(binding) {
                    if (position == 2) {
                        txtSkip.visibility = View.INVISIBLE
                        btnStart.visibility = View.VISIBLE
                        btnStart.setOnClickListener {
                            preferenceHelper.onBoard = true
                            navigateToNoteFragment()
                        }
                    } else {
                        txtSkip.visibility = View.VISIBLE
                        txtSkip.setOnClickListener {
                            setCurrentItem(currentItem + 2, true)
                        }
                        btnStart.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun navigateToNoteFragment() {
        findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
    }

}