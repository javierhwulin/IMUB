package ub.edu.model.ED;

import java.util.ArrayList;
import java.util.List;

public class Llista<T> {
    private List<T> elements;

    public Llista() {
        this.elements = new ArrayList<>();
    }

    public Llista(List<T> allElements) {
        this.elements = allElements;
    }
    public boolean add(T element) {
        // Existeix l'element amb el mateix nom?
        String newName = null;

        try {
            newName = (String) element.getClass().getMethod("getName").invoke(element);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newName != null && !containsName(newName)) {
            elements.add(element);
            return true;
        } else return false;
    }

    private boolean containsName(String name) {
        for (T element : elements) {
            try {
                String elementName = (String) element.getClass().getMethod("getName").invoke(element);
                if (elementName.equals(name)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<T> getElements() {
        return elements;
    }
    public T find(String name) {
        for (T element : elements) {
            try {
                String elementName = (String) element.getClass().getMethod("getName").invoke(element);
                if (elementName.equals(name)) {
                    return element;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void remove(T element) {
        elements.remove(element);
    }

    public int getSize() {
        return elements.size();
    }

}