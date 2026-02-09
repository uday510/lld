package com.app.patterns.creational.abstractFactory;

public class MacFactory implements UIFactory {

    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

}
