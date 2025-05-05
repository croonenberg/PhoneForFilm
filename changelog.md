# Changelog v1.0.0_patched

## I. Quick-fixes
- XML namespaces toegevoegd (activity layouts).
- Hardcoded strings gemigreerd.

## II. Resource updates
- `strings.xml`: duplicate entries verwijderd (-10 items).

## III. Refactors
- Dependency-cycli gebroken via interfaces tussen layers.
- Code style: ktlint & detekt-clean run.

## IV. Validatie
- `./gradlew clean build` → ✅
- `./gradlew ktlintCheck detekt` → ✅
