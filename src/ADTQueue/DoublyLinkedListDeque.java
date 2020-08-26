package ADTQueue;

/*  Deque: Double-ended queue that allows insertions and removal from the front and back
    removeLast runs in O(n) for singly-linked lists, but in O(1) for doubly-linked lists:
    This is because we have pointers to the previous node for doubly-linked lists.
 */


public class DoublyLinkedListDeque {
    protected node head, tail;

    public DoublyLinkedListDeque(){
        head = null;
        tail = null;
    }

    //Write all other deque methods using a doubly-linked-list:
    public Object getFirst() throws Exception{
        if(head==null) throw new Exception("Empty Deque");
        else return head;
    }

    public Object getLast() throws Exception{
        if(tail==null) throw new Exception("Empty Deque");
        else return tail;
    }

    public void addFirst(Object o){
        node n = new node(o,null,head);
        if(head!=null){
            head.setPrev(n);
        }else{
            tail = n;
        }
        head = n;
    }

    public void addLast(Object o){
        node n = new node(o,tail,null);
        if(tail!=null){
            tail.setNext(n);
        }else{
            head = n;
        }
        tail = n;
    }

    public boolean isEmpty(){
        if(head==null && tail==null) return true;
        else return false;
    }

    public Object removeFirst() throws Exception{
        if(head==null) throw new Exception("Empty Deque");
        Object ret = head.getValue();
        head = head.getNext();
        if(head==null){
            tail = null;
        }else{
            head.setPrev(null);
        }
        return ret;
    }

    //O(1) because we are using doubly-linked lists
    public Object removeLast() throws Exception {
        if (tail==null) throw new Exception("Empty deque");
        Object ret = tail.getValue();
        tail = tail.getPrev();
        if (tail==null) {
            head=null;
        } else {
            tail.setNext(null);
        }
        return ret; // If we return the object removed
    }

    public int size(){
        int count =0;
        node current = head;
        while(current!=null){
            count++;
            current = current.getNext();
        }
        return count;
    }






}
