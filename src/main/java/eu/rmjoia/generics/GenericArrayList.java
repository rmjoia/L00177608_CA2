package eu.rmjoia.generics;

import eu.rmjoia.generics.interfaces.IList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericArrayList<T> implements IList<T> {
    private static final int INITIAL_CAPACITY = 0;

    private T[] buffer;
    private int nextAvailableSlot;
    private int currentCapacity;

    public GenericArrayList() {
        currentCapacity = INITIAL_CAPACITY;
        nextAvailableSlot = INITIAL_CAPACITY;
        buffer = (T[]) new Object[currentCapacity];
    }

    @Override
    public void add(T elem) {
        expandArraySizeIfNeeded();

        buffer[nextAvailableSlot++] = elem;
    }

    @Override
    public void add(int index, T element) {
        expandArraySizeIfNeeded();

        throwIfIndexInvalid(index);

        if (index == 0) {
            add(element);
        } else if (index == nextAvailableSlot) {
            add(element);
        } else {

            for (int i = nextAvailableSlot; i > index; i--) {
                buffer[i] = buffer[i - 1];
            }

            buffer[index] = element;
            nextAvailableSlot++;
        }
    }

    @Override
    public T set(int index, T element) {
        add(index, element);

        return element;
    }

    @Override
    public T get(int index) {
        throwIfIndexInvalid(index);

        return buffer[index];
    }

    @Override
    public int size() {
        return nextAvailableSlot;
    }

    @Override
    public T remove(int index) {

        Object match = null;

        throwIfIndexInvalid(index);

        for (int i = index; i < nextAvailableSlot; i++) {

            if (i == index) {
                match = buffer[i];
            }

            buffer[i] = buffer[i + 1];
        }

        nextAvailableSlot--;
        return (T) match;
    }

    @Override
    public boolean remove(T elem) {

        boolean matchFound = false;
        for (int i = 0; i < nextAvailableSlot && !matchFound; i++) {
            if (buffer[i].equals(elem)) {
                matchFound = true;
                for (int j = i; j < nextAvailableSlot; j++) {
                    buffer[j] = buffer[j + 1];
                }
                nextAvailableSlot--;
            }
        }

        return matchFound;
    }

    @Override
    public boolean isEmpty() {
        return nextAvailableSlot == 0;
    }

    @Override
    public boolean contains(T element) {
        boolean matchFound = false;

        for (int index = 0; index < nextAvailableSlot && !matchFound; index++) {
            if (buffer[index].equals(element)) {
                return true;
            }
        }
        return matchFound;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericArrayListIterator();
    }

    @Override
    public String toString() {

        var stringToRetun = "";

        for (T t : this) {
            stringToRetun += " -> " + t;
        }
        return stringToRetun;
    }

    private void expandArraySizeIfNeeded() {
        if ((nextAvailableSlot == currentCapacity) || nextAvailableSlot == 0) {

            T[] newArray = (T[]) new Object[(buffer.length == 0 ? 3 : buffer.length) * 2];

            currentCapacity *= 2;

            System.arraycopy(newArray, 0, buffer, 0, buffer.length);

            buffer = newArray;
        }
    }

    private void throwIfIndexInvalid(int index) {
        if (index > buffer.length) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
    }

    class GenericArrayListIterator implements Iterator<T> {
        private int cursor = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return cursor < nextAvailableSlot;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (cursor == nextAvailableSlot) {
                throw new NoSuchElementException();
            }
            return buffer[cursor++];
        }

        @Override
        //You do not have to provide functionality for the remove() method
        //We already have (non-iterator) mechanism for removing elements
        public void remove() {
            throw new UnsupportedOperationException("not supported yet");
        }
    }
}
