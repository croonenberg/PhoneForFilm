val btnLanguage = findViewById<Button>(R.id.btnLanguage)

btnLanguage.setOnClickListener {
    val languages = arrayOf("English", "Nederlands", "Deutsch", "Français", "Español")

    AlertDialog.Builder(this)
        .setTitle("Choose Language")
        .setItems(languages) { _, which ->
            viewModel.setLanguage(this, which)
        }
        .show()
}
