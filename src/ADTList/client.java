package ADTList;

public class client {
    public static void main(String[]Args) throws Exception {
        linkedlist l = new linkedlist();    //the list is empty for now
        l.addFirst("Roses");
        l.addLast("are");
        l.addLast("red");

        System.out.println(l.getFirst());
        System.out.println(l.getLast());
    }

}
