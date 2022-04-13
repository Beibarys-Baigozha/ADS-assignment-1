public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.sort();

        for(int i=0; i<list.size(); i++){
            System.out.println(i + " " + list.get(i));
        }
    }
}
