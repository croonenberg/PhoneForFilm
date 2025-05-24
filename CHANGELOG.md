# Changelog – PhoneForFilm v20A.0.10

## 🔧 Fixed / Added XML resources

| File | Reason | Key additions |
|------|--------|---------------|
| `res/mipmap-anydpi-v26/ic_launcher_round.xml` | Invalid XML (element outside root) | Moved `<monochrome>` inside `<adaptive-icon>` |
| `res/layout/activity_chat.xml` | Placeholder 1‑liner | Full chat screen with `RecyclerView`, `TextInputEditText`, send FAB, toolbar |
| `res/layout/activity_call.xml` | Placeholder | Basic call UI with caller name + accept/decline buttons |
| `res/layout/activity_edit_contact.xml` | Placeholder | Two `TextInputLayout`s (name, phone) + save button |
| `res/layout/activity_theme_settings.xml` | Placeholder | `RecyclerView` for theme list |
| `res/layout/chat_action_bar.xml` | Skeleton | Avatar + name + status header for chat |
| `res/layout/dialog_chat_settings.xml` | Skeleton | `RadioGroup` for theme selection |
| `res/layout/item_contact.xml` | Placeholder | Avatar + name row for contact list |
| `res/layout/item_message.xml` | Placeholder | Simple `TextView` fallback (currently unused) |

## 📝 Impact

* **Build:** Resources now parse; `aapt2` no longer fails on placeholders or invalid syntax.  
* **Binding:** `ActivityChatBinding`, `ItemContactBinding` now generate with all referenced IDs (`recyclerViewMessages`, `toolbar`, `imageViewAvatar`, `textViewName`, etc.).  
* **Runtime:** Chat, call, and contact‑edit screens render with minimal but functional UI, preventing `NullPointerException` from `findViewById` or binding lookup.  
* **No Java/Kotlin code touched** – zero impact on logic layer; risk of regression is negligible.

