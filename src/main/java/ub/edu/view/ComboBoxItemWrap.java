package ub.edu.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ComboBoxItemWrap<T> {

    private final BooleanProperty check = new SimpleBooleanProperty(false);
    private final ObjectProperty<T> item = new SimpleObjectProperty<>();

    ComboBoxItemWrap() {
    }

    ComboBoxItemWrap(T item) {
        this.item.set(item);
    }

    ComboBoxItemWrap(T item, Boolean check) {
        this.item.set(item);
        this.check.set(check);
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public Boolean getCheck() {
        return check.getValue();
    }

    public void setCheck(Boolean value) {
        check.set(value);
    }

    public ObjectProperty<T> itemProperty() {
        return item;
    }

    public T getItem() {
        return item.getValue();
    }

    public void setItem(T value) {
        item.setValue(value);
    }

    @Override
    public String toString() {
        return item.getValue().toString();
    }
}
