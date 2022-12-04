package eu.rmjoia.generics;

import eu.rmjoia.generics.interfaces.IList;

import java.util.Iterator;

public class GenericLinkedList<T> implements IList<T> {

    private static final int INITIAL_CAPACITY = 0;

    Node<T> head;

    private int nextAvailableSlot;

    GenericLinkedList() {
        this.head = null;
        nextAvailableSlot = INITIAL_CAPACITY;
    }

    @Override
    public void add(T elem) {

        var temp = new Node<T>(elem);

        if (this.head == null) {
            head = temp;
        } else {
            var x = head;

            while (x.next != null) {
                x = x.next;
            }
            x.next = temp;
        }
        nextAvailableSlot++;
    }

    @Override
    public void add(int index, T element) {

        throwIfIndexInvalid(index);

        if (nextAvailableSlot == 0) {
            add(new Node<T>(null).data);
        }

        var nodeToBeInserted = new Node<T>(element);
        var head = this.head;

        for (int i = 0; i < index - 1; i++) {
            head = head.next;
        }

        nodeToBeInserted.next = head.next;
        head.next = nodeToBeInserted;
        nextAvailableSlot++;
    }

    @Override
    public T set(int index, T element) {

        throwIfIndexInvalid(index);

        var isError = false;
        try {
            add(index, element);
        } catch (Exception e) {
            isError = true;
        }

        if (!isError) {
            return element;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {

        throwIfIndexInvalid(index);
        var node = this.head;
        var i = 0;
        while (node.next != null) {
            if (i == index) {
                break;
            }
            i++;
            node = node.next;
        }
        return node.data;
    }

    @Override
    public int size() {
        return nextAvailableSlot;
    }

    @Override
    public T remove(int index) {
        throwIfIndexInvalid(index);

        var nodeToDelete = new Node<T>(null);
        var node = this.head;
        var i = 0;
        while (node.next != null) {
            if (i == index) {
                nodeToDelete = new Node<>(node.data);
                if (node.next != null) {
                    node.data = node.next.data;
                    node.next = node.next.next;
                }
                nextAvailableSlot--;
                break;
            }
            i++;
            node = node.next;
        }
        return nodeToDelete.data;
    }

    @Override
    public boolean remove(T elem) {

        var node = this.head;
        do {
            if (node.data.equals(elem)) {

                if (node.next != null) {
                    node.data = node.next.data;
                    node.next = node.next.next;
                }

                nextAvailableSlot--;
                return true;
            }

            if (node.next != null) {
                node = node.next;
            }
        } while (node.next != null);
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(T element) {
        var node = this.head;
        do {
            if (node.data.equals(element)) {
                return true;
            }
            node = node.next;
        } while (node.next != null);
        return false;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private void throwIfIndexInvalid(int index) {
        if (index > nextAvailableSlot + 1) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
    }

    @Override
    public String toString() {

        var stringToRetun = "";

        var thisList = this.iterator();
        while (thisList.hasNext()) {
            stringToRetun += " -> " + thisList.next();
        }
        return stringToRetun;
    }

     class LinkedListIterator implements Iterator<T> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T element = (T) current.data;
            current = current.next;
            return element;
        }
    }
}
