package com.example.quiz

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

object QuizStats {
    private const val PREFS_NAME = "quiz_stats"
    private const val KEY_GAMES = "games_played"
    private const val KEY_CORRECT = "correct_answers"
    private const val KEY_WRONG = "wrong_answers"
    private const val KEY_TIME = "total_time_millis"

    private var prefs: SharedPreferences? = null

    var gamesPlayed by mutableIntStateOf(0)
        private set

    var correctAnswers by mutableIntStateOf(0)
        private set

    var wrongAnswers by mutableIntStateOf(0)
        private set

    var totalTimeMillis by mutableLongStateOf(0L)
        private set

    val percentCorrect: Int
        get() {
            val total = correctAnswers + wrongAnswers
            return if (total == 0) 0 else correctAnswers * 100 / total
        }

    val averageTimeMillis: Long
        get() = if (gamesPlayed == 0) 0L else totalTimeMillis / gamesPlayed

    fun init(context: Context) {
        if (prefs != null) return
        val storage = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs = storage
        gamesPlayed = storage.getInt(KEY_GAMES, 0)
        correctAnswers = storage.getInt(KEY_CORRECT, 0)
        wrongAnswers = storage.getInt(KEY_WRONG, 0)
        totalTimeMillis = storage.getLong(KEY_TIME, 0L)
    }

    fun recordGame(correct: Int, wrong: Int, timeMillis: Long) {
        gamesPlayed++
        correctAnswers += correct
        wrongAnswers += wrong
        totalTimeMillis += timeMillis
        prefs?.edit()
            ?.putInt(KEY_GAMES, gamesPlayed)
            ?.putInt(KEY_CORRECT, correctAnswers)
            ?.putInt(KEY_WRONG, wrongAnswers)
            ?.putLong(KEY_TIME, totalTimeMillis)
            ?.apply()
    }
}

private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}

@Composable
fun HomeScreen(
    onOpenStats: () -> Unit,
    onStartGame: () -> Unit
) {
    val context = LocalContext.current
    var showExitDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Quiz")
        Button(onClick = onStartGame) {
            Text("Играть")
        }
        Button(onClick = onOpenStats) {
            Text("Статистика")
        }
        Button(onClick = { showExitDialog = true }) {
            Text("Выход")
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Выйти из приложения?") },
            confirmButton = {
                TextButton(onClick = { (context as? Activity)?.finish() }) {
                    Text("Да")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Нет")
                }
            }
        )
    }
}

@Composable
fun StatsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Статистика")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Сыграно игр: ${QuizStats.gamesPlayed}")
        Text("Правильных ответов: ${QuizStats.correctAnswers}")
        Text("Неправильных ответов: ${QuizStats.wrongAnswers}")
        Text("Процент правильных ответов: ${QuizStats.percentCorrect}%")
        Text("Среднее время прохождения игры: ${formatTime(QuizStats.averageTimeMillis)}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}

private fun correctAnswerText(question: Question): String = when (question) {
    is TextQuestion -> question.acceptedAnswers.first()
    is SingleChoiceQuestion -> question.options[question.correctIndex]
    is MultiChoiceQuestion -> question.correctIndices.sorted().joinToString(", ") { question.options[it] }
}

@Composable
fun GameScreen(onBack: () -> Unit) {
    var questions by remember { mutableStateOf(QuestionBank.randomGame()) }
    var index by remember { mutableIntStateOf(0) }
    var correctCount by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var wasCorrect by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }
    var selectedSingle by remember { mutableIntStateOf(-1) }
    var selectedMulti by remember { mutableStateOf(setOf<Int>()) }
    var finished by remember { mutableStateOf(false) }
    var startTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var nowMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var gameTimeMillis by remember { mutableLongStateOf(0L) }

    LaunchedEffect(startTime, finished) {
        if (!finished) {
            while (true) {
                nowMillis = System.currentTimeMillis()
                delay(500)
            }
        }
    }

    if (finished) {
        val total = questions.size
        val wrongCount = total - correctCount
        val percent = correctCount * 100 / total

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Игра окончена")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Правильных ответов: $correctCount")
            Text("Неправильных ответов: $wrongCount")
            Text("Процент верных: $percent%")
            Text("Время прохождения: ${formatTime(gameTimeMillis)}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    questions = QuestionBank.randomGame()
                    index = 0
                    correctCount = 0
                    answered = false
                    wasCorrect = false
                    textInput = ""
                    selectedSingle = -1
                    selectedMulti = emptySet()
                    gameTimeMillis = 0L
                    startTime = System.currentTimeMillis()
                    nowMillis = startTime
                    finished = false
                }
            ) {
                Text("Следующая игра")
            }
            Button(onClick = onBack) {
                Text("В главное меню")
            }
        }
        return
    }

    val question = questions[index]
    val isLast = index == questions.lastIndex

    val canAnswer = when (question) {
        is TextQuestion -> textInput.isNotBlank()
        is SingleChoiceQuestion -> selectedSingle >= 0
        is MultiChoiceQuestion -> selectedMulti.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = formatTime(nowMillis - startTime),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("Вопрос ${index + 1} из ${questions.size}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = question.text,
            style = MaterialTheme.typography.titleMedium
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                when (question) {
                    is TextQuestion -> {
                        OutlinedTextField(
                            value = textInput,
                            onValueChange = { textInput = it },
                            enabled = !answered,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    is SingleChoiceQuestion -> {
                        question.options.forEachIndexed { optionIndex, option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = selectedSingle == optionIndex,
                                    onClick = { selectedSingle = optionIndex },
                                    enabled = !answered
                                )
                                Text(option)
                            }
                        }
                    }

                    is MultiChoiceQuestion -> {
                        question.options.forEachIndexed { optionIndex, option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = optionIndex in selectedMulti,
                                    onCheckedChange = { checked ->
                                        selectedMulti = if (checked) {
                                            selectedMulti + optionIndex
                                        } else {
                                            selectedMulti - optionIndex
                                        }
                                    },
                                    enabled = !answered
                                )
                                Text(option)
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!answered) {
                Button(
                    onClick = {
                        val result = when (question) {
                            is TextQuestion -> question.isCorrect(textInput)
                            is SingleChoiceQuestion -> question.isCorrect(selectedSingle)
                            is MultiChoiceQuestion -> question.isCorrect(selectedMulti)
                        }
                        wasCorrect = result
                        if (result) correctCount++
                        answered = true
                    },
                    enabled = canAnswer
                ) {
                    Text("Ответить")
                }
            } else {
                Text(if (wasCorrect) "Правильно!" else "Неправильно")
                if (!wasCorrect) {
                    Text("Правильный ответ: ${correctAnswerText(question)}")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (isLast) {
                            val elapsed = System.currentTimeMillis() - startTime
                            gameTimeMillis = elapsed
                            QuizStats.recordGame(
                                correct = correctCount,
                                wrong = questions.size - correctCount,
                                timeMillis = elapsed
                            )
                            finished = true
                        } else {
                            index++
                            answered = false
                            wasCorrect = false
                            textInput = ""
                            selectedSingle = -1
                            selectedMulti = emptySet()
                        }
                    }
                ) {
                    Text(if (isLast) "Завершить" else "Дальше")
                }
            }
        }
    }
}