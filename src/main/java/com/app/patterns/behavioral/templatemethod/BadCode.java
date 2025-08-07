//package com.app.patterns.behavioral.templatemethod;
//
//// CSV Parser
//class CSVParser {
//    public void parse() {
//         openFile();
//         System.out.println("CSV File parsed");
//         closeFile();
//    }
//
//    private void openFile() {
//        System.out.println("File opened");
//    }
//
//    public void closeFile() {
//        System.out.println("File closed");
//    }
//}
//
//class JSONParser {
//    public void parse() {
//         openFile();
//         System.out.println("JSON File parsed");
//         closeFile();
//    }
//
//    private void openFile() {
//        System.out.println("File opened");
//    }
//
//    public void closeFile() {
//        System.out.println("File closed");
//    }
//}
//
//
//public class BadCode {
//
//    public static void main(String[] args) {
//        CSVParser csvParser = new CSVParser();
//        csvParser.parse();
//
//        JSONParser jsonParser = new JSONParser();
//        jsonParser.parse();
//    }
//
//}
