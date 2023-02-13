package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{

    /**
     * 内部节点
     */
    private class node {
        node prev;
        node next;
        T item;

        public node(T item, node prev, node next) {
            this.item = item;
            this.prev = prev;
            this.next =  next;
        }
    }
    private node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        // 新节点的next是之前的第一个节点. prev是sentinel
        node newNode = new node(item, sentinel, sentinel.next);
        // 之前的节点的prev调整为新插入的节点
        sentinel.next.prev = newNode;
        // sentinel的next指向新插入的节点
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        // 新节点的next是sentinel, prev是之前的最后一个节点
        node newNode = new node(item, sentinel.prev, sentinel);
        // 之前的最后一个节点的next指向新插入的节点
        sentinel.prev.next = newNode;
        // sentinel的prev指向新插入的节点
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " -> ");
            ptr = ptr.next;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        // 就算只有sentinel也不会有影响
        node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size--;
        return last.item;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        node first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size--;
        return first.item;
    }

    @Override
    public T get(int index) {
        node ptr = sentinel.next;
        int i = 1;
        while (i < index && ptr != sentinel) {
            System.out.print(ptr.item + " -> ");
            ptr = ptr.next;
            i++;
        }
        if (ptr == sentinel) {
            // TODO: 异常处理
            return null;
        }
        return ptr.item;
    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            node ptr = sentinel;
            @Override
            public boolean hasNext() {
                return (ptr = ptr.next) != sentinel;
            }

            @Override
            public T next() {
                return ptr.item;
            }
        };
    }
}
