package com.app.patterns.creational.abstractFactory;

// Abstract Product Interfaces
interface Button {
    void render();
}

interface Scrollbar {
    void scroll();
}

// Windows UI Components

class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

class WindowsScrollbar implements Scrollbar {
    public void scroll() {
        System.out.println("Rendering Windows Scroll Bar");
    }
}

// Mac UI Components
class MacOSButton implements Button {
    public void render() {
        System.out.println("Rendering MacOS Button");
    }
}

class MacOSScrollbar implements Scrollbar {
    public void scroll() {
        System.out.println("MacOS Scroll Bar");
    }
}

interface UIFactory {
    Button createButton();
    Scrollbar createScrollBar();
}

//Concrete Implementation
class WindowsFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Scrollbar createScrollBar() {
        return new WindowsScrollbar();
    }
}

class MacOSFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Scrollbar createScrollBar() {
        return new MacOSScrollbar();
    }
}

public class Application {

    private final Button button;
    private final Scrollbar scrollbar;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.scrollbar = factory.createScrollBar();
    }

    public void renderUI() {
        button.render();
        scrollbar.scroll();
    }

    public static void main(String[] args) {
        // Windows Factory
        UIFactory windowsFactory = new WindowsFactory();

        // MacOS Factory
        UIFactory macOSFactory = new MacOSFactory();

        Application application = new Application(macOSFactory);
        application.renderUI();

    }
}
