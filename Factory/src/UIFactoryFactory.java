public class UIFactoryFactory {
    public static UIFactory createUIFactory(String platform) {
        if (platform.equals("Android")) {
            return new AndroidUIFactory();
        } else if (platform.equals("IOS")) {
            return new IOSUIFactory();
        }
        return null;
    }
}
