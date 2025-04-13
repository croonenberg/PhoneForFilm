@Query("UPDATE Contact SET themeId = :themeId WHERE id = :contactId")
fun updateTheme(contactId: Int, themeId: Int)
