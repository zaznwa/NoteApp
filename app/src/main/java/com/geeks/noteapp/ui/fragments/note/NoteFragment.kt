package com.geeks.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.utils.PreferenceHelper

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(requireContext())
        btnPlus.setOnClickListener {
            val et = etSearch.text.toString()
            sharedPreferences.text = et
            txtText.text = et
        }
        txtText.text = sharedPreferences.text

//        btnPlus.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_noteFragment_to_noteDetailFragment,
//                null,
//                navOptions {
//                    anim {
//                        enter = R.anim.slide_in_right
//                        exit = R.anim.slide_out_left
//                    }
//                })
        }
    }
//}