package com.projeto.projetosistema.utils;

public class Tools {
    public static boolean validaValor(String obj) {
        if(obj != null && !obj.isBlank() && !obj.isEmpty())
            return true;
        else
            return false;
    }



}
