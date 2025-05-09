package com.example.app.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(\"CREATE TABLE IF NOT EXISTS chat_themes (conversationId INTEGER PRIMARY KEY NOT NULL, themeKey TEXT NOT NULL)\")
    }
}
