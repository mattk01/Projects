import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


/*
The RemoteDataAdapter class implements the DataAccess
interface to interact with a remote server for data operations
 */
public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    // Establishes connection to remote server
    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Searches for a note on remote server
    @Override
    public NoteListModel searchNote(String keyword) {
        Socket s = null;
        NoteListModel list = null;
        try {
            s = new Socket("localhost", 5056);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            RequestModel req = new RequestModel();
            req.code = RequestModel.FIND_REQUEST;
            req.body = keyword;

            // Convert the request object to JSON and send it to the server
            String json = gson.toJson(req);
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            // Parse the server response into a ResponseModel object
            ResponseModel res = gson.fromJson(received, ResponseModel.class);
            list = gson.fromJson(res.body, NoteListModel.class);

            System.out.println("Receiving a NoteListModel object of size = " + list.list.size());


            dis.close();
            dos.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Saves notes on remote servers
    @Override
    public void saveNote(NoteModel note) {
        RequestModel req = new RequestModel();

        req.code = RequestModel.SAVE_NOTE_REQUEST; // id of the request
        req.body = gson.toJson(note); //

        String json = gson.toJson(req);
        System.out.println("\nSaveNote Printout" + json);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            }
            else         // this is a JSON string for a note information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that ID!");
                }
                else {
                    //Not updated yet for saveNote method
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Receiving a NoteModel object");
                    System.out.println("ID = " + model.id);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note body = " + model.body);
                }



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to load a note with the ID from the remote server
    @Override
    public NoteModel loadNote(int ID) {
        RequestModel req = new RequestModel();
        req.code = req.LOAD_NOTE_REQUEST;
        req.body = String.valueOf(ID);

        String json = gson.toJson(req);
        System.out.println(json);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a note information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that ID!");
                    return null;
                }
                else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Receiving a NoteModel object");
                    System.out.println("ID = " + model.id);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note body = " + model.body);
                    return model; // found this note and return
                }



        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
