public class MyArraylistDemo {
    public static void main(String[] args) {
        CustomArrayList<String> arrayList = new CustomArrayList();
        arrayList.add("Rahul");
        arrayList.add("Ganesh");
        arrayList.add("Mukesh");
        System.out.println("ArrayList Before");

        System.out.println(arrayList);
        MyIterator<String> itr= arrayList.iterator();

        System.out.println("ArrayList After");
        System.out.println(arrayList);
    }
}
