package com.estoque_admin.exception;



public class EstoqueInsuficienteException extends RuntimeException{

    public EstoqueInsuficienteException(String mensagem){
        super(mensagem);
    }
}
