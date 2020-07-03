package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.dialogDatePicker.DatePickerFragment;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.local.CotizacionLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.remote.CotizacionRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.EnviarCotizacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.EnvioNotificacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerComision;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerTipoCambioDolar;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerValidacionCotizacion;
import com.application.boxmadikv1.extra.InternetCheck;
import com.bumptech.glide.Glide;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CotizacionFragment extends BaseFragment<CotizacionView, CotizacionPresenter> implements CotizacionView, InternetCheck.Consumer {

    public static final String TAG = CotizacionFragment.class.getSimpleName();

    public static final int COTIZACION_FRAGMENT = 10;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.editFechaFin)
    Button buttonFechaFin;
    @BindView(R.id.editFechaInicio)
    Button buttonFechaInicio;

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewDescripcion)
    TextView textViewNombeRubro;
    @BindView(R.id.textviewPromedio)
    TextView textViewPromedioCotizacion;

    @BindView(R.id.editTextMontoNeto)
    EditText editTextMontoNeto;
    @BindView(R.id.editTextComision)
    EditText editTextComision;

    @BindView(R.id.editLibre)
    EditText editTextTotalSoles;
    @BindView(R.id.editTextTotal)
    EditText editTextDolares;

    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextDescripcion;
    @BindView(R.id.btnCotizacion)
    Button buttonCotizacion;

    @BindView(R.id.textView31)
    TextView textViewDatosInformativo;

    //progress dialog
    private ProgressDialog progressDialog;


    public static CotizacionFragment newInstance(Bundle args) {
        CotizacionFragment fragment = new CotizacionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected CotizacionPresenter getPresenter() {
        CotizacionRepository cotizacionRepository = CotizacionRepository.getmInstance(new CotizacionLocal(
                InjectorUtils.provideComisionDao(),
                InjectorUtils.provideTipoCambioDao()
        ), new CotizacionRemote());
        return new CotizacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerComision(cotizacionRepository),
                new ObtenerTipoCambioDolar(cotizacionRepository),
                new EnviarCotizacion(cotizacionRepository),
                new ObtenerValidacionCotizacion(cotizacionRepository),
                new EnvioNotificacion(cotizacionRepository));
    }

    @Override
    protected CotizacionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_cotizacion_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    private FragmentActivity cotizacionFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cotizacionFragment = getActivity();
        progressDialog = new ProgressDialog(getActivity());
    }

    private void initViews() {
        InputFilter[] FilterArrayDni = new InputFilter[1];
        FilterArrayDni[0] = new InputFilter.LengthFilter(10);
        editTextMontoNeto.setFilters(FilterArrayDni);
    }

    @Override
    public void onStart() {
        super.onStart();
        new InternetCheck(CotizacionFragment.this, null);
        initViews();
    }

    @OnClick({R.id.editFechaInicio, R.id.editFechaFin, R.id.btnCotizacion})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.editFechaInicio:
                presenter.onClickFechaInicio();
                break;
            case R.id.editFechaFin:
                presenter.onClickFechaFin();
                break;
            case R.id.btnCotizacion:
                String descripcion = textInputEditTextDescripcion.getText().toString();
                String otros = "otros";
               // presenter.onEnviarCotizacion(Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(descripcion), otros).trim());
                presenter.onEnviarCotizacion(descripcion);
                break;
        }
    }

    @Override
    public void mostrarDialogFechaInicio() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondateDateInicio);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondateDateInicio = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            /*String dateTime = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);*/
            String dateTime = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(dayOfMonth);
            buttonFechaInicio.setText(dateTime);
            presenter.onAceptarDateInicio(dateTime);
            // Log.d(TAG,"ondateDateInicio : " + view.getd)
        }
    };

    @Override
    public void mostrarDialogFechaFin() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondateDateFin);
        date.show(getFragmentManager(), "Date Picker");
    }

    @Override
    public void mostrarDataInicial(ItemUi itemUi) {
        textViewPromedioCotizacion.setText("S/." + itemUi.getPromedioCotizacion());
        Glide.with(getActivity())
                .load(itemUi.getImagePropuesta())
                .into(imageViewRubro);
        textViewNombeRubro.setText(itemUi.getDescripcionRubro());

        editTextMontoNeto.addTextChangedListener(editTextMontoNetoWatcher);
        //editTextMontoNeto.setText("0.0");
    }

    @Override
    public void editTextComision(String resultado) {
        editTextComision.setText("Comisión: S./ " + resultado);
    }

    @Override
    public void editTextTotalSoles(String resultado) {
        editTextTotalSoles.setText("Total Soles : S./ " + resultado);
    }

    @Override
    public void ediTextTotalDolares(String resultado) {
        editTextDolares.setText("Total Dólares : $ " + resultado);
    }

    @Override
    public void mostrarMensajeEditTextMontoError(String s) {
      //  Toast.makeText(getActivity().getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        showToastSecond(s);
     /*   editTextMontoNeto.setError(s);
        editTextMontoNeto.requestFocus();*/
    }
    private Toast mToastToShow;
    public void showToastSecond(String mensaje) {
        // Set the toast and duration
        int toastDurationInMilliSeconds = 1000;
        mToastToShow = Toast.makeText(getActivity().getApplicationContext(), mensaje, Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 ) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();

        /* toast = Toast.makeText(getActivity().getApplicationContext(), mensaje,Toast.LENGTH_SHORT);
       // toast.show();
        new CountDownTimer(10000, 1000)
        {
            public void onTick(long millisUntilFinished) {toast.show();}
            public void onFinish() {toast.cancel();}
        }.start();*/
    }

    @Override
    public void mostrarMensajeEditTextDescripcionError(String s) {
        textInputEditTextDescripcion.setError(s);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(buttonFechaInicio, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarDialogProgressBar() {
        progressDialog.setMessage("Verificando la Cotizacion..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void limpiarTexto() {
        String fechaInicio = "Fecha Inicio";
        String fechaFin = "Fecha Fin";
        editTextMontoNeto.setText("");
        editTextComision.setText("");
        editTextTotalSoles.setText("");
        editTextDolares.setText("");
        textInputEditTextDescripcion.setText("");
        buttonFechaInicio.setText(fechaInicio);
        buttonFechaFin.setText(fechaFin);
        presenter.onAceptarDateInicio(fechaInicio);
        presenter.onAceptarDateFin(fechaFin);
    }

    @Override
    public void mostrarCamposLlenos(String montoNeto, String montoComision, String totalSoles, String totalDolares, String fechaInicio, String fechaFin, String descripcion) {
        InputFilter[] FilterArrayDni = new InputFilter[1];
        FilterArrayDni[0] = new InputFilter.LengthFilter(30);
        editTextMontoNeto.setFilters(FilterArrayDni);
        editTextMontoNeto.setText("Monto : S./ " + montoNeto);
        editTextMontoNeto.setEnabled(false);
        editTextComision.setText("Comisión : S./ " + montoComision);
        editTextComision.setEnabled(false);
        editTextTotalSoles.setText("Total Soles : S./ " + totalSoles);
        editTextTotalSoles.setEnabled(false);
        editTextDolares.setText("Total Dólares : $./ " + totalDolares);
        editTextDolares.setEnabled(false);
        buttonFechaInicio.setText(fechaInicio);
        buttonFechaInicio.setEnabled(false);
        buttonFechaFin.setText(fechaFin);
        buttonFechaFin.setEnabled(false);
        textInputEditTextDescripcion.setText(descripcion);
        textInputEditTextDescripcion.setEnabled(false);
        buttonCotizacion.setEnabled(false);
        buttonCotizacion.setBackgroundColor(getResources().getColor(R.color.md_grey_500));
    }

    @Override
    public void initStartActivityEspec() {
        Toast.makeText(getContext(), "Cotización enviada Correctamente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), MenuEspecialistaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void initStartActivitySeleccionarUser() {
        Toast.makeText(getContext(), "Cotización enviada Correctamente", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void mostrarDialogMensaje(String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Problemas Conexión")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(mensaje)
                .setNegativeButton("Intentarlo Nuevamente", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        Intent intent = new Intent(getContext(), MenuEspecialistaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                /*.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        //presenter.onClickRenuevaDatosGenerales(inputValidation.isNetworkAvailable());
                        dialoginterface.cancel();
                        Intent intent = new Intent(getContext(), MenuEspecialistaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        //new InternetCheck(CotizacionFragment.this, null);
                    }
                }).show();*/
    }

    @Override
    public void habilitarButtonCotizacion() {
        buttonCotizacion.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonCotizacion() {
        buttonCotizacion.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonOficioPersonal() {
        editTextComision.setText("Comisión: S./ 0.0");
        editTextMontoNeto.setEnabled(false);
        editTextComision.setEnabled(false);
        editTextTotalSoles.setEnabled(false);
    }

    @Override
    public void habilitartButtonOficioPersonal() {

    }

    @Override
    public void mostrareditTextMontoNeto(String montoNeto) {
        editTextMontoNeto.setText("Monto : " + montoNeto);
        editTextMontoNeto.setEnabled(false);
    }

    @Override
    public void textViewMostrarInformacion(String fecha_de_la_entrevista) {
        textViewDatosInformativo.setText(fecha_de_la_entrevista);
    }

    @Override
    public void mostrarMensajeEditTextMontoTotalSolesError(String s) {
        editTextTotalSoles.setError(s);
    }


    private final TextWatcher editTextMontoNetoWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // textView.setVisibility(View.VISIBLE);
            Log.d(TAG, " onTextChanged ;  " + s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            //  editTextMontoNeto.setText("Monto Neto: S./ " + s);
            presenter.onTextChanged(s);
        }

    };

    DatePickerDialog.OnDateSetListener ondateDateFin = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            /*String dateTime = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);*/
            String dateTime = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(dayOfMonth);
            //String mes = new DateFormatSymbols().getShortMonths()[monthOfYear];
            //String mes = new DateFormatSymbols().getMonths()[monthOfYear];
            buttonFechaFin.setText(dateTime);
            presenter.onAceptarDateFin(dateTime);
        }
    };


    @Override
    public void accept(Boolean internet, Object objeto) {
        if (presenter != null) presenter.onCheckConexion(internet);
    }
}
