
package com.mohware.mills.login;

public interface LoginView {
    
    void onAddSuccess(String message, String fname, String lname, String designation, String status);

    void onAddError(String message);
    
}
