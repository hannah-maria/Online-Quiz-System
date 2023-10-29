/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class QuizTest extends JFrame implements ActionListener {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "changeme";
    
    private JLabel label;
    private JRadioButton radioButtons[] = new JRadioButton[5];
    private JButton btnNext, btnResult;
    private ButtonGroup bg;
    private int count = 0, current = 0;
    
    private Connection connection;

    QuizTest(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButtons[i] = new JRadioButton();
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        btnResult.setVisible(false);
        btnResult.addActionListener(this);
        btnNext.addActionListener(this);
        add(btnNext);
        add(btnResult);
        connectToDatabase();
        setData();
        label.setBounds(30, 40, 450, 20);
        radioButtons[0].setBounds(50, 80, 450, 20);
        radioButtons[1].setBounds(50, 110, 200, 20);
        radioButtons[2].setBounds(50, 140, 200, 20);
        radioButtons[3].setBounds(50, 170, 200, 20);
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    void setData(){
        radioButtons[4].setSelected(true);
        if(current==0){
            label.setText("Q1 : What is the official language for Android development");
            radioButtons[0].setText("Java");
            radioButtons[1].setText("Kotlin");
            radioButtons[2].setText("C++");
            radioButtons[3].setText("JavaScript");
            
        }
        if(current==1){
            label.setText("Q2 : Which one of the following is not a Java feature?");
            radioButtons[0].setText("Object-oriented");
            radioButtons[1].setText("Use of pointers");
            radioButtons[2].setText("Portable");
            radioButtons[3].setText("Dynamic and Extensible");
            
        }
        if(current==2){
            label.setText("Q3 : Which of these can't be used as variable name in Java?");
            radioButtons[0].setText("identifier & keyword");
            radioButtons[1].setText("identifier");
            radioButtons[2].setText("keyword");
            radioButtons[3].setText("none of the mentioned");
            
        }
        if(current==3){
            label.setText("Q4 : What is the extension of java code files?");
            radioButtons[0].setText(".js");
            radioButtons[1].setText(".txt");
            radioButtons[2].setText(".class");
            radioButtons[3].setText(".java");
            
        }
        if(current==4){
            label.setText("Q5 : Which environment variable to set the java path?");
            radioButtons[0].setText("MAVEN_Path");
            radioButtons[1].setText("JavaPATH");
            radioButtons[2].setText("JAVA");
            radioButtons[3].setText("JAVA_HOME");
            
        }
        if(current==5){
            label.setText("Q6 : Which is not an OOPS concept in Java?");
            radioButtons[0].setText("Polymorphism");
            radioButtons[1].setText("Inheritance");
            radioButtons[2].setText("Compilation");
            radioButtons[3].setText("Encapsulation");
            
        }
        if(current==6){
            label.setText("Q7 : What is the entry point for a Java application?");
            radioButtons[0].setText("start()");
            radioButtons[1].setText("main()");
            radioButtons[2].setText("execute()");
            radioButtons[3].setText("begin()");
            
        }
        if(current==7){
            label.setText("Q8 : Which statement is true about Java?");
            radioButtons[0].setText("sequence-dependent");
            radioButtons[1].setText("code dependent");
            radioButtons[2].setText("platform-dependent");
            radioButtons[3].setText("platform-independent");
            
        }
        if(current==8){
            label.setText("Q9 : Which is used to compile, debug and execute java programs?");
            radioButtons[0].setText("JRE");
            radioButtons[1].setText("JIT");
            radioButtons[2].setText("JDK");
            radioButtons[3].setText("JVM");
            
        }
        if(current==9){
            label.setText("10 : In Java, which keyword is used to create a subclass of a class?");
            radioButtons[0].setText("inherits");
            radioButtons[1].setText("extends");
            radioButtons[2].setText("subclass");
            radioButtons[3].setText("super");
            
        }
        label.setBounds(30,40,450,20);
        for(int i=0,j=0;i<=90;i+=30,j++){
            radioButtons[j].setBounds(50,80+i,200,20);
        }
        insertQuestion("What is the official language for Android development", "Java", "Kotlin", "C++", "JavaScript", 0);
        insertQuestion("Which one of the following is not a Java feature?", "Object-Oriented", "Use of pointers", "Portable", "Dynamic and Extensible", 1);
        insertQuestion("Which of these can't be used as variable name in java?", "Identifier and Keyword", "Identifier", "Keyword", "None of the mentioned", 2);
        insertQuestion("What is the extension of Java code files?", ".js", ".txt", ".class", ".java", 3);
        insertQuestion("Which environment variable is used to set the java path", "MAVEN_Path", "JavaPATH", "JAVA", "JAVA_HOME", 3);
        insertQuestion("Which is not a OOPS concept?", "Polymorphism", "Inheritance", "Compilation","Encapsulation", 2);
        insertQuestion("What is the entry point for a Java application?", "start()", "main()", "execute()", "begin()", 1);
        insertQuestion("Which statement is true about Java?", "sequence-dependent", "code dependent", "platform-dependent", "platform-independent", 3);
        insertQuestion("Which is used to compile, debug and execute java programs", "JRE", "JIT", "JDK", "JVM", 2);
        insertQuestion("In Java, which keyword is used to create a subclass?", "inherits", "extends", "subclass", "super", 2);
    }

    boolean checkAns(){
        if(current==0){
            return (radioButtons[0].isSelected());
        }
        if(current==1){
            return (radioButtons[1].isSelected());
        }
        if(current==2){
            return (radioButtons[2].isSelected());
        }
        if(current==3){
            return (radioButtons[3].isSelected());
        }
        if(current==4){
            return (radioButtons[3].isSelected());
        }
        if(current==5){
            return (radioButtons[2].isSelected());
        }
        if(current==6){
            return (radioButtons[1].isSelected());
        }
        if(current==7){
            return (radioButtons[3].isSelected());
        }
        if(current==8){
            return (radioButtons[2].isSelected());
        }
        if(current==9){
            return (radioButtons[2].isSelected());
        }
        return false;
    }

    public void insertQuestion(String questionText, String option1, String option2, String option3, String option4, int correctOption) {
        try {
            String query = "INSERT INTO quiz_questions (question_text, option1, option2, option3, option4, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, questionText);
            statement.setString(2, option1);
            statement.setString(3, option2);
            statement.setString(4, option3);
            statement.setString(5, option4);
            statement.setInt(6, correctOption);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new QuizTest("Simple quiz app");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (checkAns())
                count = count + 1;
            current++;
            setData();
            if (current == 9) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
                btnResult.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (checkAns())
                count += 1;
            current++;
            JOptionPane.showMessageDialog(this, "Correct Answers are " + count);
            System.exit(0);
        }
    }
}
