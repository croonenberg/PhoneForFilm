# UI Integration Patch v19.9.9.7

## Modified ChatActivity.kt
- Observe `themeState` synchronously and apply theme before `super.onCreate()`.
- Updated long-press dialog to include “Reset naar standaard” option.

## Modified ChatAdapter.kt
- Enhanced `DiffUtil.ItemCallback`: compare items by `id` and contents via `equals`.

## Modified item_chat.xml
- Replaced hard-coded colors with theme attributes:
  - `android:background="?attr/colorPrimaryContainer"`
  - `android:textColor="?attr/colorOnSurface"`
