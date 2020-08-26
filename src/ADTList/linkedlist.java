package ADTList;

public class linkedlist {
    node head, tail;

    // default constructor
    public linkedlist() {
        head = null;
        tail = null;
    }

    /* constructor that takes a head and a tail of an existing list as input */
    linkedlist(node h, node t) {
        head=h;
        tail=t;
    }

    /* Returns a list that contains the same nodes as the original, except that
       the first node is skipped */
    linkedlist restOfList() {
        return new linkedlist(head.getNext(),tail);
    }

    //returns size of the linked list.
    int size() {
        int s=0;
        node current = head;
        while (current!=null) {
            s++;
            current=current.getNext();
        }
        return s;
    }


    //get methods
    public Object getFirst() throws Exception {
        if(head == null) throw new Exception("getFirst: List empty!");
        return this.head.getValue();
    }
    public Object getLast(){return this.tail.getValue();}

    /* Returns the n-th element of the list (0 to size-1) */
    /* Runs in time O(n) */
    public Object getNth(int n){
        if(n>=this.size()){
            throw new IndexOutOfBoundsException("get Nth: n is too big!");
        }
        node current = head;
        while(n>0){
            current = current.getNext();
            n--;
        }
        return current;
    }

    //Add an object to the head of the list.
    public void addFirst(Object x){
        head = new node(x, head);
        if(tail==null){
            tail=head;
        }
    }

    //Add an object to the tail of the list.
    public void addLast(Object x){
        if(tail==null){ //list is empty
            head = tail = new node(x, head);
        }else{
            tail.setNext(new node(x,null));
            tail = tail.getNext();
        }
    }

    /* insert Object at the n-th position of the list */
    /* Runs in time O(n) */
    public boolean insertNth(int n, Object x){
        if(n>=size()) throw new IndexOutOfBoundsException("insertNth: n too big!");
        node predecessor = head;
        while(n>1){     //visit the n-1 nodes first to get to the nth node.
            predecessor = predecessor.getNext();
            n--;
        }
        node nodeToInsert = new node(x, predecessor.getNext());
        predecessor.setNext(nodeToInsert);
        return true;
    }


    //Remove methods
    public boolean removeFirst(){
        if(head==null) return false;    //List was already empty
        head = head.getNext();
        return true;
    }

    public boolean removeLast(){
        if(head==null) return false;     //List was already empty
        node newTail = head;
        while(newTail.getNext()!=tail){
            newTail = newTail.getNext();
        }
        newTail.setNext(null);
        tail = newTail;
        return true;
    }

    /*  Removes from the list the first occurrence of object x.
        Returns true if x was removed. */
    public boolean remove(Object x) throws NoSuchMethodException{
        if(head== null) throw new NoSuchMethodException("List is empty!");

        //If object to remove is the head:
        if(head.getValue().equals(x)){
            head = head.getNext();
            if(head==null) tail = null;
            return true;
        }

        //Iterate until the predecessor
        node current = head;
        while(current.getNext()!=null && !current.getNext().getValue().equals(x)){
            current = current.getNext();
        }

        if(current.getNext()==null){
            return false;
        }else{
            current.setNext(current.getNext().getNext());
            if(current.getNext()==null){
                tail = current;
            }
            return true;
        }
    }
  }