public class ArrayStack <T> implements StackADT <T>{
    private T[] array;
    private int top;

    public ArrayStack() {
        array = (T[]) new Object[10];
        top = 9;
    }

    public ArrayStack(int initCapacity) {
        array = (T[]) new Object[initCapacity];
        top = initCapacity -1;
    }

    public void push (T element) {
        if (top == -1) expandCapacity();
        array[top] = element;
        top --;
    }

    public T pop() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");
        top ++;
        T returnedItem = array[top];
        array[top] = null;
        return returnedItem;
    }

    public T peek() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");
        return array[top+1];
    }

    public boolean isEmpty() {
        return top == array.length -1;
    }

    public int size() {
        return getCapacity() - 1 - top;
    }

    public int getCapacity() {
        return array.length;
    }

    public int getTop() {
        return top;
    }

    public String toString() {
        if (isEmpty()) return "Empty stack. ";
        StringBuilder sb = new StringBuilder();
        for(int i = array.length-1; i>=0; i--) {
            if (array[i]!=null) sb.append(array[i] + ", ");
        }

        sb.setLength(sb.length()-2); // remove last two char
        return sb.toString();

    }

    private void expandCapacity() {

        if (array.length <= 15) {

            T[] newArray = (T[]) new Object[array.length*2];
            int newTop = newArray.length -1;
            for (int i = 0; i < size(); i++) {
                newArray[newArray.length -1-i] = array[array.length-1-i];
                newTop--;
            }
            array = newArray;
            top = newTop;

        } else {
            T[] newArray = (T[]) new Object[array.length + 10];
            int newTop = newArray.length -1;
            for (int i = 0; i < size(); i++) {
                newArray[newArray.length -1-i] = array[array.length-1-i];
                newTop--;
            }
            array = newArray;
            top = newTop;
        }
    }
}
