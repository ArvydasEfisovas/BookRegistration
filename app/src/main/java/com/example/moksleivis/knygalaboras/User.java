package com.example.moksleivis.knygalaboras;

/**
 * Created by arvyd on 1/20/2018.
 */

public class User {
    //private variables
    int _id;
    String _name;
    String _password;
    String _email;

    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String _password, String _email){
        this._id = id;
        this._name = name;
        this._password = _password;
        this._email = _email;
    }

    // constructor
    public User(String name, String _password, String _email){
        this._name = name;
        this._password = _password;
        this._email = _email;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }
    public void setName(String name){
        this._name = name;
    }

    public String getPassword(){return this._password;}
    public void setPassword(String password){
        this._password = password;
    }

    public String getEmail(){
        return this ._email;
    }
    public void setEmail(String email){
        this._email = email;
    }

    @Override
    public String toString() {
        return this._id +this._name + this._password;
    }
}
