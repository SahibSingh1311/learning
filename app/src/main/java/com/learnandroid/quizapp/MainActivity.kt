package com.learnandroid.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.learnandroid.quizapp.ui.theme.QuizAppTheme
import com.learnandroid.quizapp.utils.Constants
import com.learnandroid.quizapp.widgets.HomeScreen
import com.learnandroid.quizapp.widgets.QuizActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            QuizAppTheme(darkTheme = true) {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding),){
                        name -> navigateToQuiz(name)}
                }
            }
        }
    }

    fun navigateToQuiz(name: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra(Constants.INTENT_NAME, name)
        }
        startActivity(intent)
        finish()
    }
}

@Preview(showBackground = true,
    backgroundColor = 1)
@Composable
private fun HomeScreenPreview() {
    QuizAppTheme(darkTheme = true) {
        HomeScreen(onStartQuiz = {})
    }
}