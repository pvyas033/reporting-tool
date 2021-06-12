package com.notebook.pages;

import com.notebook.pages.frame.ReportFrame;

public class Main {

    public static ReportFrame reportFrame;
    public static void main(String[] args) {
        reportFrame = new ReportFrame();
        reportFrame.setTitle ( "Reporting Tool" );
    }
}
