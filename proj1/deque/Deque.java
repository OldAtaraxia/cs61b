package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeLast();
    public T get(int index);
    public T removeFirst();

    public Iterator<T> iterator(); // return an iterator
    public boolean equals(Object o); // whether or not the parameter o is equal to the Deque.


}
