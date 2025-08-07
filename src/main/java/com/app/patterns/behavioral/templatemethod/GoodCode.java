package com.app.patterns.behavioral.templatemethod;


abstract class DataParser {

    // Template Method defines the steps of the algorithm
    public final void parse() {
        openFile();
        parseDate();
        closeFile();
    }

    protected void openFile() {
        System.out.println("File opened");
    }

    protected void closeFile() {
        System.out.println("File closed");
    }

    abstract void parseDate();
}

class CSVParser extends DataParser {

    @Override
    void parseDate() {
        System.out.println("CSV File Parsed");
    }
}

class JSONParser extends DataParser {

    @Override
    void parseDate() {
        System.out.println("JSON File Parsed");
    }

}

public class GoodCode {

    public static void main(String[] args) {
        DataParser csvParser = new CSVParser();
        DataParser jsonParser = new JSONParser();

        csvParser.parse();
        jsonParser.parse();
    }

}
