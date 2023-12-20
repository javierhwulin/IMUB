package ub.edu.model.ED;

public class Parell <T, V>{
    T element1;
    V element2;

    public Parell(T element1, V element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T getElement1() {
        return element1;
    }

    public V getElement2() {
        return element2;
    }

    public void setElement1(T element1) {
        this.element1 = element1;
    }

    public void setElement2(V element2) {
        this.element2 = element2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((element1 == null) ? 0 : element1.hashCode());
        result = prime * result + ((element2 == null) ? 0 : element2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Parell other = (Parell) obj;
        if (element1 == null) {
            if (other.element1 != null)
                return false;
        } else if (!element1.equals(other.element2))
            return false;
        if (element2 == null) {
            return other.element2 == null;
        } else return element2.equals(other.element2);
    }
}
