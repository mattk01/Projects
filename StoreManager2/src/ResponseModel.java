// The ResponseModel class represents a response received from the server
public class ResponseModel {

    // Response Codes
    static public int OK = 0;
    static public int UNKNOWN_REQUEST = 1;
    static public int DATA_NOT_FOUND = 2;

    // Response fields
    public int code;
    public String body;
}

