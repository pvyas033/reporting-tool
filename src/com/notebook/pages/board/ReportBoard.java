package com.notebook.pages.board;

import com.notebook.pages.components.impl.*;
import com.notebook.pages.components.impl.Rectangle;
import com.notebook.pages.panel.ReportPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.*;

public class ReportBoard extends Canvas implements MouseListener, MouseMotionListener, ActionListener, KeyListener
{
    private ReportPanel reportPanel;

    /** Text **/
    private String                                           CompanyName;
    private int                                              textAreaHeight;
    private int                                              textAreaWidth;
    private Point                                            textPointer;
    private int                                              caretPosition;
    private int                                              caretAtLine;
    private String                                           stringBuffer;
    private char                                             typeKeyChar;
    public  HashMap <Point, Text>                            tempMap;
    private Map.Entry <TextRectangle, HashMap <Point, Text>> selectedText;
    private Map.Entry <TextRectangle, HashMap <Point, Text>> movedText;
    private HashMap <TextRectangle, HashMap <Point, Text>> textMap;
    private TextRectangle textAreaRectangle, oldTextAreaRectangle;
    private HashMap <Point, Text> textList;

    private int left;
    private int right;
    private int top;
    private int bottom;

    private int                                   currentXLocation, currentYLocation;
    private int                                   lastClickedXLocation, lastClickedYLocation;
    private int                                   distanceFromX;
    private int                                   distanceFromY;
    private int                                   distanceFromX1;
    private int                                   distanceFromX2;
    private int                                   distanceFromY1;
    private int                                   distanceFromY2;
    public  LinkedList <Line>                     lines;
    private Line                  selectedLine;
    private Line                  newLine;
    private Line                  movedLine;
    private Line                  line;
    private Map <Line, Rectangle> lineCovers;
    public  TreeSet <Rectangle>   rectangles;
    private Rectangle                             selectedRectangle;
    private Rectangle                             newRectangle;
    private Rectangle                             movedRectangle;
    /* Image */
    private File                                  imageFile;
    private ImageComponent                        image;
    private HashMap <Rectangle, ImageComponent>   images;
    private Map.Entry <Rectangle, ImageComponent> selectedImage;
    private Map.Entry <Rectangle, ImageComponent> movedImage;
///

    private float titleRect_width_ratio;
    private float titleRect_height_ratio;
    private float titleRect_distX_ratio;
    private float titleRect_distY_ratio;

    private float logoRect_width_ratio;
    private float logoRect_height_ratio;
    private float logoRect_distX_ratio;
    private float logoRect_distY_ratio;

    private float specialRect_width_ratio;
    private float specialRect_height_ratio;
    private float specialRect_distX_ratio;
    private float specialRect_distY_ratio;

    private float dateRect_width_ratio;
    private float dateRect_height_ratio;
    private float dateRect_distX_ratio;
    private float dateRect_distY_ratio;

    private float firmInfoRect_width_ratio;
    private float firmInfoRect_height_ratio;
    private float firmInfoRect_distX_ratio;
    private float firmInfoRect_distY_ratio;


    private Rectangle titleRectangle;
    private Rectangle specialRectangle;
    private Rectangle dateRectangle;
    private Rectangle companyLogoRectangle;
    private Rectangle firmInfoRectangle;
    String titlePlaceholder;
    String datePlaceholder;
    String imagePlaceholder;
    private TextArea t;
    public ReportBoard(ReportPanel reportPanel)
    {
        this.reportPanel=reportPanel;
        initComponents();
        setAppearance();
        addListeners();
    }
    public void initComponents()
    {
        left=5;
        right=reportPanel.getCanvasWidth()-5;
        top=5;
        bottom=reportPanel.getCanvasHeight()-5;
        CompanyName="Thinking Machines";
        stringBuffer=new String("|");
        typeKeyChar='0';
        reportPanel.setTextFont (new Font("Verdana", Font.PLAIN,25));
        caretPosition=0;
        caretAtLine=30;
        textAreaHeight=50;
        textAreaWidth=150;
        selectedLine=null;
        selectedRectangle=null;
        selectedImage=null;
        newLine=null;
        newRectangle=null;
        image=null;
        movedLine=null;
        movedRectangle=null;
        movedImage=null;
        lines=new LinkedList<Line>();
        images=new HashMap<Rectangle,ImageComponent>();
        rectangles=new TreeSet<Rectangle>(new Comparator <Rectangle> (){
            public int compare(Rectangle r1,Rectangle r2)
            {
                if(r1.area()==r2.area())
                {
                    if(r1.getX()==r2.getX())
                    {
                        return (int)(r1.getY()-r2.getY());
                    }
                    return (int)(r1.getX()-r2.getX());
                }
                return r1.area()-r2.area();
            }
        });
        lineCovers=new LinkedHashMap<Line,Rectangle>();
        tempMap=new HashMap<Point,Text>();
        textMap=new HashMap<TextRectangle,HashMap<Point,Text>>();
        textAreaRectangle=new TextRectangle();
        textList=new HashMap<Point,Text>();
        lastClickedXLocation=0;
        lastClickedYLocation=0;
        /* ratio */
        titleRect_width_ratio=724f/1024f;
        titleRect_height_ratio=100f/768f;
        titleRect_distX_ratio=150f/1024f;
        titleRect_distY_ratio=140f/1024f;

        logoRect_width_ratio=100f/1024f;
        logoRect_height_ratio=100f/768f;
        logoRect_distX_ratio=15f/1024f;
        logoRect_distY_ratio=15f/768f;

        specialRect_width_ratio=254f/1024f;
        specialRect_height_ratio=444f/768f;
        specialRect_distX_ratio=30f/1024f;
        specialRect_distY_ratio=254f/768f;

        dateRect_width_ratio=180f/1024f;
        dateRect_height_ratio=50f/768f;
        dateRect_distX_ratio=824f/1024f;
        dateRect_distY_ratio=20f/768f;

        firmInfoRect_width_ratio=240f/1024f;
        firmInfoRect_height_ratio=45f/768f;
        firmInfoRect_distX_ratio=754f/1024f;
        firmInfoRect_distY_ratio=715f/1024f;
        titlePlaceholder="TITLE";
        datePlaceholder="DATE";
        imagePlaceholder="IMAGE";
        t=new TextArea();
    }

    /** setter getter **/

    public String getStringBuffer(){
        return stringBuffer;
    }

    public int getCaretAtLine(){
        return caretAtLine;
    }
    public TextRectangle getTextAreaRectangle ( ) {
        return textAreaRectangle;
    }

    public HashMap <TextRectangle, HashMap <Point, Text>> getTextMap ( ) {
        return textMap;
    }

    public HashMap <Rectangle, ImageComponent> getImages ( ) {
        return images;
    }

    public Point getTextPointer ( ) {
        return textPointer;
    }

    public HashMap <Point, Text> getTextList ( ) {
        return textList;
    }

    public void setTextList ( HashMap <Point, Text> textList ) {
        this.textList = textList;
    }

    public void setTextPointer ( Point textPointer ) {
        this.textPointer = textPointer;
    }

    public Map.Entry <TextRectangle, HashMap <Point, Text>> getSelectedText ( ) {
        return selectedText;
    }

    public void setSelectedText ( Map.Entry <TextRectangle, HashMap <Point, Text>> selectedText ) {
        this.selectedText = selectedText;
    }

    public int getTop ( ) {
        return top;
    }

    public void setTop ( int top ) {
        this.top = top;
    }

    public int getRight ( ) {
        return right;
    }

    public void setRight ( int right ) {
        this.right = right;
    }

    public int getLeft ( ) {
        return left;
    }

    public void setLeft ( int left ) {
        this.left = left;
    }

    public int getBottom ( ) {
        return bottom;
    }

    public void setBottom ( int bottom ) {
        this.bottom = bottom;
    }


    public Map.Entry <Rectangle, ImageComponent> getSelectedImage ( ) {
        return selectedImage;
    }

    public void setSelectedImage ( Map.Entry <Rectangle, ImageComponent> selectedImage ) {
        this.selectedImage = selectedImage;
    }

    public Line getSelectedLine ( ) {
        return selectedLine;
    }

    public void setSelectedLine ( Line selectedLine ) {
        this.selectedLine = selectedLine;
    }

    public Rectangle getSelectedRectangle ( ) {
        return selectedRectangle;
    }

    public void setSelectedRectangle ( Rectangle selectedRectangle ) {
        this.selectedRectangle = selectedRectangle;
    }

    public void setAppearance()
    {
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        t.setBounds(50,50,100,100);
        t.setVisible(true);
    }
    /** If input is invalid */
    public void actionPerformed( ActionEvent ae)
    {
        if(ae.getSource() instanceof JTextField )
        {
            JTextField textField=(JTextField)ae.getSource();
            try
            {
                int data=Integer.parseInt(textField.getText());
                if(data<0)
                {
                    JOptionPane.showMessageDialog(ReportBoard.this,"Invalid Input");
                    resetTextField();
                }
            }catch(NumberFormatException ne)
            {
                JOptionPane.showMessageDialog(ReportBoard.this,"Invalid Input");
                resetTextField();
            }
        }
    }
    public void resetTextField()
    {
        if(selectedLine!=null)
        {
            setTextFieldForLine(selectedLine);
        }
        if(selectedRectangle!=null)
        {
            setRectangleTextFields(selectedRectangle);
        }
        if(selectedImage!=null)
        {
            setImageAndTextTextFields(selectedImage.getKey());
        }
    }
    /** **/
    public void addListeners()
    {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        reportPanel.getWidthTextField ().addKeyListener(this);
        reportPanel.getHeightTextField ().addKeyListener(this);
        reportPanel.getxTextField ().addKeyListener(this);
        reportPanel.getyTextField ().addKeyListener(this);
        reportPanel.getTopField ().addKeyListener(this);
        reportPanel.getLeftField ().addKeyListener(this);
        reportPanel.getRightField ().addKeyListener(this);
        reportPanel.getBottomField ().addKeyListener(this);

        reportPanel.getWidthTextField ().addActionListener(this);
        reportPanel.getHeightTextField ().addActionListener(this);
        reportPanel.getxTextField ().addActionListener(this);
        reportPanel.getyTextField().addActionListener(this);
        reportPanel.getTopField().addActionListener(this);
        reportPanel.getRightField().addActionListener(this);
        reportPanel.getLeftField().addActionListener(this);
        reportPanel.getBottomField().addActionListener(this);
        /**/
    }
    public void setTemplate()
    {
        repaint();
    }
    public void setImageSelected(ImageComponent bi)
    {
        Graphics g=getGraphics();
        Rectangle rect=bi.getRect();
        if(rect!=null)
        {
            g.setColor(Color.RED);
            g.drawRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
            reportPanel.getxTextField().setEnabled(true);
            reportPanel.getyTextField().setEnabled(true);
            setImageAndTextTextFields(rect);
        }
        isImageSelected(new Point((int)rect.getX(),(int)rect.getY()));

    }
    public boolean isImageSelected(Point me)
    {
        try
        {
            for(Map.Entry<Rectangle,ImageComponent> image:images.entrySet())
            {
                if(image.getKey().contains(me.getX(),me.getY()))
                {
                    selectedText=null;
                    selectedLine=null;
                    selectedRectangle=null;
                    selectedImage=image;
                    reportPanel.getHistoryStackPanel ().selectedEntity(selectedImage.getValue());
                    return true;
                }
            }
        }catch(ClassCastException cce)
        {
        }
        return false;
    }
    public void deSelectText()
    {
        Graphics g=getGraphics();
        float dash1[] = { 10.0f };
        BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.white);
        if(oldTextAreaRectangle!=null) g2.draw(oldTextAreaRectangle);
        //selectedText=null;
    }
    public void setTextSelected(TextRectangle rectangle)
    {
        for(Map.Entry<TextRectangle,HashMap<Point,Text>> text:textMap.entrySet())
        {
            if(text.getKey().isEqual(rectangle))
            {
                selectedText=text;
                break;
            }
        }
        selectedLine=null;
        selectedRectangle=null;
        selectedImage=null;
        if(selectedText!=null)
        {
            oldTextAreaRectangle=selectedText.getKey();
            setImageAndTextTextFields(new Rectangle((int)selectedText.getKey().getX(),(int)selectedText.getKey().getY(),(int)selectedText.getKey().getWidth(),(int)selectedText.getKey().getHeight()));
        }
        Graphics g=getGraphics();
        float dash1[] = { 10.0f };
        BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.blue);
        g2.setStroke(dashed);
        if(oldTextAreaRectangle!=null)
        {
            g2.draw(oldTextAreaRectangle);
            reportPanel.getCanvasScrollPane ().setScrollPosition((int)oldTextAreaRectangle.getX()-50,(int)oldTextAreaRectangle.getY()-50);
        }
        reportPanel.getxTextField().setEnabled(false);
        reportPanel.getyTextField().setEnabled(false);
    }
    public boolean isTextSelected(MouseEvent me,Boolean remove)
    {
        for(Map.Entry<TextRectangle,HashMap<Point,Text>> text:textMap.entrySet())
        {
            if(text.getKey().contains(me.getX(),me.getY()))
            {
                selectedText=text;
                if(remove)
                {
                    textMap.remove(text.getKey());
                }
                reportPanel.getHistoryStackPanel ().selectedEntity(selectedText.getKey());
                return true;
            }
        }
        return false;
    }
    public void deleteSelectedText()
    {
        textMap.remove(selectedText.getKey());
        reportPanel.getHistoryStackPanel ().popEntity(selectedText.getKey());
        if(rectangles.size()==0 && lines.size()==0 && images.size()==0 && textMap.size()==0)
        {
            reportPanel.getPropertiesPanel ().setVisible(false);
            reportPanel.getToolbar ().setBounds(10,20,80,250+140);
        }
    }
    public void editText()
    {
        if(selectedText!=null)
        {
            tempMap=new HashMap<Point,Text>();
            caretAtLine=30;
            textAreaRectangle=selectedText.getKey();
            textAreaHeight=(int)textAreaRectangle.getHeight();
            textAreaWidth=(int)textAreaRectangle.getWidth();
            textList=new HashMap<Point,Text>();
            textList=selectedText.getValue();
            Point p=new Point((int)textAreaRectangle.getX(),(int)textAreaRectangle.getY()+caretAtLine);
            Text tempText=textList.remove(p);
            reportPanel.setTextFont(tempText.getTextFont());  // To change specific font
            stringBuffer=tempText.getText()+"|";
            //stringBuffer=textList.remove(p).getText()+"|";
            textPointer=new Point((int)textAreaRectangle.getX(),(int)textAreaRectangle.getY());
            tempMap.putAll(textList);
        }
    }
    public void drawImageOnCanvas(Graphics g)
    {
        try
        {
            Map.Entry<Point,ImageComponent> image=(Map.Entry<Point,ImageComponent>)images.entrySet();
            g.drawImage(image.getValue().getBufferedImage(),(int)image.getKey().getX(),(int)image.getKey().getY(),this);
        }catch(Exception cce)
        {

        }
    }
    public void drawBufferString()
    {
        if(textPointer!=null)
        {
            String nm=textAreaRectangle.getName();
            textAreaRectangle=new TextRectangle((int)textPointer.getX(),(int)textPointer.getY(),textAreaWidth,textAreaHeight,0,0);
            textAreaRectangle.setName(nm);
            drawTextArea();
        }
        Graphics g=getGraphics();
        g.setFont(reportPanel.getTextFont());
        for(Map.Entry<Point,Text> text:tempMap.entrySet())
        {
            g.setFont(reportPanel.getTextFont());
            text.getValue().setTextFont(reportPanel.getTextFont());
            if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
            {
                g.setFont(text.getValue().getTextFont());
            }
            g.drawString(text.getValue().getText(),(int)text.getKey().getX(),(int)text.getKey().getY()); // for cursor
        }
        if(textPointer!=null && stringBuffer!=null)g.drawString(stringBuffer,(int)textPointer.getX(),(int)textPointer.getY()+caretAtLine);
    }
    public void setText(KeyEvent ke)
    {
        selectedRectangle=null;
        selectedLine=null;
        String tempString="";
        Graphics g=getGraphics();
        if(stringBuffer.length()*15==textAreaWidth)
        {
            textAreaWidth+=30;
        }
        caretPosition=stringBuffer.indexOf("|");
        g.setFont(reportPanel.getTextFont ());
        if(ke.getKeyChar()==KeyEvent.VK_BACK_SPACE)
        {
            if(stringBuffer.startsWith("|") && stringBuffer.length()>1)
            {
                if(caretAtLine!=30)
                {
                    stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine-30)).getText()+stringBuffer;
                    if(caretAtLine+20==textAreaHeight)
                    {
                        textAreaHeight-=30;
                    }
                    else
                    {
                        Text t;
                        int temp=caretAtLine;
                        while(temp+20!=textAreaHeight)
                        {
                            t=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp+30));
                            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp),t);
                            temp+=30;
                        }
                        textAreaHeight-=30;
                    }
                    caretAtLine-=30;
                }
                return;
            }
            if(stringBuffer.length()>=1 && !(stringBuffer.equals("|")))
            {
                if(caretPosition==stringBuffer.length())
                {
                    stringBuffer=stringBuffer.substring(0,stringBuffer.length()-2);
                    stringBuffer=stringBuffer+"|";
                }
                else
                {
                    tempString=stringBuffer.substring(caretPosition,stringBuffer.length());
                    stringBuffer=stringBuffer.substring(0,caretPosition-1)+tempString;
                }
            }
            else if(tempMap.size()>0 && textAreaHeight>30 && stringBuffer.equals("|"))
            {
                if(caretAtLine==30) return;
                textAreaHeight-=30;
                caretAtLine-=30;
                stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine)).getText();
                stringBuffer=stringBuffer+"|";
                if(caretAtLine+20!=textAreaHeight)
                {
                    textAreaHeight+=30;
                    Text t;
                    int temp=caretAtLine+30;
                    while(temp+20!=textAreaHeight)
                    {
                        t=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp+30));
                        tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp),t);
                        temp+=30;
                    }
                    textAreaHeight-=30;
                }
            }
            return;
        }
        else  if(ke.getKeyChar()==KeyEvent.VK_ENTER)
        {
            if(stringBuffer!=null && stringBuffer.length()!=caretPosition)
            {
                tempString=stringBuffer.substring(caretPosition,stringBuffer.length());
                stringBuffer=stringBuffer.substring(0,caretPosition);
            }
            tempMap.put(new Point((int)textPointer.getX(),((int)textPointer.getY()+caretAtLine)),new Text(stringBuffer,reportPanel.getTextFont ()));
            stringBuffer="|";
            if(tempString!=null) stringBuffer=tempString;
            textAreaHeight+=30;
            caretAtLine+=30;
            if(caretAtLine+20!=textAreaHeight)
            {
                int temp=textAreaHeight-20;
                while(temp!=caretAtLine)
                {
                    tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp),tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+temp-30)));
                    temp-=30;
                }
            }
        }
        else
        {
            char data[]={ke.getKeyChar()};
            String s=new String(data);
            if(s.equals("|")) return;
            if(caretPosition!=stringBuffer.length())
            {
                tempString=stringBuffer.substring(caretPosition,stringBuffer.length());
                stringBuffer=stringBuffer.substring(0,caretPosition)+s+tempString;
            }
            else
            {
                stringBuffer=stringBuffer.substring(0,stringBuffer.length()-1);
                stringBuffer=stringBuffer+s+"|";
            }
        }
    }
    public void keyTyped(KeyEvent ke)
    {
        if(reportPanel.getActionEventHolder ()!=null && textPointer!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            setText(ke);
            repaint();
            return;
        }
        if(reportPanel.getActionEventHolder ()!=null && textPointer!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getTextButton ())
        {
            selectedRectangle=null;
            selectedLine=null;
            setText(ke);
            repaint();
            return;
        }
        // Invalid input
        try
        {
            JTextField j=(JTextField)ke.getSource();
            Integer.parseInt(j.getText());
        }catch(NumberFormatException nfe)
        {
            return;
        }catch(ClassCastException cce)
        {

            return;
        }
        if(ke.getSource()!=reportPanel.getWidthTextField () && (reportPanel.getWidthTextField ().getText().trim().equals("") || Integer.parseInt(reportPanel.getWidthTextField ().getText())==0))
        {
            if(selectedLine!=null) reportPanel.getWidthTextField ().setText(String.valueOf(selectedLine.getWidth()));
            if(selectedRectangle!=null) reportPanel.getWidthTextField ().setText(String.valueOf((int)selectedRectangle.getWidth()));
        }
        if(ke.getSource()!=reportPanel.getHeightTextField () && (reportPanel.getHeightTextField ().getText().trim().equals("") || Integer.parseInt(reportPanel.getHeightTextField ().getText())==0))
        {
            if(selectedLine!=null) reportPanel.getHeightTextField ().setText(String.valueOf(selectedLine.getHeight()));
            if(selectedRectangle!=null) reportPanel.getHeightTextField ().setText(String.valueOf((int)selectedRectangle.getHeight()));
        }
        if(ke.getSource()!=reportPanel.getxTextField () && (reportPanel.getxTextField ().getText().trim().equals("") || Integer.parseInt(reportPanel.getxTextField ().getText())==0))
        {
            if(selectedLine!=null) reportPanel.getxTextField ().setText(String.valueOf(selectedLine.getX1()));
            if(selectedRectangle!=null) reportPanel.getxTextField ().setText(String.valueOf((int)selectedRectangle.getX()));
            if(selectedImage!=null)reportPanel.getxTextField ().setText(String.valueOf((int)selectedImage.getKey().getX()));
        }
        if(ke.getSource()!=reportPanel.getyTextField () && (reportPanel.getyTextField ().getText().trim().equals("") || Integer.parseInt(reportPanel.getyTextField ().getText())==0))
        {
            if(selectedLine!=null) reportPanel.getyTextField ().setText(String.valueOf(selectedLine.getY1()));
            if(selectedRectangle!=null) reportPanel.getyTextField ().setText(String.valueOf((int)selectedRectangle.getY()));
            if(selectedImage!=null)reportPanel.getyTextField ().setText(String.valueOf((int)selectedImage.getKey().getY()));
        }
        if(ke.getSource()==reportPanel.getHeightTextField ())
        {
            Graphics g=getGraphics();
            if((selectedLine!=null && selectedLine.getWidth()==0) && ke.getKeyChar()==KeyEvent.VK_ENTER)
            {
                removeLineCover(selectedLine);
                selectedLine.setY2(selectedLine.getY1()+Integer.parseInt(reportPanel.getHeightTextField ().getText()));
                makeHeightValid(selectedLine);
                setTextFieldForLine(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                repaint();
            }
            if(selectedLine!=null && (selectedLine.getWidth()==0&&selectedLine.getHeight()==0) && ke.getKeyChar()==KeyEvent.VK_ENTER)
            {
                removeLineCover(selectedLine);
                lines.remove(selectedLine);
                reportPanel.getHistoryStackPanel ().popEntity(selectedLine);
                selectedLine=null;
                repaint();
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedRectangle!=null)
            {
                rectangles.remove(selectedRectangle);
                selectedRectangle.setSize((int)selectedRectangle.getWidth(),Integer.parseInt(reportPanel.getHeightTextField ().getText()));
                makeRectangleValid(selectedRectangle);
                rectangles.add(selectedRectangle);
                newRectangle=selectedRectangle;
                repaint();
            }
        }
        if(ke.getSource()==reportPanel.getxTextField ())
        {
            int os=10;
            Graphics g=getGraphics();
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedImage!=null)
            {
                selectedImage.getKey().setLocation(Integer.parseInt(reportPanel.getxTextField ().getText()),(int)selectedImage.getKey().getY());
                makeRectangleValid(selectedImage.getKey(),false);
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedLine!=null && selectedLine.getHeight()==0)
            {
                removeLineCover(selectedLine);
                setLineXCoordinates(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                movedLine=selectedLine;
                repaint();
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && (selectedLine!=null && selectedLine.getWidth()==0))
            {
                removeLineCover(selectedLine);
                setLineXCoordinates(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                movedLine=selectedLine;
                repaint();
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedRectangle!=null)
            {
                selectedRectangle.setLocation(Integer.parseInt(reportPanel.getxTextField ().getText()),(int)selectedRectangle.getY());
                makeRectangleValid(selectedRectangle,false);
                newRectangle=selectedRectangle;
                repaint();
            }
        } // xtextField completed
        if(ke.getSource()==reportPanel.getWidthTextField ())
        {
            Graphics g=getGraphics();
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedLine!=null && selectedLine.getHeight()==0)
            {
                removeLineCover(selectedLine);
                selectedLine.setX2(selectedLine.getX1()+Integer.parseInt(reportPanel.getWidthTextField ().getText()));
                makeWidthValid(selectedLine);
                setTextFieldForLine(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                repaint();
            }
            if(selectedRectangle!=null && ke.getKeyChar()==KeyEvent.VK_ENTER)
            {
                rectangles.remove(selectedRectangle);
                selectedRectangle.setSize(Integer.parseInt(reportPanel.getWidthTextField ().getText()),(int)selectedRectangle.getHeight());
                makeRectangleValid(selectedRectangle);
                rectangles.add(selectedRectangle);
                newRectangle=selectedRectangle;
                repaint();
            }
            if( selectedLine!=null && selectedLine.getWidth()==0&&selectedLine.getHeight()==0)
            {
                removeLineCover(selectedLine);
                lines.remove(selectedLine);
                reportPanel.getHistoryStackPanel ().popEntity(selectedLine);
                repaint();
            }
        }// widthTextField complete
        if(ke.getSource()==reportPanel.getyTextField ())
        {
            Graphics g=getGraphics();
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedImage!=null)
            {
                selectedImage.getKey().setLocation((int)selectedImage.getKey().getX(),Integer.parseInt(reportPanel.getyTextField ().getText()));
                makeRectangleValid(selectedImage.getKey(),false);
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedLine!=null && selectedLine.getWidth()==0)
            {
                removeLineCover(selectedLine);
                setLineYCoordinates(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                movedLine=selectedLine;
                repaint();
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedLine!=null && selectedLine.getHeight()==0)
            {
                removeLineCover(selectedLine);
                setLineYCoordinates(selectedLine);
                addLineCover(selectedLine);
                newLine=selectedLine;
                movedLine=selectedLine;
                repaint();
            }
            if(ke.getKeyChar()==KeyEvent.VK_ENTER && selectedRectangle!=null)
            {
                selectedRectangle.setLocation((int)selectedRectangle.getX(),Integer.parseInt(reportPanel.getyTextField ().getText()));
                makeRectangleValid(selectedRectangle,false);
                newRectangle=selectedRectangle;
                repaint();
            }
        } // y textField complete
    }
    public void keyPressed(KeyEvent e) {
        if(selectedRectangle==null)
        {
            int keyCode = e.getKeyCode();
            String[] temp;
            String tempString="";
            char tempChar=' ';
            if(e.isControlDown() && e.getKeyCode()==e.VK_Z)
            {
                //if textPointer which contains the x,y coordinate of RoundRectangle2D Object is not null
                //logic to empty the map of current textMap of RoundRectangle2D Object
                //if textPointer which contains the x,y coordinate of RoundRectangle2D Object is null
                //then remove the previous object of RoundRectangle2D from the Map
                //else return dont do anything
            }
            switch( keyCode )
            {
                case KeyEvent.VK_HOME :
                    if(stringBuffer.indexOf("|")!=0)
                    {
                        stringBuffer=stringBuffer.replaceAll("\\|","");
                        stringBuffer=new StringBuilder(stringBuffer).insert(0,"|").toString();
                    }
                    break;
                case KeyEvent.VK_END :
                    if(stringBuffer.indexOf("|")!=stringBuffer.length())
                    {
                        stringBuffer=stringBuffer.replaceAll("\\|","");
                        stringBuffer=new StringBuilder(stringBuffer).insert(stringBuffer.length(),"|").toString();
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(caretAtLine>30)
                    {
                        caretPosition=stringBuffer.indexOf("|");
                        if(!stringBuffer.equals("|"))
                        {
                            temp=stringBuffer.split("\\|");
                            try
                            {
                                stringBuffer=temp[0]+temp[1];
                            }
                            catch(ArrayIndexOutOfBoundsException aioobe)
                            {
                                stringBuffer=temp[0];
                            }
                            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine),new Text(stringBuffer,reportPanel.getTextFont ()));
                        }
                        else if(stringBuffer.equals("|"))
                        {
                            stringBuffer="";
                            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine),new Text(stringBuffer,reportPanel.getTextFont ()));
                        }
                        caretAtLine-=30;
                        stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine)).getText();
                        if(stringBuffer.equals(""))
                        {
                            stringBuffer="|";
                            break;
                        }
                        if(stringBuffer.length()<caretPosition)
                        {
                            stringBuffer=stringBuffer+"|";
                            break;
                        }
                        stringBuffer=new StringBuilder(stringBuffer).insert(caretPosition,"|").toString();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(textAreaHeight-caretAtLine>20)
                    {
                        caretPosition=stringBuffer.indexOf("|");
                        if(!stringBuffer.equals("|"))
                        {
                            temp=stringBuffer.split("\\|");
                            try
                            {
                                stringBuffer=temp[0]+temp[1];
                            }
                            catch(ArrayIndexOutOfBoundsException aioobe)
                            {
                                stringBuffer=temp[0];
                            }
                            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine),new Text(stringBuffer,reportPanel.getTextFont ()));
                        }
                        else if(stringBuffer.equals("|"))
                        {
                            stringBuffer="";
                            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine),new Text(stringBuffer,reportPanel.getTextFont()));
                        }
                        caretAtLine+=30;
                        stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine)).getText();
                        if(stringBuffer.equals(""))
                        {
                            stringBuffer=new String("|");
                            break;
                        }
                        if(stringBuffer.length()<caretPosition)
                        {
                            stringBuffer=stringBuffer+"|";
                            break;
                        }
                        stringBuffer=new StringBuilder(stringBuffer).insert(caretPosition,"|").toString();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(stringBuffer.startsWith("|"))
                    {
                        if(caretAtLine!=30)
                        {
                            tempMap.put(new Point((int)textPointer.getX(),((int)textPointer.getY()+caretAtLine)),new Text(stringBuffer.substring(1,stringBuffer.length()),reportPanel.getTextFont()));
                            stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),((int)textPointer.getY()+caretAtLine-30))).getText()+"|";
                            caretAtLine-=30;
                        }
                        break;
                    }
                    caretPosition=stringBuffer.indexOf("|");
                    if(caretPosition==stringBuffer.length()) tempString="";
                    else tempString=stringBuffer.substring(caretPosition+1,stringBuffer.length());
                    tempChar=stringBuffer.charAt(caretPosition-1);
                    stringBuffer=stringBuffer.substring(0,caretPosition-1);
                    stringBuffer=stringBuffer+"|"+tempChar+tempString;
                    break;
                case KeyEvent.VK_RIGHT :
                    caretPosition=stringBuffer.indexOf("|");
                    if(caretPosition==stringBuffer.length()-1)
                    {
                        if(textAreaHeight-caretAtLine>20)
                        {
                            tempMap.put(new Point((int)textPointer.getX(),((int)textPointer.getY()+caretAtLine)),new Text(stringBuffer.substring(0,stringBuffer.length()-1),reportPanel.getTextFont()));
                            stringBuffer=tempMap.remove(new Point((int)textPointer.getX(),((int)textPointer.getY()+caretAtLine+30))).getText();
                            if(stringBuffer==null) stringBuffer="|";
                            stringBuffer="|"+stringBuffer;
                            caretAtLine+=30;
                        }
                        break;
                    }
                    tempString=stringBuffer.substring(caretPosition+2,stringBuffer.length());
                    tempChar=stringBuffer.charAt(caretPosition+1);
                    stringBuffer=stringBuffer.substring(0,caretPosition);
                    stringBuffer=stringBuffer+tempChar+"|"+tempString;
                    break;
            }
            repaint();
        }
    }
    public void keyReleased(KeyEvent ke)
    {
    }
    public void makeWidthValid(Line line)
    {
        if(line.getWidth()<0)
        {
            return;
        }
        if(line.getX2()>right)
        {
            line.setX2(right);
        }
    }
    public void makeHeightValid(Line line)
    {
        if(line.getHeight()<0)
        {
            return;
        }
        if(line.getY2()>bottom)
        {
            line.setY2(bottom);
        }
    }
    public boolean isTextFieldValid(JTextField textField,KeyEvent ke)
    {
        if(ke.getKeyChar()!='0' || ke.getKeyChar()!='1' || ke.getKeyChar()!='3' || ke.getKeyChar()!='3' ||ke.getKeyChar()!='4' || ke.getKeyChar()!='5' || ke.getKeyChar()!='6' || ke.getKeyChar()!='7' || ke.getKeyChar()!='8' || ke.getKeyChar()!='9')
        {
            reportPanel.getxTextField ().setText(String.valueOf(0));
        }
        return true;
    }
    public void makeRectangleValid(Rectangle rectangle)
    {
        makeRectangleValid(rectangle,true);
    }
    public void makeRectangleValid(TextRectangle rectangle)
    {
        int x,y,width,height;
        x=(int)rectangle.getX();
        y=(int)rectangle.getY();
        width=(int)rectangle.getWidth();
        height=(int)rectangle.getHeight();
        if(y>bottom)
        {
            y=bottom;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(x>right)
        {
            x=right;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(y<top)
        {
            y=top;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(x<left)
        {
            x=left;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(x+width>right)
        {
            x=right-width;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(y+height>bottom)
        {
            y=bottom-height;
            rectangle.setRoundRect(x,y,width,height,0,0);
        }
        if(x<left)
        {
            x=left;
            rectangle.setRoundRect(x,y,width,height,0,0);
            width=width+(x-left);
        }
        if(y<top)
        {
            y=top;
            rectangle.setRoundRect(x,y,width,height,0,0);
            height=height+(y-top);
        }
    }
    public void makeRectangleValid(Rectangle rectangle,boolean changeSize)
    {
        int x,y,width,height;
        x=(int)rectangle.getX();
        y=(int)rectangle.getY();
        width=(int)rectangle.getWidth();
        height=(int)rectangle.getHeight();
        if(y>bottom)
        {
            y=bottom;
            rectangle.setLocation(x,y);
        }
        if(x>right)
        {
            x=right;
            rectangle.setLocation(x,y);
        }
        if(y<top)
        {
            y=top;
            rectangle.setLocation(x,y);
        }
        if(x<left)
        {
            x=left;
            rectangle.setLocation(x,y);
        }
        if(x+width>right)
        {
            if(!changeSize)
            {
                x=right-width;
                rectangle.setLocation(x,y);
            }
            if(changeSize)
            {
                width=Math.abs(x-right);
                rectangle.setSize(width,height);
            }
        }
        if(y+height>bottom)
        {
            if(changeSize)
            {
                height=Math.abs(y-bottom);
                rectangle.setSize(width,height);
            }
            if(!changeSize)
            {
                y=bottom-height;
                rectangle.setLocation(x,y);
            }
        }
        if(x<left)
        {
            width=width+(x-left);
            x=left;
            rectangle.setLocation(x,y);
            if(changeSize)rectangle.setSize(width,height);
        }
        if(y<top)
        {
            height=height+(y-top);
            y=top;
            rectangle.setLocation(x,y);
            if(changeSize)rectangle.setSize(width,height);
        }
    }
    public void makeLineCoordinatesValid(Line line)
    {
        int x1,x2,y1,y2,lineWidth,lineHeight;
        lineWidth=line.getWidth();
        lineHeight=line.getHeight();
        x1=line.getX1();
        x2=line.getX2();
        y1=line.getY1();
        y2=line.getY2();
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getLineButton ())
        {
            if(lineWidth==0)
            {
                if(y1>bottom)
                {
                    line.setY2(bottom-lineHeight);
                    line.setY1(bottom-lineHeight);
                }
                if(y2<top)
                {
                    line.setY1(top);
                    line.setY2(top+lineHeight);
                }
                if(y2>bottom)
                {
                    line.setY2(bottom);
                }
                if(y1<top)
                {
                    line.setY1(top);
                }
                if(x1<left)
                {
                    line.setX1(left);
                    line.setX2(left);
                }
                if(x1>right)
                {
                    line.setX1(right);
                    line.setX2(right);
                }
                return;
            }
            if(lineHeight==0)
            {
                /**/
                if(x1>right)
                {
                    line.setX2(right);
                    line.setX1(right-lineWidth);
                }
                if(x2<left)
                {
                    line.setX1(left);
                    line.setX2(left+lineWidth);
                }
                /**/
                if(x2>right)
                {
                    line.setX2(right);
                }
                if(x1<left)
                {
                    line.setX1(left);
                }
                /**/
                if(y1<top)
                {
                    line.setY1(top);
                    line.setY2(top);
                }
                if(y2>bottom)
                {
                    line.setY1(bottom);
                    line.setY2(bottom);
                }
                /**/
                return;
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getMoveButton ())
        {
            if(lineWidth==0)
            {
                if(y1<top)
                {
                    line.setY1(top);
                    line.setY2(top+lineHeight);
                }
                if(y2>bottom)
                {
                    line.setY2(bottom);
                    line.setY1(bottom-lineHeight);
                }
                if(x1<left)
                {
                    line.setX1(left);
                    line.setX2(left);
                }
                if(x1>right)
                {
                    line.setX1(right);
                    line.setX2(right);
                }
            }
            if(lineHeight==0)
            {
                if(x1<left)
                {
                    line.setX1(left);
                    line.setX2(left+lineWidth);
                }
                if(x2>right)
                {
                    line.setX2(right);
                    line.setX1(right-lineWidth);
                }
                if(y1<top)
                {
                    line.setY1(top);
                    line.setY2(top);
                }
                if(y1>bottom)
                {
                    line.setY1(bottom);
                    line.setY2(bottom);
                }
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            if(lineWidth==0)
            {
                if(y1<top)
                {
                    line.setY1(top);
                }
                if(y2<top)
                {
                    line.setY1(top);
                    line.setY2(y1);
                }
                if(y1>bottom)
                {
                    line.setY1(y2);
                    line.setY2(bottom);
                }
                if(y2>bottom)
                {
                    line.setY2(bottom);
                }
            }
            if(lineHeight==0)
            {
                if(x2<left)
                {
                    line.setX2(x1);
                    line.setX1(left);
                }
                if(x2>right)
                {
                    line.setX2(right);
                }
                if(x1<left)
                {
                    line.setX1(left);
                }
                if(x1>right)
                {
                    line.setX1(x2);
                    line.setX2(right);
                }
            }
        }
    }
    public void setLineYCoordinates(Line line)
    {
        //480,640
        int y1,y2,height;
        height=selectedLine.getHeight();
        selectedLine.setY2(selectedLine.getHeight()+Integer.parseInt(reportPanel.getyTextField ().getText()));
        selectedLine.setY1(Integer.parseInt(reportPanel.getyTextField ().getText()));
        y1=line.getY1();
        y2=line.getY2();
        if(y1>bottom)
        {
            reportPanel.getyTextField ().setText(String.valueOf(bottom-line.getHeight()));
            line.setY1(bottom-height);
            line.setY2(bottom);
        }
        if(y2>bottom)
        {
            reportPanel.getyTextField ().setText(String.valueOf(bottom-line.getHeight()));
            line.setY1(bottom-height);
            line.setY2(bottom);
        }
        if(y1<top && y2<top)
        {
            reportPanel.getyTextField ().setText(String.valueOf(top));
            line.setY1(top);
            line.setY2(top);
        }
        if(y1<top)
        {
            reportPanel.getyTextField ().setText(String.valueOf(top));
            line.setY1(top);
            line.setY2(top+height);
        }
    }


    public void setLineXCoordinates(Line line)
    {
        //480,640
        int x1,x2,width;
        width=selectedLine.getWidth();
        selectedLine.setX2(selectedLine.getWidth()+Integer.parseInt(reportPanel.getxTextField ().getText()));
        selectedLine.setX1(Integer.parseInt(reportPanel.getxTextField ().getText()));
        x1=line.getX1();
        x2=line.getX2();
        if(x2>right)
        {
            reportPanel.getxTextField ().setText(String.valueOf(right-line.getWidth()));
            line.setX1(right-width);
            line.setX2(right);
        }
        if(x1>right)
        {
            reportPanel.getxTextField ().setText(String.valueOf(right-line.getWidth()));
            line.setX1(right-width);
            line.setX2(right);
        }
/*if(x1<left)
{
  reportPanel.getxTextField ().setText(String.valueOf(left));
  line.setX1(left);
  line.setX2(left+width);
}*/
    }
    public void paint(Graphics g)
    {

        drawFromDS(g);
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getImageButton ())
            drawImageOnCanvas(g);
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getTextButton ())
            drawBufferString();
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getRectangleButton ())
            drawRectangleByMouse(g);
        if(reportPanel.getActionEventHolder ()!=null&&reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            selectShapeByMouse(g);
        }
        if(reportPanel.getActionEventHolder ()!=null&&reportPanel.getActionEventHolder ().getSource()==reportPanel.getMoveButton ())
            moveShapeByMouse(g);
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getLineButton ())
            drawLineByMouse(g);
        deSelectLine();
        deSelectRectangle();
        if(selectedLine!=null &&(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()!=reportPanel.getDeleteButton ()))
        {
            selectColoredLine(selectedLine);
            reportPanel.getHistoryStackPanel ().selectedEntity(selectedLine);
        }
        if(selectedRectangle!=null && (reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()!=reportPanel.getDeleteButton ()))
        {
            selectColoredRectangle(selectedRectangle);
            reportPanel.getHistoryStackPanel ().selectedEntity(selectedRectangle);
        }
    }
    public void deleteSelectedLine()
    {
        reportPanel.getHistoryStackPanel ().popEntity(selectedLine);
        lineCovers.remove(selectedLine);
        lines.remove(selectedLine);
        if(lines.size()==0)selectedLine=null;
        if(rectangles.size()==0 && lines.size()==0 && images.size()==0 && textMap.size()==0)
        {
            reportPanel.getPropertiesPanel ().setVisible(false);
            reportPanel.getToolbar ().setBounds(10,20,80,250+140);
        }
        repaint();
    }
    public void deleteSelectedRectangle()
    {
        reportPanel.getHistoryStackPanel ().popEntity(selectedRectangle);
        rectangles.remove(selectedRectangle);
        if(rectangles.size()==0) selectedRectangle=null;
        if(rectangles.size()==0 && lines.size()==0 && images.size()==0 && textMap.size()==0)
        {
            reportPanel.getPropertiesPanel ().setVisible(false);
            reportPanel.getToolbar ().setBounds(10,20,80,250+140);
        }
        repaint();
    }
    public void deleteSelectedImage()
    {
        images.remove(selectedImage.getKey());
        if(rectangles.size()==0 && lines.size()==0 && images.size()==0 && textMap.size()==0)
        {
            reportPanel.getPropertiesPanel ().setVisible(false);
            reportPanel.getToolbar ().setBounds(10,20,80,250+140);
        }
        repaint();
    }
    public void mouseMoved(MouseEvent e)
    {

    }
    public void mouseDragged(MouseEvent me)
    {
        reportPanel.getxTextField ().setEnabled(true);
        reportPanel.getyTextField ().setEnabled(true);
        reportPanel.getPropertiesPanel ().setVisible(true);
        reportPanel.getToolbar ().setBounds(10,20,80,250+140+50+120);
        Graphics g=getGraphics();
        currentXLocation=me.getX();
        currentYLocation=me.getY();
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getLineButton ())
        {
            setWidthHeightTextField(currentXLocation-lastClickedXLocation,currentYLocation-lastClickedYLocation);
            repaint();
            if(selectedLine!=null)
            {
                Rectangle rec=new Rectangle(selectedLine.getX2()-2,selectedLine.getY2()-2,4,4);
                if(rec.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setWidthHeightTextField(me.getX()-Integer.parseInt(reportPanel.getxTextField ().getText()),me.getY()-Integer.parseInt(reportPanel.getyTextField ().getText()));
                    repaint();
                }
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getRectangleButton ())
        {
            setRectangleTextFields(Math.min(lastClickedXLocation,currentXLocation),Math.min(lastClickedYLocation,currentYLocation),Math.abs(lastClickedXLocation-currentXLocation),Math.abs(lastClickedYLocation-currentYLocation));
            repaint();
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            if(selectedLine!=null)
            {
                Rectangle rec=new Rectangle(selectedLine.getX2()-2,selectedLine.getY2()-2,4,4);
                if(rec.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setWidthHeightTextField(me.getX()-Integer.parseInt(reportPanel.getxTextField ().getText()),me.getY()-Integer.parseInt(reportPanel.getyTextField ().getText()));
                    repaint();
                }
            }
            if(selectedRectangle!=null)
            {
                Point p1,p2,p3,p4;
                p1=new Point((int)selectedRectangle.getX(),(int)selectedRectangle.getY());
                p2=new Point((int)(selectedRectangle.getX()+selectedRectangle.getWidth()),(int)selectedRectangle.getY());
                p3=new Point((int)(selectedRectangle.getX()+selectedRectangle.getWidth()),(int)(selectedRectangle.getY()+selectedRectangle.getHeight()));
                p4=new Point((int)selectedRectangle.getX(),(int)(selectedRectangle.getY()+selectedRectangle.getHeight()));
                Rectangle rect1=new Rectangle((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2,4,4);
                if(rect1.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setRectangleTextFields(Math.min((int)me.getX(),(int)p3.getX()),Math.min((int)me.getY(),(int)p3.getY()),Math.abs((int)me.getX()-(int)p3.getX()),Math.abs((int)me.getY()-(int)p3.getY()));
                }
                Rectangle rect2=new Rectangle((int)selectedRectangle.getX()-2+(int)selectedRectangle.getWidth(),(int)selectedRectangle.getY()-2,4,4);
                if(rect2.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setRectangleTextFields(Math.min((int)me.getX(),(int)p4.getX()),Math.min((int)me.getY(),(int)p4.getY()),Math.abs((int)me.getX()-(int)p4.getX()),Math.abs((int)me.getY()-(int)p4.getY()));
                }
                Rectangle rect3=new Rectangle((int)selectedRectangle.getX()-2+(int)selectedRectangle.getWidth(),(int)selectedRectangle.getY()-2+(int)selectedRectangle.getHeight(),4,4);
                if(rect3.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setRectangleTextFields(Math.min((int)me.getX(),(int)p1.getX()),Math.min((int)me.getY(),(int)p1.getY()),Math.abs((int)me.getX()-(int)p1.getX()),Math.abs((int)me.getY()-(int)p1.getY()));
                }
                Rectangle rect4=new Rectangle((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2+(int)selectedRectangle.getHeight(),4,4);
                if(rect4.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    setRectangleTextFields(Math.min((int)me.getX(),(int)p2.getX()),Math.min((int)me.getY(),(int)p2.getY()),Math.abs((int)me.getX()-(int)p2.getX()),Math.abs((int)me.getY()-(int)p2.getY()));
                }
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getMoveButton ())
        {
            if(movedLine!=null)
            {
                updateLineLocation(getGraphics(),me);
            }
            if(movedRectangle!=null)
            {
                updateRectangleLocation(me);
            }
            if(movedText!=null)
            {
                reportPanel.setLast_operation(ReportPanel.last_operations.MOVE_BUTTON);
                updateTextLocation(me);
            }
            if(movedImage!=null)
            {
                updateImageLocation(me);
            }
        }
    }
    public void setWidthHeightTextField(int width,int height)
    {
        reportPanel.getWidthTextField ().setText(String.valueOf(Math.abs(width)));
        reportPanel.getHeightTextField ().setText(String.valueOf(Math.abs(height)));
    }
    public void setRectangleTextFields(Rectangle rectangle)
    {
        setRectangleTextFields((int)rectangle.getX(),(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
    }
    public void setRectangleTextFields(int x,int y,int width,int height)
    {
        reportPanel.getxTextField ().setEnabled(true);
        reportPanel.getyTextField ().setEnabled(true);
        reportPanel.getWidthTextField ().setEnabled(true);
        reportPanel.getHeightTextField ().setEnabled(true);
        reportPanel.getWidthTextField ().setText(String.valueOf(Math.abs(width)));
        reportPanel.getHeightTextField ().setText(String.valueOf(Math.abs(height)));
        reportPanel.getyTextField ().setText(String.valueOf(Math.abs(y)));
        reportPanel.getxTextField ().setText(String.valueOf(Math.abs(x)));
    }
    public void setImageAndTextTextFields(Rectangle imageRect)
    {
        reportPanel.getxTextField ().setText(String.valueOf((int)imageRect.getX()));
        reportPanel.getyTextField ().setText(String.valueOf((int)imageRect.getY()));
        reportPanel.getHeightTextField ().setEnabled(false);
        reportPanel.getHeightTextField ().setText(String.valueOf((int)imageRect.getHeight()));
        reportPanel.getWidthTextField ().setEnabled(false);
        reportPanel.getWidthTextField ().setText(String.valueOf((int)imageRect.getWidth()));
    }
    public void mouseExited(MouseEvent me)
    {

    }
    public void mouseClicked(MouseEvent me)
    {
        lastClickedXLocation=me.getX();
        lastClickedYLocation=me.getY();
        Point point=me.getPoint();
        if(me.getButton()==3)
        {
            if(isImageSelected(me.getPoint()))
            {
                deleteSelectedImage();
                reportPanel.getHistoryStackPanel ().popEntity(selectedImage.getValue());
                repaint();
            }
            return;
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getImageButton ())
        {
            loadImage(point);
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getTextButton ())
        {
            if(me.getClickCount()==2)
            {
                textPointer=new Point(me.getX(),me.getY());
                textAreaRectangle=new TextRectangle((int)textPointer.getX(),(int)textPointer.getY(),textAreaWidth,textAreaHeight,0,0);
                drawTextArea();
                reportPanel.getHistoryStackPanel ().pushEntity(textAreaRectangle);
                reportPanel.getxTextField ().setEnabled(false);
                reportPanel.getyTextField ().setEnabled(false);
                setImageAndTextTextFields(new Rectangle((int)textAreaRectangle.getX(),(int)textAreaRectangle.getY(),(int)textAreaRectangle.getWidth(),(int)textAreaRectangle.getHeight()));
            }
            else
            {
                saveText();
                repaint();
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getDeleteButton ())
        {
/*
  if(selectedLine!=null)
  {
    System.out.println(selectedLine);
    deleteSelectedLine();
    repaint();
    return;

  }
  else if(selectedText!=null)
  {
    System.out.println(selectedText);
    deleteSelectedText();
    repaint();
    return;
  }
  else if(selectedRectangle!=null)
  {
    System.out.println(selectedRectangle);
    deleteSelectedRectangle();
    repaint();
    return;
  }
  else if(selectedImage!=null)
  {
    System.out.println(selectedImage);
    deleteSelectedImage();
    reportPanel.getHistoryStackPanel ().popEntity(selectedImage.getValue());
    repaint();
    return;
  }
  /*
  if(isLineSelected(me))
  {
  deleteSelectedLine();
  repaint();
  return;
  }
  if(isRectangleSelected(me))
  {
    deleteSelectedRectangle();
    repaint();
    return;
  }
  if(isTextSelected(me,false))
  {
  deleteSelectedText();
  repaint();
  return;
  }
  if(isImageSelected(me))
  {
    deleteSelectedImage();
    reportPanel.getHistoryStackPanel ().popEntity(selectedImage.getValue());
    repaint();
    return;
  }
  System.out.println("deleteButton chala");*/

        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            if(isLineSelected(me))
            {
                selectedImage=null;
                selectedRectangle=null;
                selectedText=null;
                reportPanel.getPropertiesPanel ().setVisible(true);
                reportPanel.getToolbar ().setBounds(10,20,80,250+120+140+50);
                repaint();
                setTextFieldForLine(selectedLine);
            }
            else if(isTextSelected(me,true))
            {
                selectedImage=null;
                selectedLine=null;
                selectedRectangle=null;
                reportPanel.getPropertiesPanel ().setVisible(true);
                reportPanel.getToolbar ().setBounds(10,20,80,250+120+140+50);
                editText();
                repaint();
            }
            else if(isImageSelected(me.getPoint()))
            {
                selectedLine=null;
                selectedRectangle=null;
                selectedText=null;
                reportPanel.getPropertiesPanel ().setVisible(true);
                reportPanel.getToolbar ().setBounds(10,20,80,250+120+140+50);
                repaint();
                reportPanel.getxTextField ().setEnabled(true);
                reportPanel.getyTextField ().setEnabled(true);
                setImageAndTextTextFields(selectedImage.getKey());
            }
            else if(isRectangleSelected(me))
            {
                selectedImage=null;
                selectedLine=null;
                selectedText=null;
                reportPanel.getPropertiesPanel ().setVisible(true);
                reportPanel.getToolbar ().setBounds(10,20,80,250+120+140+50);
                repaint();
                setRectangleTextFields(selectedRectangle);
            }
            else
            {
                selectedImage=null;
                selectedLine=null;
                selectedRectangle=null;
                selectedText=null;
                reportPanel.getPropertiesPanel ().setVisible(false);
                reportPanel.getToolbar ().setBounds(10,20,80,250+140);
                reportPanel.getHistoryStackPanel ().getEntityList ().clearSelection();
                saveText();
                repaint();
            }
        }
    }
    public void loadImage(Point point)
    {
        JFileChooser jFileChooser=new JFileChooser(new File("c:\\MDCanvas"));
        FileNameExtensionFilter fnef=new FileNameExtensionFilter("All Types","jpg","gif","png","jpeg");
        jFileChooser.setFileFilter(fnef);
        int result=jFileChooser.showOpenDialog(this);
        if(result==0)
        {
            // code to load image from the system over the canvas
            imageFile=jFileChooser.getSelectedFile();
            image=new ImageComponent(imageFile,point);
            images.put(image.getRect(),image);
            repaint();
            reportPanel.getHistoryStackPanel ().pushEntity(image);
            reportPanel.getHistoryStackPanel ().selectedEntity(image);
            setImageSelected(image);
        }
    }
    public void saveText()
    {
        if(textPointer!=null)
        {
            if(textAreaRectangle!=null)
            {
                textAreaRectangle.setHeight(textAreaHeight);
                textAreaRectangle.setWidth(textAreaWidth);
            }
            if(reportPanel.getLast_operation ()!=ReportPanel.last_operations.MOVE_BUTTON && tempMap.size()==0 && stringBuffer.equals("|"))
            {
                textPointer=null;
                textMap.remove(textAreaRectangle);
                reportPanel.getHistoryStackPanel ().popEntity(textAreaRectangle);
                return;
            }
            tempMap.put(new Point((int)textPointer.getX(),(int)textPointer.getY()+caretAtLine),new Text(stringBuffer.replaceAll("\\|",""),reportPanel.getTextFont()));
            for(Map.Entry<Point,Text> xe:tempMap.entrySet())
            {
                Text text=xe.getValue();
                text.setLocation(xe.getKey());
                textList.put(xe.getKey(),text);
            }
            textMap.put(textAreaRectangle,(HashMap<Point,Text>)textList);
        }
        textAreaRectangle=null;
        textPointer=null;
        tempMap=new HashMap<Point,Text>();
        textAreaHeight=50;
        textAreaWidth=150;
        caretAtLine=30;
        stringBuffer="|";
        textList=new HashMap<Point,Text>();
    }
    public void drawTextArea()
    {
        Graphics g=getGraphics();
        float dash1[] = { 10.0f };
        BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.blue);
        g2.setStroke(dashed);
        g2.draw(textAreaRectangle);
        g2.setFont(reportPanel.getTextFont());
    }
    public void setTextFieldForLine(Line line)
    {
        reportPanel.getxTextField ().setEnabled(true);
        reportPanel.getyTextField ().setEnabled(true);
        reportPanel.getxTextField ().setText(String.valueOf(line.getX1()));
        reportPanel.getyTextField ().setText(String.valueOf(line.getY1()));
        reportPanel.getWidthTextField ().setText(String.valueOf(line.getWidth()));
        reportPanel.getHeightTextField ().setText(String.valueOf(line.getHeight()));
        if(line.getWidth()==0)
        {
            reportPanel.getWidthTextField ().setEnabled(false);
            reportPanel.getHeightTextField ().setEnabled(true);
        }
        if(line.getHeight()==0)
        {
            reportPanel.getHeightTextField ().setEnabled(false);
            reportPanel.getWidthTextField ().setEnabled(true);
        }
    }
    public void mousePressed(MouseEvent me)
    {
        if(textPointer!=null && textAreaRectangle!=null)
        {
            saveText();
        }
        lastClickedXLocation=me.getX();
        lastClickedYLocation=me.getY();

        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getMoveButton ())
        {
            if(isLineSelected(me))
            {
                distanceFromX1=me.getX()-selectedLine.getX1();
                distanceFromX2=selectedLine.getX2()-me.getX();
                distanceFromY1=me.getY()-selectedLine.getY1();
                distanceFromY2=selectedLine.getY2()-me.getY();
                movedLine=selectedLine;
                movedText=null;
                movedRectangle=null;
                movedImage=null;
            }
            else if(isTextSelected(me,false))
            {
                movedText=selectedText;
                movedLine=null;
                movedImage=null;
                movedRectangle=null;
                distanceFromX=me.getX()-(int)movedText.getKey().getX();
                distanceFromY=me.getY()-(int)movedText.getKey().getY();
            }
            else if(isImageSelected(me.getPoint()))
            {
                movedImage=selectedImage;
                movedText=null;
                movedLine=null;
                movedRectangle=null;
                distanceFromX=me.getX()-(int)movedImage.getKey().getX();
                distanceFromY=me.getY()-(int)movedImage.getKey().getY();
                reportPanel.getxTextField ().setEnabled(true);
                reportPanel.getyTextField ().setEnabled(true);
                setImageAndTextTextFields(movedImage.getKey());
            }
            else if(isRectangleSelected(me))
            {
                movedRectangle=selectedRectangle;
                movedText=null;
                movedLine=null;
                movedImage=null;
                distanceFromX=me.getX()-(int)movedRectangle.getX();
                distanceFromY=me.getY()-(int)movedRectangle.getY();
                setRectangleTextFields((int)movedRectangle.getX(),(int)movedRectangle.getY(),(int)movedRectangle.getWidth(),(int)movedRectangle.getHeight());
            }
        }
        else if((selectedLine!=null) && (reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ()))
        {
            setTextFieldForLine(selectedLine);
        }
    }
    public void mouseReleased(MouseEvent me)
    {
        currentXLocation=me.getX();
        currentYLocation=me.getY();
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getMoveButton ())
        {
            if(movedLine!=null)
            {
                selectedLine=movedLine;
                setTextFieldForLine(movedLine);
                makeLineCoordinatesValid(movedLine);
                addLineCover(this.movedLine);
                setTextFieldForLine(movedLine);
                movedLine=null;
                selectedRectangle=null;
                movedRectangle=null;
                selectedText=null;
                movedText=null;
                selectedImage=null;
                movedImage=null;
                repaint();
            }
            if(movedRectangle!=null)
            {
                selectedRectangle=movedRectangle;
                makeRectangleValid(movedRectangle);
                setRectangleTextFields(movedRectangle);
                movedRectangle=null;
                selectedLine=null;
                selectedText=null;
                movedText=null;
                movedLine=null;
                selectedImage=null;
                movedImage=null;
                repaint();
            }
            if(movedText!=null)
            {
                makeRectangleValid(movedText.getKey());
                setImageAndTextTextFields(new Rectangle((int)movedText.getKey().getX(),(int)movedText.getKey().getY(),(int)movedText.getKey().getHeight(),(int)movedText.getKey().getWidth()));
                reportPanel.getxTextField ().setEnabled(false);
                reportPanel.getyTextField ().setEnabled(false);
                movedText=null;
                selectedRectangle=null;
                selectedLine=null;
                movedRectangle=null;
                movedLine=null;
                selectedImage=null;
                movedImage=null;
                repaint();
            }
            if(movedImage!=null)
            {
                selectedImage=movedImage;
                movedImage=null;
                selectedRectangle=null;
                selectedLine=null;
                movedRectangle=null;
                movedLine=null;
                selectedText=null;
                movedText=null;
            }
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getLineButton ())
        {
            if(Math.abs(currentXLocation-lastClickedXLocation)>Math.abs(currentYLocation-lastClickedYLocation))
            {
                newLine=new Line(Math.min(lastClickedXLocation,currentXLocation),lastClickedYLocation,Math.max(currentXLocation,lastClickedXLocation),lastClickedYLocation);
                if((currentXLocation>right || currentYLocation>bottom) || (currentXLocation<left || currentYLocation<top))
                {
                    makeLineCoordinatesValid(newLine);
                }
                lines.add(newLine);
                addLineCover(newLine);
                setTextFieldForLine(newLine);
                selectedLine=newLine;
            }
            else
            {
                newLine=new Line(lastClickedXLocation,Math.min(lastClickedYLocation,currentYLocation),lastClickedXLocation,Math.max(currentYLocation,lastClickedYLocation));
                if((currentXLocation>right || currentYLocation>bottom) || (currentXLocation<left || currentYLocation<top))
                {
                    makeLineCoordinatesValid(newLine);
                }
                lines.add(newLine);
                addLineCover(newLine);
                setTextFieldForLine(newLine);
                selectedLine=newLine;
            }
            selectedRectangle=null;
            reportPanel.getHistoryStackPanel ().pushEntity(newLine);
            repaint();
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getRectangleButton ())
        {
            newRectangle=new Rectangle(Math.min(lastClickedXLocation,currentXLocation),Math.min(lastClickedYLocation,currentYLocation),Math.abs(lastClickedXLocation-currentXLocation),Math.abs(lastClickedYLocation-currentYLocation));
            if((me.getX()<left || me.getX()+newRectangle.getWidth()>right) || (me.getY()<top || me.getY()+newRectangle.getHeight()>bottom))
            {
                makeRectangleValid(newRectangle,true);
            }
            rectangles.add(newRectangle);
            setRectangleTextFields(newRectangle);
            reportPanel.getHistoryStackPanel ().pushEntity(newRectangle);
            selectedRectangle=newRectangle;
            selectedLine=null;
            repaint();
        }
        if(reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())
        {
            if(selectedLine!=null)
            {
                Rectangle rec1=new Rectangle(selectedLine.getX2()-2,selectedLine.getY2()-2,4,4);
                if(rec1.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    if(selectedLine.getWidth()==0 && selectedLine.isVertical==true)
                    {
                        removeLineCover(selectedLine);
                        selectedLine.setY2(currentYLocation);
                        makeLineCoordinatesValid(selectedLine);
                        setTextFieldForLine(selectedLine);
                        addLineCover(selectedLine);
                        repaint();
                    }
                    if(selectedLine.getHeight()==0 && selectedLine.isHorizontal==true)
                    { //abc
                        removeLineCover(selectedLine);
                        selectedLine.setX2(currentXLocation);
                        makeLineCoordinatesValid(selectedLine);
                        setTextFieldForLine(selectedLine);
                        addLineCover(selectedLine);
                        repaint();
                    }
                    if(selectedLine.getWidth()==0&&selectedLine.getHeight()==0)
                    {
                        removeLineCover(selectedLine);
                        lines.remove(selectedLine);
                        reportPanel.getHistoryStackPanel ().popEntity(selectedLine);
                        selectedLine=null;
                        repaint();
                    }
                }
                //abc
                Rectangle rec2=new Rectangle(selectedLine.getX1()-2,selectedLine.getY1()-2,4,4);
                if(rec2.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    if(selectedLine.getHeight()==0 && selectedLine.isHorizontal==true)
                    {
                        removeLineCover(selectedLine);
                        selectedLine.setX1(currentXLocation);
                        makeLineCoordinatesValid(selectedLine);
                        setTextFieldForLine(selectedLine);
                        addLineCover(selectedLine);
                        repaint();
                    }
                    if(selectedLine.getWidth()==0 && selectedLine.isVertical==true)
                    {
                        removeLineCover(selectedLine);
                        selectedLine.setY1(currentYLocation);
                        makeLineCoordinatesValid(selectedLine);
                        setTextFieldForLine(selectedLine);
                        addLineCover(selectedLine);
                        repaint();
                    }
                    if(selectedLine.getWidth()==0&&selectedLine.getHeight()==0)
                    {
                        removeLineCover(selectedLine);
                        lines.remove(selectedLine);
                        reportPanel.getHistoryStackPanel ().popEntity(selectedLine);
                        selectedLine=null;
                        repaint();
                    }
                }
            }
            if(( reportPanel.getActionEventHolder ()!=null && reportPanel.getActionEventHolder ().getSource()==reportPanel.getSelectButton ())&&selectedRectangle!=null )
            {
                Point p1,p2,p3,p4;
                p1=new Point((int)selectedRectangle.getX(),(int)selectedRectangle.getY());
                p2=new Point((int)(selectedRectangle.getX()+selectedRectangle.getWidth()),(int)selectedRectangle.getY());
                p3=new Point((int)(selectedRectangle.getX()+selectedRectangle.getWidth()),(int)(selectedRectangle.getY()+selectedRectangle.getHeight()));
                p4=new Point((int)selectedRectangle.getX(),(int)(selectedRectangle.getY()+selectedRectangle.getHeight()));
                Rectangle rect1=new Rectangle((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2,4,4);
                Rectangle rect2=new Rectangle((int)selectedRectangle.getX()-2+(int)selectedRectangle.getWidth(),(int)selectedRectangle.getY()-2,4,4);
                Rectangle rect3=new Rectangle((int)selectedRectangle.getX()-2+(int)selectedRectangle.getWidth(),(int)selectedRectangle.getY()-2+(int)selectedRectangle.getHeight(),4,4);
                Rectangle rect4=new Rectangle((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2+(int)selectedRectangle.getHeight(),4,4);
                if(rect1.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    rectangles.remove(selectedRectangle);
                    selectedRectangle.setLocation(Math.min((int)me.getX(),(int)p3.getX()),Math.min((int)me.getY(),(int)p3.getY()));
                    selectedRectangle.setSize(Math.abs((int)me.getX()-(int)p3.getX()),Math.abs((int)me.getY()-(int)p3.getY()));
                    if((me.getX()<left || me.getX()+selectedRectangle.getWidth()>right) || (me.getY()<top || me.getY()+selectedRectangle.getHeight()>bottom))
                    {
                        makeRectangleValid(selectedRectangle);
                    }
                    rectangles.add(selectedRectangle);
                    repaint();
                }
                else if(rect2.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    rectangles.remove(selectedRectangle);
                    selectedRectangle.setLocation(Math.min((int)me.getX(),(int)p4.getX()),Math.min((int)me.getY(),(int)p4.getY()));
                    selectedRectangle.setSize(Math.abs((int)me.getX()-(int)p4.getX()),Math.abs((int)me.getY()-(int)p4.getY()));
                    if((me.getX()<left || me.getX()+selectedRectangle.getWidth()>right) || (me.getY()<top || me.getY()+selectedRectangle.getHeight()>bottom))
                    {
                        makeRectangleValid(selectedRectangle);
                    }
                    rectangles.add(selectedRectangle);
                    repaint();
                }
                else if(rect3.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    rectangles.remove(selectedRectangle);
                    selectedRectangle.setLocation(Math.min((int)me.getX(),(int)p1.getX()),Math.min((int)me.getY(),(int)p1.getY()));
                    selectedRectangle.setSize(Math.abs((int)me.getX()-(int)p1.getX()),Math.abs((int)me.getY()-(int)p1.getY()));
                    if((me.getX()<left || me.getX()+selectedRectangle.getWidth()>right) || (me.getY()<top || me.getY()+selectedRectangle.getHeight()>bottom))
                    {
                        makeRectangleValid(selectedRectangle);
                    }
                    rectangles.add(selectedRectangle);
                    repaint();
                }
                else if(rect4.contains(new Point(lastClickedXLocation,lastClickedYLocation)))
                {
                    rectangles.remove(selectedRectangle);
                    selectedRectangle.setLocation(Math.min((int)me.getX(),(int)p2.getX()),Math.min((int)me.getY(),(int)p2.getY()));
                    selectedRectangle.setSize(Math.abs((int)me.getX()-(int)p2.getX()),Math.abs((int)me.getY()-(int)p2.getY()));
                    if((me.getX()<left || me.getX()+selectedRectangle.getWidth()>right) || (me.getY()<top || me.getY()+selectedRectangle.getHeight()>bottom))
                    {
                        makeRectangleValid(selectedRectangle);
                    }
                    rectangles.add(selectedRectangle);
                    repaint();
                }
            }
        }
    }
    public void removeLineCover(Line line)
    {
        lineCovers.remove(line);
    }
    public void addLineCover(Line line)
    {
        if(line.getHeight()==0)
        {
            if(line.getX1()<line.getX2())
                lineCovers.put(line,new Rectangle(line.getX1()-2,line.getY1()-2,line.getWidth()+4,line.getHeight()+4));
            else
                lineCovers.put(line,new Rectangle(line.getX2()-2,line.getY1()-2,line.getWidth()+4,line.getHeight()+4));
        }
        if(line.getWidth()==0)
        {
            if(line.getY1()<line.getY2())
                lineCovers.put(line,new Rectangle(line.getX1()-2,line.getY1()-2,line.getWidth()+4,line.getHeight()+4));
            else
                lineCovers.put(line,new Rectangle(line.getX1()-2,line.getY2()-2,line.getWidth()+4,line.getHeight()+4));
        }
    }
    public boolean isRectangleSelected(MouseEvent me)
    {
        for(Rectangle rectangle:rectangles)
        {
            if(rectangle.contains(me.getPoint()))
            {
                selectedRectangle=rectangle;
                selectedLine=null;
                selectedText=null;
                selectedImage=null;
                return true;
            }
        }
        return false;
    }
    public boolean isLineSelected(MouseEvent me)
    {
        for(Map.Entry<Line,Rectangle> lineCover:lineCovers.entrySet())
        {
            if(lineCover.getValue().contains(me.getPoint()))
            {
                selectedLine=lineCover.getKey();
                selectedRectangle=null;
                selectedText=null;
                selectedImage=null;
                reportPanel.getxTextField ().setText(String.valueOf(selectedLine.getX1()));
                reportPanel.getyTextField ().setText(String.valueOf(selectedLine.getY1()));
                reportPanel.getWidthTextField ().setText(String.valueOf(selectedLine.getWidth()));
                reportPanel.getHeightTextField ().setText(String.valueOf(selectedLine.getHeight()));
                return true;
            }
        }
        return false;
    }
    public void selectShapeByMouse(Graphics g)
    {
        if(selectedRectangle!=null)
        {
            selectColoredRectangle(g,selectedRectangle);
        }
        if(selectedLine!=null)
        {
            selectColoredLine(g,selectedLine);
        }
        if(selectedText!=null)
        {
            drawBufferString();
        }
        if(selectedImage!=null)
        {
            drawImageRectangle(getGraphics());
        }
    }
    public void moveShapeByMouse(Graphics g)
    {
        if(movedRectangle!=null)
        {
            selectColoredRectangle(g,movedRectangle);
        }
        if(movedLine!=null)
        {
            selectColoredLine(g,movedLine);
            selectedLine=movedLine;
        }
    }
    public void selectColoredRectangle(Graphics g,Rectangle selectedRectangle)
    {
        g.setColor(Color.red);
        g.drawRect((int)selectedRectangle.getX(),(int)selectedRectangle.getY(),(int)selectedRectangle.getWidth(),(int)selectedRectangle.getHeight());
        g.fillRect((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2,4,4);
        g.fillRect((int)(selectedRectangle.getX()+selectedRectangle.getWidth())-2,(int)selectedRectangle.getY()-2,4,4);
        g.fillRect((int)selectedRectangle.getX()-2,(int)(selectedRectangle.getY()+selectedRectangle.getHeight())-2,4,4);
        g.fillRect((int)(selectedRectangle.getX()+selectedRectangle.getWidth())-2,(int)(selectedRectangle.getY()+selectedRectangle.getHeight())-2,4,4);
        setRectangleTextFields((int)selectedRectangle.getX(),(int)selectedRectangle.getY(),(int)selectedRectangle.getWidth(),(int)selectedRectangle.getHeight());
    }
    /* From historyStackPanel */
    public void selectColoredRectangle(Rectangle rectangle)
    {
        if(rectangle==null)return;
        Graphics g=getGraphics();
        this.selectedRectangle=rectangle;
        selectedText=null;
        selectedImage=null;
        selectedLine=null;
        selectColoredRectangle(g,rectangle);
    }
    /*public void setLineSelected(Line line)
    {
        for(Line l:lines)
        {
          if(l.equals(line)) selectedLine=line;
        }
    }*/
    public void selectColoredLine(Line selectedLine)
    {
        if(selectedLine==null) return;
        Graphics g=getGraphics();
        this.selectedLine=selectedLine;
        selectedRectangle=null;
        selectedText=null;
        selectedImage=null;
        selectColoredLine(g,selectedLine);
    }
    /* */
    public void selectColoredLine(Graphics g,Line selectedLine)
    {
        g.setColor(Color.red);
        g.drawLine(selectedLine.getX1(),selectedLine.getY1(),selectedLine.getX2(),selectedLine.getY2());
        g.fillRect(selectedLine.getX1()-2,selectedLine.getY1()-2,4,4);
        g.fillRect(selectedLine.getX2()-2,selectedLine.getY2()-2,4,4);
    }
    public void drawRectangleByMouse(Rectangle selectedRectangle)
    {
        Graphics g=getGraphics();
        g.setColor(Color.red);
        g.drawRect((int)selectedRectangle.getX(),(int)selectedRectangle.getY(),(int)selectedRectangle.getWidth(),(int)selectedRectangle.getHeight());
        g.fillRect((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()-2,4,4);
        g.fillRect((int)selectedRectangle.getX()+(int)selectedRectangle.getWidth()-2,(int)selectedRectangle.getY()-2,4,4);
        g.fillRect((int)selectedRectangle.getX()+(int)selectedRectangle.getWidth()-2,(int)selectedRectangle.getY()+(int)selectedRectangle.getHeight()-2,4,4);
        g.fillRect((int)selectedRectangle.getX()-2,(int)selectedRectangle.getY()+(int)selectedRectangle.getHeight()-2,4,4);
    }
    public void drawRectangleByMouse(Graphics g)
    {
        if(newRectangle==null)
        {
            g.setColor(Color.blue);
            g.drawRect(Math.min(lastClickedXLocation,currentXLocation),Math.min(lastClickedYLocation,currentYLocation),Math.abs(lastClickedXLocation-currentXLocation),Math.abs(lastClickedYLocation-currentYLocation));
            g.fillRect(lastClickedXLocation-2,currentYLocation-2,4,4);
            g.fillRect(lastClickedXLocation-2,lastClickedYLocation-2,4,4);
            g.fillRect(currentXLocation-2,lastClickedYLocation-2,4,4);
            g.fillRect(currentXLocation-2,currentYLocation-2,4,4);
        }
        else
        {
            g.setColor(Color.red);
            g.drawRect((int)newRectangle.getX(),(int)newRectangle.getY(),(int)newRectangle.getWidth(),(int)newRectangle.getHeight());
            g.fillRect((int)newRectangle.getX()-2,(int)newRectangle.getY()-2,4,4);
            g.fillRect((int)newRectangle.getX()+(int)newRectangle.getWidth()-2,(int)newRectangle.getY()-2,4,4);
            g.fillRect((int)newRectangle.getX()+(int)newRectangle.getWidth()-2,(int)newRectangle.getY()+(int)newRectangle.getHeight()-2,4,4);
            g.fillRect((int)newRectangle.getX()-2,(int)newRectangle.getY()+(int)newRectangle.getHeight()-2,4,4);
            lastClickedXLocation=(int)newRectangle.getX();
            lastClickedYLocation=(int)newRectangle.getY();
            currentXLocation=(int)newRectangle.getX()+(int)newRectangle.getWidth();
            currentYLocation=(int)newRectangle.getY()+(int)newRectangle.getHeight();
            newRectangle=null;
        }
    }
    public void drawLineByMouse(Graphics g)
    {
        g.setColor(Color.blue);
        if(newLine==null)
        {
            g.drawLine(lastClickedXLocation,lastClickedYLocation,currentXLocation,currentYLocation);
            g.fillRect(lastClickedXLocation-2,lastClickedYLocation-2,4,4);
            g.fillRect(currentXLocation-2,currentYLocation-2,4,4);
        }
        else
        {
            g.drawLine(newLine.getX1(),newLine.getY1(),newLine.getX2(),newLine.getY2());
            g.fillRect(newLine.getX1()-2,newLine.getY1()-2,4,4);
            g.fillRect(newLine.getX2()-2,newLine.getY2()-2,4,4);
            lastClickedXLocation=newLine.getX1();
            lastClickedYLocation=newLine.getY1();
            currentXLocation=newLine.getX2();
            currentYLocation=newLine.getY2();
            newLine=null;
        }
    }
    public void mouseEntered(MouseEvent me)
    {
    }
    public void drawFromDS(Graphics g)
    {
        try
        {
            for(Map.Entry<Rectangle,ImageComponent> image:images.entrySet())
            {
                g.drawImage(image.getValue().getBufferedImage(),(int)image.getKey().getX(),(int)image.getKey().getY(),this);
            }
        }catch(Exception e)
        {
        }
        for(Rectangle rectangle:rectangles)
        {
            // makeRectangleValid(rectangle,true);
            g.setColor(Color.black);
            g.drawRect((int)rectangle.getX(),(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
        }
        for(Line line:lines)
        {
            //  makeLineCoordinatesValid(line);
            g.setColor(Color.black);
            g.drawLine(line.getX1(),line.getY1(),line.getX2(),line.getY2());
        }
        /**/
        Iterator i=textMap.entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry<TextRectangle,HashMap<Point,Text>> me =(Map.Entry<TextRectangle,HashMap<Point,Text>>)i.next();
            HashMap<Point,Text> texts=(HashMap<Point,Text>)me.getValue();
            Iterator i1=texts.entrySet().iterator();
            while(i1.hasNext())
            {
                Map.Entry<Point,Text> m=(Map.Entry<Point,Text>)i1.next();
                Point p=m.getKey();
                g.setFont(m.getValue().getTextFont());
                g.drawString(m.getValue().getText(),(int)(p.getX()),(int)(p.getY()));
            }
        }
        /**/
    }
    public void drawImageRectangle(Graphics g)
    {
        g.setColor(Color.RED);
        g.drawRect((int)selectedImage.getKey().getX(),(int)selectedImage.getKey().getY(),(int)selectedImage.getKey().getWidth(),(int)selectedImage.getKey().getHeight());
    }
    public void deSelectRectangle()
    {
        Graphics g=getGraphics();
        for(Rectangle rectangle:rectangles)
        {
            g.setColor(Color.white);
            g.fillRect((int)rectangle.getX()-2,(int)rectangle.getY()-2,4,4);
            g.fillRect((int)(rectangle.getX()+rectangle.getWidth())-2,(int)rectangle.getY()-2,4,4);
            g.fillRect((int)rectangle.getX()-2,(int)(rectangle.getY()+rectangle.getHeight())-2,4,4);
            g.fillRect((int)(rectangle.getX()+rectangle.getWidth())-2,(int)(rectangle.getY()+rectangle.getHeight())-2,4,4);
            g.setColor(Color.black);
            g.drawRect((int)rectangle.getX(),(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
        }
        //selectedRectangle=null;
    }
    public void deSelectImage()
    {
        Graphics g=getGraphics();
        for(Map.Entry<Rectangle,ImageComponent> image:images.entrySet())
        {
            g.setColor(Color.white);
            g.drawRect((int)image.getKey().getX(),(int)image.getKey().getY(),(int)image.getKey().getWidth(),(int)image.getKey().getHeight());
        }
        //selectedImage=null;
    }
    public void deSelectLine()
    {
        Graphics g=getGraphics();
        for(Line line:lines)
        {
            g.setColor(Color.white);
            g.fillRect(line.getX1()-2,line.getY1()-2,4,4);
            g.fillRect(line.getX2()-2,line.getY2()-2,4,4);
            g.setColor(Color.black);
            g.drawLine(line.getX1(),line.getY1(),line.getX2(),line.getY2());
        }
        //selectedLine=null;
    }
    public void updateLineLocation(Graphics g,MouseEvent e)
    {
        if(this.movedLine.getWidth()==0)
        {
            removeLineCover(this.movedLine);
            this.movedLine.setX1(e.getX());
            this.movedLine.setX2(e.getX());
            this.movedLine.setY1(e.getY()-distanceFromY1);
            this.movedLine.setY2(e.getY()+distanceFromY2);
            addLineCover(this.movedLine);
            setTextFieldForLine(this.movedLine);
            repaint();
        }
        if(movedLine.getHeight()==0)
        {
            removeLineCover(movedLine);
            this.movedLine.setY1(e.getY());
            this.movedLine.setY2(e.getY());
            this.movedLine.setX1(e.getX()-distanceFromX1);
            this.movedLine.setX2(e.getX()+distanceFromX2);
            addLineCover(movedLine);
            setTextFieldForLine(this.movedLine);
            repaint();
        }
        makeLineCoordinatesValid(this.movedLine);
    }
    public void updateImageLocation(MouseEvent me)
    {
        Rectangle rect=movedImage.getKey();
        ImageComponent image=(ImageComponent)images.remove(rect);
        rect.setLocation(me.getX()-distanceFromX,me.getY()-distanceFromY);
        makeRectangleValid(rect,false);
        image.setRect(rect);
        images.put(rect,image);
        reportPanel.getxTextField ().setEnabled(true);
        reportPanel.getyTextField ().setEnabled(true);
        setImageAndTextTextFields(rect);
        repaint();
    }
    public void updateRectangleLocation(MouseEvent me)
    {
        movedRectangle.setLocation(me.getX()-distanceFromX,me.getY()-distanceFromY);
        makeRectangleValid(movedRectangle,false);
        repaint();
    }
    public void updateTextLocation(MouseEvent me)
    {
        HashMap<Point,Text> temp=(HashMap<Point,Text>)movedText.getValue();
        HashMap<Point,Text> hm=(HashMap<Point,Text>)temp.clone();
        temp.clear();
        int textRectYCoordinate=(int)movedText.getKey().getY();
        int tempPoint=0;
        Point p;
        Text t;
        Map.Entry<Point,Text> tp;
        TextRectangle tr=movedText.getKey();
        tr.setRoundRect(me.getX()-distanceFromX,me.getY()-distanceFromY,tr.getWidth(),tr.getHeight(),0,0);
        makeRectangleValid(tr);
        Iterator<Map.Entry<Point,Text>> iter=hm.entrySet().iterator();
        while(iter.hasNext())
        {
            tp=iter.next();
            t=tp.getValue();
            p=tp.getKey();
            iter.remove();
            tempPoint=(int)(p.getY()-textRectYCoordinate)/30;
            p.setLocation((int)tr.getX(),(int)(tr.getY()+tempPoint*30));
            t.setLocation(p);
            temp.put(p,t);
        }
        textMap.put(tr,temp);
        textPointer=new Point((int)tr.getX(),(int)tr.getY());
        setImageAndTextTextFields(new Rectangle((int)tr.getX(),(int)tr.getY(),(int)tr.getWidth(),(int)tr.getHeight()));
        reportPanel.getxTextField ().setEnabled(false);
        reportPanel.getyTextField ().setEnabled(false);
        repaint();
    }
}