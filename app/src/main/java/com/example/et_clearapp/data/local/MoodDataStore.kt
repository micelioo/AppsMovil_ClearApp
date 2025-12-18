package com.example.et_clearapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.moodDataStore by preferencesDataStore(name = "estado_animo_prefs")

class MoodDataStore(private val context: Context) {

    private object Keys {
        val MOOD = stringPreferencesKey("moodSeleccionado")
        val COMENTARIO = stringPreferencesKey("comentario")
    }

    val moodSeleccionadoFlow: Flow<String?> =
        context.moodDataStore.data.map { prefs ->
            prefs[Keys.MOOD]
        }

    val comentarioFlow: Flow<String?> =
        context.moodDataStore.data.map { prefs ->
            prefs[Keys.COMENTARIO]
        }

    suspend fun guardarMood(nuevoMood: String?) {
        context.moodDataStore.edit { prefs ->
            if (nuevoMood == null) {
                prefs.remove(Keys.MOOD)
            } else {
                prefs[Keys.MOOD] = nuevoMood
            }
        }
    }

    suspend fun guardarComentario(nuevoComentario: String) {
        context.moodDataStore.edit { prefs ->
            prefs[Keys.COMENTARIO] = nuevoComentario
        }
    }
}