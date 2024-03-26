package ui;

import model.Artist;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    private Playlist playlist;
    private JPanel buttonPane;

    private static final String addSong = "Add song";
    private static final String removeSong = "Remove song";
    private static final String savePlaylist = "Save playlist";
    private static final String loadPlaylist = "Load playlist";
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField songName;
    private ImageIcon image;

    private static final String JSON_STORE = "./data/playlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Represents the Graphical User Interface portion of the application
    public GUI() {
        super(new BorderLayout());

        initialize();

        songName = new JTextField(10);
        initializeButtons();

        buttonPane = new JPanel();
        initializePanel();

        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);

        JPanel imagePane = new JPanel();
        add(imagePane, BorderLayout.BEFORE_FIRST_LINE);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates listModel and playlist to be used in GUI
    public void initialize() {
        listModel = new DefaultListModel();
        listModel.addElement("Brown Sugar");

        playlist = new Playlist("User playlist");
        playlist.addSong(new Song("Brown Sugar", 3, Artist.TheBeatles));
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

    }

    // MODIFIES: this
    // EFFECTS: sets up GUI panel with buttonPane and image
    public void initializePanel() {
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(addButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(songName);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        image = new ImageIcon("./data/abbeyRoad.jpg");
        JLabel label = new JLabel(image);
        buttonPane.add(label);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates buttons to add and remove songs
    public void initializeButtons() {
        addButton = new JButton(addSong);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addSong);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);
        songName.addActionListener(addListener);
        songName.getDocument().addDocumentListener(addListener);

        removeButton = new JButton(removeSong);
        removeButton.setActionCommand(removeSong);
        removeButton.addActionListener(new RemoveListener());

        saveButton = new JButton(savePlaylist);
        saveButton.setActionCommand(savePlaylist);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadPlaylist);
        loadButton.setActionCommand(loadPlaylist);
        loadButton.addActionListener(new LoadListener());
    }

    // MODIFIES: this
    // EFFECTS: sets up ActionListener for removeSong button
    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            String songName = listModel.getElementAt(index).toString();
            playlist.removeSong(new Song(songName, 3, Artist.TheBeatles));

            int size = listModel.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                if (index == listModel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up ActionListener for addSong button
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = songName.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                songName.requestFocusInWindow();
                songName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(songName.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());
            playlist.addSong(new Song(songName.getText(), 3, Artist.TheBeatles));

            //Reset the text field.
            songName.requestFocusInWindow();
            songName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // MODIFIES: this
        // EFFECTS: returns true if song is already in list
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: if not currently enabled then enables button, otherwise does nothing
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: returns true and disables button if document length <= 0,
        // otherwise returns false
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up ActionListener for savePlaylist button
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(playlist);
                jsonWriter.close();
                System.out.println("Saved " + playlist.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException fnf) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up ActionListener for loadPlaylist button
    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                playlist = jsonReader.read();
                System.out.println("Loaded " + playlist.getName() + " from " + JSON_STORE);
                listModel.clear();
                //DefaultListModel newListModel = new DefaultListModel();
                for (Song s : playlist.getPlaylist()) {
                    listModel.addElement(s.getTitle());
                }
            } catch (IOException io) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("reference.ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: main method
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
