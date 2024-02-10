package Coding;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomePage implements ActionListener
{
	JFrame frame = new JFrame();
	JButton btnEncode;
	JButton btnDecode;
	public HomePage() 
	{
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setTitle("HomePage");
		frame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().setLayout(null);
		frame.setSize(500, 369);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 13, 458, 294);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnEncode = new JButton("ENCODE");
		btnEncode.setFont(new Font("Consolas", Font.BOLD, 14));
		btnEncode.setBackground(Color.LIGHT_GRAY);
		btnEncode.setBounds(132, 86, 172, 41);
		btnEncode.addActionListener(this);
		panel.add(btnEncode);
		
		btnDecode = new JButton("DECODE");
		btnDecode.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDecode.setBackground(Color.LIGHT_GRAY);
		btnDecode.setBounds(132, 143, 172, 41);
		btnDecode.addActionListener(this);
		panel.add(btnDecode);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnEncode) 
		{
			EmbedImage embImg = new EmbedImage();
			frame.dispose();
		}else {
			DecodeImage decImg = new DecodeImage();
		}
	}
	public static void main(String[] args)
	{
		HomePage obj = new HomePage();
	}
	
}
/*
 * https://www.youtube.com/watch?v=ZIKCoF_H8g4
 * https://www.youtube.com/watch?v=_5ct3Il8rZc
 * https://www.youtube.com/watch?v=kiHtqHbPGwA
 * https://www.youtube.com/watch?v=SUak49mDMM0
 * 
 * */
