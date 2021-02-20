package com.notebook.pages.panel;

import com.notebook.pages.board.ReportBoard;
import com.notebook.pages.components.impl.ImageComponent;
import com.notebook.pages.components.impl.Line;
import com.notebook.pages.components.impl.Rectangle;
import com.notebook.pages.components.impl.Text;
import com.notebook.pages.components.impl.TextRectangle;
import com.notebook.pages.images.ToolIcons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportPanel extends JPanel implements ActionListener, ChangeListener
{
    public File dataFile;
    public static enum last_operations {DELETE_BUTTON,MOVE_BUTTON,SELECT_BUTTON,TEXT_BUTTON,IMAGE_BUTTON,LINE_BUTTON,RECTANGLE_BUTTON};
    private last_operations    last_operation;
    private SpinnerNumberModel spinnerLeft,spinnerRight,spinnerBottom,spinnerTop;
    private JSpinner leftSpinner,rightSpinner,topSpinner,bottomSpinner;
    private JButton pdfGeneratorButton;
    private JButton selectButton;
    private JLabel  toolbarLabel;
    private JPanel  propertiesPanel;
    public  JPanel  canvasDimensionPanel;
    private JPanel  userInformationPanel;
    private JLabel  titleLabel,companyLogoLabel,dateLabel,firmNameLabel;
    private JTextField titleTextField,firmNameTextField;
    private JButton companyLogoChooseButton;
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JTextField xTextField;
    private JTextField yTextField;
    private JLabel     widthLabel,heightLabel,xLabel,yLabel;
    private JLabel imageLabel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton lineButton;
    private JButton rectangleButton;
    private JButton           textButton;
    private JButton           moveButton;
    private JButton           deleteButton;
    private JButton           imageButton;
    private JPanel            toolbar;
    public  JPanel            canvasPanel;
    public  ScrollPane        canvasScrollPane;
    private HistoryStackPanel historyStackPanel;
    private ReportBoard reportBoard;
    private ActionEvent actionEventHolder;
    public  JPanel      marginPanel;
    public  JButton     okButtonOfMarginPanel;
    public  JButton     okButtonOfCanvasDimensionPanel;
    private JButton     okButtonOfUserInformationPanel;
    private JLabel      topLabel,leftLabel,rightLabel,bottomLabel;
    private JLabel canvasWidthLabel,canvasHeightLabel;
    private int canvasWidth;
    private int        canvasHeight;
    private JTextField topField,leftField,rightField,bottomField;
    private JTextField canvasWidthTextField,canvasHeightTextField;
    private Font textFont;

    public ReportPanel()
    {
        initComponents();
        setAppearance();
        addListeners();
    }
    /** setter getter **/

    public JTextField getCanvasWidthTextField ( ) {
        return canvasWidthTextField;
    }

    public void setCanvasWidthTextField ( JTextField canvasWidthTextField ) {
        this.canvasWidthTextField = canvasWidthTextField;
    }

    public JTextField getCanvasHeightTextField ( ) {
        return canvasHeightTextField;
    }

    public void setCanvasHeightTextField ( JTextField canvasHeightTextField ) {
        this.canvasHeightTextField = canvasHeightTextField;
    }

    public ReportBoard getReportBoard ( ) {
        return this.reportBoard;
    }

    public last_operations getLast_operation ( ) {
        return last_operation;
    }

    public void setLast_operation ( last_operations last_operation ) {
        this.last_operation = last_operation;
    }

    public JButton getRectangleButton ( ) {
        return rectangleButton;
    }

    public void setRectangleButton ( JButton rectangleButton ) {
        this.rectangleButton = rectangleButton;
    }

    public JButton getLineButton ( ) {
        return lineButton;
    }

    public void setLineButton ( JButton lineButton ) {
        this.lineButton = lineButton;
    }

    public JButton getMoveButton ( ) {
        return moveButton;
    }

    public void setMoveButton ( JButton moveButton ) {
        this.moveButton = moveButton;
    }

    public JButton getDeleteButton ( ) {
        return deleteButton;
    }

    public void setDeleteButton ( JButton deleteButton ) {
        this.deleteButton = deleteButton;
    }

    public JButton getImageButton ( ) {
        return imageButton;
    }

    public void setImageButton ( JButton imageButton ) {
        this.imageButton = imageButton;
    }

    public JButton getTextButton ( ) {
        return textButton;
    }

    public void setTextButton ( JButton textButton ) {
        this.textButton = textButton;
    }

    public JButton getSelectButton ( ) {
        return selectButton;
    }

    public void setSelectButton ( JButton selectButton ) {
        this.selectButton = selectButton;
    }

    public ActionEvent getActionEventHolder ( ) {
        return actionEventHolder;
    }

    public void setActionEventHolder ( ActionEvent actionEventHolder ) {
        this.actionEventHolder = actionEventHolder;
    }

    public JPanel getToolbar ( ) {
        return toolbar;
    }

    public void setToolbar ( JPanel toolbar ) {
        this.toolbar = toolbar;
    }

    public JPanel getPropertiesPanel ( ) {
        return propertiesPanel;
    }

    public void setPropertiesPanel ( JPanel propertiesPanel ) {
        this.propertiesPanel = propertiesPanel;
    }

    public ScrollPane getCanvasScrollPane ( ) {
        return canvasScrollPane;
    }

    public void setCanvasScrollPane ( ScrollPane canvasScrollPane ) {
        this.canvasScrollPane = canvasScrollPane;
    }

    public HistoryStackPanel getHistoryStackPanel ( ) {
        return historyStackPanel;
    }

    public void setHistoryStackPanel ( HistoryStackPanel historyStackPanel ) {
        this.historyStackPanel = historyStackPanel;
    }

    public JTextField getTopField ( ) {
        return topField;
    }

    public void setTopField ( JTextField topField ) {
        this.topField = topField;
    }

    public JTextField getLeftField ( ) {
        return leftField;
    }

    public void setLeftField ( JTextField leftField ) {
        this.leftField = leftField;
    }

    public JTextField getRightField ( ) {
        return rightField;
    }

    public void setRightField ( JTextField rightField ) {
        this.rightField = rightField;
    }

    public JTextField getBottomField ( ) {
        return bottomField;
    }

    public void setBottomField ( JTextField bottomField ) {
        this.bottomField = bottomField;
    }

    public JTextField getWidthTextField ( ) {
        return widthTextField;
    }

    public void setWidthTextField ( JTextField widthTextField ) {
        this.widthTextField = widthTextField;
    }

    public JTextField getHeightTextField ( ) {
        return heightTextField;
    }

    public void setHeightTextField ( JTextField heightTextField ) {
        this.heightTextField = heightTextField;
    }

    public JTextField getxTextField ( ) {
        return xTextField;
    }

    public void setxTextField ( JTextField xTextField ) {
        this.xTextField = xTextField;
    }

    public JTextField getyTextField ( ) {
        return yTextField;
    }

    public void setyTextField ( JTextField yTextField ) {
        this.yTextField = yTextField;
    }

    public Font getTextFont ( ) {
        return textFont;
    }

    public void setTextFont ( Font textFont ) {
        this.textFont = textFont;
    }

    public int getCanvasWidth ( ) {
        return canvasWidth;
    }

    public void setCanvasWidth ( int canvasWidth ) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight ( ) {
        return canvasHeight;
    }

    public void setCanvasHeight ( int canvasHeight ) {
        this.canvasHeight = canvasHeight;
    }

    public void changedFormat(Font f)
    {
        textFont=f;
        this.getReportBoard ().repaint();
    }
    public void initComponents() {
        /***/
        spinnerLeft=new SpinnerNumberModel(new Integer(5),new Integer(5),new Integer(20),new Integer(1));
        spinnerRight=new SpinnerNumberModel(new Integer(5),new Integer(5),new Integer(20),new Integer(1));
        spinnerTop=new SpinnerNumberModel(new Integer(5),new Integer(5),new Integer(20),new Integer(1));
        spinnerBottom=new SpinnerNumberModel(new Integer(5),new Integer(5),new Integer(20),new Integer(1));
        leftSpinner=new JSpinner(spinnerLeft);
        rightSpinner=new JSpinner(spinnerRight);
        topSpinner=new JSpinner(spinnerTop);
        bottomSpinner=new JSpinner(spinnerBottom);
        widthTextField=new JTextField(8);
        widthTextField.setText("0");
        heightTextField=new JTextField(8);
        heightTextField.setText("0");
        widthLabel=new JLabel("Width:");
        heightLabel=new JLabel("Height:");
        xLabel=new JLabel("X:");
        yLabel=new JLabel("Y:");
        xTextField=new JTextField(8);
        yTextField=new JTextField(8);
        xTextField.setText("0");
        yTextField.setText("0");
        widthLabel.setFont(new Font(widthLabel.getName(),Font.BOLD,12));
        heightLabel.setFont(new Font(heightLabel.getName(),Font.BOLD,12));
        xLabel.setFont(new Font(xLabel.getName(),Font.BOLD,12));
        yLabel.setFont(new Font(yLabel.getName(),Font.BOLD,12));
        propertiesPanel=new JPanel();
        propertiesPanel.setLayout(new GridLayout(8,1,4,4));
        propertiesPanel.add(xLabel);
        propertiesPanel.add(xTextField);
        propertiesPanel.add(yLabel);
        propertiesPanel.add(yTextField);
        propertiesPanel.add(widthLabel);
        propertiesPanel.add(widthTextField);
        propertiesPanel.add(heightLabel);
        propertiesPanel.add(heightTextField);
        canvasWidthLabel=new  JLabel("Width : ");
        canvasWidthLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        canvasHeightLabel=new JLabel("Height  : ");
        canvasHeightLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        canvasWidthTextField=new JTextField(5);
        canvasWidth=480;
        canvasWidthTextField.setText("480");
        canvasWidthTextField.setHorizontalAlignment(JTextField.RIGHT);
        canvasHeightTextField=new JTextField(5);
        canvasHeight=640;
        canvasHeightTextField.setText("640");
        canvasHeightTextField.setHorizontalAlignment(JTextField.RIGHT);
        marginPanel=new JPanel();
        topLabel=new JLabel("TOP");
        topLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        bottomLabel=new JLabel("BOTTOM");
        bottomLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        leftLabel=new JLabel("LEFT");
        leftLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        rightLabel=new JLabel("RIGHT");
        rightLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        topField=new JTextField(5);
        topField.setHorizontalAlignment(JTextField.RIGHT);
        topField.setText(String.valueOf(5));
        rightField=new JTextField(5);
        rightField.setText(String.valueOf(5));
        rightField.setHorizontalAlignment(JTextField.RIGHT);
        leftField=new JTextField(5);
        leftField.setText(String.valueOf(5));
        leftField.setHorizontalAlignment(JTextField.RIGHT);
        bottomField=new JTextField(5);
        bottomField.setText(String.valueOf(5));
        bottomField.setHorizontalAlignment(JTextField.RIGHT);
        marginPanel.setLayout(new GridLayout(5,2,4,4));
        marginPanel.add(topLabel);
        marginPanel.add(topSpinner);
        marginPanel.add(leftLabel);
        marginPanel.add(leftSpinner);
        marginPanel.add(rightLabel);
        marginPanel.add(rightSpinner);
        marginPanel.add(bottomLabel);
        marginPanel.add(bottomSpinner);
        marginPanel.add(new JLabel(""));
        okButtonOfMarginPanel=new JButton("Close");
        marginPanel.add(okButtonOfMarginPanel);
        textArea=new JTextArea("");
        actionEventHolder=null;
        toolbarLabel=new JLabel("Toolbar");
        imageLabel=new JLabel("Image");
        deleteButton=new JButton();
        pdfGeneratorButton=new JButton();
        pdfGeneratorButton.setIcon(ToolIcons.PDF_ICON);
        deleteButton.setIcon(ToolIcons.TRASH_ICON);
        moveButton=new JButton();
        moveButton.setIcon(ToolIcons.MOVE_ICON);
        selectButton=new JButton();
        selectButton.setIcon(ToolIcons.SELECTED_ICON);
        imageButton=new JButton();
        imageButton.setIcon(ToolIcons.IMAGE_ICON);
        textField=new JTextField();
        lineButton=new JButton();
        lineButton.setIcon(ToolIcons.LINE_ICON);
        rectangleButton=new JButton();
        rectangleButton.setIcon(ToolIcons.RECTANGLE_ICON);
        textButton=new JButton();
        textButton.setIcon(ToolIcons.TEXT_ICON);
        canvasPanel=new JPanel();
        reportBoard=new ReportBoard(this);
        canvasScrollPane=new ScrollPane();
        canvasScrollPane.add(reportBoard);
        canvasPanel.add(canvasScrollPane);
        canvasDimensionPanel=new JPanel();
        canvasDimensionPanel.setLayout(new GridLayout(3,2,4,4));
        canvasDimensionPanel.add(canvasWidthLabel);
        canvasDimensionPanel.add(canvasWidthTextField);
        canvasDimensionPanel.add(canvasHeightLabel);
        canvasDimensionPanel.add(canvasHeightTextField);
        canvasDimensionPanel.add(new JLabel(""));
        okButtonOfCanvasDimensionPanel=new JButton("Ok");
        canvasDimensionPanel.add(okButtonOfCanvasDimensionPanel);
        titleLabel=new JLabel("Title ");
        titleLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        companyLogoLabel=new JLabel("Company Logo");
        companyLogoLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        dateLabel=new JLabel("Date");
        dateLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        firmNameLabel=new JLabel("Firm Name");
        firmNameLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        titleTextField=new JTextField(20);
        titleTextField.setFont(new Font("Verdana",Font.PLAIN,13));
        firmNameTextField=new JTextField(20);
        firmNameTextField.setFont(new Font("Verdana",Font.PLAIN,13));
        companyLogoChooseButton=new JButton("choose");
        companyLogoLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        userInformationPanel=new JPanel();
        userInformationPanel.setLayout(new GridLayout(5,2,4,4));
        userInformationPanel.add(titleLabel);
        userInformationPanel.add(titleTextField);
        userInformationPanel.add(companyLogoLabel);
        userInformationPanel.add(companyLogoChooseButton);
        userInformationPanel.add(dateLabel);
        userInformationPanel.add(new JLabel("For Date"));
        userInformationPanel.add(firmNameLabel);
        userInformationPanel.add(firmNameTextField);
        okButtonOfUserInformationPanel=new JButton("Create PDF");
        userInformationPanel.add(new JLabel(" "));
        userInformationPanel.add(okButtonOfUserInformationPanel);
        toolbar=new JPanel();
        toolbar.setLayout(null);
        toolbar.add(toolbarLabel);
        toolbar.add(lineButton);
        toolbar.add(rectangleButton);
        toolbar.add(textButton);
        toolbar.add(selectButton);
        toolbar.add(moveButton);
        toolbar.add(deleteButton);
        toolbar.add(imageLabel);
        toolbar.add(imageButton);
        toolbar.add(propertiesPanel);
        add(pdfGeneratorButton);
/*toolbar.add(widthLabel);
toolbar.add(widthTextField);
toolbar.add(heightLabel);
toolbar.add(heightTextField);
toolbar.add(xLabel);
toolbar.add(xTextField);
toolbar.add(yLabel);
toolbar.add(yTextField);*/
        historyStackPanel=new HistoryStackPanel(this);
    }
    public void setAppearance()
    {
        int le=10;
        int re=20;
        canvasDimensionPanel.setVisible(true);
        propertiesPanel.setVisible(false);
        marginPanel.setVisible(false);
        setLayout(null);
        toolbar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        canvasPanel.setLayout(null);
        canvasPanel.setBorder(null);
        reportBoard.setPreferredSize(new Dimension(480,640));
        canvasScrollPane.setBounds(0,0,480+4,640+4);
        canvasScrollPane.getVAdjustable().setUnitIncrement(50);
        canvasScrollPane.getHAdjustable().setUnitIncrement(50);
        lineButton.setBounds(re,12+10+10,40,40);
        toolbarLabel.setBounds(10,12,80,15);
        rectangleButton.setBounds(re,12+10+10+40+le,40,40);
        textButton.setBounds(re,12+10+10+40+le+40+le,40,40);
        selectButton.setBounds(re,12+10+10+40+le+40+le+40+le,40,40);
        moveButton.setBounds(re,12+10+10+40+le+40+le+40+le+40+le,40,40);
        deleteButton.setBounds(re,12+10+10+40+le+40+le+40+le+40+le+40+le,40,40);
//imageLabel.setBounds(32,12+10+10+40+3+10+17+35,40,18);
        imageButton.setBounds(re,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le,40,40);
        propertiesPanel.setBounds(5,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40,70,170);
        pdfGeneratorButton.setBounds(25,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+10+100+70+30,50,50);
/*int g=-10,l=8;
widthLabel.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40-7+l,60,25);
widthTextField.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+g+l,60,25);
heightLabel.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+g+g+l,60,25);
heightTextField.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+25+7+g+g+g+l,60,25);
xLabel.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+25+7+25+7+g+g+g+g+l,60,25);
xTextField.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+25+7+25+7+25+7+g+g+g+g+g+l,60,25);
yLabel.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+25+7+25+7+25+7+25+7+g+g+g+g+g+g+l,60,25);
yTextField.setBounds(12,12+10+10+40+le+40+le+40+le+40+le+40+le+40+le+7+40+25+25+7+25+7+25+7+25+7+25+7+25+7+g+g+g+g+g+g+g+l,60,25);
widthTextField.setHorizontalAlignment(JTextField.RIGHT);
heightTextField.setHorizontalAlignment(JTextField.RIGHT);
xTextField.setHorizontalAlignment(JTextField.RIGHT);
yTextField.setHorizontalAlignment(JTextField.RIGHT);*/
        toolbarLabel.setFont(new Font(toolbarLabel.getName(), Font.BOLD,15));
        toolbar.setBounds(10,20,80,250+140);
        canvasPanel.setBounds(10+80+10,20,480,640);
        historyStackPanel.setBounds(10+80+10+480+10,20,200,640);
        historyStackPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        marginPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea.setBounds(260,60,10,5);
        textArea.setVisible(false);
        add(toolbar);
        add(textArea);
        add(canvasPanel);
        add(historyStackPanel);
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        setBackground(Color.LIGHT_GRAY);
        setSize(d.width,d.height-40);
        setVisible(true);
    }
    public void addListeners()
    {
        leftSpinner.addChangeListener(this);
        topSpinner.addChangeListener(this);
        rightSpinner.addChangeListener(this);
        bottomSpinner.addChangeListener(this);

        deleteButton.addActionListener(this);
        selectButton.addActionListener(this);
        lineButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        textButton.addActionListener(this);
        moveButton.addActionListener(this);
        imageButton.addActionListener(this);
        pdfGeneratorButton.addActionListener(this);
    }
    public void setDimension()
    {
        int oldCanvasWidth=canvasWidth;
        try
        {
            canvasWidth=Integer.parseInt(canvasWidthTextField.getText());
        }catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(ReportPanel.this,"Invalid Input");
            canvasWidthTextField.setText(String.valueOf(canvasWidth));
        }
        if(canvasWidth<0)
        {
            JOptionPane.showMessageDialog(ReportPanel.this,"Invalid Input");
            canvasWidthTextField.setText(String.valueOf(oldCanvasWidth));
        }
        int oldHeight=canvasHeight;
        try
        {
            canvasHeight=Integer.parseInt(canvasHeightTextField.getText());
        }catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(ReportPanel.this,"Invalid input");
            canvasHeightTextField.setText(String.valueOf(canvasHeight));
        }
        if(canvasHeight<0)
        {
            JOptionPane.showMessageDialog(ReportPanel.this,"Invalid input");
            canvasHeightTextField.setText(String.valueOf(oldHeight));
        }
        reportBoard.setRight(canvasWidth-5);
        reportBoard.setBottom(canvasHeight-5);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        reportBoard.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setDimensionForPanel();
        reportBoard.revalidate();
        reportBoard.repaint();
        revalidate();
        repaint();
        reportBoard.setTemplate();
    }
    public void setDimensionForPanel()
    {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth=(int)screenSize.getWidth()-100-50-200;
        if(canvasWidth>maxWidth)
        {
            canvasWidth=maxWidth;
        }
        canvasPanel.setBounds(100,20,canvasWidth+4,640);
        canvasScrollPane.setBounds(0,0,canvasWidth+4,640);
        historyStackPanel.setBounds(canvasWidth+4+10+100,20,200,640);
        if(canvasHeight<640)
        {
            canvasPanel.setBounds(100,20,canvasWidth+4,canvasHeight+4);
            canvasScrollPane.setBounds(0,0,canvasWidth+4,canvasHeight+4);
        }
        canvasScrollPane.revalidate();
        canvasPanel.revalidate();
    }
    public void stateChanged( ChangeEvent ce)
    {
        reportBoard.setLeft((int)leftSpinner.getValue());
        reportBoard.setRight(canvasWidth-(int)rightSpinner.getValue());
        reportBoard.setTop((int)topSpinner.getValue());
        reportBoard.setBottom(canvasHeight-(int)bottomSpinner.getValue());
    }
    public void actionPerformed(ActionEvent e)
    {
        actionEventHolder=e;
        if(actionEventHolder.getSource()==pdfGeneratorButton)
        {
            String title=new String("");
            String date=new String("");
            JFileChooser jfc=new JFileChooser(new File("./"));
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setFileFilter(new FileNameExtensionFilter ("All Types","pdf"));
            int selectedOption=jfc.showSaveDialog(this);
            if(selectedOption==jfc.APPROVE_OPTION)
            {
                File selectedFile=jfc.getSelectedFile();
                File parentFolder=new File(selectedFile.getParent());
                if(parentFolder.exists()==false)
                {
                    JOptionPane.showMessageDialog(this,"Invalid path : "+parentFolder.getAbsolutePath());
                    return;
                }
                String selectedFileNameWithPath=selectedFile.getAbsolutePath();
                if(selectedFileNameWithPath.endsWith(".pdf")==false)
                {
                    if(selectedFileNameWithPath.endsWith("."))
                    {
                        selectedFileNameWithPath+="pdf";
                    }
                    else
                    {
                        selectedFileNameWithPath+=".pdf";
                    }
                }
                 /*String fullPath=selectedFileNameWithPath;
                 Template o=new Template();
                 o.title="ReportFrame";
                 o.date="28/04/2018";
                 ArrayList<String> names=new ArrayList<String>();
                 names.add("Suresh");
                 names.add("Ramesh");
                 names.add("Yogesh");
                 names.add("Dipesh");
                 o.names=names;
                 o.width=canvasWidth;
                 o.height=canvasHeight;
                 try
                 {
                 if(dataFile.exists())
                 PDFGenerator.generatePdf(dataFile,selectedFileNameWithPath,o);
                 }catch(Exception exception)
                 {
                 System.out.println(exception);
                 }*/
            }
        }
        if(actionEventHolder.getSource()==selectButton)
        {
            selectButton.setBorder(new LineBorder (Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(actionEventHolder.getSource()==lineButton)
        {
            lineButton.setBorder(new LineBorder(Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
        else if(actionEventHolder.getSource()==rectangleButton)
        {
            rectangleButton.setBorder(new LineBorder(Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
        else if(actionEventHolder.getSource()==moveButton)
        {
            moveButton.setBorder(new LineBorder(Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        else if(actionEventHolder.getSource()==deleteButton)
        {
            deleteButton.setBorder(new LineBorder(Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            if(reportBoard.getSelectedLine ()!=null)
            {
                reportBoard.deleteSelectedLine();
                reportBoard.setSelectedLine(null);
                reportBoard.repaint();
                return;
            }
            if(reportBoard.getSelectedText ()!=null)
            {
                reportBoard.deleteSelectedText();
                reportBoard.setSelectedText(null);
                reportBoard.setTextPointer(null);
                reportBoard.repaint();
                return;
            }
            if(reportBoard.getSelectedRectangle()!=null)
            {
                reportBoard.deleteSelectedRectangle();
                reportBoard.setSelectedRectangle(null);
                reportBoard.repaint();
                return;
            }
            if(reportBoard.getSelectedImage()!=null)
            {
                reportBoard.deleteSelectedImage();
                historyStackPanel.popEntity(reportBoard.getSelectedImage().getValue());
                reportBoard.setSelectedImage(null);
                reportBoard.repaint();
                return;
            }
        }
        else if(actionEventHolder.getSource()==textButton)
        {
            textButton.setBorder(new LineBorder(Color.black,2));
            imageButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }
        else if(actionEventHolder.getSource()==imageButton)
        {
            imageButton.setBorder(new LineBorder(Color.black,2));
            textButton.setBorder(new LineBorder(Color.white,2));
            deleteButton.setBorder(new LineBorder(Color.white,2));
            moveButton.setBorder(new LineBorder(Color.white,2));
            rectangleButton.setBorder(new LineBorder(Color.white,2));
            lineButton.setBorder(new LineBorder(Color.white,2));
            selectButton.setBorder(new LineBorder(Color.white,2));
            canvasPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void saveFile( File file)
    {
        if(getReportBoard ().tempMap.size()>0)
        {
            getReportBoard ().tempMap.put(new Point((int)getReportBoard ().getTextPointer ().getX(),(int)getReportBoard ().getTextPointer ().getY()+getReportBoard ().getCaretAtLine ()),new Text (getReportBoard ().getStringBuffer ().replaceAll("\\|",""),getTextFont ()));
            for( Map.Entry<Point,Text> xe:getReportBoard ().tempMap.entrySet())
            {
                Text text=xe.getValue();
                text.setLocation(xe.getKey());
                getReportBoard ().getTextList ().put(xe.getKey(),text);
            }
            getReportBoard ().getTextMap ().put(getReportBoard ().getTextAreaRectangle (),( HashMap <Point,Text> )getReportBoard ().getTextList ());
            getReportBoard ().tempMap=new HashMap<Point,Text>();
        }
        String name=file.getName();
        if(file.exists())
        {
            file.delete();
            file=new File(name);
        }
        try
        {
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(new Point(getCanvasWidth (),getCanvasHeight ()));
            oos.writeObject(getHistoryStackPanel ().getEntities ());
            for( Line line:getReportBoard ().lines)
            {
                oos.writeObject(line);
            }
            for( com.notebook.pages.components.impl.Rectangle rectangle:getReportBoard ().rectangles)
            {
                oos.writeObject(rectangle);
            }
            Iterator i=getReportBoard ().getTextMap ().entrySet().iterator();
            while(i.hasNext())
            {
                Map.Entry me = (Map.Entry)i.next();
                oos.writeObject(me.getKey());
                oos.writeObject(me.getValue());
            }
            Iterator iter=getReportBoard ().getImages ().entrySet().iterator();
            while(iter.hasNext())
            {
                Map.Entry m=(Map.Entry)iter.next();
                oos.writeObject(m.getKey());
                oos.writeObject(m.getValue());
            }
            oos.close();
        }catch( IOException ioException)
        {

        }
    }
    public void openFile(File file)
    {
        try
        {
            TextRectangle tmpRect=null;
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            com.notebook.pages.components.impl.Rectangle imageRect=null;
            ImageComponent image;
            while(true)
            {
                int num=0;
                Object result=(Object)ois.readObject();
                if(result==null)break;
                if(result instanceof Point)
                {
                    Point p=(Point)result;
                    setCanvasWidth((int)p.getX());
                    setCanvasHeight((int)p.getY());
                    getCanvasHeightTextField ().setText(String.valueOf(getCanvasHeight ()));
                    getCanvasWidthTextField ().setText(String.valueOf(getCanvasWidth ()));
                    getReportBoard ().setRight (getCanvasWidth ()-5);
                    getReportBoard ().setBottom (getCanvasHeight ()-5);
                    //getReportBoard ().setLocation(((480/2)-(canvasWidth/2)),((640/2)-(canvasHeight/2)));
                    setDimensionForPanel();
                    getReportBoard ().setPreferredSize(new Dimension(getCanvasWidth (),getCanvasHeight ()));
                    getReportBoard ().revalidate();
                    getReportBoard ().repaint();
                }
                if(result instanceof LinkedHashMap )
                {
                    getHistoryStackPanel ().setEntities ((LinkedHashMap<String,Object>)result);
                    getHistoryStackPanel ().setEntityNumber (getHistoryStackPanel ().getEntities ().size());
                    getHistoryStackPanel ().populateList();
                }
                if(result instanceof Line)
                {
                    getReportBoard ().lines.add((Line)result);
                    getReportBoard ().addLineCover((Line)result);
                    //getHistoryStackPanel ().pushEntity((Line)result);
                }
                if(result instanceof TextRectangle)
                {
                    tmpRect=(TextRectangle)result;
                    result=(Object)ois.readObject();
                    if(result instanceof HashMap)
                    {
                        getReportBoard ().getTextMap ().put(tmpRect,(HashMap<Point,Text>)result);
                        //getHistoryStackPanel ().pushEntity((TextRectangle)tmpRect);
                    }
                }
                if(result instanceof com.notebook.pages.components.impl.Rectangle )
                {
                    imageRect=( com.notebook.pages.components.impl.Rectangle )result;
                    result=(Object)ois.readObject();
                    if(result==null)break;
                    if(result instanceof ImageComponent)
                    {
                        getReportBoard ().getImages ().put(imageRect,(ImageComponent)result);
                        //getHistoryStackPanel ().pushEntity((ImageComponent)result);
                    }
                    else if(imageRect!=null)
                    {
                        getReportBoard ().rectangles.add(( com.notebook.pages.components.impl.Rectangle )imageRect);
                        //getHistoryStackPanel ().pushEntity((Rectangle)imageRect);
                        imageRect=null;
                    }
                    if(result instanceof com.notebook.pages.components.impl.Rectangle )
                    {
                        getReportBoard ().rectangles.add(( Rectangle )result);
                        //getHistoryStackPanel ().pushEntity((Rectangle)result);
                    }
                }
            }
            ois.close();
            getReportBoard ().repaint();
        }catch(Exception ioException)
        {
            getReportBoard ().repaint();
        }
    }
}