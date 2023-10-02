import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class CustomArrayList<E> {

    private int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData;
    private int modCount = 0;


    public CustomArrayList(int INITIAL_CAPACITY) {
        this.INITIAL_CAPACITY = INITIAL_CAPACITY;
        elementData = new Object[INITIAL_CAPACITY];
    }

    public CustomArrayList() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    public void add(E element) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size++] = element;
        modCount++;
    }

    public void ensureCapacity() {
        int newCapacity = INITIAL_CAPACITY * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object removed = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        modCount++;
        return removed;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) elementData[index];
    }

    public MyIterator<E> iterator() {
        return new CustomIterator<>();
    }

    public int size() {
        return size;
    }

    class CustomIterator<E> implements MyIterator<E> {
        int cursor;       // index of next element to return
        int lastReturnedElement = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForCoModification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            // taking current state of arraylist this because there might be a case
            // when other thread can manipulate arraylist from outside at the same time
            Object[] elementData = CustomArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            lastReturnedElement = i;
            return (E) elementData[lastReturnedElement];
        }

        @Override
        public void remove() {
            // check if remove is called before next() call since it starts form -1 < 0
            if (lastReturnedElement < 0)
                throw new IllegalStateException();
            checkForCoModification();
            try {
                CustomArrayList.this.remove(lastReturnedElement);
                cursor = lastReturnedElement;
                // assigning -1 again so that we can restrict parallel remove,
                // and it should not be allowed
                lastReturnedElement = -1;
                expectedModCount = modCount;

            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public final void checkForCoModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
