package quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class medium {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/";
        String dbName = "MediumQuiz";
        String dbUser = "root";
        String dbPassword = "Nushtweets5678@";
        try (Connection connection = DriverManager.getConnection(jdbcURL + dbName, dbUser, dbPassword)) {
            // Create the student tables if they don't exist
            createTable(connection,"Java_m");
            createTable(connection,"Py_m");
            createTable(connection,"Cpp_m");

        // Insert records into Java_m table
        insertRecord(connection,"Java_m",1,
        "What is the output of the following Java code?\njava\nString str = \"Java\";\nstr.concat(\" is fun\");\nSystem.out.println(str);", 
        "\"Java is fun\"", "\"Java\"", "\" is fun\"", "Compilation error", "b");
        insertRecord(connection,"Java_m",2,"In Java, which access modifier allows a class to be accessed from any other class?", 
        "private", "protected", "public", "default (package-private)", "c");
        insertRecord(connection,"Java_m",3,"Which of the following statements is true regarding the `equals` method in Java?", 
        "It compares the object references.", "It compares the contents of the objects.", "It can only be used with primitive data types.", 
        "It is not a built-in method in Java.", "b");
        insertRecord(connection,"Java_m",4, "What is the purpose of the `super` keyword in Java?", "To call a superclass constructor.", 
        "To access static variables.", "To create a new object.", "To declare a superclass.", "a");
        insertRecord(connection, "Java_m",5,
        "What is the result of the following code?\njava\nint x = 5;\nint y = 2;\nint result = x++ + --y + x;\nSystem.out.println(result);", 
        "9", "11", "8", "10", "a");

          // Insert records into Py_m table
          insertRecord(connection, "Py_m",1, 
          "What is the result of the following Python code?\npython\ndef increment(x):\n    x += 1\n    return x\n\ny = 5\nresult = increment(y)\nprint(y)", 
          "6", "5", "7", "0","b");
          insertRecord(connection, "Py_m",2, "In Python, which data structure is commonly used to implement a queue?", "List", 
          "Set", "Dictionary", "Queue","d");
          insertRecord(connection, "Py_m",3, "What is the purpose of the `map` function in Python?", 
          "To perform arithmetic operations on lists", "To create a new dictionary", "To apply a function to each item in an iterable", 
          "To filter elements in a list","c");
          insertRecord(connection, "Py_m",4, "In Python, which of the following is used to handle exceptions or errors?", "try", 
          "finally", "catch", "attempt","a");
          insertRecord(connection, "Py_m",5, 
          "What is the output of the following Python code?\npython\nmy_list = [1, 2, 3, 4, 5]\nresult = [x * 2 for x in my_list if x % 2 == 0]\nprint(result)", 
          "[2, 4, 6, 8, 10]", "[4, 8]", "[1, 3, 5]", "[1, 2, 3, 4, 5]","b");
                      // Insert records into Cpp_m table
insertRecord(connection, "Cpp_m", 1, "What is the purpose of the `#include` directive in C programming?", "It defines a new function.", 
"It includes a standard library for input and output operations.", "It declares a variable.", "It defines a loop.","b");
insertRecord(connection, "Cpp_m", 2, 
"In C, which of the following is not a valid data type?", "int", "char", "real", "double","c");
insertRecord(connection, "Cpp_m", 3, 
"What is the result of the following code snippet in C?\nc\nint x = 5;\nint y = ++x + x++;\nprintf(\"%d\", y);", "10", "11", "12", "13","b");
insertRecord(connection, "Cpp_m", 4, "What is the purpose of the `new` operator in C++?", "To perform type casting", 
"To allocate memory for an object", "To declare a constant", "To define a function","b");
insertRecord(connection, "Cpp_m", 5, "In C++, which keyword is used to implement exception handling?", "throw", "catch", "finalize", 
"continue","a");
        } catch (SQLException e) {
            e.printStackTrace();}}

     // Create a table
     private static void createTable(Connection connection, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + 
        " (qno INT AUTO_INCREMENT PRIMARY KEY, que VARCHAR(4000), a VARCHAR(255), b VARCHAR(255), c VARCHAR(255), d VARCHAR(255), ans CHAR(1))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);}}

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
            preparedStatement.setString(7, String.valueOf(ans));
            preparedStatement.executeUpdate();}}}