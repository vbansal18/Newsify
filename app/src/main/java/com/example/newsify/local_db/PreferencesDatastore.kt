package com.example.newsify.local_db

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDatastore(context: Context) {
    private val dataStore = context.createDataStore(name = "times_app_opened_pref")

    companion object {
        val times_key = preferencesKey<Boolean>("TIMES")
    }

    suspend fun storeData(is_first_time: Boolean) {
        dataStore.edit {
            it[times_key] = is_first_time
        }
    }

    val timesFlow: Flow<Boolean> = dataStore.data.map {
        it[times_key] ?: true
    }
}