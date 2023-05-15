package arpin.mobile.opengles.utils;

public class Logger {

    private static Logger instance = null;

    public static Logger getLogger()
    {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void exception(final String msg)
    {
        log("Exception: " + msg);
    }

    public void log(final String msg)
    {
        System.out.println(msg);
    }
}
