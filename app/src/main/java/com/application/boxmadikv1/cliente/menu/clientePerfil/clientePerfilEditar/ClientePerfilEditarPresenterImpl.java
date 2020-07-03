package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase.ObtenerPais;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase.ObtenerTipoDoc;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.utils.Constantes;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ClientePerfilEditarPresenterImpl extends BaseActivityPresenterImpl<ClientePerfilEditarView> implements ClientePerfilEditarPresenter {


    private ObtenerPais obtenerPais;
    private ObtenerTipoDoc obtenerTipoDoc;
    private List<File> mFileList;
    public static final String TAG = ClientePerfilEditarPresenterImpl.class.getSimpleName();
    private ClientePerfilEditarActivity clientePerfilEditarActivity;

    public ClientePerfilEditarPresenterImpl(UseCaseHandler handler, Resources res, ClientePerfilEditarActivity clientePerfilEditarActivity,
                                            ObtenerPais obtenerPais, ObtenerTipoDoc obtenerTipoDoc) {
        super(handler, res);
        this.clientePerfilEditarActivity = clientePerfilEditarActivity;
        this.obtenerPais = obtenerPais;
        this.obtenerTipoDoc = obtenerTipoDoc;
        mFileList = new ArrayList<>();
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseObtenerPais(usuarioPais);
        initUseCaseObtenerTipoDoc(usuarioTipoDoc);
    }


    private void initUseCaseObtenerPais(String usuarioPais) {
        handler.execute(obtenerPais, new ObtenerPais.RequestValues(usuarioPais),
                new UseCase.UseCaseCallback<ObtenerPais.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerPais.ResponseValue response) {
                        if (response == null) return;
                        String nombrePais = response.getPaisNombre();
                        if (view != null) view.mostrarPaisNombre(nombrePais);

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private void initUseCaseObtenerTipoDoc(String usuarioTipoDoc) {
        handler.execute(obtenerTipoDoc, new ObtenerTipoDoc.RequestValues(usuarioTipoDoc),
                new UseCase.UseCaseCallback<ObtenerTipoDoc.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerTipoDoc.ResponseValue response) {
                        if (response == null) return;
                        String tipoDocNombre = response.getTipoDocNombre();
                        if (view != null) view.mostrarTipoDocNombre(tipoDocNombre);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void OnClickPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit) {
        if (view != null) view.deshabilitarButtonGuardar();
        if (nombreEdit.isEmpty() || nombreEdit.equals("") || nombreEdit.trim().isEmpty() || nombreEdit == null) {
            String mensaje = "Escriba su Nombre";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (apellidosEdit.isEmpty() || apellidosEdit.equals("") || apellidosEdit.trim().isEmpty() || apellidosEdit == null) {
            String mensaje = "Escriba su Apellido";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (celularEdit.isEmpty() || celularEdit.equals("") || celularEdit.trim().isEmpty() || celularEdit == null) {
            String mensaje = "Escriba su Celular";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        //initStartActivityDireccion(nombreEdit, apellidosEdit, celularEdit, usuarioFoto);
        initActualizacionPerfil(nombreEdit, apellidosEdit, celularEdit, usuarioFoto);
    }

    private void initActualizacionPerfil(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFotox) {
        RequestBody requestKeyUser = RequestBody.create(MediaType.parse("text/plain"), keyUser);
        RequestBody requestBodyNombre = RequestBody.create(MediaType.parse("text/plain"), nombreEdit);
        RequestBody requestBodyApellidos = RequestBody.create(MediaType.parse("text/plain"), apellidosEdit);
        RequestBody requestBodyCelular = RequestBody.create(MediaType.parse("text/plain"), celularEdit);
        if (view != null) view.mostrarProgressBarDialog();
        //String usuarioFotoPreferences = usuarioFotox;
        Log.d(TAG, "DatosFotoUser" + fotoComprimita);
        if (fotoComprimita.equals("iguales")) {
            Log.d(TAG, "IGUALES");
            RequestBody requestFile = null;
            RequestBody requestBodyEstado = RequestBody.create(MediaType.parse("text/plain"), "1");
            onActualizacionRetrofit(requestKeyUser, requestBodyNombre, requestBodyApellidos, requestBodyCelular,
                    requestBodyEstado, requestFile);
        } else {
            Uri uriComprimida = Uri.parse(usuarioFoto);
            File file = new File(uriComprimida.getPath());
            RequestBody requestFile = RequestBody.create(MediaType.parse(Constantes.getMimeType(file.getPath())), file);
            RequestBody requestBodyEstado = RequestBody.create(MediaType.parse("text/plain"), "0");
            onActualizacionRetrofit(requestKeyUser, requestBodyNombre, requestBodyApellidos, requestBodyCelular,
                    requestBodyEstado, requestFile);
        }
    }

    private void onActualizacionRetrofit(RequestBody requestKeyUser, final RequestBody requestBodyNombre, final RequestBody requestBodyApellidos, final RequestBody requestBodyCelular, RequestBody requestBodyEstado, RequestBody requestFile) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.guardarDatosEditadosCliente(requestFile,
                requestKeyUser, requestBodyNombre, requestBodyApellidos, requestBodyCelular, requestBodyEstado).enqueue(new Callback<EditarPerfilResponse>() {
            @Override
            public void onResponse(Call<EditarPerfilResponse> call, Response<EditarPerfilResponse> response) {
                String guardarPerfil = "Guardando Perfil";

                if (response == null) {
                    if (view != null) {
                        view.mostrarMensaje("Intentelo Más Tarde " + guardarPerfil);
                        view.ocultarProgressBarDialog();
                        view.habilitarButtonGuardar();
                        Log.d(TAG, "response == null");
                    }
                    return;
                }
                EditarPerfilResponse editarPerfilResponse = response.body();
                if (editarPerfilResponse.getError()) {
                    if (view != null) {
                        view.mostrarMensaje(editarPerfilResponse.getMessage() + guardarPerfil);
                        view.ocultarProgressBarDialog();
                        view.habilitarButtonGuardar();
                        Log.d(TAG, "editarPerfilResponse.getError()");
                    }
                    return;
                } else {
                    /*Aca Guarda Correctamente*/
                    String nombreEdit = Constantes.requestBodyToString(requestBodyNombre);
                    String apellidoEdit = Constantes.requestBodyToString(requestBodyApellidos);
                    String celularEdit = Constantes.requestBodyToString(requestBodyCelular);

                    if (fotoComprimita.equals("iguales")) {
                        if (view != null) {
                            view.ocultarProgressBarDialog();
                            view.actualizarDataPreferencesSinFoto(nombreEdit, apellidoEdit, celularEdit);
                            view.startActivityPerfil();
                            view.habilitarButtonGuardar();
                            Log.d(TAG, "Aca Guarda Correctamente.getError()");
                        }


                    } else {
                        if (view != null) {
                            view.ocultarProgressBarDialog();
                            view.actualizarDataPreferencesConFoto(nombreEdit, apellidoEdit, celularEdit, editarPerfilResponse.getUsuFoto());
                            view.startActivityPerfil();
                            view.habilitarButtonGuardar();
                            Log.d(TAG, "Aca Guarda Correctamente.getError()");
                        }
                        return;
                    }

                }
            }

            @Override
            public void onFailure(Call<EditarPerfilResponse> call, Throwable t) {
                if (view != null) {
                    view.mostrarMensaje("Intentelo Más Tarde,Problemas con el Servidor");
                    view.ocultarProgressBarDialog();
                    view.habilitarButtonGuardar();
                }
                return;
            }
        });

    }


    private void initStartActivityDireccion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto) {
        if (view != null)
            view.starActivityPerfilDireccion(nombreEdit, apellidosEdit, celularEdit, usuarioFoto);
    }

    String keyUser;
    String usuarioDni, usuarioNombre, usuarioApellidos,
            usuarioCelular, usuarioEmail, usuarioFoto, usuarioPais, usuarioTipoDoc;

    /*LLegada Preferencias*/
    @Override
    public void onInitKeyUser(String usuarioCodigo, String usuarioDni, String usuarioNombre, String usuarioApellidos,
                              String usuarioCelular, String usuarioEmail, String usuarioFoto, String usuarioPais, String usuarioTipoDoc) {
        this.keyUser = usuarioCodigo;
        this.usuarioDni = usuarioDni;
        this.usuarioNombre = usuarioNombre;
        this.usuarioApellidos = usuarioApellidos;
        this.usuarioCelular = usuarioCelular;
        this.usuarioEmail = usuarioEmail;
        this.usuarioFoto = usuarioFoto;
        this.usuarioPais = usuarioPais;
        this.usuarioTipoDoc = usuarioTipoDoc;
        if (view != null) {
            view.mostrarDatosInit(usuarioDni, usuarioNombre, usuarioApellidos, usuarioCelular,
                    usuarioEmail, usuarioFoto);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mFileList.clear();
                Uri resultUri = result.getUri();
                mFileList.add(new File(resultUri.getPath()));
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG, "onActivityResult : " + error.getMessage());
            }
        }
        comprimirImagenLista();
    }

    Uri uriComprimida;

    private void comprimirImagenLista() {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(clientePerfilEditarActivity, mFileList)
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
                            Log.i(TAG, "onSuccess : " + uriComprimida.toString() + " size " + fileList.get(size));
                            mostrarImagen(uriComprimida);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    String fotoComprimita = "iguales";

    private void mostrarImagen(Uri uriComprimida) {
        this.usuarioFoto = uriComprimida.toString();
        this.fotoComprimita = uriComprimida.toString();
        if (view != null) view.mostrarImageUsuario(uriComprimida);
    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) view.obteniendoKeyUser();
    }
}
