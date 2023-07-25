import java.sql.*;

// The SQLiteDataAdapter class implements the DataAccess interface to interact with a SQLite database
public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:notes.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            System.out.println(" conn = " + conn);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Notes");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to save a note into the SQLite database
    @Override
    public void saveNote(NoteModel note) {
        try {
            Statement stmt = this.conn.createStatement();
            if (loadNote(note.id) == null) {
                stmt.execute("INSERT INTO Notes(id, title, body) VALUES ("
                        + note.id + ","
                        + '\'' + note.title + '\'' + ","
                        + '\'' + note.body + '\'' + ")"
                );
            } else {
                stmt.executeUpdate("UPDATE Notes SET "
                        + "id = " + note.id + ","
                        + "title = " + '\'' + note.title + '\'' + ","
                        + "body = " + '\'' + note.body + '\''
                        + " WHERE id = " + note.id);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    // Method to load a note from the SQLite database given its ID
    @Override
    public NoteModel loadNote(int id) {
        NoteModel note = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Notes WHERE id = " + id);
            if (rs.next()) {
                note = new NoteModel();
                note.id = rs.getInt(1);
                note.title = rs.getString(2);
                note.body = rs.getString(3);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return note;
    }

    // Method to search for notes containing a keyword in their titles from the SQLite database
    @Override
    public NoteListModel searchNote(String keyword) {
        NoteModel note = null;
        NoteListModel listModel = new NoteListModel();

        try {
            Statement stmt = this.conn.createStatement();
            String sql = "SELECT * FROM Notes WHERE title LIKE '%" + keyword + "%'";
            System.out.println("Search SQL statement " + sql);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                note = new NoteModel();
                note.id = rs.getInt(1);
                note.title = rs.getString(2);
                note.body = rs.getString(3);
                listModel.list.add(note);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return listModel;
    }


}

