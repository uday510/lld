package com.app.patterns.creational.abstractFactory;

public class WindowsFactory implements UIFactory {

    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

}
