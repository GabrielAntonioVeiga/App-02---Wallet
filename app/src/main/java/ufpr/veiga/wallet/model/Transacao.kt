package ufpr.veiga.wallet.model

data class Transacao(
    val id: Int = 0,
    val tipo: EnClassificacaoOperacao,
    val descricao: String,
    val valor: Double
) {
}