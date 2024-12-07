package com.example.mobileuxam

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileuxam.databinding.FragmentExamQuestionBinding
import com.example.mobileuxam.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding
        get() = checkNotNull(_binding) { "View did not inflate!" }

    private val args: ResultsFragmentArgs by navArgs()

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private val viewModel: ResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.results = args.results
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        binding.apply {
            val averageBad: Float  = 50f
            val averageGood: Float = 50f
            results.text = requireContext().resources.getString(R.string.results_text).format(viewModel.results.percentGoodCorrect, averageGood, viewModel.results.percentBadCorrect, averageBad)
            restart.setOnClickListener {
                findNavController().navigate(ResultsFragmentDirections.restart())
            }
            shareButton.setOnClickListener {
                shareScore()
            }
        }
        return binding.root
    }

    private fun canResolveIntent(intent: Intent): Boolean {
        val packageManager: PackageManager = requireActivity().packageManager
        val resolvedActivity: ResolveInfo? =
            packageManager.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
        return resolvedActivity != null
    }

    private fun shareScore() {
        viewModel.results.let {
            val reportIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, requireContext().resources.getString(R.string.share_text).format(it.percentGoodCorrect, it.percentBadCorrect))
                putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject)
            }

            val chooserIntent = Intent.createChooser(
                reportIntent,
                getString(R.string.share_via)
            )

            if (canResolveIntent(chooserIntent) && canResolveIntent(reportIntent)) {
                startActivity(chooserIntent)
            }
        }
    }
}