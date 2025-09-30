package ufpr.veiga.wallet.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.wallet.R
import ufpr.veiga.wallet.database.DBHelper

class CadastroActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DBHelper(this);

        val spinner = findViewById<Spinner>(R.id.spinner)
        val descricao = findViewById<EditText>(R.id.editTextText)
        val valor = findViewById<EditText>(R.id.editTextNumberDecimal)


    }
}