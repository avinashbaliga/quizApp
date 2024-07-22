package com.avinash.navigationpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avinash.navigationpractice.databinding.GameFragmentBinding
import com.avinash.navigationpractice.utils.CommonUtils


class GameFragment : Fragment() {

    private lateinit var gameFragmentBinding: GameFragmentBinding

    private data class Question(val question: String, val answers: MutableList<String>)

    private var checkedId: Int = -1
    private var questionCount: Int = 1

    private var questions: MutableList<Question> = mutableListOf<Question>(
        Question(
            "Which plugin is used to implement navigation?",
            mutableListOf("safeArgs", "NavController", "NavHost", "NavHostController")
        ),
        Question(
            "Fragment class is moved to which library?",
            mutableListOf("androidX", "androidY", "androidZ", "androidQ")
        ),
        Question("Who developed Android OS?", mutableListOf("Google", "Apple", "Microsoft", "IBM")),
        Question("Twitter is rebranded into what?", mutableListOf("X", "Z", "A", "x"))
    )

    private fun initiateQuestions() {
        var randomQuestion = questions[CommonUtils.generateRandomNumber(0, questions.size)]
        setQuestion(randomQuestion)
    }

    private fun setQuestion(question: Question) {
        gameFragmentBinding.questionTV.text = question.question
        gameFragmentBinding.answerGroup.clearCheck()
        gameFragmentBinding.answerOneRadio.text = question.answers[0]
        gameFragmentBinding.answerTwoRadio.text = question.answers[1]
        gameFragmentBinding.answerThreeRadio.text = question.answers[2]
        gameFragmentBinding.answerFourRadio.text = question.answers[3]
        assignRadioButtonListener()
    }

    private fun assignRadioButtonListener() {
        gameFragmentBinding.answerGroup.setOnCheckedChangeListener { group, checkedId ->
            gameFragmentBinding.submitBtn.isEnabled = true
        }
    }

    private fun assignSubmitButtonListener() {
        gameFragmentBinding.submitBtn.setOnClickListener {
            checkedId = gameFragmentBinding.answerGroup.checkedRadioButtonId
            println("Checked Id is: $checkedId")
            var correctAnswer = validateAnswer()
            if (correctAnswer) {
                if (questionCount++ < 3)
                    initiateQuestions()
                else
                    it.findNavController()
                        .navigate(
                            GameFragmentDirections.actionGameFragmentToGameWonFragment(
                                questionCount-1,
                                questionCount-1
                            )
                        )
            } else
                it.findNavController()
                    .navigate(
                        GameFragmentDirections.actionGameFragmentToGameOverFragment(
                            questionCount,
                            questionCount - 1
                        )
                    )
        }
    }

    private fun validateAnswer(): Boolean {
        return (checkedId == gameFragmentBinding.answerOneRadio.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameFragmentBinding =
            GameFragmentBinding.inflate(inflater, container, false)
        gameFragmentBinding.submitBtn.isEnabled = false
        initiateQuestions()
        assignSubmitButtonListener()
        return gameFragmentBinding.root
    }
}