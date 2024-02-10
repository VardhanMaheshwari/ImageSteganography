package Coding;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class EmbedImage implements ActionListener
{
	private JFrame EmbedImageFrame = new JFrame();
	private JLabel lblEmbImage;
	private JLabel lblSrcImage;
	private JButton btnOpen, btnSave, btnReset, btnEmbed;
	private JTextArea jTextMessage;
	BufferedImage sourceImage=null, embededImage=null;
	public EmbedImage() 
	{
		EmbedImageFrame.setResizable(false);
		EmbedImageFrame.setTitle("EmbeddingWindow");
		EmbedImageFrame.setFont(new Font("Consolas", Font.PLAIN, 14));
		EmbedImageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EmbedImageFrame.getContentPane().setLayout(null);
		EmbedImageFrame.setSize(758,526);
		EmbedImageFrame.setLocationRelativeTo(null);
		
		JPanel panelSecT = new JPanel();
		panelSecT.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Secret Message", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelSecT.setBounds(12, 0, 726, 203);
		EmbedImageFrame.getContentPane().add(panelSecT);
		panelSecT.setLayout(null);
		
		jTextMessage = new JTextArea();
		jTextMessage.setBounds(5, 18, 715, 180);
		panelSecT.add(jTextMessage);
		
		JPanel panelSrc = new JPanel();
		panelSrc.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Source Image", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSrc.setBounds(12, 206, 358, 203);
		EmbedImageFrame.getContentPane().add(panelSrc);
		panelSrc.setLayout(null);
		
		lblSrcImage = new JLabel("");
		lblSrcImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblSrcImage.setBounds(9, 18, 340, 177);
		panelSrc.add(lblSrcImage);
		
		JPanel panelEmb = new JPanel();
		panelEmb.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Embedded Image", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEmb.setBounds(380, 206, 358, 203);
		EmbedImageFrame.getContentPane().add(panelEmb);
		panelEmb.setLayout(null);
		
		lblEmbImage = new JLabel("");
		lblEmbImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmbImage.setBounds(9, 18, 340, 177);
		panelEmb.add(lblEmbImage);
		
		btnOpen = new JButton("Open");
		btnOpen.setFont(new Font("Consolas", Font.BOLD, 14));
		btnOpen.setBounds(101, 422, 115, 42);
		btnOpen.addActionListener(this);
		EmbedImageFrame.getContentPane().add(btnOpen);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Consolas", Font.BOLD, 14));
		btnReset.setBounds(395, 422, 115, 42);
		btnReset.addActionListener(this);
		EmbedImageFrame.getContentPane().add(btnReset);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Consolas", Font.BOLD, 14));
		btnSave.setBounds(530, 422, 115, 42);
		btnSave.addActionListener(this);
		EmbedImageFrame.getContentPane().add(btnSave);
		
		btnEmbed = new JButton("Embed");
		btnEmbed.setFont(new Font("Consolas", Font.BOLD, 14));
		btnEmbed.setBounds(238, 422, 115, 42);
		btnEmbed.addActionListener(this);
		EmbedImageFrame.getContentPane().add(btnEmbed);
		
		EmbedImageFrame.setVisible(true);
	}
	private File showFileDialog(final boolean open) throws Exception
	{
		JFileChooser fileChooser = new JFileChooser();
		FileFilter ff = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
				return pathname.isDirectory() || pathname.toString().endsWith(".jpeg") || pathname.toString().endsWith(".jpg") 
						|| pathname.toString().endsWith(".png") || pathname.toString().endsWith(".bmp");
			}
			public String getDescription() {
				return "Image (*.jpeg, *.jpg, *.png, *.bmp)";
			}	
		};
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter((javax.swing.filechooser.FileFilter) ff);
		fileChooser.showOpenDialog(null);
		File file = null;
		file = fileChooser.getSelectedFile();
		return file;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnOpen) {
			try {
				jBtnOpenActionPerformed(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==btnEmbed) {
			jBtnEmbedActionPerformed(e);
		}
		else if(e.getSource()==btnReset) {
			jBtnResetActionPerformed(e);
		}
		else if(e.getSource()==btnSave) {
			jBtnSaveActionPerformed(e);
		}
	}
	private void jBtnOpenActionPerformed(ActionEvent e) throws Exception
	{
		/*
		 * javax.swing.filechooser.FileFilter ff = new javax.swing.filechooser.FileFilter() {
		 
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory() || pathname.toString().endsWith(".jpeg") || pathname.toString().endsWith(".jpg") 
						|| pathname.toString().endsWith(".png") || pathname.toString().endsWith(".bmp"))
					return true;
			
				return false;
			}
			public String getDescription() {
				return "Image (*.jpeg, *.jpg, *.png, *.bmp)";
			}
		};	
		try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(ff);
				fileChooser.showOpenDialog(null);
				if(fileChooser.getSelectedFile()==null) {
					return;
				}else {*/
					/*ImageIcon selectedImageIcon = new ImageIcon(fileChooser.getSelectedFile().toString());
					Image selectedImage = selectedImageIcon.getImage().getScaledInstance(318, 265, Image.SCALE_SMOOTH);
					lblSrcImage.setIcon(new ImageIcon(selectedImage));*/
					
		/*sourceImage=ImageIO.read(fileChooser.getSelectedFile());
					lblSrcImage.setIcon(new ImageIcon(sourceImage));
					//embedimageframe.validate();
				}
		}catch(Exception e1) {e1.printStackTrace();};
		*/
		File file = showFileDialog(true);
		sourceImage=ImageIO.read(file);
		lblSrcImage.setIcon(new ImageIcon(sourceImage));
	}
	private void jBtnEmbedActionPerformed(java.awt.event.ActionEvent evt) 
	{
		if(jTextMessage.getText().equals("") || sourceImage == null )
		{
			JOptionPane.showMessageDialog(EmbedImageFrame, "No Message has been embedded", "No Images has been found",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String Message = jTextMessage.getText();
		embededImage = sourceImage.getSubimage(0, 0, sourceImage.getWidth(), sourceImage.getHeight());
		embededMessage(embededImage, Message);
		////////////////////////////////////////lblEmbImage.setIcon(new ImageIcon(embededImage));
		//embedimageframe.validate();
	}
	private void embededMessage(BufferedImage img, String mesg) {
		int mesg_length = mesg.length();
		int imageWidth = img.getWidth();
		int imageHeight = img.getHeight();
		int imageSize = imageWidth * imageHeight;
		if(mesg_length*8+3 > imageSize) {
			JOptionPane.showMessageDialog(EmbedImageFrame, "Message is too long for this image", "Message too long", JOptionPane.ERROR_MESSAGE);
			return;
		}else {
				embededInteger(img, mesg_length, 0, 0);
				byte b[] = mesg.getBytes();
				for(int i=0; i<b.length; ++i)
				{
					embedByte(img, b[i], i*8+32, 0);
				}
				lblEmbImage.setIcon(new ImageIcon(embededImage));
		}
	}
	private void embededInteger(BufferedImage img, int n, int start, int storageBit) {
		int maxX = img.getWidth();
		int maxY = img.getHeight();
		int startX = start/maxY;
		int startY = start - startX*maxY;
		int count = 0;
		for (int i = startX; i<maxX && count<32; ++i){
			for (int j = startY; j<maxY && count<32; ++j){
				int rgb = img.getRGB(i, j);
				int bitVal = getBitValue(n, count);
				rgb = setBitValue(rgb, storageBit, bitVal);
				img.setRGB(i, j, rgb);
				count++;
			}
		}
	}
	private void embedByte(BufferedImage img, byte b, int start, int storageBit) {
		int maxX = img.getWidth();
		int maxY = img.getHeight();
		int startX = start/maxY;
		int startY = start - startX*maxY;
		int count = 0;
		for (int i = startX; i<maxX && count<8; ++i){
			for (int j = startY; j<maxY && count<8; ++j){
				int rgb = img.getRGB(i, j);
				int bitVal = getBitValue(b, count);
				rgb = setBitValue(rgb, storageBit, bitVal);
				img.setRGB(i, j, rgb);
				count++;
			}
		}
	}
	private int getBitValue(int n, int location) {
		int v = (int) (n & Math.round(Math.pow(2, location)));
		return v==0?0:1;
	}
	private int setBitValue(int n, int location, int bitVal) {
		int toggle = (int) Math.pow(2, location);
		int bv = getBitValue(n, location);
		if(bv == bitVal)
			return n;
		if(bv == 0 && bitVal == 1) 
			n |= toggle;
		else if(bv == 1 && bitVal == 0)
			n ^= toggle;
		
		return n;
	}
	
	private void jBtnResetActionPerformed(java.awt.event.ActionEvent evt) 
	{
		 jTextMessage.setText("");
		 sourceImage=null;
		 embededImage=null;
		 lblSrcImage.setIcon(null);
		 lblEmbImage.setIcon(null);
	}
	private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) 
	{
		if(embededImage==null) {
			JOptionPane.showMessageDialog(EmbedImageFrame, "No Image to save", "No message has been embeded", JOptionPane.ERROR_MESSAGE);
		}else {
				try {
						File file = showFileDialog(false);
						if(file==null)
							return;
						String name = file.getName();
						String extension = name.substring(name.lastIndexOf(".")+1).toLowerCase();
						
						if(!extension.equals("png") && !extension.equals("jpeg")) {
							extension="png";
							file = new File(file.getAbsolutePath()+".png");
						}
						if(file.exists())
							file.delete();
						ImageIO.write(embededImage,extension.toUpperCase(),file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*try {
					ImageIO.write(embededImage, "jpeg", new File("output_image.jpeg"));
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		}
	}
	
}
