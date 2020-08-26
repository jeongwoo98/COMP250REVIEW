package ADTList;

/* A node is the basic unit for building lists */
public class node {
    private Object value; //stores the value itself
    private node next;    // reference to the next element in the list

    public node(Object x, node n) {
        value = x;
        next = n;
    }
    public node getNext() {
        return next;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object x) {
        value = x;
    }
    public void setNext(node n) {
        next = n;
    }
}
