package com.app.principles.LSP.goodcode;

public class WritableFile implements Writable {

    public void read() {
        System.out.println("Reading...");
    }

    public void write() {
        System.out.println("Writing...");
    }

}
