import Components.Button.AndroidButton;
import Components.Button.Button;
import Components.Dropdown.Dropdown;
import Components.Menu.Menu;

public class AndroidUIFactory implements  UIFactory {
    @Override
    public AndroidButton createButton() {
        return new AndroidButton();
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
