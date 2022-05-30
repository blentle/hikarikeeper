package org.hikarikeeper.core;

import java.util.EventListener;

/**
 * This class manages events. It allows eventListeners to be associated with a string
 * and removes eventListeners and their events in addition to managing triggers.
 */
public class EventManager {

    /**
     * a monitor for lock method
     */
    private Object monitor = new Object();

    /**
     * lock monitor
     * @param path
     * @param listener
     */
    public void addEvent(String path, EventListener listener) {
        //todo:
    }

    /**
     * lock monitor
     * @param listener
     */
    public synchronized void removeListener(EventListener listener) {
        //todo:
    }
}
