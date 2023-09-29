import java.util.Arrays;

public class CustomArrayList<E> {

    private int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object elementData[];


    public CustomArrayList(int INITIAL_CAPACITY) {
        this.INITIAL_CAPACITY = INITIAL_CAPACITY;
        elementData = new Object[INITIAL_CAPACITY];
    }

    public CustomArrayList() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    public void add(E element){
        if(size == elementData.length){
            ensureCapacity();
        }
        elementData[size++] = element;
    }

    public void ensureCapacity(){
        int newCapacity = INITIAL_CAPACITY * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void display(){
        System.out.print("[");
        for(int i =0;i<size ; i++){
            System.out.print(elementData[i]+" ");
        }
        System.out.print("]");
    }
}
