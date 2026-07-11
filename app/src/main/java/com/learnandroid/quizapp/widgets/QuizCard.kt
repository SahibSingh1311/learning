package com.learnandroid.quizapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.learnandroid.quizapp.models.Question

@Composable
fun QuizCard(
    modifier: Modifier = Modifier,
    question: Question,
    selectedOptionIndex: Int?,
    isRevealed: Boolean,
    onOptionSelected: (Int) -> Unit,
    onSubmit: () -> Unit,
    onNext: () -> Unit
    ) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(question.question,
                style = MaterialTheme.typography.titleMedium)

            Image(
                painter = painterResource(question.image),
                contentDescription = "Flag Image",
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )

            val options = listOf(question.optionOne,
                question.optionTwo,
                question.optionThree,
                question.optionFour)

            options.forEachIndexed { index, optionText ->
                QuizOptionsButton(text = optionText,
                    isSelected = selectedOptionIndex == index + 1,
                    isCorrectOption = question.correctOptions == index + 1,
                    isRevealed = isRevealed,
                    onClick = {onOptionSelected(index + 1)}
                    )
            }

            Button(
                onClick = if (isRevealed) onNext else onSubmit,
                enabled = selectedOptionIndex != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if(isRevealed) "Next" else "Submit")
            }
        }
    }
}

@Composable
fun QuizOptionsButton(
    text: String,
    isSelected: Boolean,
    isCorrectOption: Boolean,
    isRevealed: Boolean,
    onClick: () -> Unit
) {
    val containerColor = when {
        !isRevealed && isSelected -> MaterialTheme.colorScheme.primaryContainer
        isRevealed && isCorrectOption -> Color(0xFF81C784)          // green
        isRevealed && isSelected -> Color(0xFFE57373)               // red
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val contentColor = if (isRevealed && (isCorrectOption || isSelected))
        Color.Black
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        onClick = onClick,
        enabled = !isRevealed,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            disabledContainerColor  = containerColor,
            contentColor = contentColor,
            disabledContentColor = contentColor
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
            )
    }
}