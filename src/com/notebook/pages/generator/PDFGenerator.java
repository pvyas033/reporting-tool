package com.notebook.pages.generator;

import java.io.*;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.notebook.pages.components.impl.ImageComponent;
import com.notebook.pages.components.impl.Line;
import com.notebook.pages.components.impl.Text;
import com.notebook.pages.components.impl.TextRectangle;

import java.lang.reflect.*;
import java.util.*;
import java.awt.*;

public class PDFGenerator {
    public static void generatePdf ( File dataFile , String pdfFileFullName , Object modelObject ) {
        Document document = new Document ( );
        Class c;
        int width, height;
        try{
            c = modelObject.getClass ( );
            Field fields[] = c.getDeclaredFields ( );
            width = ( int ) c.getDeclaredField ( "width" ).get ( modelObject );
            height = ( int ) c.getDeclaredField ( "height" ).get ( modelObject );
            document = new Document ( new com.itextpdf.text.Rectangle ( width , height ) );
            PdfWriter writer =
                    PdfWriter.getInstance ( document , new FileOutputStream ( new File ( "FirstPDF.pdf" ) ) );
            document.open ( );
            com.itextpdf.text.Font font =
                    new com.itextpdf.text.Font ( com.itextpdf.text.Font.FontFamily.TIMES_ROMAN , 24 , com.itextpdf.text.Font.BOLD );
            Phrase phrase;
            PdfContentByte pcb = writer.getDirectContent ( );
            try{
                FileInputStream fis = new FileInputStream ( dataFile );
                ObjectInputStream ois = new ObjectInputStream ( fis );
                while (true) {
                    Object result = ois.readObject ( );
                    if (result == null) break;
                    /* write text in pdf */
                    if (result instanceof TextRectangle) {
                        result = ois.readObject ( );
                        if (result instanceof HashMap) {
                            HashMap <Point, Text> textMap = ( HashMap <Point, Text> ) result;
                            Iterator i = textMap.entrySet ( ).iterator ( );
                            while (i.hasNext ( )) {
                                Map.Entry <Point, Text> m = ( Map.Entry <Point, Text> ) i.next ( );
                                String text = m.getValue ( ).getText ( );
                                /* Write in pdf   */
                                if (text.startsWith ( "{" ) && text.endsWith ( "}" )) {
                                    for (Field field : fields){
                                        if (field.getName ( ).equals ( text.substring ( 1 , text.length ( ) - 1 ).trim ( ) )) {
                                            text = ( String ) field.get ( modelObject );
                                        }
                                    }
                                } else if (text.startsWith ( "for name of names" )) {
                                    for (Field field : fields){
                                        if (field.getName ( ).equals ( "names" )) {
                                            java.util.ArrayList <String> list =
                                                    ( java.util.ArrayList <String> ) field.get ( modelObject );
                                            text = "";
                                            for (String name : list){
                                                text += name + " ";
                                            }
                                        }
                                    }
                                }
                                phrase =
                                        new Phrase ( text , FontFactory.getFont ( m.getValue ( ).getTextFont ( ).getFontName ( ) , m.getValue ( ).getTextFont ( ).getSize ( ) , m.getValue ( ).getTextFont ( ).getStyle ( ) ) );
                                ColumnText.showTextAligned ( pcb , Element.ALIGN_LEFT , phrase , ( int ) m.getValue ( ).getLocation ( ).getX ( ) , height - ( int ) m.getValue ( ).getLocation ( ).getY ( ) , 0 );
                            }
                        }
                    }
                    /** text completed **/
                    /** rectangle drawing */
                    else if (result instanceof java.awt.Rectangle) {
                        java.awt.Rectangle rectangle = ( java.awt.Rectangle ) result;
                        result = ois.readObject ( );
                        if (result == null) break;
                        if (result instanceof ImageComponent) {
                            ImageComponent ic = ( ImageComponent ) result;
                            com.itextpdf.text.Image i =
                                    com.itextpdf.text.Image.getInstance ( ( java.awt.Image ) ic.getBufferedImage ( ) , Color.red );
                            i.setAbsolutePosition ( ( float ) ic.getPoint ( ).getX ( ) , ( float ) ic.getPoint ( ).getY ( ) );
                            System.out.println ( "Helloe" );
                            document.add ( i );
                            continue; // because this rectangle is image rectangle so we don't need to draw .
                        }
                        pcb.saveState ( );
                        pcb.rectangle ( ( int ) rectangle.getX ( ) , height - ( int ) rectangle.getY ( ) - ( int ) rectangle.getHeight ( ) , ( int ) rectangle.getWidth ( ) , ( int ) rectangle.getHeight ( ) );
                        pcb.stroke ( );
                        pcb.restoreState ( );
                    } else if (result instanceof Line) {
                        Line line = ( Line ) result;
                        pcb.saveState ( );
                        if (line.getWidth ( ) == 0)
                            pcb.rectangle ( ( int ) line.getX1 ( ) , height - ( int ) line.getY2 ( ) , 0 , ( int ) line.getHeight ( ) );
                        if (line.getHeight ( ) == 0)
                            pcb.rectangle ( ( int ) line.getX1 ( ) , height - ( int ) line.getY1 ( ) - ( int ) line.getHeight ( ) , ( int ) line.getWidth ( ) , 0 );
                        pcb.stroke ( );
                        pcb.restoreState ( );
                    }
                }
            } catch (Exception ioException) {
            }
        } catch (Exception e) {
        } finally{
            document.close ( );
        }
    }
}
