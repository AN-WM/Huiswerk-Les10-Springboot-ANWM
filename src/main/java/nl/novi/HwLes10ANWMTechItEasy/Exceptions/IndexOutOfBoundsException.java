package nl.novi.HwLes10ANWMTechItEasy.Exceptions;

public class IndexOutOfBoundsException extends RuntimeException {
    public IndexOutOfBoundsException() {
        super("This index is out of bounds");
    }

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
