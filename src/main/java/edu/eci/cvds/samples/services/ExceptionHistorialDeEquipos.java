package edu.eci.cvds.samples.services;

public class ExceptionHistorialDeEquipos extends Exception{

    private static final long serialVersionUID = 5758585917279118959L;

    public ExceptionHistorialDeEquipos(String message, Exception e) {
        super(message,e);
    }

    public ExceptionHistorialDeEquipos(String message){
        super(message);
    }
    
}
