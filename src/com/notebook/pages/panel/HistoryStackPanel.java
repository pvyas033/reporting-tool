package com.notebook.pages.panel;

import com.notebook.pages.components.impl.*;
import com.notebook.pages.components.impl.Rectangle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.io.*;
import java.util.*;

public class HistoryStackPanel extends JPanel
{//200 400

    private ReportPanel reportPanel;
    private int                time;
    private JLabel             entityLabel;
    private JTable              entityList;
    private int                 entityNumber;
    private String              oldValue;
    private JScrollPane         jspForEntityList;
    private Map <String,Object> entities;
    private JTextField          listTextField;
    private DefaultTableModel   dm;
    private Vector              vector;
    HistoryStackPanel(ReportPanel reportPanel)
    {
        this.reportPanel=reportPanel;
        time=1;
        entityNumber=0;
        entityLabel=new JLabel("OBJECTS");
        entities=new LinkedHashMap <String,Object> ();
        dm = new DefaultTableModel();
        vector = new Vector();
        vector.addElement("Objects");
        dm.setDataVector(strArray2Vector(entities),vector);
        entityList=new JTable(dm);
        setLayout(null);
        setAppearanceEntities();
        addListeners();
    }

    public int getEntityNumber ( ) {
        return entityNumber;
    }

    public void setEntityNumber ( int entityNumber ) {
        this.entityNumber = entityNumber;
    }

    public Map <String, Object> getEntities ( ) {
        return entities;
    }

    public void setEntities ( Map <String, Object> entities ) {
        this.entities = entities;
    }

    public JTable getEntityList ( ) {
        return entityList;
    }

    public void setEntityList ( JTable entityList ) {
        this.entityList = entityList;
    }

    public void addListeners()
    {
        entityList.getModel().addTableModelListener(new TableModelListener (){
            public void tableChanged( TableModelEvent tme)
            {
                if(time==1)
                {
                    updateEntityList(entityList.getEditingRow());
                    time=0;
                    return;
                }
            }
        });
        entityList.getSelectionModel().addListSelectionListener(new ListSelectionListener (){
            public void valueChanged( ListSelectionEvent me)
            {
                Object entity="";
                int os=50;
                try
                {
                    time=1;
                    oldValue=(String)entityList.getValueAt((int)entityList.getSelectedRow(),0);
                    entity=entities.get(entityList.getValueAt((int)entityList.getSelectedRow(),0));
                }catch(ArrayIndexOutOfBoundsException aibo)
                {

                }
                reportPanel.getReportBoard ().deSelectRectangle();
                reportPanel.getReportBoard ().deSelectText();
                reportPanel.getReportBoard ().deSelectLine();
                reportPanel.getReportBoard ().deSelectImage();
                if(entity instanceof TextRectangle)
                {
                    TextRectangle tr=(TextRectangle)entity;
                    reportPanel.getReportBoard ().setTextSelected(tr);
                    reportPanel.getCanvasScrollPane ().setScrollPosition((int)tr.getX()-os,(int)tr.getY()-os);
                }
                if(entity instanceof Line)
                {
                    reportPanel.getReportBoard ().setSelectedLine(( Line )entity);
                    reportPanel.getReportBoard ().selectColoredLine((Line)entity);
                    reportPanel.getReportBoard ().setTextFieldForLine((Line)entity);
                    reportPanel.getCanvasScrollPane ().setScrollPosition(reportPanel.getReportBoard ().getSelectedLine ().getX1()-os,reportPanel.getReportBoard ().getSelectedLine ().getY1()-os);
                }
                if(entity instanceof Rectangle)
                {
                    reportPanel.getReportBoard ().setSelectedRectangle (( Rectangle )entity);
                    reportPanel.getReportBoard ().selectColoredRectangle((Rectangle)entity);
                    reportPanel.getReportBoard ().setRectangleTextFields((Rectangle)entity);
                    reportPanel.getCanvasScrollPane ().setScrollPosition((int)reportPanel.getReportBoard ().getSelectedRectangle ().getX()-os,(int)reportPanel.getReportBoard ().getSelectedRectangle ().getY()-os);
                }
                if(entity instanceof ImageComponent)
                {
                    reportPanel.getReportBoard ().setImageSelected((ImageComponent)entity);
                    ImageComponent ic=(ImageComponent)entity;
                    Rectangle rect=(Rectangle)ic.getRect();
                    reportPanel.getCanvasScrollPane ().setScrollPosition((int)rect.getX()-os,(int)rect.getY()-os);
                }
            }
        });

    }
    public void populateList()
    {
        remove(jspForEntityList);
        dm.setDataVector(strArray2Vector(entities),vector);
        entityList=new JTable(dm);
        addListeners();
        setAppearanceEntities();
        add(jspForEntityList);
        repaint();
        revalidate();
    }
    public void updateEntityList(int row)
    {
        try
        {
            String newValue=(String)entityList.getValueAt(row,0);
            Object entity=entities.get(oldValue);
            if(entities.get(newValue)!=null && entities.get(newValue)!=entity)
            {
                JOptionPane.showMessageDialog(HistoryStackPanel.this,"Two objects cannot have same name.");
                return;
            }
            entities.remove(oldValue);
            entities.put(newValue,entity);
            /**/
            if(entity instanceof Line)
            {
                Line line=(Line)entity;
                line.setName("Line"+String.valueOf(++entityNumber));
            }
            if(entity instanceof Rectangle)
            {
                Rectangle rect=(Rectangle)entity;
                rect.setName("Rectangle"+String.valueOf(++entityNumber));
            }
            if(entity instanceof TextRectangle)
            {
                TextRectangle tr=(TextRectangle)entity;
                tr.setName("Text"+String.valueOf(++entityNumber));
            }
            if(entity instanceof ImageComponent)
            {
                ImageComponent ic=(ImageComponent)entity;
                ic.setName("Image"+String.valueOf(++entityNumber));
            }
            /**/
        }catch(ArrayIndexOutOfBoundsException aibbo)
        {

        }
    }
    public void setAppearanceEntities()
    {
        entityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entityList.setShowGrid(false);
        jspForEntityList=new JScrollPane(entityList,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspForEntityList.setColumnHeader(null);
        if(entities.size()==0) jspForEntityList.setVisible(false);
        jspForEntityList.setVisible(true);
        entityLabel.setBounds(5,5,190,20);
        entityLabel.setFont(new Font ("Verdana",Font.PLAIN,20));
        jspForEntityList.setBounds(5,30,190,(290-8)*2+40);
        entityList.setFont(new Font("Verdana",Font.PLAIN,12));
        add(entityLabel);
        add(jspForEntityList);
    }
    public void pushEntity(Object entity)
    {
        remove(jspForEntityList);
        try
        {
            if(entity instanceof Line)
            {
                entities.put("Line"+String.valueOf(++entityNumber),entity);
                Line line=(Line)entity;
                line.setName("Line"+String.valueOf(entityNumber));
            }
            if(entity instanceof Rectangle)
            {
                entities.put("Rectangle"+String.valueOf(++entityNumber),entity);
                Rectangle rect=(Rectangle)entity;
                rect.setName("Rectangle"+String.valueOf(entityNumber));
            }
            if(entity instanceof TextRectangle)
            {
      /*if(actionEventHolder!=null && actionEventHolder.getSource()==moveButton)
      {
        TextRectangle tr=(TextRectangle)entity;
        entities.put(tr.getName(),tr);
      }
      else*/

                entities.put("Text"+String.valueOf(++entityNumber),entity);
                TextRectangle tr=(TextRectangle)entity;
                tr.setName("Text"+String.valueOf(entityNumber));

            }
            if(entity instanceof ImageComponent)
            {
                entities.put("Image"+String.valueOf(++entityNumber),(ImageComponent)entity);
                ImageComponent ic=(ImageComponent)entity;
                ic.setName("Image"+String.valueOf(entityNumber));
            }
        }catch(ClassCastException cce)
        {

        }
        dm.setDataVector(strArray2Vector(entities),vector);
        entityList=new JTable(dm);
        addListeners();
        setAppearanceEntities();
        add(jspForEntityList);
        repaint();
        revalidate();
    }
    private Vector strArray2Vector(Map map)
    {
        Vector vector = new Vector();
        Iterator iter=map.entrySet().iterator();
        while(iter.hasNext())
        {
            Map.Entry m=(Map.Entry)iter.next();
            Vector v=new Vector();
            v.addElement(m.getKey());
            vector.addElement(v);
        }
        return vector;
    }
    public void popEntity(Object entity)
    {
        remove(jspForEntityList);
        String key=null;
        Object value=null;
        for (Map.Entry<String,Object> entry : entities.entrySet())
        {
            key = entry.getKey();
            value =entry.getValue();
            if(entity instanceof Line)
            {
                if(value==(Line)entity)
                {
                    entities.remove(key);
                    break;
                }
            }
            if(entity instanceof Rectangle)
            {
                if(value==(Rectangle)entity)
                {
                    entities.remove(key);
                    break;
                }
            }
            if(entity instanceof ImageComponent)
            {
                if(value==(ImageComponent)entity)
                {
                    entities.remove(key);
                    break;
                }
            }
            if(value instanceof TextRectangle && entity instanceof TextRectangle)
            {
                if(((TextRectangle)value).isEqual(entity))
                {
                    entities.remove(key);
                    break;
                }
            }
        }
        dm.setDataVector(strArray2Vector(entities),vector);
        entityList=new JTable(dm);
        setAppearanceEntities();
        addListeners();
        add(jspForEntityList);
        repaint();
        revalidate();
    }
    public void selectedEntity(Object entity)
    {
        String key=null;
        Object value=null;
        try
        {
            for (Map.Entry<String,Object> entry : entities.entrySet())
            {
                key = entry.getKey();
                value =entry.getValue();
                if(value instanceof ImageComponent && entity instanceof ImageComponent)
                {
                    if(value==(ImageComponent)entity) break;
                }
                if(value instanceof TextRectangle && entity instanceof TextRectangle)
                {

                    if(((TextRectangle)value).isEqual(entity))
                    {
                        break;
                    }

                }
                if(value instanceof Line && entity instanceof Line)
                {
                    if(value==(Line)entity) break;
                }
                if(value instanceof Rectangle && entity instanceof Rectangle)
                {
                    if(value==(Rectangle)entity) break;
                }
            }
        }catch(ClassCastException cce)
        {

        }
        for(int index=0;index<entityList.getModel().getRowCount();index++)
        {
            if(entityList.getValueAt(index,0).equals(key))
            {
                entityList.setRowSelectionInterval(index,index);
                break;
            }
        }
    }
}