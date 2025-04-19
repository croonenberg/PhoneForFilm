import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Voorbeeld van het gebruiken van R
        binding.buttonSend.setText(R.string.send)  // Verwijst naar een string resource
        // Als je een menu-item wilt gebruiken
        val editItem: MenuItem = findViewById(R.id.menu_edit)  // Verwijst naar een menu item
    }
}
