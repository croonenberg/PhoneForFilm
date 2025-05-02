# PhoneForFilm NL

Pro Chat en Call Simulator voor Filmsets

Phone For Film is een Android-app speciaal ontwikkeld voor de filmindustrie.  
Simuleer eenvoudig:

- Inkomende oproepen
- Chat gesprekken
- Thema wisselen per chat (Greenroom, Blue Stage, Grey Card, Neutral Light, Darkroom)
- Verander taal direct in de app
- Pas schermhelderheid aan met een slider
- Scherm wordt zwart bij het oor (proximity sensor)
- Berichten opslaan en later aanpassen
- Werkt volledig offline

## Licentie

Phone For Film © 2025 — Art Department Tools  
Gebruik binnen eigen productie is toegestaan volgens voorwaarden.

Werk je aan meerdere projecten binnen je licentieperiode? Geen probleem, je mag de toestellen gewoon
gebruiken zolang je ze zelf beheert.

---

# PhoneForFilm EN

Pro Chat and Call Simulator for Filmmakers

Phone For Film is an Android app specially made for the film industry.

Simulate easily:

- Incoming call simulation
- Chat simulation
- Switch themes per chat (Greenroom, Blue Stage, Grey Card, Neutral Light, Darkroom)
- Change language directly in the app
- Screen brightness control with slider
- Screen turns black when held to ear (proximity sensor)
- Save and edit messages later
- Fully offline functionality

## Licensing

Phone For Film © 2025 — Art Department Tools  
Use within your own production is permitted in accordance with the terms and conditions.

Working on multiple productions within your license period? No problem, you can use the devices as
long as you manage them yourself.

## Changelog - Unified Chat Bubbles
- Unified sent/received chat bubble drawables: now using `background_chat_bubble_sent.xml` and `background_chat_bubble_received.xml` with standardized radius and padding.
- Removed deprecated drawables: `bg_message_sent.xml`, `bg_message_received.xml`, and unused `bubble_*.xml`.
- Updated item_message layouts to reference the unified drawables.

## v18.0.8 Enhancements
- Added vector‐based chat bubbles with tail shapes for sent/received messages.
- Unified text coloring using theme attributes (`colorOnPrimary`, `colorOnSurface`).
- Added timestamp (`tvSentTime`, `tvReceivedTime`) and status icons binding in adapter.
- Refactored `MessageAdapter` to support sent vs received view types.
