package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaChatResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("chatDatos")
    @Expose
    private List<ChatResponseList> chatResponseListList;

    public ListaChatResponse(Boolean error, String message, List<ChatResponseList> chatResponseListList) {
        this.error = error;
        this.message = message;
        this.chatResponseListList = chatResponseListList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatResponseList> getChatResponseListList() {
        return chatResponseListList;
    }

    public void setChatResponseListList(List<ChatResponseList> chatResponseListList) {
        this.chatResponseListList = chatResponseListList;
    }

    public class ChatResponseList{
        private String Chat_codigo;
        private String Chat_estado;
        private String Chat_mensaje;
        private String Chat_Fecha;
        private String Chat_Ano;
        private String Usu_Codigo;

        public ChatResponseList() {
        }

        public String getChat_codigo() {
            return Chat_codigo;
        }

        public void setChat_codigo(String chat_codigo) {
            Chat_codigo = chat_codigo;
        }

        public String getChat_estado() {
            return Chat_estado;
        }

        public void setChat_estado(String chat_estado) {
            Chat_estado = chat_estado;
        }

        public String getChat_mensaje() {
            return Chat_mensaje;
        }

        public void setChat_mensaje(String chat_mensaje) {
            Chat_mensaje = chat_mensaje;
        }

        public String getChat_Fecha() {
            return Chat_Fecha;
        }

        public void setChat_Fecha(String chat_Fecha) {
            Chat_Fecha = chat_Fecha;
        }

        public String getChat_Ano() {
            return Chat_Ano;
        }

        public void setChat_Ano(String chat_Ano) {
            Chat_Ano = chat_Ano;
        }

        public String getUsu_Codigo() {
            return Usu_Codigo;
        }

        public void setUsu_Codigo(String usu_Codigo) {
            Usu_Codigo = usu_Codigo;
        }
    }
}
