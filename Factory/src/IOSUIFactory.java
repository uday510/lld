import Components.Button.AndroidButton;
import Components.Button.Button;
import Components.Button.IOSButton;
import Components.Dropdown.Dropdown;
import Components.Menu.Menu;

public class IOSUIFactory implements UIFactory{
    @Override
    public IOSButton createButton() {
        return new IOSButton();
    }

    @Override
    public Menu createMenu() {
        return null;
    }

    @Override
    public Dropdown createDropDown() {
        return null;
    }
}
