package ufpr.veiga.wallet.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ufpr.veiga.wallet.R
import ufpr.veiga.wallet.database.DBHelper

class ExtratoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_extrato)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainExtrato)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbHelper = DBHelper(this)
        val lista = dbHelper.listAll()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerExtrato)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TransacaoAdapter(lista)
    }
}
