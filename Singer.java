import java.io.Serializable;
public class Singer implements Serializable
{
    private String name;
    private int age;
    private String gender;
    private int marks; 
    public Singer(String name, int age, String gender, int marks)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.marks = marks;
    }
    
    public String toString()
    {
        String details = "";
        details += "Name: " + name + "\n";
        details += "Age: " + age + "\n";
        details += "Gender: " + gender + "\n";
        details += "Marks: " + marks + "\n\n";
        return details;
    }
}