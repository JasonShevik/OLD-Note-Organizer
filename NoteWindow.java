import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NoteWindow extends JFrame{
	private JTextArea area;
	private JScrollPane scroll;
	private JButton go;
	private String theNoteR;
	public NoteWindow(String message, String theNote){
		super("Note");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		theNoteR = theNote;
		
		if(Main.checkMethod == 1){
			area = new JTextArea(message,25,50);
			area.setEditable(false);
			area.setText(message);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;	
			add(area, c);
		}else if(Main.checkMethod == 2){
			area = new JTextArea(message,23,50);
			area.setEditable(true);
			area.setText(message);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;	
			add(area, c);
			
			go = new JButton("Done!");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;	
			add(go, c);
			EventHandler handler = new EventHandler();
			go.addActionListener(handler);
		}else{
			
		}
		scroll = new JScrollPane(area);
		add(scroll);		
	}//*/
	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==go){
				String oldData[] = Main.getInfo();
				String newData[] = new String[oldData.length];
				int e = 0;
				for(int i = 0;;i++){
					if(oldData[i] != null && oldData[i].equals(theNoteR)){
						for(;e<i+1;e++){  //add all previous data
							newData[e] = oldData[e];
						}//*/
						newData[e] = area.getText();
						e++;
						for(;e<oldData.length;e++){
							newData[e] = oldData[e];
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
					JOptionPane.showMessageDialog(null, "Note: \"" + theNoteR + "\" has been edited");
				}catch (Exception err){
					JOptionPane.showMessageDialog(null, "Error: " + err);
				}//*/
				
				closeWindow();
			}//*/
		}//*/
	}//*/
	private void closeWindow(){
		this.dispose();
	}
}//*/
