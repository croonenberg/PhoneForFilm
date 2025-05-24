# CHANGELOG – v20A.0.23-min (Compile errors fix)

## Updated
* **adapter/ContactAdapter.kt**
  * Property `avatarUrl` → `avatarUri` (in lijn met entity).
  * Drawable refs `ic_avatar_placeholder` → `avatar_placeholder`.

* **view/MainActivity.kt**
  * Binding-id `recyclerViewContacts` → `recyclerViewFilms` (match layout).
  * Rest ongewijzigd.

* **ui/chat/ChatActivity.kt**
  * Companion object toegevoegd: `EXTRA_CONTACT_ID = "conversationId"` zodat MainActivity compileert.

Geen verdere code gewijzigd.

Generated 2025-05-24.