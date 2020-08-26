package ADTQueue;

public class Client {
    public static void main(String[] args){

        try{
            DoublyLinkedListDeque dQ = new DoublyLinkedListDeque();
            System.out.println(dQ.isEmpty());   //true
            dQ.addFirst("Roses");
            System.out.println(dQ.isEmpty());   //false
            dQ.addFirst("are");
            System.out.println(dQ.getLast().toString());   //Roses
            System.out.println(dQ.size());      //2
            dQ.removeFirst();
            dQ.removeFirst();
            System.out.println(dQ.isEmpty());   //true

        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
