package edu.eci.cvds.security;

import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;

public interface Logger {
    
    /**
     * Method for login as a User of the web app
     * @param email
     * @param password
     */
    public void login(String email, String password, boolean remember) throws ExceptionHistorialDeEquipos;

    /**
     * Mehot to logout for the web app
     */
    public void logout();

    /**
     * This method return if the user are logged 
     * @return boolean 
     */
    public boolean isLogged();

    /**
     * This method return if the user logged is a admin user in the app 
     * @return boolean 
     */
    public boolean isAdmin();

    /**
     * This methid return if the user loggerd is a normal user in the app
     * @return
     */
    public boolean isUser();
}
