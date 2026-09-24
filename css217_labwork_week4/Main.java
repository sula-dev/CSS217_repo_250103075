import java.util.List;

public class Main {
    public static void main(String[] args) {
        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        BulbAdapter bulbAdapter = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter = new ThermostatAdapter(rawThermostat);

        List<SmartDevice> deviceList = List.of(bulbAdapter, thermostatAdapter);
        ModernHub hub = new ModernHub(deviceList);

        hub.activateAll();
        System.out.println("Bulb brightness: " + rawBulb.readBrightness());
        System.out.println("Thermostat dial: " + rawThermostat.checkDial());
        System.out.println("All active: " + (bulbAdapter.isOn() && thermostatAdapter.isOn()));
        System.out.println("Average power: " + hub.calculateAveragePowerUsage());


        rawBulb.breakFilament();
        System.out.println("Fault A -> isOn: " + bulbAdapter.isOn()
                + ", power: " + bulbAdapter.getPowerPercent());

        rawThermostat.rotateDial("STUCK");
        System.out.println("Fault B -> isOn: " + thermostatAdapter.isOn()
                + ", power: " + thermostatAdapter.getPowerPercent());

        rawThermostat.rotateDial(null);
        System.out.println("Fault C -> isOn: " + thermostatAdapter.isOn()
                + ", power: " + thermostatAdapter.getPowerPercent());

        hub.emergencyShutdown();
        System.out.println("Bulb brightness: " + rawBulb.readBrightness());
        System.out.println("Thermostat dial: " + rawThermostat.checkDial());
        System.out.println("Average power: " + hub.calculateAveragePowerUsage());
    }
}
