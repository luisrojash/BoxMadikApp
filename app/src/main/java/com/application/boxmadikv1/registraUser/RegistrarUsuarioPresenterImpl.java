package com.application.boxmadikv1.registraUser;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;
import com.application.boxmadikv1.registraUser.entidad.UserUi;
import com.application.boxmadikv1.registraUser.useCase.ListarTipoDocumento;
import com.application.boxmadikv1.registraUser.useCase.ListarTipoPais;
import com.application.boxmadikv1.registraUser.useCase.RegistrarUsuario;
import com.theartofdev.edmodo.cropper.CropImage;
import com.application.boxmadikv1.utils.Constantes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.application.boxmadikv1.registraUser.RegistrarUserActivity.SECOND_ACTIVITY_REQUEST_CODE;

public class RegistrarUsuarioPresenterImpl extends BaseActivityPresenterImpl<RegistrarUsuarioView> implements RegistrarUsuarioPresenter {

    public static final String REGISTRO_USUARIO_CORRECTAMENTE = "RegistrarUsuarioPresenterImpl";
    public static final String TAG = RegistrarUsuarioPresenterImpl.class.getSimpleName();
    List<File> mFileList;
    RegistrarUsuario registrarUsuario;
    RegistrarUserActivity registrarUserActivity;
    ListarTipoDocumento listarTipoDocumento;
    ListarTipoPais listarTipoPais;

    public RegistrarUsuarioPresenterImpl(UseCaseHandler handler, Resources res, RegistrarUserActivity registrarUserActivity, RegistrarUsuario registrarUsuario, ListarTipoDocumento listarTipoDocumento, ListarTipoPais listarTipoPais) {
        super(handler, res);
        this.registrarUsuario = registrarUsuario;
        this.registrarUserActivity = registrarUserActivity;
        this.listarTipoDocumento = listarTipoDocumento;
        this.listarTipoPais = listarTipoPais;
        mFileList = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.initSpinnerAdapter();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //   initUseCaseTipoDocumentoList();
        initUseCaseTipoPaisList();
        if (view != null) view.obtenerAnioActual();
    }

    private void initUseCaseTipoPaisList() {
        handler.execute(listarTipoPais, new ListarTipoPais.RequestValues(),
                new UseCase.UseCaseCallback<ListarTipoPais.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoPais.ResponseValue response) {
                        if (view != null)
                            view.mostrarListaSpinnerTipoPais(response.getTipoPais());
                        /*for (int i = 0; i < response.getTipoPais().size(); i++) {
                            TipoPaisUi tipoPaisUi = response.getTipoPais().get(i);
                            if (tipoPaisUi.getIdTipoPais().equals("51")) {
                                Log.d(TAG, "TIPO::PERU obtener la position" +i);
                                return;
                            }
                        }*/

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseTipoDocumentoList(String tipoPais) {
        Log.d(TAG, "initUseCaseTipoDocumentoList : " + tipoPais);
        handler.execute(listarTipoDocumento, new ListarTipoDocumento.RequestValues(tipoPais),
                new UseCase.UseCaseCallback<ListarTipoDocumento.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoDocumento.ResponseValue response) {
                        if (view != null)
                            view.mostrarListaSpinnerTipoDocumento(response.getDocumentoUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String tipoDocEmpty;

    @Override
    public void onRegistrarUsuario(final UserUi userUi) {
        if (view != null) view.deshabilitarButtonRegistrar();
        if (uriUrl.equals("") || uriUrl.isEmpty() || uriUrl == null) {
            if (view != null) {
                view.mostrarMensaje("Por favor seleccione una foto de perfil");
                view.habilitarButtonRegistrar();
            }
            return;
        } else if (tipoPais.equals("0")) {
            if (view != null) {
                view.mostrarMensaje("Pais Vacio");
                view.habilitarButtonRegistrar();
            }
            return;
        } else if (tipoDocumento.equals("30")) {
            if (view != null) {
                view.mostrarMensaje("Documento Vacio");
                view.habilitarButtonRegistrar();
            }
            return;
        } else {
            validacionTipoDocumentosTamanios(tipoDocumento, userUi);
        }

    }

    private void validacionTipoDocumentosTamanios(String tipoDoc, UserUi userUi) {
        Log.d(TAG, "userUi : " + userUi.getTipoDoc().length());
        switch (tipoDoc) {//TipoDocumento
            case "0": //Otros
                // validacionTipoDocumentos(userUi.getTipoDoc(), 14, userUi);*/
                validarNumerMaxTipoDocumento(userUi.getTipoDoc(), 14, userUi);
                break;
            case "1"://Libreta Lectoral

                // validacionTipoDocumentos(userUi.getTipoDoc(), 7, userUi);
                validarNumerMaxTipoDocumento(userUi.getTipoDoc(), 7, userUi);
                break;
            case "4"://CarnetExtranjeria
                // validarNumerMaxTipoDocumento(userUi.getTipoDoc(),12,userUi);
                // validacionTipoDocumentos(userUi.getTipoDoc(), 19, userUi);
                validarMinimoYMaximo(userUi.getTipoDoc(), userUi);
                break;
            case "6"://ruc
                validacionTipoDocumentos(userUi.getTipoDoc(), 10, userUi);
                break;
            case "7":
                //validacionTipoDocumentos(userUi.getTipoDoc(), 11, userUi);
                validarMinimoYMaximo(userUi.getTipoDoc(), userUi);
                break;
            case "11":
                validacionTipoDocumentos(tipoDoc, 14, userUi);
                break;

        }
    }

    private void validarMinimoYMaximo(String tipoDoc, UserUi userUi) {
        Log.d(TAG, "userUi.getTipoDoc().length()" + userUi.getTipoDoc().length());
        //4>=1
        if (userUi.getTipoDoc().length() < 2) {
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Tiene que ser mayor a 1 digitos*");
                view.habilitarButtonRegistrar();
            }

            return;
        }
        if (userUi.getTipoDoc().length() > 12) {
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Tiene que ser menor a 12 digitos*");
                view.habilitarButtonRegistrar();
            }

            return;
        }
        validacionTipoDocumentos(tipoDoc, 0, userUi);
    }

    private void validarNumerMaxTipoDocumento(String numeroLeng, int numeroMax, UserUi userUi) {
        if (numeroLeng.length() <= numeroMax) {
            int countSuma = numeroMax + 1;
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Deben ser " + countSuma + " digitos*");
                view.habilitarButtonRegistrar();
            }
            return;
        }
        validacionTipoDocumentos(numeroLeng, numeroMax, userUi);
    }


    private void validacionTipoDocumentos(String tipoDocumentoNombre, int numeroMax, UserUi userUi) {
        Log.d(TAG, "tipoDocumento : " + tipoDocumentoNombre);
        if (tipoDocumentoNombre.isEmpty() || tipoDocumentoNombre.equals(" ") || tipoDocumentoNombre == null) {
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Ingrese Numero*");
                view.habilitarButtonRegistrar();
            }
            return;
        } else {
            String pass = "";
            pass = userUi.getClave().replace(" ", "1");

            if (tipoDocumento.equals("6")) {//TipoDocumento es Ruc

                if (tipoDocumentoNombre.length() <= numeroMax) {
                    int countSuma = numeroMax + 1;
                    if (view != null) {
                        view.mostrarErrorEditTextTipoDocumento("Deben ser " + countSuma + " digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getRazonSocial().isEmpty()) {
                    if (view != null) {
                        view.mostrarErrorEditTextRazonSocial("Ingrese Razon Social");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getEmail().isEmpty() || userUi.getEmail().trim().isEmpty() || userUi.getEmail().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese Correo*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (!Constantes.validarEmail(userUi.getEmail())) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese un Correo Correcto*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().isEmpty()) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese Clave*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave() != pass) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese una clave sin espacios en blanco*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().length() <= 5) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese un numero de 6 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().isEmpty() || userUi.getCelular().trim().isEmpty() || userUi.getCelular().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese Celular*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().length() <= 8) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese un numero de 9 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (aceptarTermino == true) {
                    if (view != null) {
                        view.mostrarMensaje("Acepte los Terminos y Condiciones!");
                        view.habilitarButtonRegistrar();
                    }
                } else {
                    Log.d(TAG, "INICIA REGISTROS");
                    userUi.setTipoIdDocumento(tipoDocumento);
                    userUi.setTipoIDPais(tipoPais);
                    // String dateTimeCumple = userUi.getAnio() + "-" + userUi.getMes() + "-" + userUi.getDia();
                    String dateTimeCumple = "0000-00-00";
                    File file = new File(uriComprimida.getPath());
                    RequestBody requestFile = RequestBody.create(MediaType.parse(Constantes.getMimeType(file.getPath())), file);
                    RequestBody requestBodyTipoDoc = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoDoc());
                    RequestBody requestBodyNombre = RequestBody.create(MediaType.parse("text/plain"), userUi.getNombre());
                    RequestBody requestBodyApellidos = RequestBody.create(MediaType.parse("text/plain"), userUi.getApellidoPaterno());
                    RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userUi.getEmail());
                    RequestBody requestBodyClave = RequestBody.create(MediaType.parse("text/plain"), userUi.getClave());
                    RequestBody requestBodyCelular = RequestBody.create(MediaType.parse("text/plain"), userUi.getCelular());
                    RequestBody requestBodyTipoDocumentoId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIdDocumento());
                    RequestBody requestBodyTipoPaisId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIDPais());
                    RequestBody requestBodyDateTimeCumple = RequestBody.create(MediaType.parse("text/plain"), dateTimeCumple);
                    RequestBody requestBodyRazonSocial = RequestBody.create(MediaType.parse("text/plain"), userUi.getRazonSocial());

                    if (view != null) view.mostrarProgressBarDialog();
                    initGuardarDatosUsuario(requestFile, requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail,
                            requestBodyClave, requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId, requestBodyDateTimeCumple, requestBodyRazonSocial);

                }
            } else {
                Log.d(TAG, "dateTimeActual : " + dateTimeActual +
                        "dateCumpple : " + dateCumpple);
                if (userUi.getNombre().isEmpty() || userUi.getNombre().trim().isEmpty() || userUi.getNombre().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextNombre("Ingrese Nombre*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getApellidoPaterno().isEmpty() || userUi.getApellidoPaterno().trim().isEmpty() || userUi.getApellidoPaterno().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextApellidos("Ingrese Apellido*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getEmail().isEmpty() || userUi.getEmail().trim().isEmpty() || userUi.getEmail().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese Correo*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (!Constantes.validarEmail(userUi.getEmail())) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese un Correo Correcto*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().isEmpty()) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese Clave*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave() != pass) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese una clave sin espacios en blanco*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().length() <= 5) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese un numero de 6 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().isEmpty() || userUi.getCelular().trim().isEmpty() || userUi.getCelular().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese Celular*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().length() <= 8) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese un numero de 9 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                    //dateTimeActual, dateCumpple
                } else if (dateCumpple == null) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese Fecha Nacimiento");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (!Constantes.CheckDatesNow(dateCumpple, dateTimeActual)) {
                    if (view != null) {
                        view.mostrarMensaje("La fecha es mayor a la actual");
                        view.habilitarButtonRegistrar();
                    }

                } else if (validateEdad < 18) {
                    if (view != null) {
                        view.mostrarMensaje("Usted es menor de edad");
                        view.habilitarButtonRegistrar();
                    }
                } else if (aceptarTermino == true) {
                    if (view != null) {
                        view.mostrarMensaje("Acepte los Terminos y Condiciones!");
                        view.habilitarButtonRegistrar();
                    }
                } else {
                    Log.d(TAG, "INICIA REGISTROS");
                    userUi.setTipoIdDocumento(tipoDocumento);
                    userUi.setTipoIDPais(tipoPais);
                    String dateTimeCumple = dateCumpple;
                    File file = new File(uriComprimida.getPath());
                    RequestBody requestFile = RequestBody.create(MediaType.parse(Constantes.getMimeType(file.getPath())), file);
                    RequestBody requestBodyTipoDoc = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoDoc());
                    RequestBody requestBodyNombre = RequestBody.create(MediaType.parse("text/plain"), userUi.getNombre());
                    RequestBody requestBodyApellidos = RequestBody.create(MediaType.parse("text/plain"), userUi.getApellidoPaterno());
                    RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userUi.getEmail());
                    RequestBody requestBodyClave = RequestBody.create(MediaType.parse("text/plain"), userUi.getClave());
                    RequestBody requestBodyCelular = RequestBody.create(MediaType.parse("text/plain"), userUi.getCelular());
                    RequestBody requestBodyTipoDocumentoId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIdDocumento());
                    RequestBody requestBodyTipoPaisId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIDPais());
                    RequestBody requestBodyDateTimeCumple = RequestBody.create(MediaType.parse("text/plain"), dateTimeCumple);
                    RequestBody requestBodyRazonSocial = RequestBody.create(MediaType.parse("text/plain"), userUi.getRazonSocial());

                    if (view != null) view.mostrarProgressBarDialog();
                    initGuardarDatosUsuario(requestFile, requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail,
                            requestBodyClave, requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId, requestBodyDateTimeCumple, requestBodyRazonSocial);

                }


                Log.d(TAG, "Correctamente : ");
            }

        }


        /*

        if (tipoDocumentoNombre.isEmpty() || tipoDocumentoNombre.equals(" ") || tipoDocumentoNombre == null) {
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Ingrese Numero*");
                view.habilitarButtonRegistrar();
            }
            return;
        } else if (tipoDocumentoNombre.length() <= numeroMax) {
            int countSuma = numeroMax + 1;
            if (view != null) {
                view.mostrarErrorEditTextTipoDocumento("Deben ser " + countSuma + " digitos*");
                view.habilitarButtonRegistrar();
            }
            return;
        } else {
            String pass = "";
            pass = userUi.getClave().replace(" ", "1");

            if (tipoDocumento.equals("6")) {//TipoDocumento es Ruc
                if (userUi.getRazonSocial().isEmpty()) {
                    if (view != null) {
                        view.mostrarErrorEditTextRazonSocial("Ingrese Razon Social");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getEmail().isEmpty() || userUi.getEmail().trim().isEmpty() || userUi.getEmail().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese Correo*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (!Constantes.validarEmail(userUi.getEmail())) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese un Correo Correcto*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().isEmpty()) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese Clave*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave() != pass) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese una clave sin espacios en blanco*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().length() <= 5) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese un numero de 6 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().isEmpty() || userUi.getCelular().trim().isEmpty() || userUi.getCelular().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese Celular*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().length() <= 8) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese un numero de 9 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (aceptarTermino == true) {
                    if (view != null) {
                        view.mostrarMensaje("Acepte los Terminos y Condiciones!");
                        view.habilitarButtonRegistrar();
                    }
                } else {
                    Log.d(TAG, "INICIA REGISTROS");
                    userUi.setTipoIdDocumento(tipoDocumento);
                    userUi.setTipoIDPais(tipoPais);
                    // String dateTimeCumple = userUi.getAnio() + "-" + userUi.getMes() + "-" + userUi.getDia();
                    String dateTimeCumple = "0000-00-00";
                    File file = new File(uriComprimida.getPath());
                    RequestBody requestFile = RequestBody.create(MediaType.parse(Constantes.getMimeType(file.getPath())), file);
                    RequestBody requestBodyTipoDoc = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoDoc());
                    RequestBody requestBodyNombre = RequestBody.create(MediaType.parse("text/plain"), userUi.getNombre());
                    RequestBody requestBodyApellidos = RequestBody.create(MediaType.parse("text/plain"), userUi.getApellidoPaterno());
                    RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userUi.getEmail());
                    RequestBody requestBodyClave = RequestBody.create(MediaType.parse("text/plain"), userUi.getClave());
                    RequestBody requestBodyCelular = RequestBody.create(MediaType.parse("text/plain"), userUi.getCelular());
                    RequestBody requestBodyTipoDocumentoId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIdDocumento());
                    RequestBody requestBodyTipoPaisId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIDPais());
                    RequestBody requestBodyDateTimeCumple = RequestBody.create(MediaType.parse("text/plain"), dateTimeCumple);
                    RequestBody requestBodyRazonSocial = RequestBody.create(MediaType.parse("text/plain"), userUi.getRazonSocial());

                    if (view != null) view.mostrarProgressBarDialog();
                    initGuardarDatosUsuario(requestFile, requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail,
                            requestBodyClave, requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId, requestBodyDateTimeCumple, requestBodyRazonSocial);

                }
            } else {

                if (userUi.getNombre().isEmpty() || userUi.getNombre().trim().isEmpty() || userUi.getNombre().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextNombre("Ingrese Nombre*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getApellidoPaterno().isEmpty() || userUi.getApellidoPaterno().trim().isEmpty() || userUi.getApellidoPaterno().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextApellidos("Ingrese Apellido*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getEmail().isEmpty() || userUi.getEmail().trim().isEmpty() || userUi.getEmail().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese Correo*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (!Constantes.validarEmail(userUi.getEmail())) {
                    if (view != null) {
                        view.mostrarErrorEditTextEmail("Ingrese un Correo Correcto*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().isEmpty()) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese Clave*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave() != pass) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese una clave sin espacios en blanco*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getClave().length() <= 5) {
                    if (view != null) {
                        view.mostrarMensaje("Ingrese un numero de 6 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().isEmpty() || userUi.getCelular().trim().isEmpty() || userUi.getCelular().equals(" ")) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese Celular*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (userUi.getCelular().length() <= 8) {
                    if (view != null) {
                        view.mostrarErrorEditTextCelular("Ingrese un numero de 9 digitos*");
                        view.habilitarButtonRegistrar();
                    }
                    return;
                } else if (validateEdad < 18) {
                    if (view != null) {
                        view.mostrarMensaje("Usted es menor de edad");
                        view.habilitarButtonRegistrar();
                    }
                } else if (aceptarTermino == true) {
                    if (view != null) {
                        view.mostrarMensaje("Acepte los Terminos y Condiciones!");
                        view.habilitarButtonRegistrar();
                    }
                } else {
                    Log.d(TAG, "INICIA REGISTROS");
                    userUi.setTipoIdDocumento(tipoDocumento);
                    userUi.setTipoIDPais(tipoPais);
                    String dateTimeCumple = dateCumpple;
                    File file = new File(uriComprimida.getPath());
                    RequestBody requestFile = RequestBody.create(MediaType.parse(Constantes.getMimeType(file.getPath())), file);
                    RequestBody requestBodyTipoDoc = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoDoc());
                    RequestBody requestBodyNombre = RequestBody.create(MediaType.parse("text/plain"), userUi.getNombre());
                    RequestBody requestBodyApellidos = RequestBody.create(MediaType.parse("text/plain"), userUi.getApellidoPaterno());
                    RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userUi.getEmail());
                    RequestBody requestBodyClave = RequestBody.create(MediaType.parse("text/plain"), userUi.getClave());
                    RequestBody requestBodyCelular = RequestBody.create(MediaType.parse("text/plain"), userUi.getCelular());
                    RequestBody requestBodyTipoDocumentoId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIdDocumento());
                    RequestBody requestBodyTipoPaisId = RequestBody.create(MediaType.parse("text/plain"), userUi.getTipoIDPais());
                    RequestBody requestBodyDateTimeCumple = RequestBody.create(MediaType.parse("text/plain"), dateTimeCumple);
                    RequestBody requestBodyRazonSocial = RequestBody.create(MediaType.parse("text/plain"), userUi.getRazonSocial());

                    if (view != null) view.mostrarProgressBarDialog();
                    initGuardarDatosUsuario(requestFile, requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail,
                            requestBodyClave, requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId, requestBodyDateTimeCumple, requestBodyRazonSocial);

                }


                Log.d(TAG, "Correctamente : ");
            }

        }


        */
    }

    private void initGuardarDatosUsuario(RequestBody requestFile, RequestBody requestBodyTipoDoc, RequestBody requestBodyNombre,
                                         RequestBody requestBodyApellidos, RequestBody requestBodyEmail, RequestBody requestBodyClave,
                                         RequestBody requestBodyCelular, RequestBody requestBodyTipoDocumentoId,
                                         RequestBody requestBodyTipoPaisId, RequestBody requestBodyDateTimeCumple,
                                         RequestBody requestBodyRazonSocial) {
        handler.execute(registrarUsuario, new RegistrarUsuario.RequestValues(requestFile,
                        requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail, requestBodyClave,
                        requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId, requestBodyDateTimeCumple,
                        requestBodyRazonSocial),
                new UseCase.UseCaseCallback<RegistrarUsuario.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistrarUsuario.ResponseValue response) {
                        if (response == null) {
                            if (view != null) {
                                view.mostrarMensaje("Verifique su conexión");
                                view.ocultarProgressBarDialog();
                                view.habilitarButtonRegistrar();
                            }
                            return;
                        }
                        if (response != null) {
                            try {
                                if (response.getDefaultResponse().getError() == null) {
                                    if (view != null) {
                                        view.mostrarMensaje("Verifique su conexión");
                                        view.ocultarProgressBarDialog();
                                        view.habilitarButtonRegistrar();
                                    }
                                    return;
                                }
                                if (response.getDefaultResponse().getError()) {
                                    Log.d(TAG, "response.getDefaultResponse().getError() " + response.getDefaultResponse().getError());

                                    if (view != null) {
                                        view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                        view.ocultarProgressBarDialog();
                                        view.habilitarButtonRegistrar();
                                    }
                                } else {
                                    if (view != null) {
                                        /*Agrega Usuario*/
                                        // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                        view.ocultarProgressBarDialog();
                                        view.startLoginActivity(REGISTRO_USUARIO_CORRECTAMENTE);
                                        view.habilitarButtonRegistrar();
                                    }
                                }
                            } catch (Exception e) {
                                Log.d("Exception :%S ", e.getLocalizedMessage());
                                if (view != null) {
                                    view.mostrarMensaje("Error al registrar");
                                    view.ocultarProgressBarDialog();
                                    view.habilitarButtonRegistrar();
                                }
                            }

                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                view.ocultarProgressBarDialog();
                                view.habilitarButtonRegistrar();
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult");
       /* if (requestCode == RegistrarUserActivity.REQUEST_CODE_CHOOSE_IMAGE && data != null) {
            mFileList.clear();
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (String str : path) {
                mFileList.add(new File(str));
            }
        }*/
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            Log.d(TAG, "CROP_IMAGE_ACTIVITY_REQUEST_CODE");
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mFileList.clear();
                Uri resultUri = result.getUri();
                mFileList.add(new File(resultUri.getPath()));
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
            comprimirImagenLista();
        } else if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {  // Check that it is the SecondActivity with an OK result
            Log.d(TAG, "SECOND_ACTIVITY_REQUEST_CODE");
            if (resultCode == RESULT_OK) {
                // Get String data from Intent

                //this.aceptarTermino = data.getBooleanExtra("keyName", true);
                Log.d(TAG, "returnString " + aceptarTermino + "");
                this.aceptarTermino = false;
                if (view != null) view.mostrarCheckTrue();
            }

        }

    }


    String tipoDocumento = "";
    String tipoPais = "";

    @Override
    public void onSpinnerTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        if (view != null) view.editTextTipoDocumentoValidar(tipoDocumento);
    }

    @Override
    public void onSpinnerTipoPais(String tipoPais) {
        this.tipoPais = tipoPais;
        /*Aqui Inicia*/
        initUseCaseTipoDocumentoList(tipoPais);/*Se Carga la lista de TipoDocumento por Pais*/
    }

    boolean aceptarTermino = true;

    @Override
    public void onAceptarTerminosCondiciones() {
       /* if (aceptarTermino) {
            aceptarTermino = false;
            Log.d(TAG, "onAceptarTerminosCondiciones " + aceptarTermino + "");
            if (view != null) view.mostrarCheckTrue();
        } else {
            aceptarTermino = true;
            Log.d(TAG, "onAceptarTerminosCondiciones " + aceptarTermino + "");
            if (view != null) view.mostrarCheckFalse();
        }*/
        if (view != null)
            view.initStartActivitytTerminosRegistrosUser(Constantes.TERMINOS_CONDICIONES_REGISTRAR_USER);

    }

    @Override
    public void onCheckBoxTerminos() {
        Log.d(TAG, "onCheckBoxTerminos ");
        if (aceptarTermino) {
            this.aceptarTermino = false;
        } else {
            this.aceptarTermino = true;
        }
    }

    @Override
    public void onFilterPeru(List<TipoPaisUi> tipoPaisUiList) {
        for (int i = 0; i < tipoPaisUiList.size(); i++) {
            TipoPaisUi tipoPaisUi = tipoPaisUiList.get(i);
            if (tipoPaisUi.getIdTipoPais().equals("51")) {
                int position = tipoPaisUiList.indexOf(tipoPaisUi);
                if (position != -1) {
                    if (view != null) view.mostrarPositionPeru(position);
                    Log.d(TAG, "TIPO::PERU obtener la position" + position);
                }

                return;
            }
        }
    }

    String dateCumpple;
    int validateEdad;

    @Override
    public void onCumple(int year, int month, int dayOfMonth) {
        String dateTime = year + "-" + month + "-" + dayOfMonth;
        Log.d(TAG, "onCumple : " + dateTime);
        validateEdad = Constantes.getAge(year, month, dayOfMonth);
        this.dateCumpple = dateTime;
    }

    String dateTimeActual;

    @Override
    public void onObtenerAnioActual(String date) {
        this.dateTimeActual = date;
        Log.d(TAG, "date : " + date);
        //   this.anioActual = year;
    }


    // Uri uriComprimida;
    String uriUrl = "";
    Uri uriComprimida;

    private void comprimirImagenLista() {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(registrarUserActivity, mFileList)
                .putGear(Luban.THIRD_GEAR)
                .launch(new OnMultiCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "start");
                    }

                    @Override
                    public void onSuccess(List<File> fileList) {
                        int size = fileList.size();
                        while (size-- > 0) {
                            uriComprimida = Uri.fromFile(fileList.get(size));
                            uriUrl = uriComprimida.toString();
                            Log.i(TAG, "onSuccess : " + uriComprimida.toString() + " size " + fileList.get(size));
                            File file = fileList.get(size);
                            mostrarImagen(uriComprimida);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }

    private void mostrarImagen(Uri uriComprimida) {
        if (view != null) view.mostrarImagenSubida(uriComprimida);
    }


}
