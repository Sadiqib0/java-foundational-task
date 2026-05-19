public class Bike {

    private boolean isOn;
    private int speed;


    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public int getSpeed() {
        return speed;
    }
    public int getGear() {
        if (speed >= 0 && speed <= 20) {
            return 1;
        } else if (speed >= 21 && speed <= 30) {
            return 2;
        } else if (speed >= 31 && speed <= 40) {
            return 3;
        } else {
            return 4;
        }
    }
    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    public void accelerate() {
        if (isOn) {
            int currentGear;
            currentGear = getGear();
            speed += currentGear;
        }
    }

    public void decelerate() {
        if (!isOn) {
            return;
        }
        int currentGear;
        currentGear = getGear();
        speed -= currentGear;

        if (speed < 0) speed = 0;
    }
}