#!/usr/bin/env bash
# Update import statements from obsolete com.example.app packages to new locations

# ChatTheme entity
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.data.ChatTheme|import com.example.phoneforfilm.data.local.entity.ChatTheme|g' {} +

# ChatThemeDao
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.data.ChatThemeDao|import com.example.phoneforfilm.data.local.dao.ChatThemeDao|g' {} +

# AppDatabase
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.db.AppDatabase|import com.example.phoneforfilm.data.local.db.AppDatabase|g' {} +

# Migrations
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.db.MIGRATION_1_2|import com.example.phoneforfilm.data.local.db.MIGRATION_3_4_CHAT_THEME|g' {} +

# ThemeUtils/ThemeMapper
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.utils.ThemeMapper|import com.example.phoneforfilm.utils.ThemeUtils|g' {} +
sed -i 's|ThemeMapper.getStyleRes|ThemeUtils.getStyleRes|g' app/src/main/java/com/example/phoneforfilm/ui/chat/ChatActivity.kt

# ChatActivity/ViewModel imports
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.ui.chat.ChatViewModel|import com.example.phoneforfilm.ui.chat.ChatViewModel|g' {} +
find app/src/main/java -type f -name "*.kt" -exec sed -i \
    's|import com.example.app.ui.chat.ChatActivity|import com.example.phoneforfilm.ui.chat.ChatActivity|g' {} +
