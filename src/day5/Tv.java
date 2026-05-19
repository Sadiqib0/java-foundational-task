public class Tv {
    private boolean isOn;
    private int channel = 1;
    private int volume = 10;

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        if (isOn && channel >= 1 && channel <= 5) {
            this.channel = channel;
        }
    }

    public void nextChannel() {
        if (isOn) {
            if (channel == 5) channel = 1;
            else channel++;
        }
    }

    public void previousChannel() {
        if (isOn) {
            if (channel == 1) channel = 5;
            else channel--;
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (isOn && volume >= 0 && volume <= 100) {
            this.volume = volume;
        }
    }

    public void increaseVolume() {
        if (isOn && volume < 100) {
            volume++;
        }
    }

    public void decreaseVolume() {
        if (isOn && volume > 0) {
            volume--;
        }
    }
}