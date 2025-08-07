package com.app.patterns.behavioral.command;


import org.w3c.dom.Text;

// Command Interface
interface Command {
    void execute();
}

// Concrete classes for commands
class BoldCommand implements Command {
    private final TextEditor editor;

    BoldCommand(TextEditor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.boldText();
    }
}

class ItalicCommand implements Command {
    private final TextEditor editor;

    ItalicCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.italicText();
    }
}

// Button Class
class Button {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void click() {
        command.execute();
    }
}

class TextEditor {

    public void boldText() {
        System.out.println("Text bolded");
    }

    public void italicText() {
        System.out.println("Italic Text");
    }

    public void underlineText() {
        System.out.println("Underline Text");
    }

}

public class CommandPattern {

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

        Button button = new Button();
        button.setCommand(new BoldCommand(textEditor));

        button.click();

        button.setCommand(new ItalicCommand(textEditor));
        button.click();

    }
}
