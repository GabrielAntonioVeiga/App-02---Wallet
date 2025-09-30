package ufpr.veiga.wallet.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ufpr.veiga.wallet.R
import ufpr.veiga.wallet.database.DBHelper
import ufpr.veiga.wallet.model.EnClassificacaoOperacao
import ufpr.veiga.wallet.model.Transacao

class ExtratoActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransacaoAdapter
    private lateinit var tvSaldo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_extrato)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainExtrato)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerExtrato)
        recyclerView.layoutManager = LinearLayoutManager(this)
        tvSaldo = findViewById(R.id.tvSaldo)

        carregarLista()

        findViewById<Button>(R.id.btnDebito).setOnClickListener {
            val lista = dbHelper.listAll().filter { it.tipo == EnClassificacaoOperacao.DEBITO }
            atualizarLista(lista)
        }

        findViewById<Button>(R.id.btnCredito).setOnClickListener {
            val lista = dbHelper.listAll().filter { it.tipo == EnClassificacaoOperacao.CREDITO }
            atualizarLista(lista)
        }

        findViewById<Button>(R.id.btnTodos).setOnClickListener {
            carregarLista()
        }
    }

    private fun carregarLista() {
        val lista = dbHelper.listAll()
        atualizarLista(lista)
    }

    private fun atualizarLista(lista: List<Transacao>) {
        adapter = TransacaoAdapter(lista)
        recyclerView.adapter = adapter

        val saldo = lista.sumOf { transacao ->
            if (transacao.tipo == EnClassificacaoOperacao.CREDITO) transacao.valor
            else -transacao.valor
        }
        tvSaldo.text = "Saldo: R$ %.2f".format(saldo)

        if (saldo < 0) {
            tvSaldo.setTextColor(Color.RED)
        } else {
            tvSaldo.setTextColor(Color.GREEN)
        }
    }
}
