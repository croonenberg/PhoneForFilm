# CHANGELOG – v20A.0.33-min (Crash fix wit scherm)

## Updated
* **AndroidManifest.xml**
  * Launcher-activity is nu `view.ChatListActivity`.
  * `android:name=".PhoneForFilmApp"` toegevoegd voor Hilt.
* **ui/chat/ChatActivity.kt**
  * Vroege guard: sluit activity met toast als `conversationId` ontbreekt, voorkomt crash.

Geen andere wijzigingen.

Generated 2025-05-24.
