package com.notebook.pages.components.impl;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TextRectangle extends RoundRectangle2D.Double
{
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;

    public TextRectangle(int x,int y,int width,int height,int a,int d)
    {
        super(x,y,width,height,0,0);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public TextRectangle()
    {

    }

    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public void setHeight(int height)
    {
        this.height=height;
    }
    public void setWidth(int width)
    {
        this.width=width;
    }
    public Point getLocation()
    {
        return new Point(this.x,this.y);
    }
    public boolean isEqual(Object o)
    {
        if(!(o instanceof TextRectangle)) return false;
        TextRectangle t=(TextRectangle)o;
        return (t.x==this.x && t.y==this.y);
    }
}