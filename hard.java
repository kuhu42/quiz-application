package quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class hard {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/";
        String dbName = "HardQuiz";
        String dbUser = "root";
        String dbPassword = "Nushtweets5678@";
        try (Connection connection = DriverManager.getConnection(jdbcURL + dbName, dbUser, dbPassword)) {
            // Create the student tables if they don't exist
            createTable(connection,"Java_h");
            createTable(connection,"Py_h");
            createTable(connection,"Cpp_h");

        // Insert records into Java_h table
        insertRecord(connection,"Java_h",1, 
        "In Java, which of the following access modifiers allows a member to be accessed only within its own package and by subclasses?", "public", 
        "private", "protected", "default (package-private)", "c");
        insertRecord(connection,"Java_h",2, "What is the purpose of the this keyword in Java?", "It is used to create an instance of a class.", 
        "It refers to the current object within a class's instance methods.", "It specifies a class to be the superclass.", "It is used for type casting.", 
        "b");
        insertRecord(connection,"Java_h",3, 
        "What is the result of the following code?\njava\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = x++ + ++x;\n        System.out.println(y);\n    }\n}", 
        "10", "11", "12", "5", "b");
        insertRecord(connection,"Java_h",4, "In Java, what is the purpose of the `assert` statement?", 
        "It is used to define a custom exception.", "It is used for debugging and testing.", "It is used to cast data types.", 
        "It is used to declare variables.", "b");
        insertRecord(connection,"Java_h",5,"What is the output of the following code?\njava\npublic class Test {\n    public static void main(String[] args) {\n        String s1 = \"Java\";\n        String s2 = new String(\"Java\");\n        System.out.println(s1 == s2);\n    }\n}", 
        "true", "false", "It will result in a compilation error.", "It will throw an exception.", "b");

          // Insert records into Py_h table
          insertRecord(connection, "Py_h",1, "What is the purpose of the `global` keyword in Python?", "It declares a global variable.", 
          "It defines a new data type.", "It imports external modules.", "It creates a class.","a");
          insertRecord(connection, "Py_h",2, "In Python, what is the Global Interpreter Lock (GIL)?", "A tool for managing global variables", 
          "A mechanism for handling exceptions", "A lock that allows only one thread to execute Python code at a time", "A function for generating random numbers",
          "c");
          insertRecord(connection, "Py_h",3, "What is the primary use of the `lambda` function in Python?", "To create anonymous functions", 
          "To handle file I/O operations", "To define classes", "To implement multithreading.","a");
          insertRecord(connection, "Py_h",4, "Which of the following is an example of an immutable data type in Python?", "List", "Dictionary", 
          "Tuple", "Set","c");
          insertRecord(connection, "Py_h",5, "What does the `yield` keyword do in a Python function?", "It defines a variable.", 
          "It raises an exception.", "It specifies the return value of the function.", 
          "It suspends the function's execution and yields a value to the caller, allowing it to resume later.","d");
                      // Insert records into Cpp_h table
insertRecord(connection, "Cpp_h", 1, "In C, what is the purpose of the `volatile` keyword when used with a variable?", 
"It indicates that the variable is constant.", "It suggests that the variable can change at any time, preventing compiler optimization.", 
"It marks the variable as private.", "It specifies that the variable is an array.","b");
insertRecord(connection, "Cpp_h", 2, "Which of the following is not a valid method for passing arguments to a C function?", "Pass by value", 
"Pass by reference", "Pass by pointer", "Pass by name","d");
insertRecord(connection, "Cpp_h", 3, "What does the `sizeof` operator in C return?", "The size of a variable in bytes", 
"The address of a variable", "The data type of a variable", "The value of a variable","a");
insertRecord(connection, "Cpp_h", 4, "In C++, what is operator overloading?", "A mechanism to increase the size of data types", 
"A feature that allows defining multiple operators with the same functionality", "A process to create new operators", 
"A way to redefine standard operators for user-defined types","d");
insertRecord(connection, "Cpp_h", 5, "In C++, what is a constructor?", "A member function that returns a value", 
"A function used for dynamic memory allocation", "A special member function used to initialize objects", "A method for creating user-defined operators","c");
} catch (SQLException e) {
    e.printStackTrace();}}

     // Create a table
     private static void createTable(Connection connection, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName +
        " (qno INT AUTO_INCREMENT PRIMARY KEY, que VARCHAR(4000), a VARCHAR(255), b VARCHAR(255), c VARCHAR(255), d VARCHAR(255), ans VARCHAR(255))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        }}

    // Insert a record into a table
    private static void insertRecord(Connection connection, String tableName, int qno, String que, String a, String b, String c, String d, String ans) 
    throws SQLException {
        String insertSQL = "INSERT INTO " + tableName + " (qno, que, a, b, c, d, ans) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, qno);
            preparedStatement.setString(2, que);
            preparedStatement.setString(3, a);
            preparedStatement.setString(4, b);
            preparedStatement.setString(5, c);
            preparedStatement.setString(6, d);
            preparedStatement.setString(7, ans);
            preparedStatement.executeUpdate();
        }}}