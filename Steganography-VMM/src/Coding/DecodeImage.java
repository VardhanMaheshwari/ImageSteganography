package Coding;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
public class DecodeImage implements ActionListener {
	private JFrame DecodeImageFrame = new JFrame();
	private JLabel lblEmbededImage;
	private JButton btnDOpen;
	private JButton btnDecrypt;
	private JButton btnDReset;
	private JTextArea jDTextMessage;
	private BufferedImage DEmbeddedImage=null;
	public DecodeImage()
	{
		DecodeImageFrame.setTitle("DecodeFrame");
		DecodeImageFrame.getContentPane().setLayout(null);
		DecodeImageFrame.setSize(767, 465);
		JPanel panelSecT2 = new JPanel();
		panelSecT2.setLayout(null);
		panelSecT2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Decoded Message", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSecT2.setBounds(12, 0, 726, 203);
		DecodeImageFrame.getContentPane().add(panelSecT2);
		
		jDTextMessage = new JTextArea();
		jDTextMessage.setBounds(5, 18, 715, 180);
		panelSecT2.add(jDTextMessage);
		
		JPanel panelEmbededImage = new JPanel();
		panelEmbededImage.setLayout(null);
		panelEmbededImage.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Embedded Image", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEmbededImage.setBounds(12, 204, 379, 203);
		DecodeImageFrame.getContentPane().add(panelEmbededImage);
		
		lblEmbededImage = new JLabel("");
		lblEmbededImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmbededImage.setBounds(9, 18, 360, 177);
		panelEmbededImage.add(lblEmbededImage);
		
		btnDOpen = new JButton("Open");
		btnDOpen.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDOpen.setBounds(416, 216, 115, 42);
		btnDOpen.addActionListener(this);
		DecodeImageFrame.getContentPane().add(btnDOpen);
		
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDecrypt.setBounds(416, 284, 115, 42);
		btnDecrypt.addActionListener(this);
		DecodeImageFrame.getContentPane().add(btnDecrypt);
		
		btnDReset = new JButton("Reset");
		btnDReset.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDReset.setBounds(416, 352, 115, 42);
		btnDReset.addActionListener(this);
		DecodeImageFrame.getContentPane().add(btnDReset);
		
		DecodeImageFrame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnDOpen) {
			try {
				jBtnDOpenActionPerformed(e);
			} catch (Exception e1) {}
		}
		else if(e.getSource()==btnDecrypt) {
			try {
				jBtnDecryptActionPerformed(e);
			} catch (Exception e1) {}
		}
		else if(e.getSource()==btnDReset) {
			jDTextMessage.setText("");
			DEmbeddedImage=null;
			lblEmbededImage=null;
		}
		
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
	private void jBtnDOpenActionPerformed(ActionEvent e) throws Exception
	{
		File file = showFileDialog(true);
		if(file==null)
			return;
		DEmbeddedImage=ImageIO.read(file);
		lblEmbededImage.setIcon(new ImageIcon(DEmbeddedImage));
	}
	private void jBtnDecryptActionPerformed(ActionEvent e) throws Exception
	{
		if(DEmbeddedImage == null) {
			JOptionPane.showMessageDialog(DecodeImageFrame, "No Image has been Selected", "No Images has been found",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
				int len = extractInteger(DEmbeddedImage, 0, 0);
				byte b[] = new byte[len];
				for(int i=0; i<len; ++i) {
					b[i]=extractByte(DEmbeddedImage, i*8+32, 0);
				}
				jDTextMessage.setText(new String(b));
		}
	}
	private byte extractByte(BufferedImage img, int start, int storageBit ) 
	{
		int maxX = img.getWidth();
		int maxY = img.getHeight();
		int startX = start/maxY;
		int startY = start - startX*maxY;
		int count = 0;
		byte b=0;
		for (int i = startX; i<maxX && count<8; ++i){
			for (int j = startY; j<maxY && count<8; ++j){
				int rgb = img.getRGB(i, j);
				int bitVal = getBitValue(rgb, storageBit);
				b = (byte)setBitValue(b, count, bitVal);
				count++;
			}
		}
		return b;
	}
	private int extractInteger(BufferedImage img, int start, int storageBit) {
		int maxX = img.getWidth();
		int maxY = img.getHeight();
		int startX = start/maxY;
		int startY = start - startX*maxY;
		int count = 0, length=0;
		for (int i = startX; i<maxX && count<32; ++i){
			for (int j = startY; j<maxY && count<32; ++j){
				int rgb = img.getRGB(i, j);
				int bitVal = getBitValue(rgb, storageBit);
				length = setBitValue(length, count, bitVal);
				count++;
			}
		}
		return length;
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
}
