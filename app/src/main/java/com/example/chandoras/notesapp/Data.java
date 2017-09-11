package com.example.chandoras.notesapp;

/**
 * Created by chandoras on 9/10/17.
 */

public class Data {
    private String title;
    private String descp;
    private int id;

Data(){

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Data(String title, String descp, int id){
        this.title = title;
        this.descp = descp;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }


}
