package com.notebook.pages.frame;

import com.notebook.pages.panel.ReportPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ReportFrame extends JFrame implements ActionListener, ListSelectionListener {
    private static ReportFrame rf;
    private        File        file;
    private        ReportPanel rp;
    private        JMenuBar    menuBar;
    private        JMenu       fileJMenu;
    private        JMenu       help;
    private        JMenu       formatJMenu;
    private        JMenuItem   saveItem, openItem, newItem, saveAsItem, marginItem, canvasItem;
    private JMenuItem      fontItem;
    private Dimension      d;
    /**
     * Text
     **/
    private JScrollPane    fontPane;
    private JScrollPane    fontStylePane;
    private JScrollPane    fontSizePane;
    private JInternalFrame fontFrame;
    private JLabel         fontLabel;
    private JLabel         fontStyleLabel;
    private JLabel         fontSizeLabel;
    private JButton        okButton;
    private JButton        cancelButton;
    private JTextField     fontTextField;
    private JTextField     fontStyleTextField;
    private JTextField     fontSizeTextField;
    private JList          fontList;
    private JList          fontSizeList;
    private JList          fontStyleList;
    /***/
    private Font           textFont;
    private String         textFontName;
    private int            textStyleValue;
    private String         textStyleName;
    private int            textSize;

    public ReportFrame ( ) {
        initComponents ( );
        addListeners ( );
        add ( fontFrame , BorderLayout.CENTER );
        fontFrame.setVisible ( false );
        add ( rp , BorderLayout.CENTER );
        add ( menuBar , BorderLayout.NORTH );
        //setSize ( d.width , d.height - 40 );
        pack();
        setSize(830,750);
        //setResizable(false);
        setVisible ( true );
        setLocationRelativeTo(null);
    }

    public static void main ( String data[] ) {

    }

    public void addListeners ( ) {
        saveItem.addActionListener ( this );
        openItem.addActionListener ( this );
        newItem.addActionListener ( this );
        saveAsItem.addActionListener ( this );
        marginItem.addActionListener ( this );
        canvasItem.addActionListener ( this );
        fontItem.addActionListener ( this );
        fontList.addListSelectionListener ( this );
        fontStyleList.addListSelectionListener ( this );
        fontSizeList.addListSelectionListener ( this );
        okButton.addActionListener ( new ActionListener ( ) {
            public void actionPerformed ( ActionEvent ae ) {
                if ( fontList.isSelectionEmpty ( ) ) {
                    textFontName = "Verdana";
                } else {
                    textFontName = fontList.getSelectedValue ( ).toString ( );
                }
                if ( fontStyleList.isSelectionEmpty ( ) ) {
                    textStyleValue = Font.PLAIN;
                    textStyleName = "Regular";
                } else {
                    textStyleName = fontStyleList.getSelectedValue ( ).toString ( );
                }
                if ( fontSizeList.isSelectionEmpty ( ) ) {
                    textSize = 24;
                } else {
                    textSize = Integer.parseInt ( fontSizeList.getSelectedValue ( ).toString ( ) );
                }
                if ( textStyleName.trim ( ).equals ( "Regular" ) ) {
                    textStyleValue = Font.PLAIN;
                }
                if ( textStyleName.trim ( ).equals ( "Bold" ) ) {
                    textStyleValue = Font.BOLD;
                }
                if ( textStyleName.trim ( ).equals ( "Italic" ) ) {
                    textStyleValue = Font.ITALIC;
                }
                fontFrame.setVisible ( false );
                rp.changedFormat ( new Font ( textFontName , textStyleValue , textSize ) );
            }
        } );
        cancelButton.addActionListener ( new ActionListener ( ) {
            public void actionPerformed ( ActionEvent ae ) {
                fontFrame.setVisible ( false );
            }
        } );
    }

    public void valueChanged ( ListSelectionEvent lse ) {
        if ( lse.getSource ( ) == fontList ) {
            fontTextField.setText ( ( String ) fontList.getSelectedValue ( ) );
        }
        if ( lse.getSource ( ) == fontStyleList ) {
            fontStyleTextField.setText ( ( String ) fontStyleList.getSelectedValue ( ) );
        }
        if ( lse.getSource ( ) == fontSizeList ) {
            fontSizeTextField.setText ( String.valueOf ( fontSizeList.getSelectedValue ( ) ) );
        }
    }

    public void actionPerformed ( ActionEvent ae ) {
        JFileChooser jfc = new JFileChooser ( new File ( "ReportFrame.java" ) );
        if ( ae.getSource ( ) == fontItem ) {
            fontFrame.setVisible ( true );
        }
        if ( ae.getSource ( ) == newItem ) {
            if ( rp.getReportBoard ( ).tempMap.size ( ) > 0 || rp.getReportBoard ( ).lines.size ( ) > 0 || rp.getReportBoard ( ).rectangles.size ( ) > 0 ) {
                int result =
                        JOptionPane.showConfirmDialog ( ReportFrame.this , "Do you want to save?" , "Report Works" , JOptionPane.YES_NO_OPTION );
                if ( result == 0 ) {
                    if ( jfc.showSaveDialog ( this ) == JFileChooser.APPROVE_OPTION ) {
                        rp.saveFile ( jfc.getSelectedFile ( ) );
                        rf.setTitle ( jfc.getSelectedFile ( ).getName ( ) );
                    }
                    newFile ( );
                    rf.setTitle ( "Untitled" );
                } else {
                    newFile ( );
                    rp.dataFile = jfc.getSelectedFile ( );
                    rf.setTitle ( "Untitled" );
                }
            } else {
                newFile ( );
                rp.dataFile = jfc.getSelectedFile ( );
                return;
            }
        }
        if ( ae.getSource ( ) == canvasItem ) {
            JOptionPane jop = new JOptionPane ( );
            JDialog dialog = jop.createDialog ( "Dimensions Panel" );
            rp.canvasDimensionPanel.setVisible ( true );
            dialog.setContentPane ( rp.canvasDimensionPanel );
            rp.okButtonOfCanvasDimensionPanel.addActionListener ( new ActionListener ( ) {
                public void actionPerformed ( ActionEvent ae ) {
                    rp.setDimension ( );
                    dialog.setVisible ( false );
                }
            } );
            dialog.setSize ( 250 , 100 );
            dialog.setVisible ( true );
        }
        if ( ae.getSource ( ) == marginItem ) {
            JOptionPane jop = new JOptionPane ( );
            JDialog dialog = jop.createDialog ( "Margin Panel" );
            rp.marginPanel.setVisible ( true );
            dialog.setContentPane ( rp.marginPanel );
            rp.okButtonOfMarginPanel.addActionListener ( new ActionListener ( ) {
                public void actionPerformed ( ActionEvent ae ) {
                    dialog.setVisible ( false );
                }
            } );
            dialog.setSize ( 170 , 220 );
            dialog.setVisible ( true );
        }
        if ( ae.getSource ( ) == saveItem ) {
            if ( rf.getTitle ( ) == "Untitled" ) {
                if ( jfc.showSaveDialog ( this ) == JFileChooser.APPROVE_OPTION ) {
                    File tempFile = jfc.getSelectedFile ( );
                    //if(tempFile.getParent().equals("c:\\MDCanvas"))
                    //{

                    String fileName = jfc.getSelectedFile ( ).getName ( );
                    rp.saveFile ( new File ( fileName ) );
                    rf.setTitle ( jfc.getSelectedFile ( ).getName ( ) );
                    //}
          /*else
          {
            JOptionPane.showMessageDialog(this,"Can't save in this folder");
          }*/
                }
            } else {
                rp.saveFile ( new File ( rf.getTitle ( ) ) );
                rp.dataFile = jfc.getSelectedFile ( );
                repaint ( );
                return;
            }
        }
        if ( ae.getSource ( ) == saveAsItem ) {

            if ( jfc.showSaveDialog ( this ) == JFileChooser.APPROVE_OPTION ) {
                File tempFile = jfc.getSelectedFile ( );
                //if(tempFile.getParent().equals("c:\\MDCanvas"))
                //{
                String fileName = jfc.getSelectedFile ( ).getName ( );
                rp.saveFile ( new File ( fileName ) );
                rf.setTitle ( jfc.getSelectedFile ( ).getName ( ) );
                //}
         /*else
         {
           JOptionPane.showMessageDialog(this,"Can't save in this folder");
         }*/
            } else {
                rp.saveFile ( new File ( rf.getTitle ( ) ) );
                rp.dataFile = jfc.getSelectedFile ( );
                repaint ( );
                return;
            }
        }
        if ( ae.getSource ( ) == openItem ) {
            if ( jfc.showOpenDialog ( this ) == jfc.APPROVE_OPTION ) {
                newFile ( );
                this.file = jfc.getSelectedFile ( );
                rp.openFile ( jfc.getSelectedFile ( ) );
                rf.setTitle ( jfc.getSelectedFile ( ).getName ( ) );
                int result =
                        JOptionPane.showConfirmDialog ( ReportFrame.this , "Do you want to save?" , "Report Works" , JOptionPane.YES_NO_OPTION );
                if ( result == 0 ) {
                    if ( jfc.showSaveDialog ( this ) == JFileChooser.APPROVE_OPTION ) {
                        rp.saveFile ( jfc.getSelectedFile ( ) );
                    }
                }
            } else {
                rp.openFile ( jfc.getSelectedFile ( ) );
                rp.dataFile = jfc.getSelectedFile ( );
                return;
            }
        }
        rp.dataFile = jfc.getSelectedFile ( );
    }

    public void newFile ( ) {
        rf.setTitle ( "Untitled" );
        remove ( rp );
        rp = new ReportPanel ( );
        add ( rp );
        repaint ( );
        revalidate ( );
    }

    public void initComponents ( ) {
        d = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        textFont = new Font ( "Verdana" , Font.PLAIN , 24 );
        /* Text */
        fontFrame = new JInternalFrame ( "Font" , true , true );
        fontLabel = new JLabel ( "Font :" );
        fontTextField = new JTextField ( );
        fontList = new JList ( GraphicsEnvironment.getLocalGraphicsEnvironment ( ).getAvailableFontFamilyNames ( ) );
        fontStyleLabel = new JLabel ( "Font Style :" );
        fontStyleTextField = new JTextField ( );
        fontStyleList = new JList ( new String[]{ "Regular" , "Bold" , "Italic" } );
        fontSizeLabel = new JLabel ( "Size :" );
        fontSizeTextField = new JTextField ( );
        fontSizeList =
                new JList ( new Integer[]{ 8 , 9 , 10 , 11 , 12 , 14 , 16 , 18 , 20 , 22 , 24 , 26 , 28 , 36 , 48 , 72 } );
        okButton = new JButton ( "Ok" );
        cancelButton = new JButton ( "Cancel" );
        fontList.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        fontStyleList.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        fontSizeList.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        fontPane =
                new JScrollPane ( fontList , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        fontStylePane =
                new JScrollPane ( fontStyleList , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        fontSizePane =
                new JScrollPane ( fontSizeList , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        fontLabel.setBounds ( 20 , 10 , 100 + 50 , 20 );
        fontTextField.setBounds ( 20 , 10 + 20 + 3 , 100 + 50 , 20 );
        fontPane.setBounds ( 20 , 10 + 20 + 3 + 20 , 100 + 50 , 150 );
        fontStyleLabel.setBounds ( 20 + 100 + 10 + 50 , 10 , 100 , 20 );
        fontStyleTextField.setBounds ( 20 + 100 + 10 + 50 , 10 + 20 + 3 , 100 , 20 );
        fontStylePane.setBounds ( 20 + 100 + 10 + 50 , 10 + 20 + 3 + 20 , 100 , 150 );
        fontSizeLabel.setBounds ( 20 + 100 + 10 + 100 + 10 + 50 , 10 , 80 , 20 );
        fontSizeTextField.setBounds ( 20 + 100 + 10 + 100 + 10 + 50 , 10 + 20 + 3 , 80 , 20 );
        fontSizePane.setBounds ( 20 + 100 + 10 + 100 + 10 + 50 , 10 + 20 + 3 + 20 , 80 , 150 );
        okButton.setBounds ( 80 , 150 + 100 , 100 , 20 );
        cancelButton.setBounds ( 150 + 80 , 150 + 100 , 100 , 20 );
        fontFrame.setLayout ( null );
        fontFrame.setSize ( 400 , 330 );
        fontFrame.setLocation ( ( d.width - 400 ) / 2 , ( d.height - 350 ) / 2 );
        fontFrame.add ( fontLabel );
        fontFrame.add ( fontTextField );
        fontFrame.add ( fontPane );
        fontFrame.add ( fontStyleLabel );
        fontFrame.add ( fontStyleTextField );
        fontFrame.add ( fontStylePane );
        fontFrame.add ( fontSizeLabel );
        fontFrame.add ( fontSizeTextField );
        fontFrame.add ( fontSizePane );
        fontFrame.add ( okButton );
        fontFrame.add ( cancelButton );
        /* */
        rp = new ReportPanel ( );
        menuBar = new JMenuBar ( );
        newItem = new JMenuItem ( "New" );
        openItem = new JMenuItem ( "Open" );
        saveItem = new JMenuItem ( "Save" );
        saveAsItem = new JMenuItem ( "Save As..." );
        marginItem = new JMenuItem ( "Set Margin" );
        canvasItem = new JMenuItem ( "Canvas Dimensions" );
        fileJMenu = new JMenu ( "File" );
        fontItem = new JMenuItem ( "Font" );
        formatJMenu = new JMenu ( "Format" );

        fileJMenu.add ( newItem );
        fileJMenu.add ( openItem );
        fileJMenu.add ( marginItem );
        fileJMenu.add ( canvasItem );
        fileJMenu.add ( saveItem );
        fileJMenu.add ( saveAsItem );
        menuBar.add ( fileJMenu );
        formatJMenu.add ( fontItem );
        menuBar.add ( formatJMenu );
    }
}
