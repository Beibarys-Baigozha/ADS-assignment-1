public class MyArrayList <T extends Comparable<T>> implements MyList<T> {


    private Object[] array;
    private int length = 0;
    private int capacity = 2;

    public MyArrayList() {
        array = new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object obj) {
        for (Object ob : array)
            if (ob == obj)
                return true;
        return false;
    }

    @Override
    public void add(T item) {
        if (length == capacity)
            increaseCapacity();
        array[length++] = item;
    }

    public void increaseCapacity() {
        capacity *= 2;
        Object[] buffer = new Object[capacity];
        System.arraycopy(array, 0, buffer, 0, array.length);
        array = buffer;
    }

    @Override
    public void add(T item, int index) {
        if(index < length){
            array[index] = item;
        }
        else if(index == length){
            this.add(item);
        }
        else{
            while (index >= capacity)
                increaseCapacity();
            Object[] buffer = new Object[capacity];
            System.arraycopy(array, 0, buffer, 0, length);

            for(int i=length; i<index; i++)
                buffer[length + i] = null;
            buffer[index] = item;

            length = index+1;
            array = buffer;
        }
    }

    @Override
    public boolean removeItem(T item) {
        int index = -1;

        for (int i = 0; i < length; i++) {
            if (array[i] == item) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T remove(int index) {
        Object[] result = new Object[length];
        Object obj = null;

        for (int i = 0, was = 0; i < length; i++) {
            if (i == index) {
                was = 1;
                obj = array[i];
                continue;
            }
            result[i - was] = array[i];
        }

        length --;
        array = result;
        return (T) obj;
    }

    @Override
    public void clear() {
        length = 0;
        capacity = 2;
        array = new Object[capacity];
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++)
            if (array[i] == obj)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        int lastIndex = -1;
        for (int i = 0; i < length; i++)
            if (array[i] == obj)
                lastIndex = i;
        return lastIndex;
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
}
