public class Ac {
    private boolean isOn;
    private int temperature = 16; // Initial default temperature

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (isOn && temperature >= 16 && temperature <= 30) {
            this.temperature = temperature;
        }
    }

    public void increaseTemperature() {
        if (isOn && temperature < 30) {
            temperature++;
        }
    }

    public void decreaseTemperature() {
        if (isOn && temperature > 16) {
            temperature--;
        }
    }
}