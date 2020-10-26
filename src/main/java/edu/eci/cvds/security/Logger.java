package edu.eci.cvds.security;


public interface Logger {
    
    /**
     * Method for login as a User of the web app
     * @param email
     * @param password
     */
    public void login(String email, String password, boolean remember);

    /**
     * Mehot to logout for the web app
     */
    public void logout();

    /**
     * This method return if the user are logged 
     * @return boolean 
     */
    public boolean isLogged();
}
