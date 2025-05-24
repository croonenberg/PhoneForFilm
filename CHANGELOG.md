
# Changelog v20A.0.11 (code-complete)

## 🔄 Gewijzigde bestanden
- **app/src/main/java/com/example/phoneforfilm/di/AppModule.kt**  
  *Volledige Hilt-DI‑module geschreven; voorziet DB, DAO’s en repositories.*

- **app/src/main/java/com/example/phoneforfilm/adapter/ContactAdapter.kt**  
  *Volledig bind‑ & click‑ready; gebruikt Coil voor avatar‑load.*

- **app/src/main/java/com/example/phoneforfilm/ui/chat/ChatAdapter.kt**  
  *DiffUtil‑gedreven ListAdapter voor berichten.*

- **app/src/main/java/com/example/phoneforfilm/ui/chat/ChatViewModel.kt**  
  *Volwaardige VM met Flow van messages en thema‑state.*

- **app/src/main/java/com/example/phoneforfilm/ui/chat/ChatActivity.kt**  
  *UI gekoppeld aan ViewModel, menu‑gestuurde thema‑wissel, auto‑scroll.*

- **app/src/main/java/com/example/phoneforfilm/view/MainActivity.kt**  
  *Observeert `ContactViewModel`, opent chats.*

- **res/menu/menu_chat.xml**  
  *Toolbar‑menu met “Thema”.*

- **res/values/strings_chat.xml**  
  *Nieuwe string `"action_theme"`. *

- **res/layout** (18 stuks)  
  *Volledige resource‑herstel uit v20A.0.10.*

## 📝 Impact
* Build compileert weer: ontbrekende DI‑bindings en resource‑errors opgelost.  
* Thema‑wissel werkt via toolbar‑menu.  
* Contactlijst vult zich uit Room‑DB; klik opent Chat.  
* Chat‑berichtenstromen live, scroll‑to‑bottom bij nieuwe message.  

## 🛡️ Regressie‑preventie
* Enkel toevoegingen / complete herschrijvingen van stub‑files.  
* Bestaande API‑signatures en package‑structuur ongewijzigd.  
* Alle nieuwe resources namespace‑safe; geen ID‑conflicts.  

