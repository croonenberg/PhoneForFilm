#!/usr/bin/env bash
set -e

# Script to remove duplicate and legacy packages from the project
# Run this from the root of your Android Studio project (where settings.gradle is).

declare -a paths=(
  "app/src/main/java/com/example/app"
  "app/src/main/java/com/example/phoneforfilm/presentation"
)

echo "Removing duplicate packages..."
for p in "${paths[@]}"; do
  if [ -d "$p" ]; then
    echo "  rm -rf $p"
    rm -rf "$p"
  else
    echo "  Skipping missing path: $p"
  fi
done

echo "Cleanup completed."
