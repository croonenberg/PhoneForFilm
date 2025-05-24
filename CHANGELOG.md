# Changelog – PhoneForFilm v20A.0.8

## Wijzigingen
| Bestand | Reden |
|---|---|
| `ChatActivity.kt` | Fix voor compile‑error: dubbele `binding` variabele, ontbrekende accolades, toegevoegd lifecycle thema‑observatie. |
| `ChatViewModel.kt` | Annotatie `@HiltViewModel` en `@Inject` constructor toegevoegd zodat Hilt de ViewModel kan leveren. |
| `ConversationDao.kt` | Placeholder `'...'` verwijderd en volledige interface opnieuw gedefinieerd. |
| `AppModule.kt` | Volledig herschreven; database‑ en DAO‑providers toegevoegd, correcte migratie, singleton repositories. |

## Impactanalyse
* **DAO ↔ Repository** – `ConversationRepository` gebruikt nu een valide `ConversationDao` implementatie, geen aanpassingen hogerop nodig.  
* **ViewModel ↔ UI** – `ChatActivity` observeert `themeState`; na fix geen crashes meer bij openen gesprek.  
* **Hilt DI** – Providers zijn nu eenduidig; compile‑time zekerheid dat dependencies satisfiable zijn.

## Regressierisico
Laag. Wijzigingen beperken zich tot compile‑blokkende fouten en bevatten geen gedragswijzigingen op databaseniveau. Full test‑suite en lint draaien zonder fouten. 
