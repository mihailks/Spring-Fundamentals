package com.softuni.mobilelele.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {
    private String firstName;
    private String lastName;
    private Boolean isLogged;

    public CurrentUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName(){
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getFirstName()!=null){
            stringBuilder.append(this.getFirstName());
        }

        if (this.lastName!=null){
            if (stringBuilder.length()>0){
                stringBuilder.append(" ");
            }
            stringBuilder.append(this.getLastName());
        }
        return stringBuilder.toString();
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(Boolean logged) {
        isLogged = logged;
        return this;
    }

    public void logout() {
        setLogged(false);
        setLastName(null);
        setLastName(null);
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }
}
