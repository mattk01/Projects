import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteController implements ActionListener {

    NoteView myView;
    DataAccess myDAO;

    public NoteController(NoteView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
        myView.btnSearch.addActionListener(this);
    }

    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadNoteAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveNote();
        }

        if (e.getSource() == myView.btnSearch) {      // button Load is clicked
            searchAndDisplayNotes();
        }

    }

    // Method to save a new or existing note
    private void saveNote() {
        NoteModel noteModel = new NoteModel();

        try {
            int id = Integer.parseInt(myView.txtID.getText());
            noteModel.id = id;
            noteModel.title = myView.txtTitle.getText();
            noteModel.body = myView.txtBody.getText();

            myDAO.saveNote(noteModel);
            JOptionPane.showMessageDialog(null, "Note saved successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for ID");
            ex.printStackTrace();
        }    }


    // Method to load existing note
    private void loadNoteAndDisplay() {
        try {
            int ID = Integer.parseInt(myView.txtID.getText());
            NoteModel noteModel = myDAO.loadNote(ID);

            if (noteModel == null)
                JOptionPane.showMessageDialog(null, "No existing note with this ID " + ID);
            else {

                myView.txtTitle.setText(noteModel.title);
                myView.txtBody.setText(noteModel.body);
            }

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for ID");
            ex.printStackTrace();
        }
    }

    // Method to search existing note
    private void searchAndDisplayNotes() {
            String result = myView.txtKeyword.getText();
            NoteListModel noteListModel = myDAO.searchNote(myView.getTitle());

            if (noteListModel == null)
                JOptionPane.showMessageDialog(null, "No existing note for the keyword: " + result);
            else {
                    myView.txtResults.setText(noteListModel.toString());
            }
        }
}
