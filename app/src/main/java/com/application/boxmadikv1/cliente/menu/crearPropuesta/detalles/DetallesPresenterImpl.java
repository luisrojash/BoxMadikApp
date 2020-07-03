package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.MostrarPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.GuardarEspecialidad;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.GuardarPropuesta;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.ObtenerDireccion;
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

public class DetallesPresenterImpl extends BaseActivityPresenterImpl<DetallesView> implements DetallesPresenter {

    public static final String CREADO_CORRECTAMENTE_PROPUESTA = "DetallesPresenterImpl";

    public static String TAG = DetallesPresenterImpl.class.getSimpleName();
    List<File> mFileList;
    DetallesActivity detallesActivity;
    GuardarPropuesta guardarPropuesta;
    GuardarEspecialidad guardarEspecialidad;
    ObtenerDireccion obtenerDireccion;
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public DetallesPresenterImpl(UseCaseHandler handler, Resources res, DetallesActivity detallesActivity,
                                 GuardarPropuesta guardarPropuesta, GuardarEspecialidad guardarEspecialidad,
                                 ObtenerDireccion obtenerDireccion) {
        super(handler, res);
        mFileList = new ArrayList<>();
        this.detallesActivity = detallesActivity;
        this.guardarPropuesta = guardarPropuesta;
        this.guardarEspecialidad = guardarEspecialidad;
        this.obtenerDireccion = obtenerDireccion;
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    int rubroId;
    int oficioId;
    String tipoTurno = "";
    String tipoPrecio = "";
    String tipoDias = "";
    String imageRubro = "";
    String nombreOficio = "";
    int posicionTipoTurno;
    int posicionTipoPrecio;
    int posicionTipoDias;
    ArrayList<String> listaIdEspecialistas;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.rubroId = extras.getInt("rubroId", 0);
        this.oficioId = extras.getInt("oficioId", 0);
        this.tipoTurno = extras.getString("tipoTurno");
        this.tipoPrecio = extras.getString("tipoPrecio");
        this.tipoDias = extras.getString("tipoDias");
        this.imageRubro = extras.getString("imageRubro");
        this.nombreOficio = extras.getString("nombreOficio");
        this.posicionTipoPrecio = extras.getInt("posicionTipoPrecio", 0);
        this.posicionTipoTurno = extras.getInt("posicionTipoTurno", 0);
        this.posicionTipoDias = extras.getInt("posicionTipoDias", 0);

        Log.d(TAG, "setExtras : " + posicionTipoPrecio + posicionTipoTurno + posicionTipoDias);
        this.listaIdEspecialistas = extras.getStringArrayList("mylist");
        Log.d(TAG, "  / rubroId :" + rubroId +
                "/ oficioId" + oficioId +
                "/ tipoTurno" + tipoTurno +
                "/ tipoPrecio" + tipoPrecio +
                "/ tipoDias" + tipoDias);
        if (listaIdEspecialistas == null) {
            return;
        } else {
            for (String mylista : listaIdEspecialistas) {
                Log.d(TAG, "  / mylista :" + mylista);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarCabecera(imageRubro, nombreOficio);
    }

    private void mostrarCabecera(String imageRubro, String nombreOficio) {
        if (view != null) view.mostrarCabecera(imageRubro, nombreOficio);
    }

    String tipoActivityResul = "";

    boolean aBooleanPrimeraImagen = true;

    //  String titop

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult");
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                if (aBooleanPrimeraImagen) {
                    Uri resultUriPrimeraImagen = result.getUri();
                    this.tipoActivityResul = "PrimeroImagen";
                    mFileList.clear();
                    mFileList.add(new File(resultUriPrimeraImagen.getPath()));
                    aBooleanPrimeraImagen = false;
                    Log.d(TAG, "onActivityResult : " + resultUriPrimeraImagen.getPath());
                } else {
                    aBooleanPrimeraImagen = true;
                    this.tipoActivityResul = "SegundaImagen";
                    Uri resultUriSegundaImagen = result.getUri();
                    mFileList.clear();
                    mFileList.add(new File(resultUriSegundaImagen.getPath()));
                    Log.d(TAG, "onActivityResult : " + resultUriSegundaImagen.getPath());
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        comprimirImagenLista();
    }

    @Override
    public void onClickPublicarPropuesta(String titulo, String detalles) {
        Log.d(TAG, "titulo : " + titulo +
                "detalles : " + detalles);
        if (view != null) view.deshabilitarButtonPublicar();
        if (titulo.isEmpty() && detalles.isEmpty()) {
            if (view != null) {
                view.mostrarMensaje("No se permiten campos Vacios");
                view.habilitarButtonPublicar();
            }
            return;
        } else if (titulo.isEmpty() || titulo == null) {
            if (view != null) {
                view.mostrarMensajeErrorTitulo("Complete el Titulo");
                view.habilitarButtonPublicar();
            }
            return;
        } else if (detalles.isEmpty() || detalles == null) {
            if (view != null) {
                view.mostrarMensajeErrorDetalles("Complete el Titulo");
                view.habilitarButtonPublicar();
            }
            return;
        } else {
            String otros = "otros";
            /*String validateTitulo = Constantes.isResultadoEspecial(titulo);
            String validateDetalles = Constantes.isResultadoEspecial(detalles);*/


            String removeAcentosCadenaTitulo = Constantes.removeAcentos(titulo);
            String isPrimeroResultadoCharacterTitulo = Constantes.isPrimeroResultadoCharacter(removeAcentosCadenaTitulo);
            String isSegundoresultadoCharacterTitulo = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacterTitulo);

            String removeAcentosCadenaDetalle= Constantes.removeAcentos(detalles);
            String isPrimeroResultadoCharacterDetalle= Constantes.isPrimeroResultadoCharacter(removeAcentosCadenaDetalle);
            String isSegundoresultadoCharacterDetalle = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacterDetalle);

            initUseCaseGuardarPropuesta(isSegundoresultadoCharacterTitulo, isSegundoresultadoCharacterDetalle, rubroId, oficioId, tipoTurno, tipoPrecio, tipoDias, listaIdEspecialistas);
        }
    }


    String keyUser;
    ArrayList<String> stringListIdRubros;
    String codigoDepartamento = "", codigoProvincia = "", codigoDistrito = "", codigoPais = "",
            nombreDepartamento = "", nombreProvincia = "", nombreDistrito;

    @Override
    public void onInitKeyUser(String keyUser, ArrayList<String> stringListIdRubros, String codigoPais) {
        this.keyUser = keyUser;
        this.stringListIdRubros = stringListIdRubros;
        this.codigoPais = codigoPais;
        initRetrofitDatosUsuarios(keyUser);
       /* this.codigoDepartamento = codigoDepartamento;
        this.codigoProvincia = codigoProvincia;
        this.codigoDistrito = codigoDistrito;*/

    }


    private void initRetrofitDatosUsuarios(String keyUser) {
        Call<MostrarPerfilResponse> call = loginService.mostrarPerfilDireccionUsuario(keyUser);
        call.enqueue(new Callback<MostrarPerfilResponse>() {
            @Override
            public void onResponse(Call<MostrarPerfilResponse> call, Response<MostrarPerfilResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    if (view != null) view.mostrarMensaje(mensajeError);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MostrarPerfilResponse mostrarPerfilResponse = response.body();
                        if (mostrarPerfilResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            Log.d(TAG, "mensajeError : " + mensajeError);
                            return;
                        }
                        MostrarPerfilResponse.MostrarUsuResponse mostrarUsuResponse = mostrarPerfilResponse.getMostrarUsuResponse();
                        if (mostrarUsuResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            Log.d(TAG, "mensajeError : " + mensajeError);
                            return;
                        }
                       /* codigoDepartamento = mostrarUsuResponse.getCodigoDepartamento();
                        codigoProvincia = mostrarUsuResponse.getCodigoProvincia();
                        codigoDistrito = mostrarUsuResponse.getCodigoDistrito();
                        nombreDepartamento = mostrarUsuResponse.getNombreDepartamento();
                        nombreProvincia = mostrarUsuResponse.getNombreProvincia();
                        nombreDistrito = mostrarUsuResponse.getNombreDistrito();*/
                        initUseCaseLocalDireccion(mostrarUsuResponse);
                        // if(view!=null)view.mostrarTextViewUbicacion(nombreDepartamento,nombreProvincia,nombreDistrito);
                        Log.d(TAG, "codigoDistrito : " + codigoDistrito);

                    }
                }

            }

            @Override
            public void onFailure(Call<MostrarPerfilResponse> call, Throwable t) {
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    private void initUseCaseLocalDireccion(MostrarPerfilResponse.MostrarUsuResponse mostrarUsuResponse) {
        String codeDepartamento = mostrarUsuResponse.getCodigoDepartamento();
        String codeProvincia = mostrarUsuResponse.getCodigoProvincia();
        String codeDistrito = mostrarUsuResponse.getCodigoDistrito();
        handler.execute(obtenerDireccion, new ObtenerDireccion.RequestValues(codigoPais, codeDepartamento, codeProvincia, codeDistrito),
                new UseCase.UseCaseCallback<ObtenerDireccion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerDireccion.ResponseValue response) {
                        codigoDepartamento = response.getCodigoDepartamento();
                        codigoProvincia = response.getCodigoProvincia();
                        codigoDistrito = response.getCodigoDistrito();
                        nombreDepartamento = response.getNombreDepartamento();
                        nombreProvincia = response.getNombreProvincia();
                        nombreDistrito = response.getNombreDistrito();
                        Log.d(TAG, "nombreDepartamento : " + nombreDepartamento +
                                "nombreProvincia : " + nombreProvincia +
                                "nombreDistrito " + nombreDistrito);
                        if (view != null)
                            view.mostrarTextViewUbicacion(nombreDepartamento, nombreProvincia, nombreDistrito);
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "Esta LLegando Nulo");
                    }
                });
    }

    @Override
    public void eliminarImagenSegundo() {
        imageStringPathSegundo = null;
        if (view != null) {
            view.limpiarSubidaImagenSegundo();
        }
    }

    @Override
    public void eliminarImagenUno() {
        imageStringPathPrimer = null;
        if (view != null) {
            view.limpiarSubidaImagenUno();
        }
    }


    private void initUseCaseGuardarPropuesta(String titulo, String detalles, final int rubroId, final int oficioId, String tipoTurno, String tipoPrecio, String tipoDias, final ArrayList<String> listaIdEspecialistas) {

        // final String pais_codidgo = "51";
        File file1, file2;
        RequestBody requestFile1 = null, requestFile2 = null;
        if (view != null) view.mostrarDialogProgress("Registrando Propuesta...");
        if (imageStringPathPrimer == null && imageStringPathSegundo == null) {
            Log.d(TAG, "SIN IMAGENES");
        } else if (imageStringPathPrimer != null && imageStringPathSegundo == null) {
            file1 = new File(imageStringPathPrimer.getPath());
            requestFile1 = RequestBody.create(MediaType.parse(getMimeType(file1.getPath())), file1);
            Log.d(TAG, "PRIMER IMAGEN");
        } else if (imageStringPathSegundo != null && imageStringPathPrimer == null) {
            file2 = new File(imageStringPathSegundo.getPath());
            requestFile2 = RequestBody.create(MediaType.parse(getMimeType(file2.getPath())), file2);
            Log.d(TAG, "SEGUNDO IMAGEN");
        } else if (imageStringPathSegundo != null && imageStringPathPrimer != null) {
            file1 = new File(imageStringPathPrimer.getPath());
            file2 = new File(imageStringPathSegundo.getPath());
            requestFile1 = RequestBody.create(MediaType.parse(getMimeType(file1.getPath())), file1);
            requestFile2 = RequestBody.create(MediaType.parse(getMimeType(file2.getPath())), file2);
            Log.d(TAG, "TODOS CON  IMAGEN");
        }
        RequestBody bodyTitulo = RequestBody.create(MediaType.parse("text/plain"), titulo);
        RequestBody bodyDetalles = RequestBody.create(MediaType.parse("text/plain"), detalles);
        RequestBody bodyPaisCodigo = RequestBody.create(MediaType.parse("text/plain"), codigoPais);
        RequestBody bodyRangPrecioId = RequestBody.create(MediaType.parse("text/plain"), tipoPrecio);
        RequestBody bodyRangDiasId = RequestBody.create(MediaType.parse("text/plain"), tipoDias);
        RequestBody bodyRangTurnoId = RequestBody.create(MediaType.parse("text/plain"), tipoTurno);
        RequestBody bodyRubroId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(rubroId));
        RequestBody bodyOficioId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(oficioId));
        RequestBody bodyUserId = RequestBody.create(MediaType.parse("text/plain"), keyUser);
        RequestBody bodyCodigoDepartamento = RequestBody.create(MediaType.parse("text/plain"), codigoDepartamento);
        RequestBody bodyCodigoProvincia = RequestBody.create(MediaType.parse("text/plain"), codigoProvincia);
        RequestBody bodyCodigoDistrito = RequestBody.create(MediaType.parse("text/plain"), codigoDistrito);

        Log.d(TAG, "codigoDepartamento :  " + codigoDepartamento +
                "nombreDepartamento :  " + nombreDepartamento +
                "codigoProvincia :  " + codigoProvincia +
                "nombreProvincia :  " + nombreProvincia);

        handler.execute(guardarPropuesta, new GuardarPropuesta.RequestValues(bodyTitulo,
                        bodyDetalles, bodyPaisCodigo, bodyRangPrecioId, bodyRangDiasId,
                        bodyRangTurnoId, bodyRubroId, bodyOficioId, listaIdEspecialistas,
                        requestFile1, requestFile2, bodyUserId,
                        bodyCodigoDepartamento, bodyCodigoProvincia,
                        bodyCodigoDistrito), new UseCase.UseCaseCallback<GuardarPropuesta.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarPropuesta.ResponseValue response) {


                        if (response != null) {
                            if (response.getDefaultResponse() == null) {
                                if (view != null) {
                                    // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgress();
                                    view.habilitarButtonPublicar();
                                    view.limpiarCajasTexto();
                                    view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgress();
                                    view.habilitarButtonPublicar();
                                }
                                return;
                            } else {

                                String codigoPropuesta = response.getDefaultResponse().getLastid();
                                Log.d(TAG, "codigoPropuesta: " + codigoPropuesta);
                                /*19-02-2019- Se dejo de usar Especialidades*/
                                //initUSeCaseRegistroEspecialidades(codigoPropuesta, rubroId, oficioId, listaIdEspecialistas);

                                /*Aqui empezara las notificaciones */
                                initRetrofitNotificaCreacionPropuesta(Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA,
                                        codigoPais,
                                        Constantes.GRUPO_NOTIFICACION_CLIENTE,
                                        Constantes.NOTIFICACION_NOT_ESTADO_PENDIENTE,
                                        codigoPropuesta,
                                        keyUser);
                                initRetrofitActualizarUsuarios();
                                if (view != null)
                                    view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                            }
                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                view.ocultarDialogProgress();
                                view.habilitarButtonPublicar();
                            }
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );

    }

    private void initRetrofitNotificaCreacionPropuesta(String tipoNotificacionCreacionPropuesta, String pais_codidgo, String grupoNotificacionCliente, String notificacionNotEstadoPendiente, String codigoPropuesta, String keyUser) {
        loginService.envioNotificacionCreacionPropuesta(tipoNotificacionCreacionPropuesta,
                pais_codidgo,
                grupoNotificacionCliente,
                notificacionNotEstadoPendiente,
                codigoPropuesta,
                keyUser
        ).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        Log.d(TAG, "if");
                    } else {
                        Log.d(TAG, "else");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                if (view != null) {
                    view.ocultarDialogProgress();
                    view.mostrarMensaje(mensaje);
                }
                return;
            }
        });

    }

    private void initUSeCaseRegistroEspecialidades(String userLastId, int rubroId, int oficioId, ArrayList<String> listaIdEspecialistas) {
        handler.execute(guardarEspecialidad, new GuardarEspecialidad.RequestValues(userLastId,
                        rubroId,
                        oficioId,
                        codigoPais,
                        listaIdEspecialistas),
                new UseCase.UseCaseCallback<GuardarEspecialidad.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarEspecialidad.ResponseValue response) {
                        if (response == null) {
                            Log.d(TAG, "response == null");
                        } else {
                            DefaultResponseRegistro defaultResponse = response.getDefaultResponseRegistro();
                            if (defaultResponse == null) {
                                Log.d(TAG, "DefaultResponseRegistro == null");
                                //  initRetrofitActualizarUsuarios();
                                return;
                            }
                            if (response.getDefaultResponseRegistro().getError()) {
                                initRetrofitActualizarUsuarios();
                                Log.d(TAG, "response.getDefaultResponseRegistro().getError()");
                                return;
                            } else {
                                //  initRetrofitActualizarUsuarios();
                                Log.d(TAG, "else");

                            }
                        }


                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initRetrofitActualizarUsuarios() {
        loginService.guardarUsuariosConectados(keyUser, "2").enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                // DefaultResponse loginResponse = response.body();
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    if (view != null) {
                        view.ocultarDialogProgress();
                        view.mostrarMensaje(mensaje);
                    }
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "Ocurrio Algun Error";
                        if (view != null) {
                            view.ocultarDialogProgress();
                            view.mostrarMensaje(mensaje);
                        }
                        return;
                    } else {
                        if (view != null) {
                            view.ocultarDialogProgress();
                            view.limpiarCajasTexto();
                        }
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                if (view != null) {
                    view.ocultarDialogProgress();
                    view.mostrarMensaje(mensaje);
                }
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
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

    // Uri uriComprimida;
    String uriUrl = "";

    private void comprimirImagenLista() {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(detallesActivity, mFileList)
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

                            Uri uriComprimida = Uri.fromFile(fileList.get(size));
                            uriUrl = uriComprimida.toString();
                            Log.i(TAG, "onSuccess : " + uriComprimida.toString() + " size " + fileList.get(size));
                            String imageGetPath = fileList.get(size).getPath();
                            //lo que se quiere en SLIM
                            mostrarImagen(uriComprimida, imageGetPath);
                            //mImageViews.get(size).setImageURI(Uri.fromFile(fileList.get(size)));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }

    Uri imageStringPathPrimer;
    Uri imageStringPathSegundo;

    private void mostrarImagen(Uri uriComprimida, String getPathImage) {
        switch (tipoActivityResul) {
            case "PrimeroImagen":
                this.imageStringPathPrimer = uriComprimida;
                Log.d(TAG, "imageStringPathPrimer : " + imageStringPathPrimer);
                if (view != null) view.mostrarImagenSubidaPrimera(uriComprimida);
                break;
            case "SegundaImagen":
                this.imageStringPathSegundo = uriComprimida;
                Log.d(TAG, "imageStringPathSegundo : " + imageStringPathSegundo);
                if (view != null) view.mostrarImagenSubidaSegunda(uriComprimida);
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityBackPressed() {
        //if (posicionTipoPrecio > 0 && posicionTipoTurno > 0 && posicionTipoDias > 0) {
        if (view != null)
            view.startActivityBackAtencion(posicionTipoPrecio, posicionTipoTurno, posicionTipoDias);
        // }

    }

    @Override
    public void onClickUbicacion() {
        if (view != null) view.initDialogUbicacion(codigoPais);
        Log.d(TAG, "onClickUbicacion : ");
    }

    @Override
    public void onUbicacionNueva(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito) {
        this.codigoDepartamento = codigoDepartamento;
        this.codigoProvincia = codigoProvincia;
        this.codigoDistrito = codigoDistrito;
        Log.d(TAG, "codigoDepartamento :  " + codigoDepartamento +
                "nombreDepartamento :  " + nombreDepartamento +
                "codigoProvincia :  " + codigoProvincia +
                "nombreProvincia :  " + nombreProvincia);
        if (view != null)
            view.mostrarTextViewUbicacion(nombreDepartamento, nombreProvincia, nombreDistrito);
    }


}
