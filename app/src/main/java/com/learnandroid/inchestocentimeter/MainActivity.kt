package com.learnandroid.inchestocentimeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learnandroid.inchestocentimeter.ui.theme.InchesToCentimeterTheme
import kotlin.toString

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InchesToCentimeterTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Gray) { innerPadding ->
                    ConvertInchesToCentimeter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ConvertInchesToCentimeter(modifier: Modifier = Modifier) {
    val inchValue = 2.54f
    var showError by remember { mutableStateOf(false) }
    var inchesInput by remember { mutableStateOf("") }
    var centimeterOutput by remember { mutableStateOf("") }

    fun convert() {
        val value = inchesInput.toFloatOrNull()
        if (value != null && value > 0) {
            centimeterOutput = (value * inchValue).toString()
            showError = false
        } else {
            showError = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.text_view_heading),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_view_message),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = inchesInput,
                onValueChange = {
                    inchesInput = it
                    showError = false
                    centimeterOutput = ""
                                },
                label = { Text(stringResource(R.string.input_label)) },
                suffix = {Text("\"")},
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { convert() })
            )
        }
        if(showError) {
            Text(text = stringResource(R.string.text_view_error_message),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_view_output_message),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = centimeterOutput,
                onValueChange = { inchesInput = it },
                label = { Text(stringResource(R.string.text_view_output_message)) },
                suffix = {Text("cm")},
                modifier = Modifier.weight(1f),
                readOnly = true
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            convert()
        }) {
            Text("Convert")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InchesToCentimeterTheme {
        ConvertInchesToCentimeter()
    }
}
