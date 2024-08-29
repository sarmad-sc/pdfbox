package com.pdfbox;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PdfService {

    public byte[] generatePDF() {

//        Map<List<String>, List<List<String>>> map = new HashMap<>();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent(), null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String htmlContent() {

        String branch = "CIVIC CENTER BRANCH";
        StringBuilder html = new StringBuilder("""
                <html lang="en">
                <head>
                    <title>Summary Report</title>
                    <style type="text/css">
                     .f-ss {
                         font-family: sans-serif;
                     }
                     .fs {
                     font-size: 10px;
                     }
                     
                     p {
                     margin-bottom: 30px;
                     }
                     .container {
                     }
                     img {
                     height: 40%;
                     width: 40%;
                     float: left; /* Floats the image to the left */
                     }
                     .text-block {
                     font-family: sans-serif;
                     float: right;
                     font-size: 8px;
                     width: 150px;
                     }
                     .tar {
                     text-align: right;
                     }
                     </style>
                </head>
                <body class="f-ss fs">
                  <img src="http://localhost:8080/mrwlogo.gif"/>
                  <div class="text-block">
                   <div><span style="font-weight: bold;">Report:</span> Daily Cash Book Summary</div>
                   <div style="margin-top: 3px;"><span style="font-weight: bold;">Branch: </span>""" + branch + """
                  </div>
                  <div style="margin-top: 3px;"><span style="font-weight: bold;">Terminal: </span>TERM0121</div>
                  <div style="margin-top: 3px;"><span style="font-weight: bold;">Username:</span> QASIM</div>
                  <div style="margin-top: 3px;"><span style="font-weight: bold;">Date From: </span>Aug 12, 2024</div>
                  <div style="margin-top: 3px;"><span style="font-weight: bold;">Date To:</span> Aug 12, 2024</div>
                 </div>
                 <div style="margin-bottom: 85px;"></div>
                <table style="width: auto; border-collapse: collapse;" class="f-ss">
                       <thead style="text-align: center;">
                           <tr style="background-color: #f2f2f2; color: #333; font-weight: bold;">
                       """);

        List<List<String>> headings = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();

        List<List<Map<String, String>>> md = new LinkedList<>();

        List<Map<String, String>> rm = new LinkedList<>();

        Map<String, String> map = new HashMap<>();

        map.put("heading1", "10.187");
        map.put("heading2", "2.0");
        map.put("heading3", "3.24");

        rm.add(map);
        map = new HashMap<>();

        map.put("heading4", "d4");
        map.put("heading5", "d5");

        rm.add(map);
        map = new HashMap<>();

        map.put("heading6", "d6");
        map.put("heading7", "d7");
        map.put("heading8", "d8");

        rm.add(map);
        md.add(rm);

        rm = new LinkedList<>();
        map = new HashMap<>();

        map.put("heading1", "d9");
        map.put("heading2", "d10");
        map.put("heading3", "d11");

        rm.add(map);
        map = new HashMap<>();

        map.put("heading4", "d12");
        map.put("heading5", "d13");

        rm.add(map);
        map = new HashMap<>();

        map.put("heading6", "d14");
        map.put("heading7", "d15");
        map.put("heading8", "d16");
        rm.add(map);
        md.add(rm);


        // Populate the ArrayList with dummy data
//        list.add("heading 1");
//        list.add("heading 2");
//        list.add("heading 3");
//        headings.add(list);
//        list = new ArrayList<>();
//        list.add("heading 4");
//        list.add("heading 5");
//        headings.add(list);
//        list = new ArrayList<>();
//        list.add("heading 6");
//        list.add("heading 7");
//        list.add("heading 8");
//        headings.add(list);

        md.get(0).forEach(m -> {
            html.append("""
                    <th style="border: 1px solid #ddd; padding: 8px;">""");
            m.keySet().forEach(k -> html.append("<div>").append(k).append("</div>"));
            html.append("</th>");
        });

        html.append("</tr></thead>");

//        List<List<List<String>>> data = new ArrayList<>();
//
//        List<List<String>> r = new ArrayList<>();
//        List<String> c = new ArrayList<>();
//
//        c.add("1.00");
//        c.add("2.234");
//        c.add("30.452");
//        r.add(c);
//        c = new ArrayList<>();
//        c.add("d4");
//        c.add("d5");
//        r.add(c);
//        c = new ArrayList<>();
//        c.add("d6");
//        c.add("d7");
//        c.add("d8");
//        r.add(c);
//        data.add(r);
//        r = new ArrayList<>();
//        c = new ArrayList<>();
//        c.add("d9");
//        c.add("d10");
//        c.add("d11");
//        r.add(c);
//        c = new ArrayList<>();
//        c.add("d12");
//        c.add("d13");
//        r.add(c);
//        c = new ArrayList<>();
//        c.add("d14");
//        c.add("d15");
//        c.add("d16");
//        r.add(c);
//        data.add(r);

        html.append("<tbody>");

        md.forEach(row -> {
            html.append("""
                    <tr style="background-color: #ffffff;">""");
            row.forEach(cell -> {
                html.append("""
                        <td style="border: 1px solid #ddd;">
                        <div>
                        """);
                cell.values().forEach(d -> html.append("""
                        <div class="tar">""").append(d).append("</div>"));
                html.append("</div></td>");
            });
            html.append("</tr>");
        });

        html.append("""
                   </tbody>
                  </table>
                 </body>
                </html>
                 """);

        return html.toString();
    }
}
