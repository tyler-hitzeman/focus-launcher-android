package minium.co.launcher2.events;

/**
 * Created by Shahab on 1/9/2017.
 */

public class NFCEvent {

    private boolean isConnected;

    public NFCEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
