import javax.swing.*;
import java.awt.*;

// This class represents the GUI view for the Note application
public class NoteView extends JFrame {

    // Test fields for displaying and inputting note data
    public JTextField txtID = new JTextField(30);
    public JTextField txtTitle = new JTextField(30);
    public JTextField txtBody = new JTextField(30);
    public JTextField txtKeyword = new JTextField(30);
    public JTextField txtResults = new JTextField( 30);

    // Interactive User buttons
    public JButton btnLoad = new JButton("Load");
    public JButton btnSave = new JButton("Save");
    public JButton btnSearch = new JButton("Search");

    public NoteView() {

        this.setTitle("Note View");
        this.setSize(new Dimension(600, 600));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("ID"));
        line1.add(txtID);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Title"));
        line2.add(txtTitle);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Body"));
        line3.add(txtBody);
        this.getContentPane().add(line3);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);

        this.getContentPane().add(buttonPanel);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Keyword"));
        line4.add(txtKeyword);
        this.getContentPane().add(line4);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(btnSearch);

        this.getContentPane().add(buttonPanel2);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Results"));
        line5.add(txtResults);
        this.getContentPane().add(line5);

    }



}
