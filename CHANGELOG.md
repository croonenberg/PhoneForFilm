# CHANGELOG – v20A.0.16-min (Fix AppModule imports)

## Fixed
* **di/AppModule.kt**
  * Corrected import path to `com.example.phoneforfilm.data.local.db.PhoneForFilmDatabase`.
  * Builder chain compiles; `fallbackToDestructiveMigration()` recognised.
  * Dao provider unchanged.

No duplicate ChatThemeRepository binding (repo provider still removed).

Generated 2025-05-24.
