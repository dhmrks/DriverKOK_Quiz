package QuizServices;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.json.*;



/**
 *
 * @author Dimitris Rakas
 */
public class DBManager {
    
    static DBManager Inst = null;
    

    /**
     * Δημιουργώ ένα στιγμιότυπο της κλάσσης DBMnanager ώστε
     *  να επωφεληθώ από τα πλεονεκτήματα του singleton. 
     */
    public static DBManager getInstance() throws ClassNotFoundException, SQLException   {
        if(Inst == null)
            Inst = new DBManager();
        return Inst;
    }


    /**
     * Η υλοποίηση της login,ελέγχει αν υπάρχει ο χρήστης στη βάση και του επιστρέφει ένα code--> role_id.
     */
    public int login(String username,String password) throws ClassNotFoundException, SQLException    {
        //Δημιουργώ ένα connection με τη βάση μου.
        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        int key = 0;
        String sql = "SELECT * FROM users WHERE username = '" + username + "'"
                                 + " AND password = '" + password + "'";
        ResultSet rs = myStatement.executeQuery(sql);
            
        while (rs.next()) {
            key = rs.getInt("role_id");
        }
        myStatement.close();
        myConnection.close();
        return key;
    }

    /**
     * Επιλέγει 5 τυχαίες ερωτήσεις από τη βάση δεδομένων με μορφή JSON.
     */

    public String examq() throws ClassNotFoundException, SQLException {
        
        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        
        Random random = new Random();
        ArrayList<Integer> ids = new ArrayList(); //Επιλέγω τα id από τον πίνακα των ερωτήσεων.
        ArrayList<Integer> numbers = new ArrayList(); //Τυχαία βάζουμε 5 id στην 2η λίστα.
        String sql = "SELECT question_id FROM questions";
        ResultSet   rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                ids.add(rs.getInt("question_id"));
            }
         
        while (numbers.size() < 5) {
            int random_id = random.nextInt(ids.size());
            if (!numbers.contains(random_id))
                numbers.add(random_id);
        }
        
        for (int j = 0; j < 5; j++) {
            String sql2 = "SELECT * FROM questions WHERE question_id = " + ids.get(numbers.get(j));
            rs = myStatement.executeQuery(sql2);
            while (rs.next()) {
                jsonA = jsonA.add(factory.createObjectBuilder()
                .add("id", rs.getString("question_id"))
                .add("question", rs.getString("question"))
                .add("answer1", rs.getString("question_answer1"))
                .add("answer2", rs.getString("question_answer2"))
                .add("answer3", rs.getString("question_answer3"))
                .add("answer4", rs.getString("question_answer4"))
                .add("answer", rs.getString("question_answer")));
            }
        }
        jsonB = jsonB.add("questions", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    

    /**
     * Η συνάρτηση exam_con ελέγχει αν υπάρχει διαθέσιμη εξέταση και αν 
     * ο συγκεκριμένος χρήστης έχει δικαίωμα συμμετοχής σε αυτή.
     */
    public int exam_con( String username) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        int match_id = 0;
        int key = 0;

        //Εύρεση match_id
        String sql = "SELECT match_id FROM exam_matches, users WHERE username = '" + username + "' AND exam_matches.user_id = users.user_id";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            match_id = rs.getInt("match_id");
        }
        String sql1 = "SELECT exams.exam_id, exam_status, username FROM exams, exam_matches, users "
                    + "WHERE users.user_id = exam_matches.user_id "
                    + "AND exams.exam_id = exam_matches.exam_id AND exam_status = 1 AND username = '" 
                    + username + "'";
        rs = myStatement.executeQuery(sql1);
        while (rs.next()) {
            key = match_id; 
        }
        //Τσεκάρουμε να μην έχει ξανακάνει την εξέταση.
        String sql2 = "SELECT * FROM logs WHERE match_id = '" + match_id + "'";
        rs = myStatement.executeQuery(sql2);
        while (rs.next()) {            
            key = 0;
        }
        myStatement.close();
        myConnection.close();        
        return key;
    }
    
  
    /**
     * Είναι υπεύθυνη για την ενεργοποίηση/απενεργοποίηση της κάθε εξέτασης(από τον Υπέυθυνο).
     */
    public String exam_enable(String id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String sql = "UPDATE exams SET exam_status = !exam_status WHERE exam_id = " + id;
        int rs = myStatement.executeUpdate(sql);
        if (rs <= 0) return "Error";
        myStatement.close();
        myConnection.close();
        return "Επιτυχής ενέργεια";
    }
    
    /**
     * Κάνουμε εισαγωγή ερώτησης(μέσω φόρμας) από τον admin.
     */
    
    public String add_question(String question,String answer1,String answer2,String answer3,String answer4,String answer) throws ClassNotFoundException, SQLException {

        //Δημιουργώ ένα connect με τη βάση μου.
        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        String message = "Error";

        if (question == null || question.isEmpty()) return message;
        String sql = "INSERT INTO questions (question, question_answer1, question_answer2, question_answer3, "
                       + "question_answer4, question_answer) "
                       + "VALUES ('" + question + "', '" + answer1 + "', '" + answer2 + "', '" + answer3 + "', '" + answer4 + "', '" + answer + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Η ερώτηση ήδη υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
    /**
     * Κάνουμε εισαγωγή υποψήφιου(μέσω φόρμας) από τον admin.
     */
    
    public String addUser(String username,String password,String full_name) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String message = "Error";

        if (username == null || username.isEmpty()) return message;
        String sql = "INSERT INTO users (username, password, full_name, role_id) "
                   + "VALUES ('" + username + "', '" + password + "', '" + full_name + "', 3);";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Ο χρήστης ήδη υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
   /**
    * Εισάγει εξεταστικό κέντρο(μέσω φόρμας) από τον admin.
    */ 
    
    public String addExamCenter(String center_name,String center_addr) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String message = "Error";

        if (center_name == null || center_name.isEmpty()) return message;
        String sql = "INSERT INTO exam_centers (exam_center_name, exam_center_addr) "
                   + "VALUES ('" + center_name + "', '" + center_addr + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Το κέντρο ήδη υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
    
    /**
     * Εισάγει εξέταση(μέσω φόρμας) από τον admin.
     */
    
    public String addExam(String datetime,String center_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String message = "Error";

        if (datetime == null || datetime.isEmpty()) return message;
        String sql = "INSERT INTO exams (exam_datetime, exam_center_id) "
                   + "VALUES ('" + datetime + "', '" + center_id + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Λανθασμένη εισαγωγή!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
    /**
     * Καταχώρηση αποτελέσματος κατα την ολοκλήρωση της εξέτασης.
     */
    
    public String addResult(String match_id,String result) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String message = "Επιτυχής εισαγωγή!";

        String sql = "INSERT INTO results (match_id, result) VALUES (" + match_id + ", " + result + ")";
        try {
            myStatement.executeUpdate(sql);
        }
        catch (SQLException a) {
            message = "Error";
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
    
    /**
     * Καταχώρηση της απάντησης του εξεταζόμενου κάθε φορά που απαντάει σε μια ερώτηση.
     */
    
    public String answerQuestion(String match_id,String id,String answer,String user_answer) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        String message = "Επιτυχής εισαγωγή!";

        String sql = "INSERT INTO logs (match_id, question_id, question_answer, question_answer_user) "
                    + "VALUES ('" + match_id + "', '" + id + "', "
                    + "'" + answer + "', "
                    + "'" + user_answer + "')";
        try {
            myStatement.executeUpdate(sql);
        }
        catch (SQLException a) {
            message = "Error";
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
    
    /**
     * Εμφανίζει όλα τα εξεταστικά κέντρα που δεν έχουν ακόμη υπεύθυνο(από τον admin) 
     */
    
    public String centersAva() throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT * FROM exam_centers WHERE user_id IS NULL";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_center_id"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr")));
        }
        jsonB = jsonB.add("centers", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    /**
     * Επιστρέφει όλα τα εξεταστικά κέντρα στον admin.
     */
    public String centers() throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT * FROM exam_centers";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_center_id"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr")));
        }
        jsonB = jsonB.add("centers", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    
     /**
     * Επιστρέφει όλες τις εξετάσεις.
     */
    
    public String Allexams() throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT * from  exams ";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_id"))
            .add("status", rs.getString("exam_status")));
        }
        jsonB = jsonB.add("all_exams", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
        
        
    /**
     * Επιστρέφει όλες τους users.
     */
    
    public String AllUsers() throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT * from  users where role_id = 3";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("user_id"))
            .add("username", rs.getString("username"))
            .add("name", rs.getString("full_name")));
        }
        jsonB = jsonB.add("all_users", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }     
    
    /**
     * Εμφανίζει τα εξεταστικά κέντρα και τις εξετάσεις που έχει ένας Υπεύθυνος.
     */
    
    public String examInfo(String username) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT exam_id, exam_datetime, exam_center_name, exam_center_addr, exam_status FROM exams, exam_centers, users "
                + "WHERE exams.exam_center_id = exam_centers.exam_center_id "
                + "AND users.user_id = exam_centers.user_id AND username = '" + username + "'";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("status", rs.getString("exam_status")));
        }
        jsonB = jsonB.add("resposible", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    /**
     * Επιστρέφει τους εξεταζόμενους που δεν έχουν αντιστοιχηθεί ακόμη με εξέταση.
     */
    
    public String usersAvaM() throws ClassNotFoundException, SQLException {
 
        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT user_id, username, password, full_name FROM users "
                   + "WHERE user_id NOT IN (SELECT user_id FROM exam_matches) "
                   + "AND role_id = 3";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("user_id"))
            .add("username", rs.getString("username"))
            .add("password", rs.getString("password"))
            .add("full_name", rs.getString("full_name")));
        }
        jsonB = jsonB.add("users", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    
    /**
     * Εμφάνιση των χρηστών που είναι/δύναται να γίνουν Υπεύθυνοι εξεταστικού κέντρου.
     */
    
    public String usersAvaRe() throws ClassNotFoundException, SQLException {
        
        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT user_id, username, password, full_name FROM users "
                   + "WHERE user_id NOT IN (SELECT user_id FROM exam_matches) "
                   + "AND role_id != 1";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("user_id"))
            .add("username", rs.getString("username"))
            .add("password", rs.getString("password"))
            .add("full_name", rs.getString("full_name")));
        }
        jsonB = jsonB.add("users", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
 
    /**
     * Γίνετε αντιστοίχιση εξεταζόμενου με εξεταστικό κέντρο.
     */
    
    public String usersMatch(String exam_id,String user_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        if (exam_id == null || user_id == null) return "Σφάλμα!";
        String sql = "INSERT INTO exam_matches (exam_id, user_id) VALUES (" + exam_id + ", " + user_id + ")";
        myStatement.executeUpdate(sql);
        myStatement.close();
        myConnection.close();
        return "Ενέργεια επιτυχής!";
    }
    
    /**
     * Πιστοποίηση Υπεύθυνου σε εξεταστικό κέντρο από τον admin.
     */
    
    public String usersRe(String center_id,String user_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        if (center_id == null || user_id == null) return "Σφάλμα!";
        String sql1 = "UPDATE exam_centers SET user_id = " + user_id + " WHERE exam_center_id = " + center_id; 
        myStatement.executeUpdate(sql1);
        String sql2 = "SELECT role_id FROM users WHERE user_id = " + user_id;
        ResultSet rs = myStatement.executeQuery(sql2);
        while (rs.next()) {
            if (rs.getString("role_id").equals("3")) {
                sql2 = "UPDATE users SET role_id = 2 WHERE user_id = " + user_id; 
                myStatement.executeUpdate(sql2);
            }
        }
        myStatement.close();
        myConnection.close();
        return "Ενέργεια επιτυχής!";
    }
    
    
    
    public String log1() throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT exams.exam_id, exam_datetime, exam_center_name, exam_center_addr, username, result FROM exams, exam_centers, users, results, exam_matches "
                + "WHERE users.user_id = exam_matches.user_id AND results.match_id = exam_matches.match_id AND exams.exam_id = exam_matches.exam_id "
                + "AND exam_centers.exam_center_id = exams.exam_center_id";
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("username", rs.getString("username"))
            .add("result", rs.getString("result")));
        }
        jsonB = jsonB.add("results", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    
    
    
    public String log2(String center_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT exams.exam_id, exam_datetime, exam_center_name, exam_center_addr, username, result FROM exams, exam_centers, users, results, exam_matches "
                + "WHERE users.user_id = exam_matches.user_id AND results.match_id = exam_matches.match_id "
                + "AND exams.exam_id = exam_matches.exam_id AND exam_centers.exam_center_id = exams.exam_center_id AND exams.exam_center_id = " + center_id;
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("username", rs.getString("username"))
            .add("result", rs.getString("result")));
        }
        jsonB = jsonB.add("results", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    
    
    public String log3(String exam_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT exams.exam_id, exam_datetime, exam_center_name, exam_center_addr, username, result FROM exams, exam_centers, users, results, exam_matches "
                + "WHERE users.user_id = exam_matches.user_id AND results.match_id = exam_matches.match_id AND exams.exam_id = exam_matches.exam_id "
                + "AND exam_centers.exam_center_id = exams.exam_center_id AND exams.exam_id = " + exam_id;
        ResultSet rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("username", rs.getString("username"))
            .add("result", rs.getString("result")));
        }
        jsonB = jsonB.add("results", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
    
    
    
    public String log4(String user_id) throws ClassNotFoundException, SQLException {

        String myDatabase = "jdbc:mysql://localhost:3306/quiz?useUnicode=yes&characterEncoding=UTF-8&user=root&password=root";
        Connection myConnection;
        Statement myStatement;
        ResultSet rs;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT logs.question_id, question, logs.question_answer, logs.question_answer_user,"
                + " datetime FROM logs, questions, exam_matches, results WHERE user_id = " + user_id + ""
                + " AND exam_matches.match_id = logs.match_id AND logs.question_id = questions.question_id "
                + "AND logs.match_id = results.match_id";
        rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("question_id"))
            .add("question", rs.getString("question"))
            .add("answer", rs.getString("question_answer"))
            .add("answer", rs.getString("question_answer"))
            .add("answer_user", rs.getString("question_answer_user"))
            .add("datetime", rs.getString("datetime")));
        }
        jsonB = jsonB.add("user", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();

    }
        
    
    
    
    
    
    
    
}