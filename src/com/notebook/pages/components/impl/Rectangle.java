package com.notebook.pages.components.impl;

import com.notebook.pages.components.Component;
import java.io.Serializable;

public class Rectangle extends java.awt.Rectangle implements Serializable,Component {

    private int x;
    private int y;
    private int width;
    private int height;
    private String name;

    public Rectangle ( int x , int y , int width , int height ) {
        super ( x , y , width , height );
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getName ( ) {
        return this.name;
    }

    public void setWidth ( int width ) {
        this.width = width;
    }

    public void setHeight ( int height ) {
        this.height = height;
    }

    public int area ( ) {
        return ( int ) ( this.getWidth ( ) * this.getHeight ( ) );
    }
}
