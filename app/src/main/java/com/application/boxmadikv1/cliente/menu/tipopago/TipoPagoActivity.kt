package com.application.boxmadikv1.cliente.menu.tipopago

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.Person
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amyu.stack_card_layout_manager.StackCardLayoutManager
import com.application.boxmadikv1.R
import com.application.boxmadikv1.cliente.menu.tipopago.adapter.TipoPagoAdapter
import com.application.boxmadikv1.cliente.menu.tipopago.model.TipoPagoUi
import com.application.boxmadikv1.cliente.menu.tipopago.reportepago.ReportePagoActivity
import com.application.boxmadikv1.utils.Constantes
import kotlinx.android.synthetic.main.activity_tipo_pago.*

class TipoPagoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_pago)
        val montoPagar = intent.extras?.getString("montoPagar")
        Log.i("TipoPagoActivity: ", "montoPagar " + montoPagar)
        initAdapter(montoPagar)
    }

    private fun initAdapter(montoPagar: String?) {
        if (montoPagar != null) {
            val adapter = TipoPagoAdapter(applicationContext).apply {
                onItemClickListener = { cardView, cardViewModel ->
                    val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@TipoPagoActivity,
                            cardView,
                            cardView.transitionName
                    )
                    initStartActivity(cardViewModel)
                }
                submitList(
                        listOf(
                                TipoPagoUi(R.drawable.ic_omg,
                                        R.drawable.card_ejemplo_1,
                                        "Plin.",
                                        montoPagar
                                ),
                                TipoPagoUi(R.drawable.ic_nem,
                                        R.drawable.card_ejemplo_2,
                                        "MasterCard.",
                                        montoPagar
                                ),
                                TipoPagoUi(R.drawable.ic_bitcoin,
                                        R.drawable.card_ejemplo_5,
                                        "Bitcoin",
                                        montoPagar

                                ),
                                TipoPagoUi(R.drawable.ic_ripple,
                                        R.drawable.card_ejemplo_4,
                                        "Visa",
                                        montoPagar

                                ),
                                TipoPagoUi(R.drawable.ic_ethereum,
                                        R.drawable.card_ejemplo_3,
                                        "Yape.",
                                        montoPagar
                                )

                        )
                )
            }

            recicladorPagos.adapter = adapter
            recicladorPagos.layoutManager = StackCardLayoutManager(6)

            val itemDecor = ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                        override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                        ): Boolean = true.also {
                            val fromPos = viewHolder.adapterPosition
                            val toPos = target.adapterPosition
                            adapter.notifyItemMoved(fromPos, toPos)
                        }

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        }
                    })

            itemDecor.attachToRecyclerView(recicladorPagos)
        }

    }

    private fun initStartActivity(tipoPago: TipoPagoUi) {
        val initStartReportPago = Intent(this, ReportePagoActivity::class.java)
        initStartReportPago.putExtra("tipoEmpresa", tipoPago.title)
        initStartReportPago.putExtra("montoPagar", tipoPago.montoPagar)
        startActivity(initStartReportPago)
    }


}

