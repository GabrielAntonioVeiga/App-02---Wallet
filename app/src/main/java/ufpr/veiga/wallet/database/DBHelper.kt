package ufpr.veiga.wallet.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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

}
