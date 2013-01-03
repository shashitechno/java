import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


import javax.swing.JMenuBar;

import javax.swing.JMenu;



import javax.swing.JButton;

import javax.swing.JTable;

import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;


import java.io.File;


public class Index extends MouseAdapter implements ActionListener {
	static int count=0;
	private JFrame frame;
	
	private JTextField textField;
	public JButton btnExtract,btnUp,btnCopy,btnDelete,btnZip,btnInfo,btnTest,btnMove;
	public JPanel panel;
	DefaultTableModel dtm;
	public  JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static float getFolderSize(File dir) {
	    int size = 0;
	    for (File file : dir.listFiles()) {
	        if (file.isFile()) {
	         
	            size += file.length();
	            break;
	        }
	        else
	            size += getFolderSize(file);
	    }
	    return (size/1024);
	}

	/**
	 * Create the application.
	 */
	public Index() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setTitle("7-Zip By ShashiTechno");
		ImageIcon image=new ImageIcon("images\\logo.png");
		frame.setIconImage(image.getImage());
		frame.setBounds(100, 100, 889, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 btnZip = new JButton();
		btnZip.setToolTipText("Add The Folder");
		ImageIcon ic1=new ImageIcon("images\\add.png");
		btnZip.setIcon(ic1);
		btnZip.setBounds(0, 0, 89, 44);
		frame.getContentPane().add(btnZip);
		
		 btnExtract = new JButton();
		 btnExtract.setToolTipText("Extract The Folder");
		 ImageIcon ic2=new ImageIcon("images\\minus.png");
		 btnExtract.setIcon(ic2);
		btnExtract.setBounds(104, 0, 89, 44);
		frame.getContentPane().add(btnExtract);
		
		 btnTest = new JButton();
		btnTest.setToolTipText("Test");
		 ImageIcon ic3=new ImageIcon("images\\test.png");
		 btnTest.setIcon(ic3);
		btnTest.setBounds(214, 0, 89, 44);
		frame.getContentPane().add(btnTest);
		
		btnCopy = new JButton();
		btnCopy.setToolTipText("Copy");
		 ImageIcon ic4=new ImageIcon("images\\copy.png");
		 btnCopy.setIcon(ic4);
		btnCopy.setBounds(320, 0, 89, 44);
		frame.getContentPane().add(btnCopy);
		
		 btnMove = new JButton();
		btnMove.setToolTipText("Move");
		ImageIcon ic5=new ImageIcon("images\\move.png");
		 btnMove.setIcon(ic5);
		btnMove.setBounds(422, 0, 89, 44);
		frame.getContentPane().add(btnMove);
		
	     btnDelete = new JButton();
	     btnDelete.setToolTipText("Delete");
		ImageIcon ic7=new ImageIcon("images\\delete.png");
		 btnDelete.setIcon(ic7);
		btnDelete.setBounds(522, 0, 89, 44);
		frame.getContentPane().add(btnDelete);
		
		 btnInfo = new JButton();
		btnInfo.setToolTipText("Info");
		ImageIcon ic6=new ImageIcon("images\\alert.gif");
		 btnInfo.setIcon(ic6);
		btnInfo.setBounds(619, 0, 89, 44);
		frame.getContentPane().add(btnInfo);
	     btnUp = new JButton();
		btnUp.setBounds(0, 55, 79, 23);
		ImageIcon ic=new ImageIcon("images\\up.png");
		btnUp.setIcon(ic);
		frame.getContentPane().add(btnUp);
		
		textField = new JTextField("E:\\new\\");
		textField.setBounds(82, 54, 666, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		 panel = new JPanel();
		panel.setBounds(-11, 79, 792, 988);
		frame.getContentPane().add(panel);
		
		btnExtract.addActionListener(this);
		btnCopy.addActionListener(this);
		btnUp.addActionListener(this);
		textField.addActionListener(this);
		btnDelete.addActionListener(this);
		btnZip.addActionListener(this);
		btnInfo.addActionListener(this);
		btnTest.addActionListener(this);
		btnMove.addActionListener(this);
		String s=textField.getText();
		int i=s.lastIndexOf("\\");
		int j=s.indexOf("\\");
		if(i==j)btnUp.setEnabled(false);
		
		
		
			
		 dtm = new getInfo().addContent(textField.getText(),dtm);
		 table = new JTable(dtm);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setPreferredScrollableViewportSize(new Dimension(730, 530));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    panel.add(scrollPane);
		frame.getContentPane().add(panel);
		table.addMouseListener(this);
		
	
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		
		JMenu View = new JMenu("View");
		menuBar.add(View);
		
		JMenu Favorites = new JMenu("Favorites");
		menuBar.add(Favorites);
		
		JMenu tools = new JMenu("tools");
		menuBar.add(tools);
		
		JMenu Help = new JMenu("Help");
		menuBar.add(Help);
		
	}
	@Override
    public void mouseClicked(MouseEvent e)
    {
		
		if(e.getClickCount()==2)
		{
			
        int row= table.getSelectedRow();
        table.clearSelection();
        
        String str[]=new getInfo().getContent(textField.getText());
        String s=str[row];
        if(str[row].indexOf(".")==-1)
        {
        textField.setText(textField.getText()+s+"\\");
        int i=textField.getText().lastIndexOf("\\");
		int j=textField.getText().indexOf("\\");
		if(i!=j)btnUp.setEnabled(true);
        DefaultTableModel dtmnew;
		dtmnew=new getInfo().addContent(textField.getText(),dtm);
		table.setModel(dtmnew);
		table.revalidate();
        }
		}	
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExtract)
		{
			String s=JOptionPane.showInputDialog("Please Enter The Output Folder Name");
			new Actions(). unzipMyZip(textField.getText(),textField.getText()+s);
			
		}
		else if(e.getSource()==textField)
		{
			
			DefaultTableModel dtmnew;
			dtmnew=new getInfo().addContent(textField.getText(),dtm);
			int i=textField.getText().lastIndexOf("\\");
			int j=textField.getText().indexOf("\\");
			if(i!=j)btnUp.setEnabled(true);
			table.setModel(dtmnew);
			table.revalidate();
			
		}
		else if(e.getSource()==btnUp)
		{
			String s=textField.getText();
			int i=s.lastIndexOf("\\");
			int j=s.indexOf("\\");
			if(i!=j)
			{
			String s1=textField.getText().substring(0,i);
			String pre=s1.substring(0,s1.lastIndexOf("\\")+1);
			textField.setText(pre);
			int p=textField.getText().lastIndexOf("\\");
			int q=textField.getText().indexOf("\\");
			if(p!=q)btnUp.setEnabled(true);
			else
				btnUp.setEnabled(false);
			DefaultTableModel dtmnew;
			dtmnew=new getInfo().addContent(textField.getText(),dtm);
			table.setModel(dtmnew);
			table.revalidate();
			}
			
		}
		else if(e.getSource()==btnCopy)
		{
			 int row= table.getSelectedRow();

		      
		        count++;
			if(row!=-1 || count==2)
			{
				
			
			 
		        String str[]=new getInfo().getContent(textField.getText());
		        if(row==-1)
		        {
		        	row=5;
		        }
		        String source=textField.getText()+str[row];
		     
		        ImageIcon im=new ImageIcon("images\\paste.png");
		       
		        btnCopy.setIcon(im);
		        
		        if(btnCopy.getIcon()==im && count==2)
		        {
		        	new Operations().CopyDirectory(source,textField.getText()+str[row]);
		        	JOptionPane.showMessageDialog(null,"Sucessfully Copied!!");
		        	count=0;
		        	btnCopy.setIcon(new ImageIcon("images\\copy.png"));
		        }
			}
			else
			{
				JOptionPane.showMessageDialog(null,"You Must Select AtLeast One Directory");
			}
		        
		}
		else if(e.getSource()==btnDelete)
		{
			
			int row= table.getSelectedRow();
			
			if(row!=-1)
			{
				
				int q=JOptionPane.showConfirmDialog(null, "Do You Really Want To Delete This?" );
				if(q==0)
				{	
				
				 
			        String str[]=new getInfo().getContent(textField.getText());
			        String source=textField.getText()+str[row];
				 new Operations().deleteContent(source);
				 DefaultTableModel dtmnew;
					dtmnew=new getInfo().addContent(textField.getText(),dtm);
					table.setModel(dtmnew);
					table.revalidate();
				 JOptionPane.showMessageDialog(null, "SucessFully Deleted!");
				 
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"You Must Select AtLeast One Directory");
			}
			
			
			
			
		}
		
		else if(e.getSource()==btnZip)
		{
                  int row= table.getSelectedRow();
			
			if(row!=-1)
			{
				
				String s=JOptionPane.showInputDialog("Please Give the Output Folder Name!" );
				
				if(s.equals(null) || s.equals(""))
				{
					int q=JOptionPane.showConfirmDialog(null,"Your Input Can't be Accepted!Zip Will be Generated With Same Name as Folder");
					if(q==0)
					{
						String str[]=new getInfo().getContent(textField.getText());
				        String source=textField.getText()+str[row];
						new Actions().zipFiles(source,textField.getText()+str[row]+".zip");
						DefaultTableModel dtmnew;
						dtmnew=new getInfo().addContent(textField.getText(),dtm);
						table.setModel(dtmnew);
						table.revalidate();
					}
				}
				else
				{
					String str[]=new getInfo().getContent(textField.getText());
			        String source=textField.getText()+str[row];
					new Actions().zipFiles(source,textField.getText()+s+".zip");
					DefaultTableModel dtmnew;
					dtmnew=new getInfo().addContent(textField.getText(),dtm);
					table.setModel(dtmnew);
					table.revalidate();
				}
				
		}
		else
	{
     	JOptionPane.showMessageDialog(null,"You Must Select Atleast One Directory to Make Compression!");		}
			
	}
		else if(e.getSource()==btnInfo)
		{
			JOptionPane.showMessageDialog(null,"Kaam Chal rha h :)");
		}
		else if(e.getSource()==btnMove)
		{
			
		}
		else if(e.getSource()==btnTest)
		{
		       int row= table.getSelectedRow();
				
	             if(row!=-1)
	                {
			            String str[]=new getInfo().getContent(textField.getText());
	                    String source=textField.getText()+str[row];
	                    if(source.indexOf("zip")!=-1)
	                    {
			          boolean t=  new Actions().isValid(new File(source));
         			          if(t)
		             	          {
			                    	  JOptionPane.showMessageDialog(null,"Archice Is Tested!! & Found Compatible");
			                      }
         			          else
         			             {
         			        	 JOptionPane.showMessageDialog(null,"Archice Is Tested!! & Found Corrupted");
         			             }
	                   }
	                    else
	                    {
	                    	 JOptionPane.showMessageDialog(null,"Selected File is Not an Archive!!!");
	                    }
	                }
	            else
	               {
		               JOptionPane.showMessageDialog(null,"You must Select Atleast one Archive to Test");
	               }
		}
	
	}}
