import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem help;
	
	private JRadioButton edit, addNote, newCat, newSubCat, select, deleteNote;
	
	private JButton go;
	
	private JTextArea left;
	private JScrollPane scroll;
	
	private JTextField field;
	
	private String spaces = "";
	private String stuffToAdd = "";
	private int numberNotes = 0;
	public GUI(){
		super("Freak's Note Organizer");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
/*--------------------------------------------------------------
 * 		Menu Bar
 */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);
		help = new JMenuItem("Help!");
		file.add(help);
		
/*--------------------------------------------------------------
 * 		Elements of the GUI
 */
		//the stuff in the display field
		/* commands for client:
		 *  [category] (next line is name of category)
		 *  [subcategory] (next line is how many tabs to add, line after is name) --- used to know how many spaces to add
		 *  [note] (next line is name, line after is data)
		 */
		left = new JTextArea(12,30);
		left.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;	
		c.gridwidth=1;
		add(left, c);
		scroll = new JScrollPane(left);
		add(scroll);
		update();
		
		//other stuff---------------------------------------
		edit = new JRadioButton("Edit an existing note", true);
		addNote = new JRadioButton("Add a new note");
		newCat = new JRadioButton("Add a new category");
		newSubCat = new JRadioButton("Add a subcategory to an existing (sub)category");
		select = new JRadioButton("Select a note to view");
		deleteNote = new JRadioButton("Delete a note");
		JRadioButton array[] = {edit, addNote, newCat, newSubCat, select, deleteNote};
		ButtonGroup group = new ButtonGroup();
		for(int i = 0; i<array.length; i++){
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i+1;
			c.gridwidth=1;
			add(array[i], c);
			group.add(array[i]);
		}//*/
		go = new JButton("Go!");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;	
		c.gridwidth=1;
		add(go, c);
		
		field = new JTextField();
		field.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;	
		c.gridwidth=1;
		add(field, c);
		
/*--------------------------------------------------------------
 *		Events
 */
		EventHandler handler = new EventHandler();
		help.addActionListener(handler);
		go.addActionListener(handler);
	}//*/
	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==help){
				JOptionPane.showMessageDialog(null, "Either select the radiobutton for what you want to do:" +
													"\nIf you choose edit, then enter the name of the note you wish to edit" +
													"\nIf you choose select, then enter the name of the note you wish to view" +
													"\nIf you choose new note, then enter the subcategory you wish to add a note to" +
													"\nIf you choose new category, then just enter what you wish to name the category" +
													"\nIf you choose new subcategory, then just enter the name of the (sub)category you wish to add a subcategory to" +
													"\nIf you choose delete note, then just enter the name of the note you wish to delete" +
													"\nThen for all above options, just follow the instructions that follow!" +
													"\n(Warning: Notes have a maximum length of 1024 characters)" +
													"\n(Warning: When editing notes, do not add multiple lines, it will ignore all but the first and the program will error upon startup)" +
													"\nNote: notes can only be placed into subcategories");
			}else if(event.getSource()==go){
				if(edit.isSelected()){
					Main.checkMethod = 2;
					Main.edit(field.getText());
				}else if(addNote.isSelected()){
					Main.addNote(field.getText());
					update();
				}else if(newCat.isSelected()){
					Main.newCat(field.getText());
					update();
				}else if(newSubCat.isSelected()){
					Main.newSubCat(field.getText());
					update();
				}else if(select.isSelected()){
					Main.checkMethod = 1;
					Main.select(field.getText());
				}else if(deleteNote.isSelected()){
					Main.delete(field.getText());
					update();
				}//*/
			}//*/
		}//*/
	}//*/
	private void update(){
		stuffToAdd = "";
		String getInfoArr[] = Main.getInfo();
		for(int i = 0; i < getInfoArr.length -1;i++){
			if(getInfoArr[i].equals("[category]")){
				numberNotes = 0;
				i++;
				stuffToAdd += getInfoArr[i] + "\n";
			}else if(getInfoArr[i].equals("[subcategory]")){
				numberNotes = 0;
				i++;
				spaces = "";
				for(int e = 1; e <= Integer.parseInt(getInfoArr[i]); e++){
					spaces += "          ";
				}//*/
				i++;
				stuffToAdd += spaces + getInfoArr[i] + "\n";
			}else if(getInfoArr[i].equals("[note]")){
				if(numberNotes == 0){
					numberNotes++;
					spaces += "          ";
				}
				i++;
				stuffToAdd += spaces + getInfoArr[i] + "\n";
				i++;
			}else{
				JOptionPane.showMessageDialog(null, "There was an invalid line inside of Notes.txt\n" +
						"If you reach this then the only way to fix it is to\n" +
						"go inside of Notes.txt and change it from there\n" +
						"(do not attempt if you do not understand its organization)");
			}//*/
		}//*/
		left.setText(stuffToAdd);
	}//*/
}//*/
