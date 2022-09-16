package co.grandcircus.adventure.exception;

public class StoryNotFoundException extends RuntimeException {

    public static long serialVersionUID = 1L;

    public StoryNotFoundException(String s) {
        super(s);
    }

    public StoryNotFoundException() {
        super("Story not found!");
    }
}
