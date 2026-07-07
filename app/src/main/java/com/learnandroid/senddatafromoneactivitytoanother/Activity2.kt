 package com.learnandroid.senddatafromoneactivitytoanother

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.learnandroid.senddatafromoneactivitytoanother.ui.theme.SendDataFromOneActivityToAnotherTheme

 class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val data = intent.extras


        val stringMessage = data?.getString(Constants.INTENT_MESSAGE_KEY) ?: "No message received"
        val numberValue = data?.getInt(Constants.INTENT_MESSAGE2_KEY) ?: 0

        setContent {
            SendDataFromOneActivityToAnotherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowText(message = stringMessage, number = numberValue, onClick = { sendResultAndFinish() }, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

     private fun sendResultAndFinish() {
         val resultIntent = Intent().apply {
             putExtra(Constants.RESULT_CODE, "Data sent back from Activity2!")
         }
         setResult(Activity.RESULT_OK, resultIntent)
         finish()
     }
}

 @Composable
 fun ShowText(modifier: Modifier = Modifier, message: String , number: Int, onClick : ()->Unit ) {
     Column {
         Text(
             text = stringResource(R.string.activity_2),
             modifier = modifier
         )
         Text(text = message,
             modifier = modifier)
         Text(text = number.toString(),
             modifier = modifier)

         Button(onClick = onClick) {
             Text(text = stringResource(R.string.button_go_back_to_first_activity))
         }
     }
 }