public class MyCustomArrayIndexOutOfBoundsException extends RuntimeException {
    public MyCustomArrayIndexOutOfBoundsException(int index, int size) {
        super(String.format("Invalid index %d for array with length %d", index, size));
    }
}
