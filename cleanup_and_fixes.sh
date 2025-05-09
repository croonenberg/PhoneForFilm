#!/usr/bin/env bash
set -e

# Run from project root

echo "1. Removing legacy and duplicate packages..."
declare -a to_delete=(
  "app/src/main/java/com/example/app"
  "app/src/main/java/com/example/phoneforfilm/presentation"
  "app/src/main/java/com/example/phoneforfilm/view"
  "app/src/main/java/com/example/phoneforfilm/data/repository/ChatRepository.kt"
)
for p in "${to_delete[@]}"; do
  if [ -e "$p" ]; then
    echo "  rm -rf $p"
    rm -rf "$p"
  else
    echo "  Skipping missing path: $p"
  fi
done

echo "2. Updating imports from 'presentation' to 'ui'..."
# Replace any presentation package references to ui
find app/src/main/java -type f -name "*.kt" -exec sed -i 's|com.example.phoneforfilm.presentation|com.example.phoneforfilm.ui|g' {} +

echo "3. Fix Hilt DatabaseModule imports..."
# Fix import and class references in DatabaseModule.kt
DB_MODULE="app/src/main/java/com/example/phoneforfilm/di/DatabaseModule.kt"
if [ -f "$DB_MODULE" ]; then
  sed -i 's|import com.example.app.db.AppDatabase|import com.example.phoneforfilm.data.local.db.AppDatabase|g' "$DB_MODULE"
  sed -i 's|addMigrations(.*)|addMigrations(MIGRATION_3_4_CHAT_THEME)|g' "$DB_MODULE"
else
  echo "  DatabaseModule.kt not found"
fi

echo "Cleanup and basic fixes applied. Please rebuild the project."
