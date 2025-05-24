# CHANGELOG – v20A.0.21-min (Startscherm fix)

## Updated
* **adapter/ContactAdapter.kt**
  * Verwijderde ellips (`...`) en implementeerde volledige ViewHolder‑binding.
  * Laadt avatar via Coil met placeholder, triggert `onClick` callback.

* **view/MainActivity.kt**
  * Volledig ingevuld: adapter‑initialisatie, RecyclerView‑setup, contact‑flow observer.
  * Op `onClick` wordt `ChatActivity` gestart met `EXTRA_CONTACT_ID`.

Resultaat: startscherm toont nu dynamische contactenlijst i.p.v. lege UI.
