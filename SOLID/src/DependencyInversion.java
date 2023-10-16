public class DependencyInversion {
    //TODO: The DIP states that high-level modules should not depend on low-level modules. Both should depend on abstractions.

    static class LightBulb {
        public void turnOn() {
            System.out.println("LightBulb is turned on.");
        }
        public void turnOff() {
            System.out.println("LightBulb is turned off.");
        }
    }

    interface Switch {
        void operate();
    }
   static class RemoteControl implements Switch {
        private final LightBulb bulb;

        public RemoteControl(LightBulb bulb) {
            this.bulb = bulb;
        }

        public void operate() {
            bulb.turnOn();
        }
   }
}
