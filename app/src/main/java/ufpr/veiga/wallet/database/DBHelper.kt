package ufpr.veiga.wallet.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ufpr.veiga.wallet.model.EnClassificacaoOperacao
import ufpr.veiga.wallet.model.Transacao

class DBHelper(context: Context): SQLiteOpenHelper(context, "wallet.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tipo TEXT NOT NULL,
                descricao TEXT NOT NULL,
                valor REAL NOT NULL
            )
        """.trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS transactions")
        onCreate(db)
    }

    fun insertTransacao(transacao: Transacao): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("tipo", transacao.tipo.name)
            put("descricao", transacao.descricao)
            put("valor", transacao.valor)
        }
        return db.insert("transactions", null, values)
    }

    fun listAll(): List<Transacao> {
        val db = this.readableDatabase
        val cursor = db.query("transactions", arrayOf("id", "tipo", "descricao", "valor"),
            null, null, null, null, null)

        val transactions = ArrayList<Transacao>()
        with(cursor) {
            while (moveToNext()) {
                val id: Int = getInt(getColumnIndexOrThrow("id"))
                val type: String = getString(getColumnIndexOrThrow("tipo"))
                val description: String = getString(getColumnIndexOrThrow("descricao"))
                val value: Double = getDouble(getColumnIndexOrThrow("valor"))

                val transaction = Transacao(
                    id = id,
                    tipo = EnClassificacaoOperacao.valueOf(type),
                    descricao = description,
                    valor = value
                )

                transactions.add(transaction)
            }
        }
        cursor.close()
        db.close()
        return transactions
    }
}
