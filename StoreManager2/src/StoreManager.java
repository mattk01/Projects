/*
 The StoreManager class is a class responsible
 for managing the components of the note application
 */
public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private NoteView noteView = null;

    public NoteView getNoteView() {
        return noteView;
    }

    private NoteController noteController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();
        noteView = new NoteView();
        noteController = new NoteController(noteView, dao);
    }

}
