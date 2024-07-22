package com.avinash.navigationpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.avinash.navigationpractice.R
import com.avinash.navigationpractice.databinding.GameOverFragmentBinding

class GameOverFragment : Fragment() {

    private lateinit var gameOverFragmentBinding: GameOverFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameOverFragmentBinding = GameOverFragmentBinding.inflate(inflater, container, false)
        setTryAgainListener()

        var args = GameOverFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(activity, "Questions asked: ${args.questionCount}. Correct answer: ${args.correctAnswers}", Toast.LENGTH_LONG).show()
        return gameOverFragmentBinding.root
    }

    private fun setTryAgainListener() {
        gameOverFragmentBinding.youLostTryAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameOverFragment_to_titleFragment)
        }
    }
}