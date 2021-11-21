package com.example.fms;

public class Userlist {



    String no, id, fn, ln, un,em,pass;

    public String getNo() {
        return no;
    }

    public String getId() {
        return id;
    }

    public String getFn() {
        return fn;
    }

    public String getLn() {
        return ln;
    }

    public String getUn() {
        return un;
    }

    public String getEm() {
        return em;
    }

    public String getPass() {
        return pass;
    }

    public Userlist(String id, String fn, String ln, String un, String em, String pass) {
        this.id = id;
        this.fn = fn;
        this.ln = ln;
        this.un = un;
        this.em = em;
        this.pass = pass;
    }
}
