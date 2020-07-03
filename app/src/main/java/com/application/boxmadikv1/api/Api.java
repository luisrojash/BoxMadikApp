package com.application.boxmadikv1.api;

import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.api.culqui.IntegracionResponse;
import com.application.boxmadikv1.api.response.CambioResponse;
import com.application.boxmadikv1.api.response.DatosInicialResponse;
import com.application.boxmadikv1.api.response.DefaultNotiTipoPropuesta;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.DefaultResponseEstadosLastId;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.ListaChatGrupoResponse;
import com.application.boxmadikv1.api.response.ListaChatResponse;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.api.response.ListaRubrosEspecResponse;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.api.response.MostrarDatosPerfilResponse;
import com.application.boxmadikv1.api.response.MostrarPerfilResponse;
import com.application.boxmadikv1.api.response.ObtenerDatosPerfilContador;
import com.application.boxmadikv1.api.response.UsuPresentacionResponse;
import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;
import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.api.response.cliente.ObtenerRespuestaRevocaEspResponse;
import com.application.boxmadikv1.api.response.defaultunico.DefaultCotizacionResponse;
import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.api.response.especialista.GetAceptoCotizacionResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.api.response.especialista.ListaNotificacionResponse;
import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.api.response.especialista.ListaZonaTrabajoResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarCentroEstudiosResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarEspeBancoResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.utils.Constantes;
import com.google.gson.JsonElement;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("LoginApi.php")
    Call<LoginResponse> loginUser(
            @Field("usu_email") String Usu_Email,
            @Field("usu_clave") String Usu_Clave);


    @FormUrlEncoded
    @POST("UsuariosConectadosApi.php")
    Call<DefaultResponse> guardarUsuariosConectados(
            @Field("codigo_usuario") String codigo_usuario,
            @Field("estado") String estado);


    @FormUrlEncoded
    @POST("CambiarClaveApi.php")
    Call<DefaultResponse> cambiarClaveUsuario(
            @Field("usu_email") String usu_email);


   /*SLIM
    @GET("datosInicial/{pais_codigo}")
    Call<DatosInicialResponse> obtenerDatosInicialImportAndroid(
            @Path("pais_codigo") String pais_codigo
    );
    */

    /*Api Insertacion de Datos Generales*/
//    @GET("DatosGeneralesBoxApi.php")
//    Call<DatosInicialResponse> obtenerDatosGeneralesImportAndroid(
//            @Query("pais_codigo") String pais_codigo
//    );
    @GET("DatosGeneralesBoxApi.php")
    Call<DatosInicialResponse> obtenerDatosGeneralesImportAndroid();/*Aqui Se obtiene todo el dato  de los paises*/

    /*CrearPropuestaCliente-2*/
    @FormUrlEncoded
    @POST("CrearPropuestaEspecialidadApi.php")
    Call<DefaultResponseRegistro> crearPropuestaEspecialidad(@Field("pe_descripcion") String pe_descripcion,
                                                             @Field("especialidad_codigo") String especialidad_codigo,
                                                             @Field("pi_codigo") String pi_codigo,
                                                             @Field("rubro_codigo") String rubro_codigo,
                                                             @Field("oficio_codigo") String oficio_codigo,
                                                             @Field("codigoPais") String codigoPais);


    /*CrearPropuestaCliente-1*/
    @Multipart
    @POST("CrearPropuestaApi.php")
    Call<DefaultResponseRegistro> crearPropuestaCliente(@Part("image1\"; filename=\"myfile.jpg\" ") RequestBody file1,
                                                        @Part("image2\"; filename=\"myfile2.jpg\" ") RequestBody file2,
                                                        @Part("pro_titulo") RequestBody pro_titulo,
                                                        @Part("pro_detalle") RequestBody pro_detalle,
                                                        @Part("pais_codigo") RequestBody pais_codigo,
                                                        @Part("rang_precioId") RequestBody rang_precioId,
                                                        @Part("rang_diasId") RequestBody rang_diasId,
                                                        @Part("rang_turnoId") RequestBody rang_turnoId,
                                                        @Part("rubro_codigo") RequestBody rubro_codigo,
                                                        @Part("oficio_codigo") RequestBody oficio_codigo,
                                                        @Part("usu_codigo") RequestBody usu_codigo,
                                                        @Part("codi_departamento") RequestBody codi_departamento,
                                                        @Part("codi_provincia") RequestBody codi_provincia,
                                                        @Part("codi_distrito") RequestBody codi_distrito
    );


    /*Api Registrar Usuario*/
    @Multipart
    @POST("RegistroUserApi.php")
    Call<DefaultResponse> crearUsuarioImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                            @Part("usu_dni") RequestBody usu_dni,
                                            @Part("usu_nom1") RequestBody usu_nom1,
                                            @Part("usu_ape_pa_mat") RequestBody usu_ape_pa_mat,
                                            @Part("usu_email") RequestBody usu_email,
                                            @Part("usu_clave") RequestBody usu_clave,
                                            @Part("usu_celular") RequestBody usu_celular,
                                            @Part("usu_tipo_documento") RequestBody usu_tipo_documento,
                                            @Part("usu_tipo_pais") RequestBody usu_tipo_pais,
                                            @Part("usu_date_cumple") RequestBody usu_date_cumple,
                                            @Part("usu_razon_social") RequestBody requestBodyRazonSocial);


    /*Menu Cliente -  Lista Pendiente - Tambien se puede Manejar por estados no mas */
    @GET("ListaPropuestaClientePendienteApi.php")
    Call<ListaPropuestaPendienteResponse> obtenerListaPropuestaPendienteCliente(@Query("pais_codigo") String pais_codigo,
                                                                                @Query("estado") String estado,
                                                                                @Query("usu_codigo") String usu_codigo);

    /*Menu Cliente -  Lista Cotiza*/
    @GET("ListaCotizaClienteApi.php")
    Call<ListaCotizaResponse> obtenerListaCotizaCliente(@Query("usu_codigo") String usu_codigo,
                                                        @Query("pais_codigo") String pais_codigo,
                                                        @Query("propuesta_inicial_codigo") String propuesta_inicial_codigo
    );


    /*Menu Especialista -  Lista Propuesta Total - Solo como maximo 3 filtros*/
    @GET("ListaPropuestaTotalEspecialidadApi.php")
    Call<ListaPropuestaTotalResponse> obtenerListaPropuestaTotalEspecialista(@Query("primerRubroId") int primerRubroId,
                                                                             @Query("segundoRubroId") int segundoRubroId,
                                                                             @Query("tercerRubrodId") int tercerRubrodId,
                                                                             @Query("estado") int estado,
                                                                             @Query("user_codigo") String user_codigo,
                                                                             @Query("depa_codigo") String depa_codigo,
                                                                             @Query("codigoPais") String codigoPais);

    /*LoadMore -- Lista Propuesta Total -  */
    @GET("LoadMorePropuestaApi.php")
    Call<ListaPropuestaTotalResponse> loadMorePropuesta(@Query("primerRubroId") int primerRubroId,
                                                        @Query("segundoRubroId") int segundoRubroId,
                                                        @Query("tercerRubrodId") int tercerRubrodId,
                                                        @Query("estado") int estado,
                                                        @Query("user_codigo") String user_codigo,
                                                        @Query("depa_codigo") String depa_codig,
                                                        @Query("contador_pagina") int contador_pagina);

    /*Lista Oficios con parametros especificos*/
    @GET("ListaPropuestaEspecialidadApi.php")
    Call<ListaPropuestaEspecialidadResponse> obtenerListaPropuestaEspecialidad(@Query("proini_codigo") int proini_codigo,
                                                                               @Query("rubro_codigo") int rubro_codigo,
                                                                               @Query("oficio_codigo") int oficio_codigo,
                                                                               @Query("paisCodigo") int paisCodigo
    );

    /*Lista Especialidad de cliente*/
    @GET("ListaPropuestaEspecClienteApi.php")
    Call<ListaPropuestaEspecialidadResponse> obtenerListaEspecialidadCliente(@Query("proini_codigo") int proini_codigo);

    /*Mostrar imagenes de Propuestas*/
    @FormUrlEncoded
    @POST("MostrarImagenPropuestaApi.php")
    Call<MostrarImagenResponse> mostrarImagenPropuesta(@Field("codigo_propuesta_inicial") String codigo_propuesta_inicial);


    /*Obteniendo el tipo de cambio - Cambio dolar */
    @GET("convert")
    Call<CambioResponse> obtenerCambioDolarActual(@Query("q") String q,
                                                  @Query("compact") String compact);


    /*Enviar Cotizacion*/
    @FormUrlEncoded
    @POST("EnviarCotizacionApi.php")
    Call<DefaultResponseRegistro> enviarCotizacionPropuesta(@Field("coti_descripcion") String pe_descripcion,
                                                            @Field("coti_montoNeto") String coti_montoNeto,
                                                            @Field("coti_comisionTotal") String coti_comisionTotal,
                                                            @Field("coti_sumaTotalSoles") String coti_sumaTotalSoles,
                                                            @Field("coti_sumaTotalDolares") String coti_sumaTotalDolares,
                                                            @Field("codigoUsuario") String codigoUsuario,
                                                            @Field("coti_monedaCambio") String coti_monedaCambio,
                                                            @Field("codigo_propuesta_inicial") String codigo_propuesta_inicial,
                                                            @Field("codigo_pais") String codigo_pais,
                                                            @Field("coti_fecha_inicio") String coti_fecha_inicio,
                                                            @Field("coti_fecha_fin") String coti_fecha_fin,
                                                            @Field("boxmadik_comision") String boxmadik_comision
    );

    /*Lista Cotizaciones Especialista*/
    @GET("ListaCotizacionesEstadosEspecialistasApi.php")
    Call<ListaCotizacionesResponse> obtenerListaCotizaciones(@Query("codigo_usuario") String codigo_usuario,
                                                             @Query("pais_codigo") String pais_codigo,
                                                             @Query("coti_estado") String coti_estado);

    /*Registrar Revocación Especialista*/
    @FormUrlEncoded
    @POST("RegistroRevocacionEspecialistaApi.php")
    Call<DefaultResponse> registrarRevocacionEspecialista(@Field("observacion") String observacion,
                                                          @Field("porcentaje_trabajo") String porcentaje_trabajo,
                                                          @Field("solucion_revocacion_codigo") String solucion_revocacion_codigo,
                                                          @Field("motivo_revocacion_codigo") String motivo_revocacion_codigo,
                                                          @Field("propuesta_inicial_codigo") String propuesta_inicial_codigo,
                                                          @Field("codigo_usuario_crea") String codigo_usuario_crea,
                                                          @Field("codigo_usuario_resp") String codigo_usuario_resp,
                                                          @Field("pais_codigo") String pais_codigo);

    /*Registrar Revocación Cliente*/
    @FormUrlEncoded
    @POST("RegistroRevocacionClienteApi.php")
    Call<DefaultResponse> registrarRevocacionCliente(@Field("observacion") String observacion,
                                                     @Field("porcentaje_trabajo") String porcentaje_trabajo,
                                                     @Field("solucion_revocacion_codigo") String solucion_revocacion_codigo,
                                                     @Field("motivo_revocacion_codigo") String motivo_revocacion_codigo,
                                                     @Field("propuesta_inicial_codigo") String propuesta_inicial_codigo,
                                                     @Field("codigo_usuario_crea") String codigo_usuario_crea,
                                                     @Field("codigo_usuario_resp") String codigo_usuario_resp,
                                                     @Field("codigo_calidad_trabajo") String codigo_calidad_trabajo,
                                                     @Field("pais_codigo") String pais_codigo);

    @FormUrlEncoded
    @POST("EnvioNotificacionRevocacionApi.php")
    Call<DefaultResponse> envioNotificacionRevocacion(@Field("grupoNotiCodigo") String grupoNotiCodigo,
                                                      @Field("tipoNotiCodigo") String tipoNotiCodigo,
                                                      @Field("usuarioCodigo") String usuarioCodigo,
                                                      @Field("propuestaCodigo") String propuestaCodigo,
                                                      @Field("paisCodigo") String paisCodigo,
                                                      @Field("cotizacionCodigo") String cotizacionCodigo,
                                                      @Field("notiEstado") String notiEstado,
                                                      @Field("tipoNotificacionImage") String tipoNotificacionImage);

    /*Validar Revocacion Cliente - Especialista*/
    @FormUrlEncoded
    @POST("ValidarRevocacionApi.php")
    Call<DefaultResponse> validarRevocacion(@Field("pais_codigo") String pais_codigo,
                                            @Field("propuesta_codigo") String propuesta_codigo,
                                            @Field("codigo_usuario_crea") String codigo_usuario_crea,
                                            @Field("estado_propuesta") String estado_propuesta);

    /*Lista Cotizaciones Especialista*/
    @GET("ObtenerDatosPerfilClienteApi.php")
    Call<DatosPerfilResponse> obtenerDatosPerfilCliente(@Query("usuario_codigo_propuesta") String usuario_codigo_propuesta,
                                                        @Query("codigo_pais") String codigo_pais);

    @FormUrlEncoded
    @POST("ObtenerPerfilEditApi.php")
    Call<ObtenerDatosPerfilContador> obtenerDataPerfilContador(@Field("codigo_usuario") String codigo_usuario,
                                                               @Field("pais_codigo") String pais_codigo,
                                                               @Field("tipo_cliente") String tipo_cliente);

    //  @Query("estado_propuesta_finalizada") String estado_propuesta_finalizada);

    /*Obtener Cotizacion - Validacion , Si tuvo Cotizacion o no */
    @GET("ObtenerValidacionCotizacionApi.php")
    Call<DatosCotizacionResponse> obtenerValidacionCotizacion(@Query("usuario_codigo") String usuario_codigo,
                                                              @Query("estado_cotizacion") String estado_cotizacion,
                                                              @Query("propuesta_codigo") String propuesta_codigo,
                                                              @Query("pais_codigo") String pais_codigo);

    /*Envio Notificacion-Cuando Crea Cotizacion*/
    @FormUrlEncoded
    @POST("EnvioNotificacionCotizacionCreadaApi.php")
    Call<DefaultResponse> envioNotificacionCotizacion(@Field("grupoNotificacionCodigo") String grupoNotificacionCodigo,
                                                      @Field("tipoNotificacionCodigo") String tipoNotificacionCodigo,
                                                      @Field("usuarioCodigo") String usuarioCodigo,
                                                      @Field("propuestaCodigo") String propuestaCodigo,
                                                      @Field("paisCodigo") String paisCodigo,
                                                      @Field("codigoCotizacion") String codigoCotizacion);

    /*Cambiando de estado Coti_Estado = 0 - 1*/
    @FormUrlEncoded
    @POST("ActualizarEstadoEspecialistaCanceladoApi.php")
    Call<DefaultResponse> eliminarCotizacionesEspecialista(@Field("pais_codigo") String pais_codigo,
                                                           @Field("usu_codigo") String usu_codigo,
                                                           @Field("coti_codigo") String coti_codigo,
                                                           @Field("propuesta_codigo") String propuesta_codigo,
                                                           @Field("coti_estado") String coti_estado);

    /*Cambiando de estado propuesta = 0 - 1 (Falta Servicios)*/
    @FormUrlEncoded
    @POST("AceptoCotizacionClienteDeEspecialistaApi.php")
    Call<DefaultResponse> eliminarPropuestaCotizaciones(@Field("pais_codigo") String pais_codigo,
                                                        @Field("usu_codigo_propuesta") String usu_codigo_propuesta,
                                                        @Field("usu_codigo_cotizacion") String usu_codigo_cotizacion,
                                                        @Field("propuesta_estado") String propuesta_estado,
                                                        @Field("coti_estado") String coti_estado);


    /*Muestra Datos de Perfil de Cliente*/
    //@FormUrlEncoded
    @GET("DatosUsuarioApi.php")
    Call<MostrarDatosPerfilResponse> mostrarDatosPerfilCliente(@Query("usu_codigo") String usu_codigo);


    /*ACEPTANDO COTIZACION CLIENTE Y PAGANDO UN PORCENTAJE
     *
     * Jugando con la actualizacion de Estado
     * */
    @FormUrlEncoded
    @POST("AceptoCotizacionClienteDeEspecialistaApi.php")
    Call<DefaultResponse> aceptarCotizacionClienteDeEspecialista(@Field("pais_codigo") String pais_codigo,
                                                                 @Field("propuesta_codigo") String propuesta_codigo,
                                                                 @Field("coti_codigo") String coti_codigo,
                                                                 @Field("usu_codigo_propuesta") String usu_codigo_propuesta,
                                                                 @Field("usu_codigo_cotizacion") String usu_codigo_cotizacion,
                                                                 @Field("propuesta_estado") String propuesta_estado,
                                                                 @Field("coti_estado") String coti_estado,
                                                                 @Field("coti_estado_califica") String coti_estado_califica);

    @FormUrlEncoded
    @POST("EnvioNotificacionCotizacionAceptadaApi.php")
    Call<DefaultResponse> envioNotificacionCotizacionAceptada(@Field("notiEstado") String notiEstado,
                                                              @Field("tipoNotiCodigo") String tipoNotiCodigo,
                                                              @Field("grupoNotiCodigo") String grupoNotiCodigo,
                                                              @Field("propuestaCodigo") String propuestaCodigo,
                                                              @Field("cotizacionCodigo") String cotizacionCodigo,
                                                              @Field("paisCodigo") String paisCodigo);

    /*ACEPTANDO COTIZACION CLIENTE Y PAGANDO UN PORCENTAJE
     *
     * Jugando con la actualizacion de Estado
     * */
    @FormUrlEncoded
    @POST("RegistroCalificacionPropuestaApi.php")
    Call<DefaultResponse> registrarCalificacionPropuestaCliente(@Field("usu_tec_codigo") String usu_tec_codigo,
                                                                @Field("usu_tec_estrellas") String usu_tec_estrellas,
                                                                @Field("usu_tec_comentarios") String usu_tec_comentarios,
                                                                @Field("codigo_propuesta") String codigo_propuesta,
                                                                @Field("usu_cli_codigo") String usu_cli_codigo);

    @FormUrlEncoded
    @POST("ActualizarEstadoPropuestaEliminadoClienteApi.php")
    Call<DefaultResponse> cambiarEstadoEliminadoPropuestaUnica(@Field("pais_codigo") String pais_codigo,
                                                               @Field("propuestaCodigo") String propuestaCodigo,
                                                               @Field("usu_codigo") String usu_codigo,
                                                               @Field("propuesta_estado") String propuesta_estado,
                                                               @Field("tipo_propuesta") String tipo_propuesta);


    /* @Multipart
     @POST("GuardarDatosEditadosClienteApi.php")
     Call<EditarPerfilResponse> guardarDatosEditadosCliente(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                                            @Part("requestKeyUser") RequestBody requestKeyUser,
                                                            @Part("requestBodyNombre") RequestBody requestBodyNombre,
                                                            @Part("requestBodyApellidos") RequestBody requestBodyApellidos,
                                                            @Part("requestBodyCelular") RequestBody requestBodyCelular,
                                                            @Part("requestBodyCodigoDepartamento") RequestBody requestBodyCodigoDepartamento,
                                                            @Part("requestBodyCodigoProvincia") RequestBody requestBodyCodigoProvincia,
                                                            @Part("requestBodyCodigoDistrito") RequestBody requestBodyCodigoDistrito,
                                                            @Part("requestBodyLatitud") RequestBody requestBodyLatitud,
                                                            @Part("requestBodyLongitud") RequestBody requestBodyLongitud,
                                                            @Part("requestBodyDireccion") RequestBody requestBodyDireccion,
                                                            @Part("body_estado") RequestBody body_estado);*/
    @Multipart
    @POST("GuardarDatosEditadosClienteApi.php")
    Call<EditarPerfilResponse> guardarDatosEditadosCliente(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                                           @Part("requestKeyUser") RequestBody requestKeyUser,
                                                           @Part("requestBodyNombre") RequestBody requestBodyNombre,
                                                           @Part("requestBodyApellidos") RequestBody requestBodyApellidos,
                                                           @Part("requestBodyCelular") RequestBody requestBodyCelular,
                                                           @Part("body_estado") RequestBody body_estado);


    @FormUrlEncoded
    @POST("GuardarDireccionPerfilApi.php")
    Call<DefaultResponse> guardarDireccionPerfil(@Field("codigoDepartamento") String codigoDepartamento,
                                                 @Field("codigoProvincia") String codigoProvincia,
                                                 @Field("codigoDistrito") String codigoDistrito,
                                                 @Field("direccion") String direccion,
                                                 @Field("codigoUsuario") String codigoUsuario,
                                                 @Field("latitud") String latitud,
                                                 @Field("longitud") String longitud);


    /*Lista Comentarios con parametros especificos*/
    @GET("ListaComentariosEspecApi.php")
    Call<ComentarioResponse> obtenerListaComentarioEspecialista(@Query("usu_codigo") String usu_codigo,
                                                                @Query("usu_pais") String usu_pais);

    @GET("ListaCursosEspecialistaApi.php")
    Call<ListaCursosResponse> obtenerListaCursos(@Query("usu_codigo") String usu_codigo);


    @FormUrlEncoded
    @POST("ValidacionAntecedentesUsuarioApi.php")
    Call<DefaultResponse> validacionAntecedentes(@Field("usuario_codigo") String usuario_codigo);

    @FormUrlEncoded
    @POST("GuardarDistritosEspecialistaApi.php")
    Call<DefaultResponse> guardarDistritoEspecialista(@Field("usuario_codigo") String usuario_codigo,
                                                      @Field("pais_codigo") String pais_codigo,
                                                      @Field("depa_codigo") String depa_codigo,
                                                      @Field("provi_codigo") String provi_codigo,
                                                      @Field("jsonListDistritos") String jsonListDistritos,
                                                      @Field("estado") String estado);


    @FormUrlEncoded
    @POST("ActualizarPresentacionUserApi.php")
    Call<DefaultResponse> actualizarPresentacionUser(@Field("usuario_codigo") String usuario_codigo,
                                                     @Field("usu_presentacion") String usu_presentacion);

   /* @FormUrlEncoded
    @POST("GuardarCentroEstudiosCursoEspeApi.php")
    Call<DefaultResponse> guardarCentroEspecCurEspecialista(@Field("usuario_codigo") String usuario_codigo,
                                                            @Field("centro_estudi_codigo") String centro_estudi_codigo,
                                                            @Field("tipo_estudio_codigo") String tipo_estudio_codigo,
                                                            @Field("estado_validacion") String estado_validacion,
                                                            @Field("estu_ano_inicio") String estu_ano_inicio,
                                                            @Field("estu_ano_fin") String estu_ano_fin,
                                                            @Field("estu_mes_inicio") String estu_mes_inicio,
                                                            @Field("estu_mes_fin") String estu_mes_fin,
                                                            @Field("estu_nombre") String estu_nombre,
                                                            @Field("tipoEstado") String tipoEstado,
                                                            @Field("codigoEstudiosEspe") String codigoEstudiosEspe,
                                                            @Field("fecha_inicio") String fecha_inicio,
                                                            @Field("fecha_fin") String fecha_fin,
                                                            @Field("codigo_pais") String codigo_pais);*/

    //                keyUser,jsonList,tipoEstadoInsertar,tipoEstadoValidadoCurso,
    //                paisCodigo  keyUser,jsonList,tipoEstadoInsertar,tipoEstadoValidadoCurso,
    //                paisCodigo

    @FormUrlEncoded
    @POST("GuardarCentroEstudiosCursoEspeApi.php")
    Call<DefaultResponse> guardarCentroEspecCurEspecialista(@Field("usuario_codigo") String usuario_codigo,
                                                            @Field("jsonList") String jsonList,
                                                            @Field("estado_validacion") String estado_validacion,
                                                            @Field("tipoEstado") String tipoEstado,
                                                            @Field("codigo_pais") String codigo_pais);

    /*Lista Comentarios Cliente con parametros especificos*/
    @GET("ListaComentariosClienteApi.php")
    Call<ComentarioClienteResponse> obtenerListaComentarioCliente(@Query("usuario_codigo_propuesta") String usuario_codigo_propuesta,
                                                                  @Query("pais_codigo") String pais_codigo);

    /*Mostrar Listado de perfiles Editados*/
    @FormUrlEncoded
    @POST("MostrarPerfilApi.php")
    Call<MostrarPerfilResponse> mostrarPerfilDireccionUsuario(@Field("usuario_codigo") String usuario_codigo);

    /*Zona de Trabajos Especialista*/
    @FormUrlEncoded
    @POST("MostrarEspeZonaTrabajoApi.php")
    Call<ListaZonaTrabajoResponse> mostrarListaZonaTrabajo(@Field("usuario_codigo") String usuario_codigo,
                                                           @Field("usuario_pais") String usuario_pais);

    @FormUrlEncoded
    @POST("MostrarPresentacionUserApi.php")
    Call<UsuPresentacionResponse> mostrarUsuarioPresentacion(@Field("usuario_codigo") String usuario_codigo,
                                                             @Field("pais_codigo") String pais_codigo);


    @FormUrlEncoded
    @POST("GuardarUsuPresentacionApi.php")
    Call<DefaultResponse> guardarUsuarioPresentacion(@Field("usuario_codigo") String usuario_codigo,
                                                     @Field("pais_codigo") String pais_codigo,
                                                     @Field("estado") String estado,
                                                     @Field("presentacion") String presentacion);

    @FormUrlEncoded
    @POST("GuardarEspeBancoApi.php")
    Call<DefaultResponse> guardarBancoEspec(@Field("usuario_codigo") String usuario_codigo,
                                            @Field("pais_codigo") String pais_codigo,
                                            @Field("numeroCuenta") String numeroCuenta,
                                            @Field("tipoCuenta") String tipoCuenta,
                                            @Field("numeroCuentainterbk") String numeroCuentainterbk,
                                            @Field("banco_codigo") String banco_codigo);



    /*LLEga del listado Edicion de Datos*/
    @FormUrlEncoded
    @POST("MostrarEspeBancoApi.php")
    Call<MostrarEspeBancoResponse> mostrarDataBancaria(@Field("usuario_codigo") String usuario_codigo,
                                                       @Field("pais_codigo") String pais_codigo);

    /*Centro Estudios*/
    @FormUrlEncoded
    @POST("MostrarEspeCentroEstudiosApi.php")
    Call<MostrarCentroEstudiosResponse> mostrarListaCentroEstudiosEspe(@Field("usuario_codigo") String usuario_codigo);

    @FormUrlEncoded
    @POST("ActualizarTipoUsuarioApi.php")
    Call<DefaultResponse> actualizarTipoUsuario(@Field("usuario_codigo") String usuario_codigo,
                                                @Field("pais_codigo") String pais_codigo,
                                                @Field("tipoUsuario") String tipoUsuario);

    @FormUrlEncoded
    @POST("ActualizarUsuarioTokenApi.php")
    Call<DefaultResponse> actualizarTokenUser(@Field("usuario_codigo") String usuario_codigo,
                                              @Field("pais_codigo") String pais_codigo,
                                              @Field("usuario_token") String token);
    /*   @FormUrlEncoded
    @POST("GuardarUsuRubroClienteApi.php")
    Call<DefaultResponse> guardarUsuRubro(@Field("usuario_codigo") String usuario_codigo,
                                          @Field("usuario_rubro") String usuario_rubro,
                                          @Field("estado") String estado);*/

    //guardarUsuRubroGson

    @FormUrlEncoded
    @POST("GuardarUsuRubroClienteApi.php")
    Call<DefaultResponse> guardarUsuRubroGson(@Field("usuario_codigo") String usuario_codigo,
                                              @Field("jsonList") String jsonList,
                                              @Field("estado") String estado,
                                              @Field("paisCodigo") String paisCodigo);

    /*Validacion de las tablas con multiInsertaciones*/
    @FormUrlEncoded
    @POST("ValidarUsuRubroClienteApi.php")
    Call<DefaultResponseEstados> validarUsuRubroExiste(@Field("usuario_codigo") String usuario_codigo);

    @FormUrlEncoded
    @POST("ValidarZonaTrabajoApi.php")
    Call<DefaultResponseEstados> validarZonaTrabajoExiste(@Field("usuario_codigo") String usuario_codigo,
                                                          @Field("pais_codigo") String pais_codigo);

    @FormUrlEncoded
    @POST("ValidarUsuDireccionApi.php")
    Call<DefaultResponseEstados> validarUsuDireccion(@Field("usuario_codigo") String usuario_codigo,
                                                     @Field("pais_codigo") String pais_codigo);


    @FormUrlEncoded
    @POST("EnvioNotificacionesApi.php")
    Call<DefaultResponse> envioNotificacionCreacionPropuesta(@Field("tipo_notificacion") String tipo_notificacion,
                                                             @Field("pais_codigo") String pais_codigo,
                                                             @Field("grupo_notificacion") String grupo_notificacion,
                                                             @Field("notificacion_estado") String notificacion_estado,
                                                             @Field("codigo_propuesta") String codigo_propuesta,
                                                             @Field("usu_codigo") String usu_codigo);


    @FormUrlEncoded
    @POST("ActualizarNotificacionesLeidosApi.php")
    Call<DefaultResponse> actualizarNotificacionesLeidas(@Field("pais_codigo") String pais_codigo,
                                                         @Field("tipo_notificacion") String tipo_notificacion,
                                                         @Field("grupo_notificacion") String grupo_notificacion,
                                                         @Field("usu_codigo") String usu_codigo,
                                                         @Field("propuesta_codigo") String propuesta_codigo);

    @FormUrlEncoded
    @POST("NotificacionesLeidoApi.php")
    Call<DefaultResponse> actualizarNotificacion(@Field("notificacion_id") String notificacion_id);


    /*Lista Notificaciones Cliente */
    @GET("ListaNotiClienteApi.php")
    Call<ListaNotificacionResponse> obtenerListaNotiCliente(@Query("pais_codigo") String pais_codigo,
                                                            @Query("codigoGrupoNoti") String codigoGrupoNoti,
                                                            @Query("usu_codigo") String usu_codigo,
                                                            @Query("contador_pagina") String contador_pagina,
                                                            @Query("tipoNotificacion") String tipoNotificacion);


    /*Datos Unicos de Respuestaaaa - Lado de Notificaciones*/
    @FormUrlEncoded
    @POST("DatosTipoNotificacionApi.php")
    Call<DefaultNotiTipoPropuesta> obtenerDatoTipoNotificacion(@Field("usu_codigo") String usu_codigo,
                                                               @Field("pais_codigo") String pais_codigo,
                                                               @Field("noti_codigo") String noti_codigo,
                                                               @Field("tipoNotificacion") String tipoNotificacion);

    @FormUrlEncoded
    @POST("DatosTipoNotificacionApi.php")
    Call<DefaultCotizacionResponse> obtenerDatoNotificacionCreacionCotiza(@Field("usu_codigo") String usu_codigo,
                                                                          @Field("pais_codigo") String pais_codigo,
                                                                          @Field("noti_codigo") String noti_codigo,
                                                                          @Field("tipoNotificacion") String tipoNotificacion,
                                                                          @Field("usu_codigo_docu")String usuCodigoDocu);

    /*obtener Datoa CotizacionAceptada */

    @FormUrlEncoded
    @POST("DatosTipoNotificacionApi.php")
    Call<GetAceptoCotizacionResponse> getAceptoCotizacion(@Field("usu_codigo") String usu_codigo,
                                                          @Field("pais_codigo") String pais_codigo,
                                                          @Field("noti_codigo") String noti_codigo,
                                                          @Field("tipoNotificacion") String tipoNotificacion,
                                                          @Field("propuesta_codigo") String propuesta_codigo,
                                                          @Field("usu_codigo_cotiza") String usu_codigo_cotiza,
                                                          @Field("usu_codigo_propuesta") String usu_codigo_propuesta);


    /*Validacion si el ChatGrupo Existe*/
    @FormUrlEncoded
    @POST("ValidarGrupoChatExisteApi.php")
    Call<DefaultResponseEstadosLastId> validarChatGrupoExiste(@Field("codigo_propuesta") String codigo_propuesta,
                                                              @Field("pais_codigo") String pais_codigo,
                                                              @Field("usuario1") String usuario1,
                                                              @Field("usuario2") String usuario2);

    /*Crear el inicio de Grupo Chat*/
    @FormUrlEncoded
    @POST("CrearMensajeApi.php")
    Call<DefaultResponseRegistro> enviarMensajeChat(@Field("chat_mensaje") String chat_mensaje,
                                                    @Field("chat_estado") String chat_estado,
                                                    @Field("usu_codigo") String usu_codigo,
                                                    @Field("grupo_chat_codigo") String grupo_chat_codigo,
                                                    @Field("pais_codigo") String pais_codigo,
                                                    @Field("datetime") String datetime,
                                                    @Field("tipoUsuario") String tipoUsuario);

    /*Obtener Lista De Chat*/
    @GET("ObtenerListaChatApi.php")
    Call<ListaChatResponse> obtenerListaChat(@Query("chat_estado") String chat_estado,
                                             @Query("grupo_chat_codigo") String grupo_chat_codigo,
                                             @Query("contador_pagina") String contador_pagina);

    @FormUrlEncoded
    @POST("CambiarClave2Api.php")
    Call<DefaultResponse> cambiarClave2(@Field("usu_codigo") String usu_codigo,
                                        @Field("pais_codigo") String pais_codigo,
                                        @Field("clave_actual") String clave_actual,
                                        @Field("clave_nueva") String clave_nueva);

    /*Validar si Existe Calificacion en Cliente*/

    @FormUrlEncoded
    @POST("ValidarCalificacionApi.php")
    Call<DefaultResponseEstados> validarCliente(@Field("pais_codigo") String pais_codigo,
                                                @Field("codigo_propuesta") String codigo_propuesta,
                                                @Field("usu_tec_codigo") String usu_tec_codigo,
                                                @Field("usu_clie_codigo") String usu_clie_codigo);

    @FormUrlEncoded
    @POST("CalificarClienteApi.php")
    Call<DefaultResponse> calificarCliente(@Field("pais_codigo") String pais_codigo,
                                           @Field("codigo_propuesta") String codigo_propuesta,
                                           @Field("usu_tec_codigo") String usu_tec_codigo,
                                           @Field("usu_clie_codigo") String usu_clie_codigo,

                                           @Field("usu_clie_estrellas") String usu_clie_estrellas,
                                           @Field("usu_clie_comentario") String usu_clie_comentario,
                                           @Field("usu_tipo") String usu_tipo,

                                           @Field("usu_tec_estrellas") String usu_tec_estrellas,//usu_tec_comentario
                                           @Field("usu_tec_comentario") String usu_tec_comentario,
                                           @Field("pais_Codigo_Tec") String pais_Codigo_Tec
    );


    /*Obtener Lista De Chat*/
    @GET("ObtenerListaGrupoChatApi.php")
    Call<ListaChatGrupoResponse> obtenerListaGrupoChat(@Query("usuario_codigo") String usuario_codigo,
                                                       @Query("pais_codigo") String pais_codigo,
                                                       @Query("tipoUsuario") String tipoUsuario);

    /*Marcar Chat como leidos*/
    @FormUrlEncoded
    @POST("MarcarLeidosMensajeChatApi.php")
    Call<DefaultResponse> actualizarChatMensajeLeidos(@Field("usuario_codigo") String usuario_codigo,
                                                      @Field("pais_codigo") String pais_codigo,
                                                      @Field("chat_grupo_codigo") String chat_grupo_codigo);

    /*Valida Propuesta Activa - Aceptar Cotizacion  DefaultResponseEstados*/

    @FormUrlEncoded
    @POST("ValidaPropuestaActivaApi.php")
    Call<DefaultResponseEstados> validaPropuesta(@Field("pri_codigo") String pri_codigo,
                                                 @Field("pais_codigo") String pais_codigo);

    // @FormUrlEncoded
    @GET("ConteoNotificacionApi.php")
    Call<DefaultResponseEstados> obtenerConteoNotificacion(@Query("pais_codigo") String pais_codigo,
                                                           @Query("codigoGrupoNoti") String codigoGrupoNoti,
                                                           @Query("usu_codigo") String usu_codigo,
                                                           @Query("tipoNotificacion") String tipoNotificacion);
    /*Mostrar Lista Rubros Para Editar*/

    @FormUrlEncoded
    @POST("MostrarListaRubrosEspecApi.php")
    Call<ListaRubrosEspecResponse> mostrarListaRubrosEspec(@Field("usuario_codigo") String usuario_codigo,
                                                           @Field("usuario_pais") String usuario_pais);


    @FormUrlEncoded
    @POST("CambiarEstadoCotizacionApi.php")
    Call<DefaultResponse> actualizarCotizacion(@Field("propuesta_codigo") String propuesta_codigo,
                                               @Field("propuesta_pais_codigo") String propuesta_pais_codigo,
                                               @Field("estado_cotiza") String estado_cotiza);

    @FormUrlEncoded
    @POST("RespuestaRevocacionApi.php")
    Call<DefaultResponse> respuestaCotizacion(@Field("tipoRespuesta") String tipoRespuesta,
                                              @Field("propuestRevocacionId") String propuestRevocacionId,
                                              @Field("propuestaId") String propuestaId,
                                              @Field("detalleRespuesta") String detalleRespuesta,
                                              @Field("codigoUsuarioPropuesta") String codigoUsuarioPropuesta,
                                              @Field("codigoUsuarioCotizacion") String codigoUsuarioCotizacion
    );

    /*ValidaRespuestaRevocacionOp*/
    @FormUrlEncoded
    @POST("ValidaRespuestaRevocacionApi.php")
    Call<DefaultResponse> validaRespuestaCotizacion(@Field("propuestaId") String propuestaId,
                                                    @Field("codigoUsuarioPropuesta") String codigoUsuarioPropuesta,
                                                    @Field("codigoUsuarioCotizacion") String codigoUsuarioCotizacion
    );


    /*ObtenerDatos Respuesta*/
    @GET("ObtenerRespuestaRevocacionApi.php")
    Call<ObtenerRespuestaRevocaResponse> obtenerRespuestaRevocacion(@Query("pri_codigo") String pri_codigo,
                                                                    @Query("codigo_user_crea") String codigo_user_crea,
                                                                    @Query("codigo_user_resp") String codigo_user_resp,
                                                                    @Query("revocacion_pais") String revocacion_pais);

    @GET("ObtenerRespuestaRevocacionEspecApi.php")
    Call<ObtenerRespuestaRevocaEspResponse> obtenerRespuestaRevocacionEspec(@Query("pri_codigo") String pri_codigo,
                                                                            @Query("codigo_user_crea") String codigo_user_crea,
                                                                            @Query("codigo_user_resp") String codigo_user_resp,
                                                                            @Query("revocacion_pais") String revocacion_pais);

    /*Actualizar Estado Revocacion*/
    @FormUrlEncoded
    @POST("ActualizarEstadoRevocacionApi.php")
    Call<DefaultResponse> actualizarEstadoRevocacion(@Field("pri_codigo") String pri_codigo,
                                                     @Field("codigo_user_crea") String codigo_user_crea,
                                                     @Field("codigo_user_resp") String codigo_user_resp,
                                                     @Field("revocacion_pais") String revocacion_pais);


    /*Actualizar Propuesta Inicial*/
    @FormUrlEncoded
    @POST("ActualizarPropuestaInicialApi.php")
    Call<DefaultResponse> actualizarPropuestaInicial(@Field("pais_codigo") String pais_codigo,
                                                     @Field("propuesta_codigo") String propuesta_codigo,
                                                     @Field("usuario_codigo_propuesta") String usuario_codigo_propuesta,
                                                     @Field("estado_propuesta") String estado_propuesta);

    /*ValidarBanco */
    @FormUrlEncoded
    @POST("ValidaBancoApi.php")
    Call<DefaultResponse> validarBanco(@Field("pais_codigo") String pais_codigo,
                                                     @Field("usuario_codigo") String usuario_codigo);

    /*Datos Confidenciales - Culqui*/
    /*Registro Token*/
    @Headers({Constantes.CULQUI_CONTENT_TYPE, Constantes.CULQUI_AUTHORIZATION_API_PRIVADA})
    @POST("tokens")
    Call<IntegracionResponse> registerToken(@Body String body);

    /*Consultar Token*/
    @Headers({Constantes.CULQUI_CONTENT_TYPE, Constantes.CULQUI_AUTHORIZATION_API_PRIVADA})
    @GET("tokens/{id}")
    Call<IntegracionResponse> consultarToken(@Path("id") String id);

    /*Registro Cargos Pagos*/
    @Headers({Constantes.CULQUI_CONTENT_TYPE, Constantes.CULQUI_AUTHORIZATION_API_PRIVADA})
    @POST("charges")
    Call<CargoResponse> crearCargosPago(@Body String body);
}
