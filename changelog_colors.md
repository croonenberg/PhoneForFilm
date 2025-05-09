# Patch v19.9.9.9 - Add Missing Color Resources

## Added to values/colors.xml
- Defined `white` (#FFFFFF) and `black` (#000000).
- Aliased `greenroom_primary` & `greenroom_secondary` to `whatsapp_green`.
- Aliased `bluestage_primary` & `bluestage_secondary` to `whatsapp_blue`.
- Defined `greycard_primary`/`secondary`, `neutral_light_primary`/`secondary`, `darkroom_primary`/`secondary`, `primary`, and `secondary`.

## Added to values-night/colors.xml
- Defined `white` and `black` for night mode.
- Aliased `greenroom_primary` & `greenroom_secondary` to `whatsapp_green_dark`.
- Aliased `bluestage_primary` & `bluestage_secondary` to `whatsapp_blue_dark`.
- Defined `greycard_primary`/`secondary`, `neutral_light_primary`/`secondary`, `darkroom_primary`/`secondary`, `primary`, and `secondary` for night.

**Reason**: Resolve unresolved symbol errors in `themes.xml` by providing all referenced colors.
