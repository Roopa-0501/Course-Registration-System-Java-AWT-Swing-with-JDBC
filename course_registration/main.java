import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class CourseRegistrationDB9 extends JFrame implements ActionListener {
    private Connection connection;

    // UI Components
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Sign-In Components
    private JTextField txtEmailSignIn;
    private JPasswordField txtPasswordSignIn;
    private JButton btnSignIn, btnGoToSignUp;

    // Sign-Up Components
    private JTextField txtEmailSignUp;
    private JPasswordField txtPasswordSignUp, txtConfirmPasswordSignUp;
    private JButton btnSignUp, btnGoToSignIn;

    // Registration Components
    private JTextField txtName, txtPhoneNumber, txtCollege, txtEducation;
    private JComboBox<String> courseDropdown;
    private JButton btnRegister;

    // Logged-in User Email
    private String loggedInEmail;

    // Courses
    private String[] courses = {"Java Programming", "Python Programming", "Web Development", "Machine Learning", "Data Science"};

    public CourseRegistrationDB9() {
        setTitle("Course Registration System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeDatabaseConnection();
        initializeUIComponents();
    }

    private void initializeDatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "testuser", "password");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + ex.getMessage());
            System.exit(1);
        }
    }
    private void initializeUIComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createSignInPage();
        createSignUpPage();
        createRegistrationPage();

        add(mainPanel);
        setVisible(true);
    }
    private void createSignInPage() {
    JPanel signInPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    // Title Label
    JLabel lblTitle = new JLabel("Sign In", JLabel.CENTER);
    lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
    lblTitle.setForeground(Color.BLUE);

    // Adding Title to Panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2; // Span across 2 columns
    signInPanel.add(lblTitle, gbc);

    // Reset grid width for subsequent elements
    gbc.gridwidth = 1;

    // Rest of the UI components
    JLabel lblEmail = new JLabel("Email:");
    lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
    txtEmailSignIn = new JTextField(20);

    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
    txtPasswordSignIn = new JPasswordField(20);

    btnSignIn = new JButton("Sign In");
    btnGoToSignUp = new JButton("Go to Sign Up");

    btnSignIn.addActionListener(this);
    btnGoToSignUp.addActionListener(this);

    gbc.gridx = 0;
    gbc.gridy = 1;
    signInPanel.add(lblEmail, gbc);

    gbc.gridx = 1;
    signInPanel.add(txtEmailSignIn, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    signInPanel.add(lblPassword, gbc);

    gbc.gridx = 1;
    signInPanel.add(txtPasswordSignIn, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    signInPanel.add(btnSignIn, gbc);

    gbc.gridx = 1;
    signInPanel.add(btnGoToSignUp, gbc);

    mainPanel.add(signInPanel, "SignIn");
}
private void createSignUpPage() {
    JPanel signUpPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    // Title Label
    JLabel lblTitle = new JLabel("Sign Up", JLabel.CENTER);
    lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
    lblTitle.setForeground(Color.BLUE);

    // Adding Title to Panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2; // Span across 2 columns
    signUpPanel.add(lblTitle, gbc);

    // Reset grid width for subsequent elements
    gbc.gridwidth = 1;

    // Rest of the UI components
    JLabel lblEmail = new JLabel("Email:");
    lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
    txtEmailSignUp = new JTextField(20);

    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
    txtPasswordSignUp = new JPasswordField(20);
    
    JLabel lblConfirmPassword = new JLabel("Confirm Password:");
    lblConfirmPassword.setFont(new Font("Arial", Font.BOLD, 14));
    txtConfirmPasswordSignUp = new JPasswordField(20);

    btnSignUp = new JButton("Sign Up");
    btnGoToSignIn = new JButton("Go to Sign In");

    btnSignUp.addActionListener(this);
    btnGoToSignIn.addActionListener(this);

    gbc.gridx = 0;
    gbc.gridy = 1;
    signUpPanel.add(lblEmail, gbc);

    gbc.gridx = 1;
    signUpPanel.add(txtEmailSignUp, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    signUpPanel.add(lblPassword, gbc);

    gbc.gridx = 1;
    signUpPanel.add(txtPasswordSignUp, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    signUpPanel.add(lblConfirmPassword, gbc);

    gbc.gridx = 1;
    signUpPanel.add(txtConfirmPasswordSignUp, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    signUpPanel.add(btnSignUp, gbc);

    gbc.gridx = 1;
    signUpPanel.add(btnGoToSignIn, gbc);

    mainPanel.add(signUpPanel, "SignUp");
}

 private void createRegistrationPage() {
    JPanel registrationPanel= new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    // Title Label
    JLabel lblTitle = new JLabel("Course Registration", JLabel.CENTER);
    lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
    lblTitle.setForeground(Color.BLUE);

    // Adding Title to Panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2; // Span across 2 columns
      registrationPanel.add(lblTitle, gbc);

    // Reset grid width for subsequent elements
    gbc.gridwidth = 1;

    // Rest of the UI components
    JLabel lblName = new JLabel("Name:");
    lblName.setFont(new Font("Arial", Font.BOLD, 14));
    txtName = new JTextField(20);
    
    JLabel lblPhone = new JLabel("Phone:");
    lblPhone.setFont(new Font("Arial", Font.BOLD, 14));
    txtPhoneNumber = new JTextField(20);

     JLabel lblCollege = new JLabel("College:");
     lblCollege.setFont(new Font("Arial", Font.BOLD, 14));
     txtCollege = new JTextField(20);
     
     JLabel lblEducation = new JLabel("Education:");
     lblEducation.setFont(new Font("Arial", Font.BOLD, 14));
     txtEducation = new JTextField(20);
     
     JLabel lblCourse = new JLabel("Course:");
     lblCourse.setFont(new Font("Arial", Font.BOLD, 14));
     courseDropdown = new JComboBox<>(courses);
    
     btnRegister = new JButton("Register");
     btnRegister.addActionListener(this);

    gbc.gridx = 0;
    gbc.gridy = 1;
   registrationPanel.add(lblName, gbc);

    gbc.gridx = 1;
    registrationPanel.add(txtName, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    registrationPanel.add(lblPhone, gbc);

    gbc.gridx = 1;
    registrationPanel.add(txtPhoneNumber,gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    registrationPanel.add(lblCollege, gbc);

    gbc.gridx = 1;
 registrationPanel.add(txtCollege, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    registrationPanel.add(lblEducation, gbc);

    gbc.gridx = 1;
   registrationPanel.add(txtEducation, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 5;
   registrationPanel.add(lblCourse, gbc);

    gbc.gridx = 1;
    registrationPanel.add(courseDropdown, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 7;
    registrationPanel.add(btnRegister, gbc);

    mainPanel.add(registrationPanel, "Register");
}
 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignIn) {
            handleSignIn();
        } else if (e.getSource() == btnGoToSignUp) {
            cardLayout.show(mainPanel, "SignUp");
        } else if (e.getSource() == btnSignUp) {
            handleSignUp();
        } else if (e.getSource() == btnRegister) {
            handleRegistration();
        }
    }

    private void handleSignIn() {
        String email = txtEmailSignIn.getText();
        String password = new String(txtPasswordSignIn.getPassword());
         if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email and password cannot be empty.");
            return;
        }

        try {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loggedInEmail = email;
                cardLayout.show(mainPanel, "Register");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    private void handleSignUp() {
        String email = txtEmailSignUp.getText();
        String password = new String(txtPasswordSignUp.getPassword());
        String confirmPassword = new String(txtConfirmPasswordSignUp.getPassword());
	if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        try {
            String query = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sign-Up Successful! Please sign in.");
            cardLayout.show(mainPanel, "SignIn");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void handleRegistration() {
        String name = txtName.getText();
        String phone = txtPhoneNumber.getText();
        String college = txtCollege.getText();
        String education = txtEducation.getText();
        String course = (String) courseDropdown.getSelectedItem();
       if (name.isEmpty() || phone.isEmpty() || college.isEmpty() || education.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        try {
            // Fetch user ID
            String userIdQuery = "SELECT id FROM users WHERE email = ?";
            PreparedStatement userStmt = connection.prepareStatement(userIdQuery);
            userStmt.setString(1, loggedInEmail);
            ResultSet rs = userStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");

                // Insert user details
                String insertDetailsQuery = "INSERT INTO user_details (user_id, name, phone_number, ollege, education, course) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement detailsStmt = connection.prepareStatement(insertDetailsQuery);
                detailsStmt.setInt(1, userId);
                detailsStmt.setString(2, name);
                detailsStmt.setString(3, phone);
                detailsStmt.setString(4, college);
                detailsStmt.setString(5, education);
                detailsStmt.setString(6, course);

                detailsStmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration Successful for " + course);
            } else {
                JOptionPane.showMessageDialog(this, "User not found. Please sign in again.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CourseRegistrationDB9();
    }
}

