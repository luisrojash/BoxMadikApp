package com.application.boxmadikv1.utils;

import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.util.Patterns;
import android.webkit.MimeTypeMap;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

public class Constantes {

    public static final String TAG = Constantes.class.getSimpleName();
    /*Autorizacion al Api Culqui*/
    public static final String CULQUI_CONTENT_TYPE = "Content-Type: application/json; charset=utf-8";
    /*Integracion*/
    /* public static final String CULQUI_AUTHORIZATION_API_PUBLICA = "Authorization: Bearer pk_test_pkTUbx6Fpi9Qd4j7";
     public static final String CULQUI_AUTHORIZATION_API_PRIVADA = "Authorization: Bearer sk_test_3Nv75q2dcoxB6SlG";*/
    /*Produccion*/
    public static final String CULQUI_AUTHORIZATION_API_PUBLICA = "Authorization: Bearer pk_live_FWNw6VxghsvodkAw";
    public static final String CULQUI_AUTHORIZATION_API_PRIVADA = "Authorization: Bearer sk_live_YoNRdiZtbxe5kVhb";

    /*Preferencias Encriptadas Prohibido */
    public static final String KEY_SECURE_PREFERENCE = "SometopSecretKey1235";
    public static final String KEY_SECURE_USUARIO_CODIGO = "use_codigo_key_box";
    public static final String KEY_SECURE_USUARIO_DNI = "use_dni_key_box";
    public static final String KEY_SECURE_USUARIO_NOMBRE = "use_nombre_key_box";
    public static final String KEY_SECURE_USUARIO_APELLIDOS = "use_ape_pat_key_box";
    public static final String KEY_SECURE_USUARIO_CELULAR = "use_celular_key_box";
    public static final String KEY_SECURE_USUARIO_EMAIL = "use_email_key_box";
    public static final String KEY_SECURE_USUARIO_FOTO = "use_foto_key_box";
    public static final String KEY_SECURE_USUARIO_PAIS = "use_pais_key_box";
    public static final String KEY_SECURE_USUARIO_CODIGO_DOC = "use_codigo_doc_key_box";
    public static final String KEY_SECURE_USUARIO_TIPO = "use_tipo_usu_key_box";
    public static final String KEY_SECURE_USUARIO_TOKEN = "use_token_usu_key_box";
    /*DATOS DIRECCION CLIENTE CUANDO CREA PROPUESTA- CLIENTE*/
  /*  public static final String KEY_SECURE_USUARIO_CLIENTE_CODIGO_DEPARTAMENTO= "use_departamento_usu_key_box";
    public static final String KEY_SECURE_USUARIO_CLIENTE_CODIGO_PROVINCIA= "use_provincia_usu_key_box";
    public static final String KEY_SECURE_USUARIO_CLIENTE_CODIGO_DISTRITO = "use_distrito_usu_key_box";*/

    public static final String KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT = "use_department_usu_key_box";
    /*TOKEN QUE SE ALMACENA TEMPORAL*/
    public static final String KEY_SECURE_CULQUI_TOKEN = "use_culqui_token_box";

    /************************/
    public static final String PAIS_CODIGO_PERU = "51";
    /*      Propuesta estaddos - Cliente */
    public static final String PROPUESTA_ESTADO_CLIENTE_CANCELADOS = "0";
    public static final String PROPUESTA_ESTADO_CLIENTE_PENDIENTE = "1";
    public static final String PROPUESTA_ESTADO_CLIENTE_PROCESO = "2";
    public static final String PROPUESTA_ESTADO_CLIENTE_FINALIZADO = "3";
    public static final String PROPUESTA_ESTADO_CLIENTE_PAGADO = "4";
    public static final String PROPUESTA_ESTADO_CLIENTE_REVOCADOS = "5";
    /*      Cotizaciones estados - Especialista */

    // 0=Cancelado; 1= Pendiente; 2 = Aceptado; 3=NO Aceptado ; 4= Pagado ; 5= Finalizado
    public static final String ESTADO_ESPECIALISTA_CANCELADOS = "0";
    public static final String ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS = "1";
    public static final String ESTADO_ESPECIALISTA_ACEPTADO = "2";/*o Aceptado*/
    public static final String ESTADO_ESPECIALISTA_NO_ACEPTADO = "3";/*o No Aceptado*/
    public static final String ESTADO_ESPECIALISTA_PAGADO = "4";
    public static final String ESTADO_ESPECIALISTA_REVOCADOS = "10"; //revocados no existe
    public static final String ESTADO_ESPECIALISTA_FINALIZADO = "5";

    /*Tipos de Conexion*/
    public static String BASE_URL_LOCAL = "http://192.168.1.19/Archivosphp/api/";
    String servidorRemote = "http://52.87.14.79/BoxMyApi/api/";
    //public static String BASE_URL_REMOTE = "http://diazosorio.com/BoxMyApi2/api/";
     //   public static String BASE_URL_REMOTE = "http://192.168.1.9:8080/BoxMyApi2/api/";
    //public static String BASE_URL_REMOTE = "http://52.87.14.79/BoxMyApi2/api/";
    public static String BASE_URL_REMOTE = "http://52.87.14.79/BoxMyApiPrueba/api/";
    /*Constantes Clientes*/
    /*String*/
    public static String EXTRAS_CLIENTE_CODIGO_PROPUESTA = "codigo_propuesta";
    public static String EXTRAS_CLIENTE_NOMBRE_PROPUESTA = "nombre_propuesta";
    public static String EXTRAS_CLIENTE_IMAGEN_RUBRO = "imagen_rubro";
    public static String EXTRAS_CLIENTE_FECHA_PROPUESTA = "fecha_propuesta";
    public static String EXTRAS_CLIENTE_ESTADO_PROPUESTA = "estado_propuesta";

    public static String EXTRAS_CLIENTE_ESTADO_PENDIENTE = "estado_pendiente";

    public static String EXTRAS_CLIENTE_DETALLES_PROPUESTA = "detalles_cotizacion";

    /*****TIPOS DE TERMINOS Y CONDICIONES ESTADOS*******/
    //1= Registrar usuario; 2= pagar; 3= revocacion
    public static final int TERMINOS_CONDICIONES_REGISTRAR_USER = 1;
    public static final int TERMINOS_CONDICIONES_PAGAR = 2;
    public static final int TERMINOS_CONDICIONES_REVOCACION = 3;
    /*******EXTRASSS BUNDLEE******/
    public static String EXTRAS_TERMINOS_CONDICIONES = "tipoEstadoTerminos";


    /*********************NOTIFICACIONES***********************************/
    public static final String NOTIFICACION_NOT_ESTADO_PENDIENTE = "1";
    /*******************tipo_notificaciones********************************/
    public static final String TIPO_NOTIFICACION_MENSAJE_BOXMADIK = "1";
    public static final String TIPO_NOTIFICACION_CREACION_PROPUESTA = "2";
    public static final String TIPO_NOTIFICACION_CREACION_REVOCACION = "4";
    public static final String TIPO_NOTIFICACION_AUTORIZACION_PAGO = "5";
    public static final String TIPO_NOTIFICACION_ACEPTO_COTIZACION = "6";
    public static final String TIPO_NOTIFICACION_CREACION_COTIZACION = "7";
    public static final String TIPO_NOTIFICACION_CHAT_MENSAJE = "8";
    public static final String TIPO_NOTIFICACION_RESPUESTA_NOTIFICACION = "9";
    /******************grupo_notificacion**********************************/
    public static final String GRUPO_NOTIFICACION_BOXMADIK = "1";
    public static final String GRUPO_NOTIFICACION_CLIENTE = "2";
    public static final String GRUPO_NOTIFICACION_ESPECIALISTA = "3";


    /*Tipo de Estados GLOBALE*/

    public static final String ESTADO_ACTIVO = "1";

    public static String requestToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public static String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    public static boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    public static String recortarFechaRealizo(String FechaRealizo) {
        String[] parts = FechaRealizo.split(":");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
        String s = part1;
        FechaRealizo = s.substring(0, 10);//he
        return FechaRealizo;
    }

    /*Validar Fechas - Retorna Truo o False*/
    public static boolean CheckDates(String fechaInicio, String fechaFin) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        boolean b = false;
        try {
            if (dfDate.parse(fechaInicio).before(dfDate.parse(fechaFin))) {
                b = true;//If start date is before end date
            } else if (dfDate.parse(fechaInicio).equals(dfDate.parse(fechaFin))) {
                b = true;//If two dates are equal
            } else {
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }

    public static boolean CheckDatesNow(String fechaInicio, String fechaFin) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        boolean b = false;
        try {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String format = formatter.format(currentTime);

            if (dfDate.parse(fechaInicio).after(dfDate.parse(fechaFin))) {
                b = false;
            } else if (dfDate.parse(fechaInicio).after(dfDate.parse(format))) {
                b = false;
            } else if (dfDate.parse(fechaFin).after(dfDate.parse(format))) {
                b = false;
            } else {
                b = true;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static String f_fecha_letras(String vstr_Start) {
        String mstr_fecha = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {

            String[] vobj_days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
            String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

            Date date = format.parse(vstr_Start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mstr_fecha;
    }

    /*Character Especiales
     * No acepta numeros tampoco correo @*/
    public static String isResultadoCharacterSpecial(String data, String tipoCambio) {
        //Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!éúíóá");
        /*Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));
        String listString = "";
        for (int i = 0; i < test.size(); i++) {
            String str = test.get(i);
            List<String> characterSpecial = new ArrayList<>();
            if (isNumeric(str)) {
                //Log.d(TAG, "Numerico : " + str);
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, "");

                } else {
                    String numerico = test.get(i).replace("", "1");
                    // Log.d(TAG, "numerico eelse : " + numerico);
                    if (numerico.length() == 7 || numerico.length() == 9) {
                        test.set(i, "");

                    } else {

                        characterSpecial.add(str.trim());
                    }
                    for (String s : characterSpecial) {
                        listString += s.trim();
                    }
                }
            } else {
                // Log.d(TAG, "else");
                if (validEmail(str)) {
                    //  Log.d(TAG, "if");
                    test.set(i, "");

                    continue;
                } else {
                    // Log.d(TAG, "elseelse");
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");

                        //  Log.d(TAG, "elseelseif");
                    } else {
                        // Log.d(TAG, "elseelseelse");
                    }
                }
               listString += " " + test.get(i) + " ";

            }
        }*/

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));//Aqui hara un recorte de cada string que envia
        String listString = " ";
        for (int i = 0; i < test.size(); i++) {
            String str = test.get(i);
            List<String> characterSpecial = new ArrayList<>();
            if (isNumeric(str)) { // Metodo que valida si es numerico o String
                Log.d(TAG, "Numerico : " + str);
                characterSpecial.add(str.trim());
                for (String s : characterSpecial) {
                    listString += s.trim();
                }
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, "");

                    /*characterSpecial.add(str.trim());
                    for (String s : characterSpecial) {
                        listString += s.trim();
                    }*/
                    Log.d(TAG, "matcher.matchesNUMERICO : ");
                } else {
                    String numerico = test.get(i).replace("", "1");
                    // Log.d(TAG, "numerico eelse : " + numerico);
                    if (numerico.length() == 7 || numerico.length() == 9) {
                        test.set(i, "");

                    } else {

                        characterSpecial.add(str.trim());
                    }
                    for (String s : characterSpecial) {
                        listString += s.trim();
                    }

                    //test.set(i, "");

                }
            } else {

                Log.d(TAG, "else");

              /*  if (validEmail(str)) {
                    Log.d(TAG, "if");
                    test.set(i, "");
                } else {
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");
                        Log.d(TAG, "elseelseif");
                    } else {
                        String data2 = test.get(i).toString();
                        test.set(i, data2 + " ");
                        Log.d(TAG, "elseelseelse");
                    }
                }*/

                if (validEmail(str)) {
                    //  Log.d(TAG, "if");
                    test.set(i, "");

                    continue;
                } else {
                    // Log.d(TAG, "elseelse");
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");

                        //  Log.d(TAG, "elseelseif");
                    } else {
                        // Log.d(TAG, "elseelseelse");
                    }
                }
                listString += " " + test.get(i) + " ";

            }
            // listString += " " + test.get(i) + " ";
            Log.d(TAG, "listString : " + listString);
        }
        return listString;
    }


    public static String isResultadoEspecial(String data) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));//Aqui hara un recorte de cada string que envia
        String listString = " ";
        for (int i = 0; i < test.size(); i++) {
            List<String> characterSpecial = new ArrayList<>();
            String str = test.get(i);
            if (isNumeric(str)) { // Metodo que valida si es numerico o String
                Log.d(TAG, "Numerico : " + str);
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, "");
                    Log.d(TAG, "matcher.matchesNUMERICO : ");
                } else {
                    String numerico = test.get(i).replace(" ", "1"); //
                    if (numerico.length() == 7 || numerico.length() == 9) {
                        test.set(i, "");
                        Log.d(TAG, "numerico.length() : " + test.get(i));
                    } else {

                        //characterSpecial.add(str.trim());
                    }
                    /*for (String s : characterSpecial) {
                     //   listString += s.trim();
                    }*/

                }
            } else {

                Log.d(TAG, "else");

                if (validEmail(str)) {
                    Log.d(TAG, "if");
                    test.set(i, "");
                } else {
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");
                        Log.d(TAG, "elseelseif");
                    } else {
                        /*String data2 = test.get(i).toString();
                        test.set(i, data2 + " ");*/
                        Log.d(TAG, "elseelseelse");
                    }
                }
            }
            listString += " " + test.get(i) + " ";
            Log.d(TAG, "listString : " + listString);
        }
        return listString;
    }

    /*Reemplazar Caracater por ******/

    public static String isResultadoCharacterChat(String data) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));//Aqui hara un recorte de cada string que envia
        String listString = " ";
        for (int i = 0; i < test.size(); i++) {
            String str = test.get(i);
            if (isNumeric(str)) { // Metodo que valida si es numerico o String
                // Log.d(TAG, "Numerico : " + str);
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, " ");
                    // Log.d(TAG, "matcher.matchesNUMERICO : ");
                } else {
                    String numerico = test.get(i).replace(" ", "1"); //
                    if (numerico.length() == 7 || numerico.length() == 9) {
                        test.set(i, "SSSSS ");
                        //   Log.d(TAG, "numerico.length() : " + test.get(i));
                    } else {
                        test.get(i).replaceAll("\\s", "");
                    }
                }
            } else {

                ///   Log.d(TAG, "else");

                if (validEmail(str)) {
                    //  Log.d(TAG, "if");
                    test.set(i, "SSSSS ");
                } else {
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, " ");
                        //   Log.d(TAG, "elseelseif");
                    } else {
                        String data2 = test.get(i).toString();
                        test.set(i, data2 + " ");
                        //    Log.d(TAG, "elseelseelse");
                    }
                }
            }
            listString += "" + test.get(i) + "";
            //Log.d(TAG, "listString : " + listString);
        }
        return listString;
    }

    public static String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static boolean isNumeric(String string) {
        //    String string = "12345.15";
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if (numeric) {
            System.out.println(string + " is a number");
            return true;
        } else {
            System.out.println(string + " is not a number");
            return false;
        }

    }

    public static boolean validEmail(String str_newEmail) {
        return str_newEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public static String validateUsuCalificacion(String usuCalificacion) {
        if (usuCalificacion == null) {
            return "0";
        }
        double usuCalifica = Float.parseFloat(usuCalificacion);
        if (usuCalifica > 0 && usuCalifica < 0.5) {
            usuCalifica = 0.5;
        } else if (usuCalifica > 0.5 && usuCalifica < 1) {
            usuCalifica = 1;
        } else if (usuCalifica > 1 && usuCalifica < 1.5) {
            usuCalifica = 1.5;
        } else if (usuCalifica > 1.5 && usuCalifica < 2) {
            usuCalifica = 2;
        } else if (usuCalifica > 2 && usuCalifica < 2.5) {
            usuCalifica = 2.5;
        } else if (usuCalifica > 2.5 && usuCalifica < 3) {
            usuCalifica = 3;
        } else if (usuCalifica > 3 && usuCalifica < 3.5) {
            usuCalifica = 3.5;
        } else if (usuCalifica > 3.5 && usuCalifica < 4) {
            usuCalifica = 4;
        } else if (usuCalifica > 4 && usuCalifica < 4.5) {
            usuCalifica = 4.5;
        } else if (usuCalifica > 4.5 && usuCalifica < 5) {
            usuCalifica = 5;
        }
        usuCalificacion = String.valueOf(usuCalifica);
        return usuCalificacion;
    }

    /*Calcular Edad*/
    public static int getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        // String ageS = ageInt.toString();

        return ageInt;
    }


    /*Character Especial 01/04/2019*/

    public static String isPrimeroResultadoCharacter(String data) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));//Aqui hara un recorte de cada string que envia
        String listString = " ";
        for (int i = 0; i < test.size(); i++) {
            String str = test.get(i);
            List<String> characterSpecial = new ArrayList<>();
            if (isNumeric(str)) { // Metodo que valida si es numerico o String
                characterSpecial.add(str.trim());
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, "");
                } else {
                    String numerico = test.get(i).replace("", "1");
                    if (numerico.length() == 7 ||numerico.length() == 8|| numerico.length() == 9) {
                        test.set(i, "");
                    } else {
                        characterSpecial.add(str.trim());
                    }
                    for (String s : characterSpecial) {
                        listString += s.trim();
                        break;
                    }
                }
            } else {

                if (validEmail(str)) {
                    test.set(i, "");
                } else {
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");
                    } else {
                        if (isNumeric(str)) {
                        }
                    }
                }
                listString += " " + test.get(i) + " ";

            }
        }
        return listString;
    }

    public static String isSegundoresultadoCharacter(String data) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+!*");
        List<String> test = Arrays.asList(data.split("\\s+"));//Aqui hara un recorte de cada string que envia
        String listString = " ";
        for (int i = 0; i < test.size(); i++) {
            String str = test.get(i);
            if (isNumeric(str)) { // Metodo que valida si es numerico o String
                Matcher matcher = pattern.matcher(test.get(i));
                if (!matcher.matches()) {
                    test.set(i, "");
                } else {
                    String numerico = test.get(i).replace(" ", "1"); //
                    if (numerico.length() == 7 ||numerico.length() == 8|| numerico.length() == 9) {
                        test.set(i, "");
                    } else {
                    }
                }
            } else {
                if (validEmail(str)) {
                    test.set(i, "");
                } else {
                    Matcher matcher = pattern.matcher(test.get(i));
                    if (!matcher.matches()) {
                        test.set(i, "");
                    } else {
                    }
                }
            }
            listString += " " + test.get(i) + " ";
        }
        return listString;
    }

    public static String removeAcentos(String resultado) {
       /* String cadenaNormalize = Normalizer.normalize(resultado, Normalizer.Form.NFKC);
        String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
        return cadenaSinAcentos;*/
        return resultado == null ? null :
                Normalizer.normalize(resultado, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /*Character Especiales*/


}
