# Patch v19.9.9.7 - Domain & Presentation Enhancements

## Modified Data Layer
- **Message.kt**: Added index on `conversationId` for query performance and kept existing fields intact.
- **Conversation.kt**: Ensured `theme` field is nullable and matches data model.
- **ConversationWithMessages.kt**: New relational data class to fetch conversations with messages.

## Added Repository
- **ChatThemeRepository.kt**: Abstracted DAO access for theme get/set operations.

## Added Domain Use-Cases
- **GetConversationThemeUseCase.kt**: Wraps repository getTheme flow.
- **SetConversationThemeUseCase.kt**: Executes repository setTheme.

## Added UI-State Model
- **UIState.kt**: Sealed class for Loading, Success, Error states.

## Updated ViewModels
- **ChatViewModel.kt**: Uses use-cases, exposes UIState, handles applyTheme with error handling.
- **SettingsViewModel.kt**: Manages default theme via use-cases, uses UIState model.

