package com.notebook.pages.components.impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageComponent implements Serializable
{
    private String    name;
    private File      imageFile;
    private Point     point;
    private Rectangle rect;
    public ImageComponent(File imageFile,Point point)
    {
        this.imageFile=imageFile;
        this.point=point;
        try
        {
            BufferedImage image= ImageIO.read(this.imageFile);
            this.rect=new Rectangle((int)point.getX()-2,(int)point.getY()-2,image.getWidth()+2,image.getHeight()+2);
        }catch(IOException ioException)
        {
            this.rect=null;
        }
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setRect(Rectangle rect)
    {
        this.rect=rect;
    }
    public Rectangle getRect()
    {
        return this.rect;
    }
    public BufferedImage getBufferedImage()throws IOException
    {
        return ImageIO.read(this.imageFile);
    }
    public Point getPoint()
    {
        return this.point;
    }
}