package com.learnandroid.senddatafromoneactivitytoanother

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.learnandroid.senddatafromoneactivitytoanother.ui.theme.SendDataFromOneActivityToAnotherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SendDataFromOneActivityToAnotherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GoToNextActivity(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GoToNextActivity(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var getResult by remember { mutableStateOf("No result yet") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK) {
            val returnValue = result.data?.getStringExtra(Constants.RESULT_CODE)
            getResult = returnValue ?: "No data received"
        }
    }
    Column {
        Text(
            text = stringResource(R.string.activity_1),
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            val intent = Intent(context, Activity2::class.java).apply {
                putExtra(Constants.INTENT_MESSAGE_KEY, "This is the data from first Activity")
                putExtra(Constants.INTENT_MESSAGE2_KEY, 456)
            }
            launcher.launch(intent)
        }) {
            Text(
                text = stringResource(R.string.button_go_to_second_activity)
            )
        }
        Text(
            text = "Result = $getResult"
        )

    }

}