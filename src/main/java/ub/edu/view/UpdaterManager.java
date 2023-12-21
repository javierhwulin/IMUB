package ub.edu.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdaterManager {
    Map<String, List<UpdateListeners>> listeners = new HashMap<>();
    public UpdaterManager(String... updates) {
        for(String update : updates) {
            this.listeners.put(update, new ArrayList<>());
        }
    }
    public void subscribe(String updateType, UpdateListeners listener) {
        List<UpdateListeners> users = listeners.computeIfAbsent(updateType, k -> new ArrayList<>());
        users.add(listener);
    }
    public void unsubscribe(String updateType, UpdateListeners listener) {
        List<UpdateListeners> users = listeners.get(updateType);
        users.remove(listener);
    }
    public void notify(String updateType) {
        List<UpdateListeners> users = listeners.get(updateType);
        for (UpdateListeners listener : users)
            listener.update(updateType);
    }
}
