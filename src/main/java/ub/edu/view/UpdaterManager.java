package ub.edu.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdaterManager {
    Map<String, List<UpdateListener>> listeners = new HashMap<>();
    public UpdaterManager(String... updates) {
        for(String update : updates) {
            this.listeners.put(update, new ArrayList<>());
        }
    }
    public void subscribe(String updateType, UpdateListener listener) {
        List<UpdateListener> users = listeners.computeIfAbsent(updateType, k -> new ArrayList<>());
        users.add(listener);
    }
    public void unsubscribe(String updateType, UpdateListener listener) {
        List<UpdateListener> users = listeners.get(updateType);
        users.remove(listener);
    }
    public void notify(String updateType) {
        List<UpdateListener> users = listeners.get(updateType);
        for (UpdateListener listener : users)
            listener.update(updateType);
    }
}
