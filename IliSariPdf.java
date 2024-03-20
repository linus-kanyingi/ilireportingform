//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import *;
//import pdf.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.PdfCell;
//import com.sun.xml.internal.ws.api.ResourceLoader;

////import //java.awt.Desktop;
public class IliSariPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    String facilityID = "";
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    java.lang.String disease = null;
    java.lang.String servicepnt = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.afrisoftech.lib.DBObject dbObject;
    Font pFontHeader = null; //FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    Font pFontHeader1 = null; //FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String serialnumber = null;

    public void IliSariPdf(java.sql.Connection connDb, java.lang.String serialNO) {

        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        serialnumber = serialNO;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberListPdf().MemberListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            try {
                this.generatePdf();
            } catch (DocumentException ex) {
                Logger.getLogger(IliSariPdf.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(50);

                System.out.println("It's time for us threads to get back to work after the nap");

            } catch (java.lang.InterruptedException IntExec) {

                System.out.println(IntExec.getMessage());

            }

            threadCheck = false;

            System.out.println("We shall be lucky to get back to start in one piece");

        }

        if (!threadCheck) {

            Thread.currentThread().stop();

        }

    }

    public java.lang.String getDateLable() {

        java.lang.String date_label = null;

        java.lang.String month_now_strs = null;

        java.lang.String date_now_strs = null;

        java.lang.String year_now_strs = null;

        java.lang.String minute_now_strs = null;

        java.lang.String hour_now_strs = null;

        java.lang.Runtime rt = java.lang.Runtime.getRuntime();

        java.util.Calendar calinst = java.util.Calendar.getInstance();

        java.util.Date date_now = calinst.getTime();

        int date_now_str = date_now.getDate();

        int month_now_str = date_now.getMonth();

        int year_now_str = date_now.getYear();

        int hour_now_str = date_now.getHours();

        int minute_now_str = date_now.getMinutes();

        int year_now_abs = year_now_str - 100;

        if (year_now_abs < 10) {

            year_now_strs = "200" + year_now_abs;

        } else {

            year_now_strs = "20" + year_now_abs;

        }

        switch (month_now_str) {

            case 0:
                month_now_strs = "JAN";

                break;

            case 1:
                month_now_strs = "FEB";

                break;

            case 2:
                month_now_strs = "MAR";

                break;

            case 3:
                month_now_strs = "APR";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUN";

                break;

            case 6:
                month_now_strs = "JUL";

                break;

            case 7:
                month_now_strs = "AUG";

                break;

            case 8:
                month_now_strs = "SEP";

                break;

            case 9:
                month_now_strs = "OCT";

                break;

            case 10:
                month_now_strs = "NOV";

                break;

            case 11:
                month_now_strs = "DEC";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = "0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = "0" + date_now_str;

        } else {

            date_now_strs = "" + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = "0" + minute_now_str;

        } else {

            minute_now_strs = "" + minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = "0" + hour_now_str;

        } else {

            hour_now_strs = "" + hour_now_str;

        }

        date_label = date_now_strs + month_now_strs + year_now_strs + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf() throws DocumentException {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            /*com.itextpdf.text.pdf.BaseFont pFontHeaderOrig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader1Orig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader11Orig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader12Orig = null;

            com.itextpdf.text.Font pFontHeader = null;
            com.itextpdf.text.Font pFontHeader1 = null;
            com.itextpdf.text.Font pFontHeader11 = null;
            com.itextpdf.text.Font pFontHeader12 = null;

            //  ResourceLoader loader = new ResourceLoader("/fonts/ARIALUNI.ttf");
            pFontHeaderOrig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
            pFontHeader1Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
            pFontHeader11Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
            pFontHeader12Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);

            pFontHeader = new com.itextpdf.text.Font(pFontHeaderOrig, 10f);
            pFontHeader1 = new com.itextpdf.text.Font(pFontHeader1Orig, 9f);
            pFontHeader11 = new com.itextpdf.text.Font(pFontHeader11Orig, 10f);
            pFontHeader12 = new com.itextpdf.text.Font(pFontHeader12Orig, 10f);

            System.out.println("Font pFontHeader [" + pFontHeader + "]");*/
            com.itextpdf.text.Font pFontHeader = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD);
            com.itextpdf.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.COURIER, 15, Font.BOLD);
            com.itextpdf.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.COURIER, 13, Font.BOLD);
            com.itextpdf.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD);
            com.itextpdf.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL);
            //com.itextpdf.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
            //com.itextpdf.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD);

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //Document docPdf = new Document();
            Document docPdf = new Document(PageSize.A4.rotate());
            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,facility_id from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            facilityID = rset2.getObject(2).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                    Header footer = new com.itextpdf.text.Header("", Header.AUTHOR);
                    docPdf.addHeader("IDSR LISTING", Header.PRODUCER);

                    docPdf.open();

                    PdfPTable table = new PdfPTable(12);
                    int headerwidths[] = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
                    table.setWidths(headerwidths);
                    table.setHeaderRows(2);
                    table.setWidthPercentage((100));
                    Phrase phrase = new Phrase("", pFontHeader);
                    //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    table.getDefaultCell().setColspan(3);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    table.getDefaultCell().setFixedHeight(50);
                    table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                    table.getDefaultCell().setFixedHeight(-1);
                    try {
                        java.sql.Statement st32 = connectDB.createStatement();
                        java.sql.ResultSet rset22 = st32.executeQuery("SELECT header_name,current_date FROM pb_header");
                        while (rset22.next()) {
                            table.getDefaultCell().setColspan(9);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"), pFontHeader);
                            table.addCell(phrase);
                        }
                    } catch (java.sql.SQLException e) {
                        e.printStackTrace();
                    }
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                    //java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                    //java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                    //System.out.println("" + endDate1);
                    table.getDefaultCell().setColspan(12);
                    phrase = new Phrase("SEVERE ACUTE RESPIRATORY ILLNESS AND INFLUENZA LIKE ILLNESS ENROLMENT QUESTIONNAIRE ", pFontHeader1);
                    table.addCell(phrase);

                    javax.swing.JTable registerReportTable = new com.afrisoftech.dbadmin.JXTable();
                    //registerReportTable.setModel(biz.systempartners.reports.IDRSRegisterReportIntfr.getTableModel(connectDB, beginDate, endDate, disease));

                    table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(12);
                    phrase = new Phrase("Demographic Information ", pFontHeader2);
                    table.addCell(phrase);

                    phrase = new Phrase(" ", pFontHeader3);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("Hospital ", pFontHeader3);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(" ", pFontHeader3);
                    table.addCell(phrase);

                    try {

                        //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement stmt1 = connectDB.createStatement();
                        java.sql.ResultSet rSet1 = null;
                        rSet1 = stmt1.executeQuery("SELECT patient_no, patient_name, gender, age, village, county, onset_date, visit_date, "
                                + "symptoms, symptoms_child, risk_factor, temperature, temperature_mode, oxygen_saturation, oxygen_saturation_mode,"
                                + " resp_rate, icu_hdu, ventilation, influenza_vaccine, influenza_verified, covid_vaccine, covid_doses, covid_verified, "
                                + "covid_test, covid_result, specimen_collected, specimen_type, specimen_date, outcome, outcome_date, trans_date, user_name,"
                                + " oid, disease FROM public.hp_symptoms_screening_sari_ili where oid = '" + serialnumber + "';");
                        while (rSet1.next()) {
                            //if (rSet1.getFloat(1) > 0){
                            //listActVector.addElement(rSet1.getObject(1).toString());
                            //}

                            System.out.println("description" + rSet1.getObject(1).toString());
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Patient Name ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Patient Age ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(compName, pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("patient_name"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("age"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Sex ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Village/Estate ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("County ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(rSet1.getString("gender"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("village"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("county"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Clinical Description ", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Date of onset of the Current Illness ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Date of Hospital Admission ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(rSet1.getString("onset_date"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("visit_date"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Has The Patient Had the Following Symptoms or Signs with his illness", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(rSet1.getString("symptoms"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Complete the following for children < 5 years", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(rSet1.getString("symptoms_child"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Risk Factors", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Does the patient have any of the medical conditions listed below as diagnosed by a clinician?", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(rSet1.getString("risk_factor"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Pysical Examination", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Temperature (°C)", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Temperature Mode", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("temperature"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("temperature_mode"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Respiratory Rate : breaths per minute(count for full minute) ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Oxygen Saturation(%)", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Oxygen Saturation Mode ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(rSet1.getString("resp_rate"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("oxygen_saturation"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("oxygen_saturation_mode"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            disease = rSet1.getString("disease");
                            if (disease.equalsIgnoreCase("SARI")) {

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                table.getDefaultCell().setColspan(12);
                                phrase = new Phrase(" ", pFontHeader4);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(12);
                                phrase = new Phrase("Disease Severity", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Has the patient been admitted to ICU/HDU?", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader3);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Has the Patient Received Mechanical Ventilation?", pFontHeader3);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase(rSet1.getString("icu_hdu"), pFontHeader4);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader4);
                                table.addCell(phrase);

                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase(rSet1.getString("ventilation"), pFontHeader4);
                                table.addCell(phrase);
                            } else {
                            }

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Vaccination", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Has the patient received any of the following vaccinations?", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Influenza vaccine in the past 12 months", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Was this verified using a vaccination card or hospital records?", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("influenza_vaccine"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("influenza_verified"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("COVID-19 Vaccine", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("How many doses of COVID-19 vaccine?", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("covid_vaccine"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("covid_doses"), pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Was this verified using a vaccination card or hospital records", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Since the patients vaccination has she/he been tested for COVID-19?", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("covid_verified"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("covid_test"), pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("If yes, what was the COVID-19 test result?", pFontHeader3);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("covid_result"), pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Specimen collection", pFontHeader2);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Was Any Specimen Collected from this Patient for Influenza Testing?", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Type of swab specimen collected?", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Date of specimen collection", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(rSet1.getString("specimen_collected"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("specimen_type"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(rSet1.getString("specimen_date"), pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                           
                            if (disease.equalsIgnoreCase("SARI")) {
                                table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Final outcome", pFontHeader2);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("What was the final outcome?", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Date of final outcome?", pFontHeader3);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("outcome"), pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader4);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase(rSet1.getString("outcome_date"), pFontHeader4);
                            table.addCell(phrase);


                            }
                            else {
                            }



                        }
                    } catch (java.sql.SQLException sqlExec) {

                        sqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

                    }


                    /*phrase = new Phrase("Patient ID.", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Visit Type", pFontHeader);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Name", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Sex", pFontHeader);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("DOB", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Age", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("County", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Village", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Visit Date", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Admission Date", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Temp", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Cough", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Difficulty in Breathing", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Vomitting", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Water diarrhoea", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Blood in Stool", pFontHeader);
                    table.addCell(phrase);
                    phrase = new Phrase("Suspected Case", pFontHeader);
                    table.addCell(phrase);*/
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    try {
                        java.sql.Statement st11 = connectDB.createStatement();
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        /*for (int i = 0; i < registerReportTable.getRowCount(); i++) {
                            for (int p = 0; p < registerReportTable.getColumnCount(); p++) {
                                phrase = new Phrase(dbObject.getDBObject(registerReportTable.getValueAt(i, p), "-"), pFontHeader1);
                                table.addCell(phrase);
                            }
                        }*/

                        docPdf.add(table);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                    //
                    // }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

            //com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }
    ////*********THE CHANGE DONE CASTED THE input_date::date

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM funsoft_hp_patient_visit('" + beginDate + "','" + endDate + "')    order by 2 ASC");
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}

                System.out.println("description" + rSet1.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
