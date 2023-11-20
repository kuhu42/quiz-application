package quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class easy {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/";
        String dbName = "EasyQuiz";
        String dbUser = "root";
        String dbPassword = "Nushtweets5678@";
        try (Connection connection = DriverManager.getConnection(jdbcURL + dbName, dbUser, dbPassword)) {
            // Create the student tables if they don't exist
            createTable(connection,"Java_e");
            createTable(connection,"Py_e");
            createTable(connection,"Cpp_e");

         // Insert records into Java_e table
           insertRecord(connection,"Java_e", 1, "What is the correct way to declare a variable in Java?", "variable x;", "x = 10;",
         "int x;", "x int = 10;", 'c');
           insertRecord(connection,"Java_e",2, "In Java, which keyword is used to define a constant?", "final", "static", "constant", 
           "const", 'a');
           insertRecord(connection,"Java_e",3,"What is the output of the following code?\njava\nint a = 5;\nint b = 2;\nSystem.out.println(a / b);",
            "3", "2.5", "2", "Error", 'c');
           insertRecord(connection,"Java_e",4,"Which of the following is a data type in Java?", "void", "null", "string", "boolean", 
           'd');
           insertRecord(connection,"Java_e",5,"What is the correct way to declare and initialize an array of integers in Java?", 
           "int[] numbers = [1, 2, 3];", "int[] numbers = new int[]{1, 2, 3};", "int numbers[] = {1, 2, 3};", "array numbers = [1, 2, 3];", 'b');

         //Insert records into Py_e table
         insertRecord(connection,"Py_e",1, "What is the output of the following Python code?\npython\nnumbers = [1, 2, 3, 4, 5]\nresult = [n * 2 for n in numbers if n % 2 == 0]\nprint(result)", 
         "[1, 2, 3, 4, 5]", "[4, 8]", "[2, 4]", "[1, 3, 5]",'c');
         insertRecord(connection,"Py_e", 2, "In Python, what is the purpose of the `elif` keyword in an `if` statement?", 
         "It represents the end of the `if` block.", "It is used to define a class.", "It is used for exception handling.", 
         "It specifies an alternative condition to be checked if the `if` condition is false.",'d');
         insertRecord(connection,"Py_e",3, "Which of the following data types in Python is mutable (can be modified after creation)?", 
         "int", "float", "tuple", "list",'d');
         insertRecord(connection,"Py_e",4, "What will be the output of the following Python code?\npython\ndef add(a, b):\n    return a + b\n\nresult = add(3, \"2\")\nprint(result)", 
         "5", "\"32\"", "32", "TypeError",'d');
         insertRecord(connection,"Py_e",5, "In Python, how do you open a file named \"data.txt\" in read-only mode and assign it to a variable?", 
         "`file = open(\"data.txt\", \"w\")", "`file = open(\"data.txt\", \"r\")", "`file = open(\"data.txt\", \"a\")", "`file = open(\"data.txt\", \"x\")",
         'b');

            // Insert records into Cpp_e table
           insertRecord(connection, "Cpp_e", 1, "What is the symbol for the end of a statement in C?", ";", ":", ",", ".",'a');
           insertRecord(connection, "Cpp_e", 2, "Which data type is used to store whole numbers in C?", "float", "double", "int",
            "char",'c');
           insertRecord(connection, "Cpp_e", 3, "What is the `printf` function used for in C?", "Reading user input", 
           "Displaying text on the screen", "Mathematical calculations", "Conditional statements",'b');
           insertRecord(connection, "Cpp_e", 4, "Which of the following is an object-oriented programming (OOP) concept in C++?", 
           "Inheritance", "Looping", "Bitwise operations", "Pointer arithmetic",'a');
           insertRecord(connection, "Cpp_e", 5, "What does the `cin` object in C++ handle?", "Output operations", "File operations", 
           "Exception handling", "Input operations",'d');

        } catch (SQLException e) {
            e.printStackTrace();
        }}

     // Create a table
     private static void createTable(Connection connection, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + 
        " (qno INT AUTO_INCREMENT PRIMARY KEY, que VARCHAR(4000), a VARCHAR(255), b VARCHAR(255), c VARCHAR(255), d VARCHAR(255), ans CHAR(1))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        }
    }

    // Insert a record into a table
    private static void insertRecord(Connection connection, String tableName, int qno, String que, String a, String b, String c, String d, char ans) 
    throws SQLException {
        String insertSQL = "INSERT INTO " + tableName + " (qno, que, a, b, c, d, ans) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, qno);
            preparedStatement.setString(2, que);
            preparedStatement.setString(3, a);
            preparedStatement.setString(4, b);
            preparedStatement.setString(5, c);
            preparedStatement.setString(6, d);
            preparedStatement.setString(7, String.valueOf(ans));
            preparedStatement.executeUpdate();
        }
    }
}