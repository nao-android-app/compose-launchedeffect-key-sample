package com.nao.launchedeffectkeysample

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nao.launchedeffectkeysample.ui.theme.LaunchedEffectKeySampleTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "LaunchedEffect"

@Composable
fun LaunchedEffectKeyScreen(
    modifier: Modifier = Modifier
) {
    var selectedKey by remember {
        mutableStateOf(KeyType.UNIT)
    }

    var count by remember {
        mutableIntStateOf(0)
    }

    var text by remember {
        mutableStateOf("Hello")
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text("Count : $count")

            Button(
                onClick = { count++ }
            ) {
                Text("Count++")
            }
        }

        HorizontalDivider()

        Column {
            Text("Text : $text")

            Button(
                onClick = {
                    text = if (text == "Hello") {
                        "Compose"
                    } else {
                        "Hello"
                    }
                }
            ) {
                Text("Change Text")
            }
        }

        HorizontalDivider()

        Text("Select LaunchedEffect Key")

        KeyType.entries.forEach { keyType ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedKey = keyType
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedKey == keyType,
                    onClick = {
                        selectedKey = keyType
                    }
                )

                Text(keyType.displayName)
            }
        }

        when (selectedKey) {
            KeyType.UNIT -> UnitKeyEffect()
            KeyType.TRUE -> TrueKeyEffect()
            KeyType.COUNT -> CountKeyEffect(count)
            KeyType.TEXT -> TextKeyEffect(text)
            KeyType.COUNT_AND_TEXT -> CountAndTextKeyEffect(
                count = count,
                text = text
            )
        }
    }
}

@Composable
private fun UnitKeyEffect() {
    LaunchedEffect(Unit) {
        Log.d(TAG, "START [Unit]")

        try {
            delay(10_000.milliseconds)
        } finally {
            Log.d(TAG, "END   [Unit]")
        }
    }
}

@Composable
private fun TrueKeyEffect() {
    LaunchedEffect(true) {
        Log.d(TAG, "START [true]")

        try {
            delay(10_000.milliseconds)
        } finally {
            Log.d(TAG, "END   [true]")
        }
    }
}

@Composable
private fun CountKeyEffect(
    count: Int
) {
    LaunchedEffect(count) {
        Log.d(TAG, "START [Count] count=$count")

        try {
            delay(10_000.milliseconds)
        } finally {
            Log.d(TAG, "END   [Count] count=$count")
        }
    }
}

@Composable
private fun TextKeyEffect(
    text: String
) {
    LaunchedEffect(text) {
        Log.d(TAG, "START [Text] text=$text")

        try {
            delay(10_000.milliseconds)
        } finally {
            Log.d(TAG, "END   [Text] text=$text")
        }
    }
}

@Composable
private fun CountAndTextKeyEffect(
    count: Int,
    text: String
) {
    LaunchedEffect(count, text) {
        Log.d(TAG, "START [Count + Text] count=$count text=$text")

        try {
            delay(10_000.milliseconds)
        } finally {
            Log.d(TAG, "END   [Count + Text] count=$count text=$text")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LaunchedEffectKeyScreenPreview() {
    LaunchedEffectKeySampleTheme {
        LaunchedEffectKeyScreen()
    }
}
