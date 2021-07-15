import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*; 
import java.io.*;
public class SingerGUI extends JFrame implements ActionListener, ItemListener
{
    private JLabel singerName;
    private JLabel singerAge;
    private JButton enterBtn;
    private JButton nextBtn;
    private JButton saveBtn;
    private JButton viewBtn;
    private JTextField nameEntry;
    private JTextField ageEntry;
    private JLabel singerQualities;
    private JCheckBox voiceCB;
    private JCheckBox originalityCB;
    private JCheckBox personalityCB;
    private JLabel genderRB;
    private JRadioButton maleRB;
    private JRadioButton femaleRB;
    private ButtonGroup bgGenders;
    private JLabel maleArea;
    private JLabel femaleArea;
    private JTextArea maleResults;
    private JTextArea femaleResults; 
    private JScrollPane maleScroll;
    private JScrollPane femaleScroll;
    private static ArrayList<Singer> maleSingers;
    private static ArrayList<Singer> femaleSingers; 
    private File fm;
    private File ff; 
    private JPanel first;
    private JPanel second;
    private JPanel group1;
    private JPanel group2;
    private JPanel group3;
    private JPanel group4;
    private JPanel group5;
    private String gender = "";
    private int points;
    
    public SingerGUI()
    {
        setTitle("All Out! Contestant Details Entry");
        
        singerName = new JLabel("Singer Name: ");
        singerAge = new JLabel("Singer Age: " );
        enterBtn = new JButton("Enter");
        nextBtn = new JButton("Next");
        saveBtn = new JButton("Save");
        viewBtn = new JButton("View");
        nameEntry = new JTextField(30);
        ageEntry = new JTextField(3);
        singerQualities = new JLabel("The singers has the...");
        voiceCB = new JCheckBox("Voice");
        originalityCB = new JCheckBox("Originality");
        personalityCB = new JCheckBox("Personality");
        genderRB = new JLabel("Gender of singer:");
        maleRB = new JRadioButton("Male");
        femaleRB = new JRadioButton("Female");
        bgGenders = new ButtonGroup();
        bgGenders.add(maleRB);
        bgGenders.add(femaleRB);
        maleArea = new JLabel("Male Contestants");
        femaleArea = new JLabel("Female Contestants");
        maleResults = new JTextArea(15,15);
        femaleResults = new JTextArea(15,15);
        maleScroll = new JScrollPane(maleResults,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        femaleScroll = new JScrollPane(femaleResults,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        maleSingers = new ArrayList<Singer>();
        femaleSingers = new ArrayList<Singer>();
        fm = new File("Male Contestants.txt");
        ff = new File("Female Contestants.txt");
        
        try
        {
            if(fm.isFile() && ff.isFile())
            {
                FileInputStream fism1 = new FileInputStream(fm);
                FileInputStream fisf1 = new FileInputStream(ff);
                ObjectInputStream oism1 = new ObjectInputStream(fism1);
                ObjectInputStream oisf1 = new ObjectInputStream(fisf1);
                maleSingers = (ArrayList<Singer>) oism1.readObject();
                femaleSingers = (ArrayList<Singer>) oisf1.readObject();
            }
        }
        catch(ClassNotFoundException f){}
        catch(IOException i){}
        
        setLayout(new GridLayout(1,2));
        setSize(1200,330);
        
        first = new JPanel();
        second = new JPanel();
        group1 = new JPanel();
        group2 = new JPanel();
        group3 = new JPanel();
        group4 = new JPanel();
        group5 = new JPanel();
        
        first.setLayout(new FlowLayout());
        second.setLayout(new FlowLayout());
        group1.setLayout(new FlowLayout());
        group2.setLayout(new FlowLayout());
        group3.setLayout(new FlowLayout());
        group4.setLayout(new FlowLayout());
        group5.setLayout(new FlowLayout());
        
        group1.add(singerName);
        group1.add(nameEntry);
        group1.add(singerAge);
        group1.add(ageEntry);
        group2.add(singerQualities);
        group2.add(voiceCB);
        group2.add(originalityCB);
        group2.add(personalityCB);
        group3.add(genderRB);
        group3.add(maleRB);
        group3.add(femaleRB);
        group4.add(enterBtn);
        group4.add(nextBtn);
        group4.add(saveBtn);
        group4.add(viewBtn);
        group5.add(maleArea);
        group5.add(maleScroll);
        group5.add(femaleArea);
        group5.add(femaleScroll);
        
        first.add(group1);
        first.add(group2);
        first.add(group3);
        first.add(group4);
        
        second.add(group5);
        
        enterBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        
        voiceCB.addItemListener(this);
        originalityCB.addItemListener(this);
        personalityCB.addItemListener(this);
        
        maleRB.addItemListener(this);
        femaleRB.addItemListener(this);
        
        add(first);
        add(second);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource() == enterBtn)
        {
            singerName.setForeground(Color.BLACK);
            singerAge.setForeground(Color.BLACK);
            genderRB.setForeground(Color.BLACK);
            int age = 0;
            try
            {
                if (ageEntry.getText().length() > 0 && isADigitString(ageEntry.getText()) && ageEntry.getText().length() <= 3) // make sure age has something and that it is a number
                    age = Integer.parseInt(ageEntry.getText());
                else // else if above false set text to nothing as it is invalid entry
                    ageEntry.setText("");
            }
            catch (Exception e){}
            if (nameEntry.getText().length() > 0 && ageEntry.getText().length() > 0 && gender.length() > 0) // Check if values have been entered
            {
                Singer s = new Singer(nameEntry.getText(), age, gender, points);
                JLabel enter = new JLabel("Singer Entered");
                first.add(enter);
                if (gender.equals("Male"))
                {
                    maleResults.append(s.toString());
                    maleSingers.add(s);
                }
                else if (gender.equals("Female"))
                {
                    femaleResults.append(s.toString());
                    femaleSingers.add(s);
                }
            }
            else
            {
                if(nameEntry.getText().length() == 0 || nameEntry.getText() == null)
                {
                    singerName.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Please enter a name");
                }
                if(ageEntry.getText().length() == 0 || ageEntry.getText() == null)
                {
                    singerAge.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Please enter a age");
                }
                if (gender.equals(""))
                {
                    genderRB.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Please select a gender");
                }
            }
        }
        if(a.getSource() == nextBtn)
        {
            JLabel next = new JLabel("Please Enter Next");
            first.add(next);
            nameEntry.setText(""); 
            ageEntry.setText("");
            voiceCB.setSelected(false);
            originalityCB.setSelected(false);
            personalityCB.setSelected(false);
            bgGenders.clearSelection();
        }
        if(a.getSource() == saveBtn)
        {
            maleResults.setText("");
            femaleResults.setText("");
            if (maleSingers.size() == 0 && femaleSingers.size() == 0)
            {
                JOptionPane.showMessageDialog(null, "There is nothing to save :(");
                return;
            }
            try
            {              
                FileOutputStream fosm = new FileOutputStream(fm);
                FileOutputStream fosf = new FileOutputStream(ff);
                ObjectOutputStream oosm = new ObjectOutputStream(fosm);
                ObjectOutputStream oosf = new ObjectOutputStream(fosf);
                
                oosm.writeObject(maleSingers);
                oosf.writeObject(femaleSingers);
                
                oosf.close();
                oosm.close();
                fosf.close();
                fosm.close();
            }
            catch (FileNotFoundException f)
            {
                System.out.println("File was not found");
            }
            catch (IOException i)
            {
                System.out.println("There was a I/O Exception");
            }
            catch (Exception e)
            {
                System.out.println("A general exception was encountered");
            }
            finally
            {
                JOptionPane.showMessageDialog(null, "Saving work and shutting down");
                System.exit(0);
            }
        }
        if(a.getSource() == viewBtn)
        {
            try
            {
                FileInputStream fism = new FileInputStream(fm);
                FileInputStream fisf = new FileInputStream(ff);
                ObjectInputStream oism = new ObjectInputStream(fism);
                ObjectInputStream oisf = new ObjectInputStream(fisf);
                
                ArrayList<Singer> maleContestants = (ArrayList<Singer>) oism.readObject();
                ArrayList<Singer> femaleContestants = (ArrayList<Singer>) oisf.readObject();

                for (Singer smc: maleContestants)
                    maleResults.append(smc.toString());
                for (Singer sfc: femaleContestants)
                    femaleResults.append(sfc.toString());

                oisf.close();
                oism.close();
                fisf.close();
                fism.close();
            }
            catch (FileNotFoundException f)
            {
                System.out.println("File was not found");
            }
            catch (IOException i)
            {
                System.out.println("There was a I/O Exception");
            }
            catch (Exception e)
            {
                System.out.println("A general exception was encountered");
            }
        }
    }
    
    public void itemStateChanged(ItemEvent i)
    {
        points = 0;
        gender = "";
        if (voiceCB.isSelected())
            points += 10;
        if (originalityCB.isSelected())
            points += 10;
        if (personalityCB.isSelected())
            points += 10;
        if (maleRB.isSelected())
            gender = "Male";   
        else if (femaleRB.isSelected())
            gender = "Female";   
    }
    
    public boolean isADigitString(String text) //Checks if string entered is fully comprised of digits
    {
        int counter = 0;
        for(int i = 0; i < text.length(); i++)
            if (text.codePointAt(i) >=48 && text.codePointAt(i) <= 57)
                counter++;
        if (counter  == (int) text.length())
            return true;            
        else  
            return false;         
    }
    
    public static void main(String[] args)
    {
        SingerGUI myFrame = new SingerGUI();
        MyWindowListener myWindow = new MyWindowListener();        
        myFrame.addWindowListener(myWindow);
    }
} 