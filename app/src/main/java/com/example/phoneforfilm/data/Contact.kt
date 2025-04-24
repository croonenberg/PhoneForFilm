
package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Contact saved locally – each row optionally links back to the real Android
 * Contacts row via [androidContactId].  A thumbnail photo URI is stored when
 * available so we can render an avatar in lists.
 */
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val androidContactId: Long? = null,
    val name: String,
    val phoneNumber: String,
    val photoUri: String? = null
)
