package IntList;

public class SLList {
    /**
     * inner node class
     */
    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;

    /**
     * empty list
     */
    public SLList() {
        first = null;
    }


    public SLList(int x) {
        first = new IntNode(x, null);
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public int getFirst() {
        return first.item;
    }

    public void addLast(int x) {
        IntNode p = first;

        while (p != null && p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public int size() {
        return size(first);
    }

    private static int size(IntNode p) {
        if (p.next == null) return 1;
        return 1 + size(p.next);
    }

    /**
     * hide the naked recursive data structure
     */
    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        int x = L.getFirst();
        System.out.println(L.size());
    }

}
