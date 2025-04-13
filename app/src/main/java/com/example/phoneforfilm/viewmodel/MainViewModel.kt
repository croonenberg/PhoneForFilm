fun setLanguage(context: Context, languageCode: Int) {
    val locale = when (languageCode) {
        0 -> "en"
        1 -> "nl"
        2 -> "de"
        3 -> "fr"
        4 -> "es"
        else -> "en"
    }

    val config = context.resources.configuration
    config.setLocale(Locale(locale))
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
    (context as Activity).recreate()
}
