public class MyArraylistDemo {
    public static void main(String[] args) {
        CustomArrayList<String> arrayList = new CustomArrayList();
        arrayList.add("Rahul");
        arrayList.add("Ganesh");
        arrayList.add("Bhashkar");
        arrayList.display();
        System.out.println("Element at index : 1 " +arrayList.get(1));
        arrayList.remove(1);
        System.out.println("Elements after removal");
        arrayList.display();
    }
}
