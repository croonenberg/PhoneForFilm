# CHANGELOG – v20A.0.27-min (Deprecation warnings opgelost)

## Updated
* **di/AppModule.kt**
  * `fallbackToDestructiveMigration(true)` i.p.v. gedepr. no-arg variant.
* **settings/SettingsRepository.kt**
  * Verwijderd import `android.preference.PreferenceManager` (deprecated).
  * Nu gebruik van `androidx.preference.PreferenceManager` via reguliere import.

Geen functionele wijzigingen, alleen warning-cleanup.

Generated 2025-05-24.
