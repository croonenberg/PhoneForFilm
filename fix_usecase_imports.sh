#!/usr/bin/env bash
set -e

# This script fixes the import for ViewModelComponent in UseCaseModule.kt
# Run this from the root of your Android Studio project.

FILE="app/src/main/java/com/example/phoneforfilm/di/UseCaseModule.kt"

if [ -f "$FILE" ]; then
  echo "Patching $FILE..."
  sed -i 's|import dagger.hilt.components.ViewModelComponent|import dagger.hilt.android.components.ViewModelComponent|' "$FILE"
  echo "Import updated."
else
  echo "File not found: $FILE"
  exit 1
fi
