import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main{
	private static int lengthOfFile;
	public static int checkMethod;
	private static boolean lastType; //true is for [cateogry] false is for [subcategory]
	private static int lastSpaces;
	public static void main(String args[]){
		GUI myInterface = new GUI();  //interface object
		myInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program end when 'X' is pressed
		myInterface.setSize(530,450);            //set the size of the window to fit perfect
		myInterface.setVisible(true);  //make the window visible
	}//*/
	
/*--------------------------------------------------------------
 * 		Methods for what to do
 */
	public static void edit(String theNote){			//To edit existing note
		String oldData[] = getInfo();
		for(int i = 0;;i++){
			if(oldData[i] != null && oldData[i].equals(theNote)){
				NoteWindow view = new NoteWindow(oldData[i+1], theNote);
				view.setSize(600,500);
				view.setVisible(true);
				break;
			}else if(oldData[i] == null){
				JOptionPane.showMessageDialog(null, "Note: " + theNote + " does not exist...");
			}//*/
		}//*/
	}//*/
	
	public static void addNote(String category){		//To add a new note
		String noteName = JOptionPane.showInputDialog(null, "Enter the name of your note:");
		String oldData[] = getInfo();
		String theNoteData = JOptionPane.showInputDialog(null, "Type your note: (max length of 1024 characters)");
		if(theNoteData.length() <= 1024){
			String newData[] = new String[lengthOfFile+3]; //same as old length plus the 3 it takes to have a note
			int e = 0;
			for(int i = 0;;i++){
				if(oldData[i] != null && oldData[i].equals(category)){ //find the (sub)category to put the note in
					i++;
					for(;e<i;e++){  //add all previous data
						newData[e] = oldData[e];
					}//*/
					newData[e] = "[note]";
					newData[e+1] = noteName;
					newData[e+2] = theNoteData;
					for(;e<lengthOfFile;e++){
						newData[e+3] = oldData[e];
					}//*/
					break;
				}else if(oldData[i] == null){
					JOptionPane.showMessageDialog(null, "Category: " + category + " does not exist...");
				}//*/
			}//*/
			try{
				FileWriter wFileStream = new FileWriter("Notes.txt");
				BufferedWriter out = new BufferedWriter(wFileStream);
				for(int f = 0; f<newData.length; f++){
					out.write(newData[f] + "\n");
				}//*/
				out.close();
				JOptionPane.showMessageDialog(null, "Note: \"" + noteName + "\" was created");
			}catch (Exception err){
				JOptionPane.showMessageDialog(null, "Error: " + err);
			}//*/
		}//*/
	}//*/
	
	public static void newCat(String theCat){		//To add a new category
		String oldData[] = getInfo();
		String newData[] = new String[oldData.length+1];
		newData[0] = "[category]";
		newData[1] = theCat;
		for(int i = 2;i<=oldData.length;i++){
			newData[i] = oldData[i-2];
		}//*/
		try{
			FileWriter wFileStream = new FileWriter("Notes.txt");
			BufferedWriter out = new BufferedWriter(wFileStream);
			for(int f = 0; f<newData.length; f++){
				out.write(newData[f] + "\n");
			}//*/
			out.close();
			JOptionPane.showMessageDialog(null, "Category: \"" + theCat + "\" has been created");
		}catch (Exception err){
			JOptionPane.showMessageDialog(null, "Error: " + err);
		}//*/
	}//*/
	
	public static void newSubCat(String theSubCat){		//To add a new subcategory
		String oldData[] = getInfo();
		String newData[] = new String[oldData.length+2];
		String subCatName = JOptionPane.showInputDialog(null, "What would you like the subcategory to be called?");
		int e = 0;
		for(int i = 0;;i++){
			if(oldData[i] != null && oldData[i].equals(theSubCat)){ //find the (sub)category to put the note in
				i++;
				for(;e<i;e++){  //add all previous data
					if(oldData[e].equals("[category]")){
						lastType = true;
						lastSpaces = 1;
					}else if(oldData[e].equals("[subcategory]")){
						lastType = false;
						lastSpaces = Integer.parseInt(oldData[e+1])+1;
					}//*/
					newData[e] = oldData[e];
				}//*/
				if(e<lengthOfFile){
					for(;;e+=3){
						if(oldData[e].equals("[note]")){
							newData[e] = oldData[e];
							newData[e+1] = oldData[e+1];
							newData[e+2] = oldData[e+2];
						}else{
							newData[e] = "[subcategory]";
							if(lastType){
								newData[e+1] = "1";
							}else if(!lastType){
								newData[e+1] = Integer.toString(lastSpaces);
							}//*/
							break;
						}//*/
					}//*/
				}else{
					newData[e] = "[subcategory]";
					if(lastType){
						newData[e+1] = "1";
					}else if(!lastType){
						newData[e+1] = Integer.toString((Integer.parseInt(newData[e-2]))+1);
					}//*/
				}//*/
				newData[e+2] = subCatName;
				for(;e<lengthOfFile;e++){
					newData[e+3] = oldData[e];
				}//*/
				break;
			}//*/
		}//*/
		try{
			FileWriter wFileStream = new FileWriter("Notes.txt");
			BufferedWriter out = new BufferedWriter(wFileStream);
			for(int f = 0; f<newData.length; f++){
				out.write(newData[f] + "\n");
			}//*/
			out.close();
			JOptionPane.showMessageDialog(null, "Note: \"" + subCatName + "\" was created");
		}catch (Exception err){
			JOptionPane.showMessageDialog(null, "Error: " + err);
		}//*/
	}//*/
	
	public static void select(String theNote){		//To select an existing note
		String oldData[] = getInfo();
		for(int i = 0;;i++){
			if(oldData[i] != null && oldData[i].equals(theNote)){
				NoteWindow view = new NoteWindow(oldData[i+1], theNote);
				view.setSize(600,500);
				view.setVisible(true);
				break;
			}else if(oldData[i] == null){
				JOptionPane.showMessageDialog(null, "Note: " + theNote + " does not exist...");
			}//*/
		}//*/
	}//*/

	public static void delete(String theNote){		//To delete an existing note
		String oldData[] = getInfo();
		String newData[] = new String[oldData.length-4];
		int e = 0;
		for(int i = 0;;i++){
			if(oldData[i] != null && oldData[i].equals(theNote)){ //find the note to delete
				for(;e<i-1;e++){  //add all previous data
					newData[e] = oldData[e];
				}//*/
				for(;e<oldData.length-4;e++){
					newData[e] = oldData[e+3];
				}//*/
				break;
			}else if(oldData[i] == null){
				JOptionPane.showMessageDialog(null, "Note: " + theNote + " does not exist...");
			}//*/
		}//*/
		try{
			FileWriter wFileStream = new FileWriter("Notes.txt");
			BufferedWriter out = new BufferedWriter(wFileStream);
			for(int f = 0; f<newData.length; f++){
				out.write(newData[f] + "\n");
			}//*/
			out.close();
			JOptionPane.showMessageDialog(null, "Note: \"" + theNote + "\" has been deleted");
		}catch (Exception err){
			JOptionPane.showMessageDialog(null, "Error: " + err);
		}//*/
	}//*/
	
/*--------------------------------------------------------------
 * 		File handling
 */
	public static String[] getInfo(){
		try{
			BufferedReader in = new BufferedReader(new FileReader("Notes.txt"));
			BufferedReader in2 = new BufferedReader(new FileReader("Notes.txt"));
			int i = 0;
			String textData[];
			for(;in.readLine() != null;) {  //to find number of lines
				i++;
			}//*/
			lengthOfFile = i;
			textData = new String[i+1];
			for(int e = 0; e <= i; e++){	//to store the data in textData[]
				textData[e] = in2.readLine();
			}//*/
			in.close();
			in2.close();
			return textData;
		}catch (IOException e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
			return null;
		}//*/
	}//*/
}//*/
