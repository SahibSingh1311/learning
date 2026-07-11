package com.learnandroid.quizapp.utils

import com.learnandroid.quizapp.R
import com.learnandroid.quizapp.models.Question

object Constants {
    const val INTENT_NAME = "name"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val q1 = Question(
            1,
            "What country this flag belongs to?",
            R.drawable.israel,
            "India",
            "Israel",
            "Italy",
            "Iraq",
            2
        )
        questions.add(q1)

        val q2 = Question(
            2,
            "What country this flag belongs to?",
            R.drawable.mexico,
            "Mexico",
            "Poland",
            "Italy",
            "Japan",
            1
        )
        questions.add(q2)

        val q3 = Question(
            3,
            "What country this flag belongs to?",
            R.drawable.poland,
            "Pakistan",
            "Poland",
            "Austria",
            "Finland",
            2
        )
        questions.add(q3)

        val q4 = Question(
            4,
            "What country this flag belongs to?",
            R.drawable.serbia,
            "Mexico",
            "Poland",
            "Serbia",
            "Japan",
            3
        )
        questions.add(q4)

        val q5 = Question(
            5,
            "What country this flag belongs to?",
            R.drawable.cuba,
            "Cuba",
            "Poland",
            "Italy",
            "Japan",
            1
        )
        questions.add(q5)

        val q6 = Question(
            6,
            "What country this flag belongs to?",
            R.drawable.barbados,
            "Mexico",
            "Poland",
            "Italy",
            "Barbados",
            4
        )
        questions.add(q6)

        val q7 = Question(
            7,
            "What country this flag belongs to?",
            R.drawable.fiji,
            "Mexico",
            "Fiji",
            "Italy",
            "Japan",
            2
        )
        questions.add(q7)

        val q8 = Question(
            8,
            "What country this flag belongs to?",
            R.drawable.kazakhstan,
            "Kazakhstan",
            "Poland",
            "Italy",
            "Japan",
            1
        )
        questions.add(q8)

        val q9 = Question(
            9,
            "What country this flag belongs to?",
            R.drawable.saint_patrick,
            "Saint Patrick",
            "Poland",
            "Italy",
            "Japan",
            1
        )
        questions.add(q9)

        val q10 = Question(
            10,
            "What country this flag belongs to?",
            R.drawable.spain,
            "Mexico",
            "Poland",
            "Italy",
            "Spain",
            4
        )
        questions.add(q10)

        return questions
    }
}