public class MyLinkedList <T extends Comparable<T>> implements MyList<T> {
    private static class MyNode<T>{
        T data;
        MyNode<T> next, prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private int length = 0;
    private MyNode<T> head, tail;

    public MyLinkedList() {

    }

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    public void add(T item, int index) {
        if (index<0 || index>length)
            throw new IndexOutOfBoundsException("Your index should be positive");
        MyNode<T> temp = head;
        for(int i=0; i<index; i++){
            temp = temp.next;
        }
        MyNode<T> newNode = new MyNode<>(item);

        if (index==0) {
            head=newNode;
            newNode.next=temp;
            temp.prev=newNode;
            length++;
        }
        else if (index==length) {
            this.add(item);
        }
        else {
            MyNode<T> prevNode = temp.prev;
            newNode.prev = temp.prev;
            prevNode.next = newNode;
            newNode.next=temp;
            length++;
        }
    }

    @Override
    public boolean removeItem(T item) {
        for(MyNode<T> node=head; node!=null; node=node.next) {
            if (node.data == item) {
                MyNode<T> nextNode = node.next;
                MyNode<T> prevNode = node.prev;
                if(prevNode == null)
                    head = nextNode;
                else if(nextNode == null)
                    tail = node.prev;
                else {
                    prevNode.next = nextNode;
                    nextNode.prev = prevNode;
                }
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        MyNode<T> temp = head;
        for(int i=0; i<index; i++){
            temp = temp.next;
        }
        T res = temp.data;
        MyNode<T> nextNode = temp.next;
        MyNode<T> prevNode = temp.prev;
        if(prevNode == null)
            head = nextNode;
        else if(nextNode == null)
            tail = temp.prev;
        else {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        length--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("Your index should be positive and less than size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp.data;
    }

    @Override
    public int indexOf(Object obj) {
        int i = 0;
        for(MyNode<T> node=head; node!=null; node=node.next, i++)
            if(node.data == obj)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        int result = -1;
        int i = 0;
        for(MyNode<T> node=head; node!=null; node=node.next, i++)
            if (node.data == obj)
                result = i;
        return result;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length - i - 1; j++){
                T a = this.get(j);
                T b = this.get(j + 1);
                if (first_greater(a, b)) {
                    this.add(b, j);
                    this.add(a,j+1);
                }
            }
        }
    }

    private boolean first_greater(T obj, T obj2){
        return obj.compareTo(obj2) > 0;
    }

    @Override
    public boolean contains(Object obj) {
        for(MyNode<T> i=head; i.next!=null; i=i.next)
            if(i.data == obj)
                return true;
        return false;
    }

    @Override
    public void clear(){
        length = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return length;
    }
}
