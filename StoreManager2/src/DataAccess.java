// This interface defines the necssary data access operations in the note application
public interface DataAccess {
    void connect();

    void saveNote(NoteModel note);

    NoteModel loadNote(int id);

    NoteListModel searchNote(String keyword);
}
