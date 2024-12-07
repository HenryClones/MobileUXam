package com.example.mobileuxam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mobileuxam.databinding.FragmentInstructionsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionsFragment : Fragment() {
    private var _binding: FragmentInstructionsBinding? = null
    private val binding: FragmentInstructionsBinding
        get() = checkNotNull(_binding) { "View did not inflate!" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        binding.apply {
            start.setOnClickListener {
                findNavController().navigate(InstructionsFragmentDirections.start(0, Score()))
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = InstructionsFragment()
    }
}