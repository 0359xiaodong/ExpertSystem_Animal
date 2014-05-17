package com.chaos.esa;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainGui extends JFrame {

	private JCheckBox[] checkBoxs = null; 

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878367215945318971L;
	private ArrayList<String> database = null; 

	public MainGui() {
		setLayout(new BorderLayout(9, 4));

		Panel optionPanel = new Panel(new GridLayout(4, 5, 4, 4));
		int length = ESARuleSet.basicWordPairs.length;  
		checkBoxs = new JCheckBox[length]; 
		for (int i = 0; i < length; i++) {
			checkBoxs[i] = new JCheckBox(ESARuleSet.basicWordPairs[i].word); 
			optionPanel.add(checkBoxs[i]);
		}

		add(optionPanel, BorderLayout.CENTER);
		
		JButton btn = new JButton("推理"); 
		add(btn, BorderLayout.SOUTH);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				database = new ArrayList<String>(); 
				for (int i = 0; i < checkBoxs.length; i++) {
					if (checkBoxs[i].isSelected()) {
						database.add(ESARuleSet.basicWordPairs[i].key); 
						System.out.print(ESARuleSet.basicWordPairs[i].word + "\t");
					}
				}
				System.out.println();
				System.out.println(database);
				String conclusion = ESARuleSet.ratiocinate(database);
//				String conclusion = ESARuleSet.ratiocinate("fly", "lay_eggs", "fly_well");
				System.out.println(conclusion);
				JOptionPane.showMessageDialog(null, ESARuleSet.database2String(database) + "\n=>" + conclusion, "推理的结果是", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ESARuleSet.init(); 
		MainGui frame = new MainGui();
		frame.setTitle("动物推理");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}

}
