package com.application.boxmadikv1.chat.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.chat.entidad.MensajesUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatHolder extends RecyclerView.ViewHolder {
    public static final String TAG = ChatHolder.class.getSimpleName();

    @BindView(R.id.txt_day_group)
    TextView textViewFechaGrupal;
    @BindView(R.id.message)
    TextView textViewMensaje;
    @BindView(R.id.timestamp)
    TextView textViewTimeStamp;

    private static String today;

    public ChatHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MensajesUi mensajesUi) {
        textViewMensaje.setText(mensajesUi.getMensaje());

        textViewTimeStamp.setText(mensajesUi.getFecha()+ "");
        Log.d(TAG, "mensajesUi.getFecha() "+mensajesUi.getFecha());
    }


    public static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";
        today = today.length() < 2 ? "0" + today : today;
        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a") : new SimpleDateFormat("dd LLL, hh:mm a");
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }



}
