package com.notebook.pages.components.impl;

import com.notebook.pages.components.Component;
import java.io.Serializable;

public class Line implements Component, Serializable {

    private String  name;
    private int     x1;
    private int     y1;
    private int     x2;
    private int     y2;
    public  Boolean isHorizontal;
    public  Boolean isVertical;

    public Line ( int x1 , int y1 , int x2 , int y2 ) {
        isHorizontal = false;
        isVertical = false;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if ( x1 == x2 ) isVertical = true;
        if ( y2 == y1 ) isHorizontal = true;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getName ( ) {
        return this.name;
    }

    public void setX2 ( int x2 ) {
        this.x2 = x2;
    }

    public void setY2 ( int y2 ) {
        this.y2 = y2;
    }

    public void setX1 ( int x1 ) {
        this.x1 = x1;
    }

    public void setY1 ( int y1 ) {
        this.y1 = y1;
    }

    public int getX1 ( ) {
        return this.x1;
    }

    public int getY1 ( ) {
        return this.y1;
    }

    public int getX2 ( ) {
        return this.x2;
    }

    public int getY2 ( ) {
        return this.y2;
    }

    public int getHeight() {
        return Math.abs(this.x2 - this.x1);
    }

    public int getWidth() {
        return Math.abs(this.y2 - this.y1);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Line)) return false;
        {
            Line line=(Line)o;
            return (this.x1==line.x1 && this.y1==line.y1 && this.getWidth()==line.getWidth() && this.getHeight()==line.getHeight())?true:false;
        }
    }
}
