fun setChatTheme(themeId: Int) {
    CoroutineScope(Dispatchers.IO).launch {
        db.contactDao().updateTheme(1, themeId) // Aannemende dat je 1 contact gebruikt
    }
}
