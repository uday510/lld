public class Flutter {
    void setRefreshRate() {
        System.out.println("Setting refresh rate");
    }
    void setTheme() {
        System.out.println("Setting theme");
    }
    public UIFactory createUIFactory(String platform) {
        return UIFactoryFactory.createUIFactory(platform);
    }
}
