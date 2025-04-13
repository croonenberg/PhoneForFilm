val btnChangeTheme = findViewById<Button>(R.id.btnChangeTheme)

btnChangeTheme.setOnClickListener {
    val themes = arrayOf("Greenroom", "Blue Stage", "Grey Card", "Neutral Light", "Darkroom")

    AlertDialog.Builder(this)
        .setTitle("Kies Thema")
        .setItems(themes) { _, which ->
            viewModel.setChatTheme(which)
        }
        .show()
}
