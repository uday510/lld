import Components.Button.Button;
import Components.Dropdown.Dropdown;
import Components.Menu.Menu;

public interface UIFactory {

    public Button createButton();
    public Menu createMenu();
    public Dropdown createDropDown();
}
