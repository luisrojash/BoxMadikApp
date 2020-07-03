package com.application.boxmadikv1.cliente.menu.tipopago.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.application.boxmadikv1.cliente.menu.tipopago.model.TipoPagoUi


class TipoPagoAdapter(context: Context) : ListAdapter<TipoPagoUi, TipoPagoHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TipoPagoUi>() {
            override fun areItemsTheSame(oldItem: TipoPagoUi, newItem: TipoPagoUi): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: TipoPagoUi, newItem: TipoPagoUi): Boolean =
                    oldItem == newItem
        }
    }

    private val inflater = LayoutInflater.from(context)

    var onItemClickListener: ((cardView: CardView, cardViewModel: TipoPagoUi) -> Unit)? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TipoPagoHolder = TipoPagoHolder.create(inflater, parent, false)

    override fun onBindViewHolder(holder: TipoPagoHolder, position: Int) = holder.bind(getItem(position), onItemClickListener)
}
