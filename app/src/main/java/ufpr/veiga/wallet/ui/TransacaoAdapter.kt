package ufpr.veiga.wallet.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ufpr.veiga.wallet.R
import ufpr.veiga.wallet.model.Transacao

class TransacaoAdapter(private val transacoes: List<Transacao>) :
    RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    class TransacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTipo: TextView = itemView.findViewById(R.id.tvTipo)
        val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)
        val tvValor: TextView = itemView.findViewById(R.id.tvValor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transacao, parent, false)
        return TransacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = transacoes[position]
        holder.tvTipo.text = transacao.tipo.name
        holder.tvDescricao.text = transacao.descricao
        holder.tvValor.text = "R$ %.2f".format(transacao.valor)
    }

    override fun getItemCount(): Int = transacoes.size
}
