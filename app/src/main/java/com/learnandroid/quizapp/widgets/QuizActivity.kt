package com.learnandroid.quizapp.widgets

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learnandroid.quizapp.MainActivity
import com.learnandroid.quizapp.R
import com.learnandroid.quizapp.ui.theme.QuizAppTheme
import com.learnandroid.quizapp.utils.Constants

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val playerName = intent.getStringExtra(Constants.INTENT_NAME) ?: ""
        val questionsList = Constants.getQuestions()

        fun restartQuiz() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContent {
            QuizAppTheme(darkTheme = true) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentQuestionIndex by remember { mutableIntStateOf(0) }
                    var selectedOption by remember { mutableStateOf<Int?>(null) }
                    var isRevealed by remember { mutableStateOf(false) }
                    var score by remember { mutableIntStateOf(0) }

                    if(currentQuestionIndex < questionsList.size) {
                        QuizCard(
                            modifier = Modifier.padding(innerPadding),
                            question = questionsList[currentQuestionIndex],
                            selectedOption,
                            isRevealed = isRevealed,
                            onOptionSelected = {selectedOption = it},
                            onSubmit = {
                                isRevealed = true
                                if (selectedOption == questionsList[currentQuestionIndex].correctOptions){
                                    score++
                                }
                            },
                            onNext = {
                                selectedOption = null
                                isRevealed = false
                                currentQuestionIndex++
                            }
                        )
                    } else {
                        Column {
                            Text(
                                "Quiz Completed. $playerName's Score : $score/${questionsList.size}",
                                modifier = Modifier.padding(innerPadding)
                            )
                            Button(
                                onClick = { restartQuiz() }
                            ) {
                                Text("Restart")
                            }
                        }
                    }
                }
            }
        }
    }
}
