package com.learnandroid.changebackground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learnandroid.changebackground.ui.theme.ChangeBackgroundTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChangeBackgroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var backgroundColor by remember { mutableStateOf(Color.White) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(color = backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        ChangeBackground(onClick = {
                            backgroundColor = Color(
                                red = Random.nextFloat(),
                                green = Random.nextFloat(),
                                blue = Random.nextFloat(),
                                alpha = 1f,
                            )
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeBackground(modifier: Modifier = Modifier, onClick: ()->Unit = {}) {
    FloatingActionButton(onClick = onClick,
        modifier = Modifier.padding(16.dp)
            ) {
        Text(
            text = "Change Background",
            modifier = modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeBackgroundPreview() {
    ChangeBackgroundTheme {
        ChangeBackground()
    }
}