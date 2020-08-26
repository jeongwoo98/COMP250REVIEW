package ADTQueue;

public class node {
    node prev,next;
    Object value;

    public node(Object value, node p, node n){
        this.value = value;
        prev = p;
        next = n;
    }

    //Get methods:
    public node getPrev(){
        return prev;
    }
    public node getNext(){
        return next;
    }
    public Object getValue(){
        return value;
    }

    //Set methods:
    public void setPrev(node n){
        prev = n;
    }
    public void setNext(node n){
        next = n;
    }
    public void setValue(Object o){
        value = o;
    }

}
