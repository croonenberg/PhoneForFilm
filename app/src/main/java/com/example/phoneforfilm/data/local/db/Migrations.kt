package com.example.phoneforfilm.data.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4_CHAT_THEME = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS chat_themes (
                conversationId INTEGER PRIMARY KEY NOT NULL,
                themeKey TEXT NOT NULL
            )
        """.trimIndent())
    }
}
