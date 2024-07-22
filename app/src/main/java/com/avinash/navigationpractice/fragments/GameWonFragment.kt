package com.avinash.navigationpractice.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.avinash.navigationpractice.R
import com.avinash.navigationpractice.databinding.GameWonFragmentBinding

class GameWonFragment : Fragment() {

    private lateinit var gameWonFragmentBinding: GameWonFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameWonFragmentBinding = GameWonFragmentBinding.inflate(inflater, container, false)
        attachTryAgainListener()


        setHasOptionsMenu(true)
        return gameWonFragmentBinding.root
    }

    private fun attachTryAgainListener() {
        gameWonFragmentBinding.youWonTryAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameWonFragment_to_titleFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(getIntent())
        return true
    }

    private fun getIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        var intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, "Sharing my score in quiz app. Question: ${args.questionCount} and correct answer: ${args.correctAnswers}")
        return intent
    }
}