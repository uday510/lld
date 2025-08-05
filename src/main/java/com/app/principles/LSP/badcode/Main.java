    package com.app.principles.LSP.badcode;

public class Main {

    public static void main(String[] args) {

        File file = new ReadOnlyFile();
        file.read();

        file.write();

    }
}
