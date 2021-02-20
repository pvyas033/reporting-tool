package com.notebook.pages.components.impl;

import java.awt.*;
import java.io.Serializable;

public class Text implements Serializable {

    private String text;
    private   Font   textFont;
    private   Point  point;
    private String name;

    public Text(String text,Font textFont)
    {
        this.text=text;
        this.textFont=textFont;
    }
    public Text()
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
    public void setLocation(Point point)
    {
        this.point=point;
    }
    public Point getLocation()
    {
        return this.point;
    }
    public void setText(String text)
    {
        this.text=text;
    }
    public void setTextFont(Font textFont)
    {
        this.textFont=textFont;
    }
    public String getText()
    {
        return this.text;
    }
    public Font getTextFont()
    {
        return this.textFont;
    }

}
