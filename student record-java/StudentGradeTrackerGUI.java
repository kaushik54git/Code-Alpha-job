import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeTrackerGUI {
    private List<Student> studentList;

    public StudentGradeTrackerGUI() {
        studentList = new ArrayList<>();
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Components
        JTextField nameField = new JTextField(15);
        JTextField gradeField = new JTextField(5);
        JTextArea resultArea = new JTextArea(10, 30);
        JButton addButton = new JButton("Add Student");
        JButton calculateButton = new JButton("Calculate Stats");

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addButton);
        inputPanel.add(calculateButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Event listeners
        addButton.addActionListener(e -> addStudent(nameField.getText(), Integer.parseInt(gradeField.getText())));
        calculateButton.addActionListener(e -> calculateStats(resultArea));

        frame.pack();
        frame.setVisible(true);
    }

    private void addStudent(String name, int grade) {
        studentList.add(new Student(name, grade));
    }

    private void calculateStats(JTextArea resultArea) {
        if (studentList.isEmpty()) {
            resultArea.setText("No students added.");
            return;
        }

        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (Student student : studentList) {
            total += student.getGrade();
            highest = Math.max(highest, student.getGrade());
            lowest = Math.min(lowest, student.getGrade());
        }

        double average = (double) total / studentList.size();

        resultArea.setText("Average Grade: " + average +
                "\nHighest Grade: " + highest +
                "\nLowest Grade: " + lowest);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeTrackerGUI::new);
    }
}

class Student {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
