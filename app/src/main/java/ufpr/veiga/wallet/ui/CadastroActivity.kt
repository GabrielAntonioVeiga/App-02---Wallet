package ufpr.veiga.wallet.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ufpr.veiga.wallet.R
import ufpr.veiga.wallet.database.DBHelper
import ufpr.veiga.wallet.model.EnClassificacaoOperacao
import ufpr.veiga.wallet.model.Transacao
import java.util.Objects

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

        val opcoes = arrayOf("Selecione", "Crédito", "Débito")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        spinner.adapter = adapter
    }

    fun salvar(view: View) {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val descricao = findViewById<EditText>(R.id.editTextText)
        val valor = findViewById<EditText>(R.id.editTextNumberDecimal)


        val opcaoSelecionado = spinner.selectedItem.toString()
        val descricaoText = descricao.text.toString()
        val valorDouble = valor.text.toString().toDoubleOrNull()

        if (opcaoSelecionado == "Selecione" || descricaoText.isBlank() || valorDouble == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            return
        }

        val tipo = if (opcaoSelecionado == "Crédito") EnClassificacaoOperacao.CREDITO else EnClassificacaoOperacao.DEBITO

        dbHelper.insertTransacao(
            Transacao(
                tipo = tipo,
                descricao = descricaoText,
                valor = valorDouble
            )
        )
        Toast.makeText(this, "Transação salva!", Toast.LENGTH_SHORT).show()
    }
}