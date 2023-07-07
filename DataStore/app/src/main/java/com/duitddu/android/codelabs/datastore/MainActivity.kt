package com.duitddu.android.codelabs.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.duitddu.android.codelabs.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val dataStore: DataStore<Preferences> by preferencesDataStore("app.pref")

    private val clickCountFlow: Flow<Int> by lazy {
        dataStore.data.map { pref -> pref[PREF_KEY_CLICK_COUNT] ?: 0 }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val clickCount = clickCountFlow.collectAsState(initial = 0).value

                        Text("Click count : $clickCount")
                        Button(onClick = {
                            increaseClickCount()
                        }) {
                            Text("Increase")
                        }
                    }
                }
            }
        }
    }

    private fun increaseClickCount() {
        lifecycleScope.launch {
            dataStore.edit { pref ->
                val currentValue = pref[PREF_KEY_CLICK_COUNT] ?: 0
                pref[PREF_KEY_CLICK_COUNT] = currentValue + 1
            }
        }
    }

    companion object {
        private val PREF_KEY_CLICK_COUNT = intPreferencesKey("PREF_KEY_CLICK_COUNT")
    }
}