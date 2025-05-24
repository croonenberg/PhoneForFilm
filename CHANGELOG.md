# CHANGELOG – PhoneForFilm v20A.0.9

_Datum: 2025-05-24_

## ✨ Gewijzigde bestanden
| Pad | Reden |
|-----|-------|
| app/src/main/java/com/example/phoneforfilm/ui/chat/ChatActivity.kt | Bestond uit afgebroken fragment → volledige herimplementatie (binding‑fix, RecyclerView‑setup, theme‑observer). |
| app/src/main/java/com/example/phoneforfilm/ui/chat/ChatViewModel.kt | `@HiltViewModel` + constructor‐injectie toegevoegd, foutafhandeling verbeterd. |
| app/src/main/java/com/example/phoneforfilm/domain/usecase/CreateConversationUseCase.kt | Volledige implementatie + injectie. |
| app/src/main/java/com/example/phoneforfilm/domain/usecase/GetAllMessagesUseCase.kt | Volledige implementatie + injectie. |
| app/src/main/java/com/example/phoneforfilm/domain/usecase/GetConversationThemeUseCase.kt | Injectie‐constructor. |
| app/src/main/java/com/example/phoneforfilm/domain/usecase/SetConversationThemeUseCase.kt | Injectie‐constructor. |
| app/src/main/java/com/example/phoneforfilm/data/repository/ConversationRepository.kt | `findByContact()` toegevoegd. |
| app/src/main/java/com/example/phoneforfilm/di/AppModule.kt | Bestond uit afgebroken code → volledig herschreven module. |

## 📂 Toegevoegde functionaliteit
* App compileert weer — syntaxisfouten verwijderd.
* Thema‑wijziging kan opnieuw via `ChatActivity` getriggerd worden.
* Use‑cases zijn nu via Hilt injecteerbaar.

## 🗑️ Aanbevolen te verwijderen bestanden
_Worden niet in deze patch meegeleverd – verwijderen op repo‑niveau_
| Pad | Waarom |
|-----|--------|
| `app/src/main/java/com/example/phoneforfilm/data/Conversation.kt` | Dubbele entity (conflicteert met `data/local/entity/Conversation.kt`). |
| `.kotlin/errors/*` | IDE‑error logs; niet source‑gerelateerd. |

## ⚠️ Nog openstaande issues
* 14 XML‑bestanden zijn ernstig afgekapt (<200 B) en veroorzaken resource‑build‑fouten. Herstel of vervang deze lay‑outs vóór productie‑build.
* `ContactAdapter.kt` is nog steeds afgekapt; compile‑fout volgt bij gebruik.
* Theme‑dialoog in `ChatActivity` bevat placeholder‑implementatie.