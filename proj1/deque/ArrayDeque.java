package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    T[] items;
    int size;
    int head; // 语义nextfirst是first + 1
    int tail; // 为了方便实现, 定义last为数组最后位置的后一个位置

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    // 双倍扩容
    // 原来的head对应0, tail对应size
    private void grow() {
        // System.out.println("now grow");
        T[] newItems = (T[]) new Object[items.length * 2];
        int idx = 0; // 新数组的
        int i = 0;
        while (idx < items.length) {
            newItems[idx++] = items[i];
            i = (i + 1) % items.length;
        }

        head = 0;
        tail = idx;
        items = newItems;
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[items.length / 2];
        int idx = 0; // 新数组的
        int i = 0;
        while (idx < items.length) {
            newItems[idx++] = items[i];
            i = (i + 1) % items.length;
        }
        head = 0;
        tail = idx;
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        size++;
        if (size > items.length) grow();
        // 在我这里需要特殊判空一次
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
    }

    @Override
    public void addLast(T item) {
        size++;
        if (size > items.length) grow();
        items[tail] = item;
        tail = (tail + 1) % items.length;
        if (tail == head) grow();
    }

    @Override
    public boolean isEmpty() {
        // 检查first == last可能也行
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = head; i != tail; i = (i + 1) % items.length) {
            System.out.print(items[i] + "");
        }
    }


    @Override
    public T removeLast() {
        if (size == 0) return null;
        size--;
        T result = items[(tail - 1 + items.length) % items.length];
        items[(tail - 1 + items.length) % items.length] = null;
        tail = (tail - 1 + items.length) % items.length;
        return result;
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        size--;
        T result = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        return result;
    }

    @Override
    public T get(int index) {
        if (index > size) return null;
        return items[(head + index) % items.length];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int idx = head - 1; // 初始值为head - 1
            @Override
            public boolean hasNext() {
                // 让idx自增
                idx = (idx + 1) % items.length;
                return idx != tail;
            }

            @Override
            public T next() {
                return items[idx];
            }
        };
    }
}
