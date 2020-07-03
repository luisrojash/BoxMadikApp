package com.application.boxmadikv1.cliente.menu.tipopago.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.application.boxmadikv1.cliente.menu.tipopago.model.TipoPagoUi
import com.application.boxmadikv1.databinding.ItemTipoPagoBinding


class TipoPagoHolder private constructor(val binding: ItemTipoPagoBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
                inflater: LayoutInflater,
                parent: ViewGroup,
                attachToRoot: Boolean
        ): TipoPagoHolder = TipoPagoHolder(ItemTipoPagoBinding.inflate(inflater, parent, attachToRoot))
    }

    fun bind(
            cardViewModel: TipoPagoUi,
            onItemClickListener: ((cardView: CardView, cardViewModel: TipoPagoUi) -> Unit)?
    ) {
        binding.viewModel = cardViewModel

        binding.cardView.setOnClickListener {
            if (cardViewModel.title.equals("Yape.") || cardViewModel.title.equals("Plin.")) {
                onItemClickListener?.invoke(binding.cardView, cardViewModel)
            }
        }

        binding.textViewMontoPagar.text = "Monto a pagar S/." + cardViewModel.montoPagar
    }
}