//package com.app.patterns.behavioral.command;
//
//
//class TextEditor {
//
//    public void boldText() {
//        System.out.println("Text bolded");
//    }
//
//    public void italicText() {
//        System.out.println("Italic Text");
//    }
//
//    public void underlineText() {
//        System.out.println("Underline Text");
//    }
//
//}
//
//
//// UI Button classes
//class BoldButton {
//    final TextEditor textEditor;
//
//    BoldButton(TextEditor textEditor) {
//        this.textEditor = textEditor;
//    }
//
//    public void click() {
//        textEditor.boldText();
//    }
//}
//
//class ItalicButton {
//    final TextEditor textEditor;
//
//    ItalicButton(TextEditor textEditor) {
//        this.textEditor = textEditor;
//    }
//
//    public void click() {
//        textEditor.italicText();
//    }
//}
//
//class UnderlineButton {
//    final TextEditor textEditor;
//
//    UnderlineButton(TextEditor textEditor) {
//        this.textEditor = textEditor;
//    }
//
//    public void click() {
//        textEditor.italicText();
//    }
//}
//
//
//public class BadCode {
//
//    public static void main(String[] args) {
//
//        TextEditor textEditor = new TextEditor();
//
//        BoldButton boldButton = new BoldButton(textEditor);
//        boldButton.click();
//
//        ItalicButton italicButton = new ItalicButton(textEditor);
//        italicButton.click();
//
//    }
//}
