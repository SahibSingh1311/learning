package com.learnandroid.quizapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.learnandroid.quizapp.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onStartQuiz: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)) {
        Text(
            stringResource(R.string.text_app_heading),
            style = MaterialTheme.typography.headlineMedium,
        )

        Text(stringResource(R.string.text_intro))

        Spacer(modifier = Modifier.padding(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = {Text(stringResource(R.string.label_name))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )

        )

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = { onStartQuiz(name) },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.button_start))
        }
    }
}