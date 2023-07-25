// The RequestModel class represents a request to be sent to the server
public class RequestModel {

    // Codes for different requests
    static public int EXIT_REQUEST = 0;
    static public int LOAD_NOTE_REQUEST = 3;
    static public int SAVE_NOTE_REQUEST = 33;
    static public int FIND_REQUEST = 3;

    // Request fields
    public int code;
    public String body;
    public NoteModel noteModel;
}
