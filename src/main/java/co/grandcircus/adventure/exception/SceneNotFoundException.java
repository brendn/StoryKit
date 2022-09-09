package co.grandcircus.adventure.exception;

public class SceneNotFoundException extends RuntimeException {

    public static long serialVersionUID = 1L;

    public SceneNotFoundException(String s) {
        super(s);
    }
}
