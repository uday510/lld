package com.app.principles.LSP.goodcode;

public class File {

    public static void main(String[] args) {
        Readable readable = new ReadableFile();
        readable.read();

        Writable writable = new WritableFile();
        writable.write();

    }
}
