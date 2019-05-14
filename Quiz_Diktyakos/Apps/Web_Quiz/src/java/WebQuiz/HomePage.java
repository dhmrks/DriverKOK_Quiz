package WebQuiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONObject;

/**
 *
 * @author Dimitris Rakas
 */
@WebServlet(name = "HomePage", urlPatterns = {"/HomePage"})
public class HomePage extends HttpServlet {

   private static final String BASE_URI = "http://localhost:8080/QuizServices/webresources";
    private final Client client = javax.ws.rs.client.ClientBuilder.newClient();
    private final WebTarget webTarget = client.target(BASE_URI);
    ArrayList<Integer> unanswered; //Βάζουμε τις αναπάντητες ερωτήσεις.
    Integer i, result; //Αριθμός ερώτησης και αποτέλεσμα εξέτασης.
    JSONObject myJson; //Αποθηκεύεται τα αποτέλεσματα web services που επιστρέφουν JSON.
    String answer, user_answer;
    JSONObject x; //Χειριζόμαστε τα πεδία ενός JSONObject.
    
    //web services
//----------------------------------------------Return Json-----------------------------------------------------
    public JSONObject exam() {
        WebTarget resource = webTarget.path("examq");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    public JSONObject exam_info(String n1) {
        WebTarget resource = webTarget.path("exam_info");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject centers_ava() {
        WebTarget resource = webTarget.path("centers_ava");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject centers() {
        WebTarget resource = webTarget.path("centers");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject users_ava_m() {
        WebTarget resource = webTarget.path("users_ava_m");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject users_ava_re() {
        WebTarget resource = webTarget.path("users_ava_r");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject return_all_exams() {
        WebTarget resource = webTarget.path("all_exams");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject return_all_users() {
        WebTarget resource = webTarget.path("all_users");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject log1() {
        WebTarget resource = webTarget.path("log1");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    public JSONObject log2(String n1) {
        WebTarget resource = webTarget.path("log2");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    public JSONObject log3(String n1) {
        WebTarget resource = webTarget.path("log3");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    public JSONObject log4(String n1) {
        WebTarget resource = webTarget.path("log4");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
//----------------------------------------------Return Strings-----------------------------------------------------
    public String users_match(String n1, String n2) {
        WebTarget resource = webTarget.path("users_match");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String users_resp(String n1, String n2) {
        WebTarget resource = webTarget.path("users_re");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String exam_enable(String n1) {
        WebTarget resource = webTarget.path("exam_enable");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String answer_question(String n1, String n2, String n3, String n4) {
        WebTarget resource = webTarget.path("answer_question");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        resource = resource.queryParam("n4", n4);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_user(String n1, String n2, String n3) {
        WebTarget resource = webTarget.path("add_user");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_question(String n1, String n2, String n3, String n4, String n5, String n6) {
        WebTarget resource = webTarget.path("add_question");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        resource = resource.queryParam("n4", n4);
        resource = resource.queryParam("n5", n5);
        resource = resource.queryParam("n6", n6);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_exam_center(String n1, String n2) {
        WebTarget resource = webTarget.path("add_exam_center");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_exam(String n1, String n2) {
        WebTarget resource = webTarget.path("add_exam_center");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_result(String n1, int n2) {
        WebTarget resource = webTarget.path("add_result");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    // 5 τυχαίες ερωτήσεις από την αποθήκη ερωτήσεων του test.
    public void fiveQuestions(int i, PrintWriter out) {
                out.println("<form action=\"\" method=\"post\" style=\"text-align: center; margin-top: 20%;\">");
                out.println("<h4>" + (i + 1) + ". " + x.get("question") + "</h4>");
                out.println("<div style=\"margin-left: 50%; width: 250px; text-align: left;\">");
                out.println("<input style=\"float: left;\"checked type=\"radio\" name=\"option\" value=\"" + x.get("answer1") + "\">" + x.get("answer1") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer2") + "\">" + x.get("answer2") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer3") + "\">" + x.get("answer3") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer4") + "\">" + x.get("answer4") + "</input><br>");
                out.println("</div>");
                out.println("<br><input name=\"confirm\" type=\"submit\" value=\"Οριστικοποίηση\">");
                out.println("<input name=\"next\" type=\"submit\" value=\"Επόμενη\">");
                out.println("</form>");
            }
  
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Δημιουργούμαι το session για την αποθήκευση attributes.
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
        response.setHeader("Apo8hkeush", "no-cache"); //HTTP 1.0 backward compatibility 
        request.setCharacterEncoding("UTF-8"); //Default charset
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Εξέταση Home</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                        "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            
            if(session != null) {
                
                out.println("<nav class=\"navbar navbar-inverse\">\n" +
                            "   <div class=\"container-fluid\">\n" +
                            "       <div  class=\"navbar-header\">\n" +
                            "          <a class=\"navbar-brand\" href=\"#\">\n" +
                            "          <img src=\"logo.png\" width=\"90\" height=\"100\" class=\"d-inline-block align-top\" alt=\"\">\n" +
                            "           <br><br>  " +
                            "          <a class=\"navbar-brand\">Σήματα ΚΟΚ</a>\n" +
                            "       </div>\n" +
                            "       <ul class=\"nav navbar-nav\"> \n" +
                            "       <!--<li><a href=\"moderator.php\">Αρχική</a></li>-->");
                            //------------ADMIN---------------
                            if (session.getAttribute("role").equals("1")) {
                                out.println(
                                            "   <button class=\"btn btn-default dropdown-toggle\" type=\"button\" id=\"menu1\" style=\"text-align  right; margin-top: 16%;\"  data-toggle=\"dropdown\">\n" + 
                                            "     Ενέργειες\n" + 
                                            "               <span class=\"caret\"></span></button>\n" +
                                            "              <ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"menu1\">\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Εισαγωγή ερώτησης</li>\n" +
                                            "                <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"?option=questions_form\">Μέσω φόρμας</a></li>\n" +
                                            "                <li role=\"presentatioν\" class=\"divider\"></li>\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Εισαγωγή χρήστη</li>\n" +
                                            "                <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"?option=users_form\">Μέσω φόρμας</a></li>\n" +
                                            "                <li role=\"presentatioν\" class=\"divider\"></li>\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Εισαγωγή κέντρου</li>\n" +
                                            "                <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"?option=centers\">Κέντρο</a></li>\n" +
                                            "                <li role=\"presentatioν\" class=\"divider\"></li>\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Εισαγωγή εξέτασης</li>\n" +
                                            "                <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"?option=exams\">Εξέταση</a></li>\n" +
                                            "                <li role=\"presentatioν\" class=\"divider\"></li>\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Πιστοποίηση υπεύθυνου</li>\n" +
                                            "                <li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"?option=responsibles\">Πιστοποίηση</a></li>\n" +
                                            "                <li role=\"presentatioν\" class=\"divider\"></li>\n" +
                                            "                <li role=\"presentation\" class=\"dropdown-header\">Αναφορές συστήματος</li>\n" +
                                            "                <li><a href=\"?option=log1\">Αναφορά 1η</a></li>\n" +
                                            "                <li><a href=\"?option=log2\">Αναφορά 2η</a></li>\n" +
                                            "                <li><a href=\"?option=log3\">Αναφορά 3η</a></li>\n" +
                                            "                <li><a href=\"?option=log4\">Αναφορά 4η</a></li>\n" +
                                            "              </ul>\n" );
                            }
                            out.println("       </ul>\n" +
                                        "       <ul class=\"nav navbar-nav navbar-right\">\n" +
                                        "           <li><a> Χρήστης: " + session.getAttribute("name") + "</a></li>\n" +
                                        "           <li><a href=\"Logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Exit</a></li>\n" +
                                        "       </ul>\n" +
                                        "   </div>\n" +
                                        "</nav>");
                //----------------Εξεταζόμενος-----------------
                if (session.getAttribute("role").equals("3")) { 
                    //Ελ;eγχουμε αν ο χρήστης δικαιούται να συμμετάσχει σε μια εξέταση.
                    String confirm = (String) session.getAttribute("confirm");
                    if (confirm.equals("0")) {
                         out.println("<h1 style=\"font-size:60px;\" \"text-align: center; margin-top: 20%;\">Δεν υπάρχει καμία εξέταση για σένα!</h1>");
                        return;                                     
                    }

                    //Αποθήκευση μεταβλητών ως session attributes προς αποφυγή μοιράσματος αυτών σε άλλη εκτέλεση.
                    i = (Integer) session.getAttribute("question");
                    myJson = (JSONObject) session.getAttribute("exam");
                    unanswered = (ArrayList<Integer>) session.getAttribute("unanswered");
                    x = (JSONObject) session.getAttribute("answer");
                    //Αποτέλεσμα
                    result = (Integer) session.getAttribute("result");
                    //Αρχικοποίηση των βασικών σμεταβλητών
                    if (i == null) {
                        unanswered = new ArrayList();
                        for (int j = 0; j < 5; j++)
                            unanswered.add(j);
                            session.setAttribute("unanswered", unanswered);
                            i = 0;
                            session.setAttribute("question", i);
                            myJson = exam();
                            session.setAttribute("exam", myJson);
                            result = 0;
                            session.setAttribute("result", result);
                    }    
                    
          //Χρόνος 5' (Δεν δουλεύει σωστά διότι κανει ρεφρες κάθε φορά που πατάω καταχωρώ απάντηση)
            
           out.println("<br></br>\n" +
                     "  <script> \n" +
                        "function startTimer(duration, display) {\n" +
                             " var timer = duration, minutes, seconds;\n" +
                             " setInterval(function () {\n" +
                              "    minutes = parseInt(timer / 60, 10);\n" +
                               "   seconds = parseInt(timer % 60, 10);\n" +

                                "  minutes = minutes < 10 ? \"0\" + minutes : minutes;\n" +
                                " seconds = seconds < 10 ? \"0\" + seconds : seconds;\n" +
                                "     display.textContent = minutes + \":\" + seconds;\n" +
                                "    if (--timer < 0) {\n" +
                                "      var xronos = 1 ;\n" +
                                "       alert(\"Time is up!\"); }\n" +
                                "}, 1000);\n" +
                             "}\n" +
                            "var time;\n" +
                            "window.onload = function () {\n" +
                            "    time=new Date();\n" +
                            "  var fiveMinutes = 60 * 5,\n" +
                            "     display = document.querySelector('#time');\n" +
                            "startTimer(fiveMinutes, display);\n" +
                            "};\n" +
                    "</script><div id=\"time\" class=\"time_box\">05:00</div> \n" +
                   "</div>"
                         );
                            
                    

                    if (request.getParameter("confirm") != null) {
                        user_answer = request.getParameter("option");
                        answer = (String) x.get("answer");
                        String question_id = (String) x.get("id");
                        answer_question(confirm, question_id, answer, user_answer);
                        System.out.println(answer + ": " + user_answer + " --> " + session.getAttribute("name")); //για debuging
                        //Συλλέγουμε το συνολικό αποτέλεσμα απαντήσεων στο result
                        if (answer.equals(user_answer)) { 
                            result++;
                            session.setAttribute("result", result);  
                        }
                        if (i < 4 && unanswered.size() > 0) {
                            unanswered.remove(i);
                            if (unanswered.isEmpty() ) {
                                add_result(confirm, result);
                                session.invalidate(); //τελιώνει η εξέταση και απενεργοποιήτε το session.
                                response.sendRedirect("LoginPage"); //Επιστρέφει στην αρχική.
                                return;
                            }
                            else {
                                while (!unanswered.contains(i + 1) && i < 4) {
                                    i++;
                                    session.setAttribute("question", i);
                                }
                                if (i > 3) {
                                    i = unanswered.get(0);
                                    session.setAttribute("question", i);
                                }
                                else {
                                    i++;
                                    session.setAttribute("question", i);
                                }
                            }
                        }
                        else {
                            if (i == 4) 
                                unanswered.remove(i);
                            if (!unanswered.isEmpty()) {
                                i = unanswered.get(0);  
                                session.setAttribute("question", i);
                            }
                            else {
                                add_result(confirm, result);
                                session.invalidate();
                                response.sendRedirect("LoginPage");
                                return;
                            }
                        }      
                    }
                    //Επόμενη ερώτηση
                    if (request.getParameter("next") != null) {
                        while (!unanswered.contains(i + 1) && i < 4) {
                            i++;
                            session.setAttribute("question", i);
                        }
                        if (i < 4) {
                          i++;
                          session.setAttribute("question", i);
                        }
                        else {
                            i = unanswered.get(0);
                            session.setAttribute("question", i);
                        }    
                    }
                    //Κλήση της fiveQuestions για την εξέταση.
                    x = (JSONObject) myJson.getJSONArray("questions").get(i);
                    session.setAttribute("answer", x);
                    fiveQuestions(i, out);
                    System.out.println(unanswered + " --> " + session.getAttribute("name"));

                }   //--------------------Υπεύθυνος----------------------
                else if (session.getAttribute("role").equals("2")) { 
                    if (request.getParameter("status") != null)
                        exam_enable(request.getParameter("status"));
                    if (request.getParameter("match") != null)
                        users_match(request.getParameter("exams"), request.getParameter("match"));
                    String user = (String) session.getAttribute("name");
                    out.println("<br><br></br></br>\n");
                    out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\"  border = \"1\" width = \"70%\">");
                    out.println("<th>Ημερομηνία & ώρα</th><th>Kέντρο</th><th>Διεύθυνση</th>" + 
                                "<th>Κατάσταση</th><th>Ενέργεια</th>");
                    out.println("<tr>");
                    String status;
                    myJson = exam_info(user);
                    i = 0;
                    while (i < myJson.getJSONArray("resposible").length()) { 
                        x = (JSONObject) myJson.getJSONArray("resposible").get(i); 
                        if (x.getString("status").equals("1"))
                            status  = "Ενεργή";
                        else
                            status  = "Ανενεργή";
                        out.println("<td>" + x.getString("datetime") + "</td>");
                        out.println("<td>" + x.getString("center_name") + "</td>");
                        out.println("<td>" + x.getString("center_addr") + "</td>");
                        out.println("<td>" + status + "</td>");
                        out.println("<td style=\"width: 15%\"><form method=\"post\" action=\"\">");
                        if (status.equals("Ανενεργή"))
                            out.println("<button type=\"submit\" name=\"status\" id=\"linkButton\" value=\"" + x.getString("id") + "\">Ενεργοποίηση</button>");
                        else
                            out.println("<button type=\"submit\" name=\"status\" id=\"linkButton\" value=\"" + x.getString("id") + "\">Απενεργοποίηση</button>");
                        out.println("</form></td></tr>");
                        i++;
                    }
                    out.println("</table>");
                    //Αρχικοποίηση μεταβλητών και δήλωση βοηθητικών
                    i = 0;
                    JSONObject myJson2 = users_ava_m(), y;
                    out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                    out.println("<th>Username</th><th>Ονοματεπώνυμο</th><th>Κέντρο</th><th>Ενέργεια</th>"); 
                    out.println("<tr>");
                    while (i < myJson2.getJSONArray("users").length()) { 
                        int j = 0;
                        y = (JSONObject) myJson2.getJSONArray("users").get(i);
                        out.println("<td>" + y.getString("username") + "</td>");
                        out.println("<td>" + y.getString("full_name") + "</td>");
                        out.println("<td>");
                        out.println("<form method=\"post\" action=\"\"><select name=\"exams\">");
                        while (j < myJson.getJSONArray("resposible").length()) { 
                            x = (JSONObject) myJson.getJSONArray("resposible").get(j);
                            out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                            j++;
                        }
                        out.println("</select></td>");
                        out.println("<td style=\"width: 20%\">");
                        if (j != 0) //Αν υπάρχει εξέταση με το επιλεγμένο κέντρο
                            out.println("<button type=\"submit\" name=\"match\" id=\"linkButton\" value=\"" + y.getString("id") + "\">Αντιστοίχιση</button>");
                        out.println("</form></td></tr>");
                        i++;
                    }
                    out.println("</table>");
                }
                //----------------ADMIN--------------------------
                else { 
                    if ("questions_form".equals(request.getParameter("option"))) {
                        //Εισαγωγή ερώτησης
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center;  width: 400px;\"><h4>Εισαγωγή ερώτησης</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Ερώτηση:<br>\n" +
                                    "<input required type=\"text\" name=\"question\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Απάντηση 1:<br>\n" +
                                    "<input required type=\"text\" name=\"answer1\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Απάντηση 2:<br>\n" +
                                    "<input required type=\"text\" name=\"answer2\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Απάντηση 3:<br>\n" +
                                    "<input required type=\"text\" name=\"answer3\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Απάντηση 4:<br>\n" +
                                    "<input required type=\"text\" name=\"answer4\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Σωστή απάντηση:<br>\n" +
                                    "<input required type=\"text\" name=\"answer\" value=\"\">\n" +
                                    "<br><br>\n" +
                                    "<button name=\"add_question\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος.
                        if (request.getParameter("add_question") != null)
                            out.println("<br>" + add_question(request.getParameter("question"), 
                                                               request.getParameter("answer1"), 
                                                               request.getParameter("answer2"),
                                                               request.getParameter("answer3"), 
                                                               request.getParameter("answer4"),
                                                               request.getParameter("answer")) + "</div>");
                        }
                        if ("users_form".equals(request.getParameter("option"))) {
                            //Εισαγωγή χρήστη
                            out.print("<br><br><br><center><div class=\"well\" style=\"text-align: center;  width: 400px;\"><h4>Εισαγωγή χρήστη</h4>");
                            out.println("<form action=\"\" method=\"post\">\n" +
                                        "Όνομα:<br>\n" +
                                        "<input required type=\"text\" name=\"username\" value=\"\">\n" +
                                        "<br>\n" +
                                        "Κωδικός:<br>\n" +
                                        "<input required type=\"text\" name=\"password\" value=\"\">\n" +
                                        "<br>\n" +
                                        "Ονοματεπώνυμο: <br>\n" +
                                        "<input required type=\"text\" name=\"full_name\" value=\"\">\n" +
                                        "<br><br>\n" +
                                        "<button name=\"add_user\"type=\"submit\"  class=\"btn btn-success\">submit</button>" + 
                                        "</form>");
                            //Εκτέλεση και εμφάνιση αποτελέσματος του web service.
                            if (request.getParameter("add_user") != null)
                                out.println("<br><b>" + 
                                            add_user(request.getParameter("username"), 
                                                          request.getParameter("password"), 
                                                          request.getParameter("full_name")) + "</b></div>");
                    }
                    if ("centers".equals(request.getParameter("option"))) {
                        //Εισαγωγή εξεταστικού κέντρου.
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 400px;\"><h4>Εισαγωγή εξεταστικού κέντρου</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Όνομα:<br>\n" +
                                    "<input required type=\"text\" name=\"center_name\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Διεύθυνση:<br>\n" +
                                    "<input required type=\"text\" name=\"center_addr\" value=\"\">\n" +
                                    "<br><br>\n" +
                                    "<button name=\"add_center\"type=\"submit\" class=\"btn btn-success\">submit</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος.
                        if (request.getParameter("add_center") != null)
                            out.println("<br><b>" + 
                                        add_exam_center(request.getParameter("center_name"), 
                                                   request.getParameter("center_addr")) + "</b></br></div>");
                    }
                    //Πιστοποίηση
                    if (request.getParameter("resp") != null)
                        users_resp(request.getParameter("centers"), request.getParameter("resp"));
                    if ("responsibles".equals(request.getParameter("option"))) {
                        out.println("<br><br></br</br><table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>Όνομα κέντρου</th><th>Διεύθυνση</th>");
                        out.println("<tr>");
                        myJson = centers_ava();
                        i = 0;
                        while (i < myJson.getJSONArray("centers").length()) { 
                            x = (JSONObject) myJson.getJSONArray("centers").get(i);
                            //Εκτέλεση ενέργειας web service 
                            out.println("<tr><td>" + x.getString("center_name") + "</td>");
                            out.println("<td>" + x.getString("center_addr") + "</td>");
                            i++;
                        }
                        out.println("</table>");
                        //Αρχικοποίηση μεταβλητών και δήλωση βοηθητικών
                        i = 0;
                        JSONObject myJson2 = users_ava_re(), y;
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>username</th><th>Ονοματεπώνυμο</th><th>Επιλογή Κέντρου</th><th>Ενέργεια</th>"); 
                        out.println("<tr>");
                        while (i < myJson2.getJSONArray("users").length()) { 
                            int j = 0;
                            y = (JSONObject) myJson2.getJSONArray("users").get(i);
                            out.println("<td>" + y.getString("username") + "</td>");
                            out.println("<td>" + y.getString("full_name") + "</td>");
                            out.println("<td>");
                            out.println("<form method=\"post\" action=\"\"><select name=\"centers\">");
                            while (j < myJson.getJSONArray("centers").length()) { 
                                x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                j++;
                            }
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");
                            if (j != 0) //Αν υπάρχουν εξεταστικά κέντρα χωρίς υπεύθυνο.
                                out.println("<button type=\"submit\" name=\"resp\" id=\"linkButton\" value=\"" + y.getString("id") + "\">Πιστοποίηση</button>");
                            out.println("</form></td></tr>");
                            i++;
                        }
                        out.println("</table>");
                    }
                  
                    //Αναφορές

                    if ("log1".equals(request.getParameter("option"))) {
                        i = 0;
                        out.println("<br><br><br></br></br></br>");
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>ID</th><th>Χρόνος</th><th>Εξεταστικό κέντρο</th><th>Διεύθυνση</th><th>Username</th><th>Αποτέλεσμα</th>"); 
                        out.println("<tr>");
                        myJson = log1();
                        while (i < myJson.getJSONArray("results").length()) { 
                            int j = 0;
                            x = (JSONObject) myJson.getJSONArray("results").get(i);
                            out.println("<td>" + x.getString("id") + "</td>");
                            out.println("<td>" + x.getString("datetime") + "</td>");
                            out.println("<td>" + x.getString("center_name") + "</td>");
                            out.println("<td>" + x.getString("center_addr") + "</td>");
                            out.println("<td>" + x.getString("username") + "</td>");
                            out.println("<td>" + x.getString("result") + "</td>");
                            out.println("<td>");
                            while (j < myJson.getJSONArray("centers").length()) { 
                                x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                j++;
                            }
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");;
                            i++;
                        }
                        out.println("</table>");
                    }
                    if ("log2".equals(request.getParameter("option"))) {
                        int i = 0,j = 0;
                        myJson = centers();
                        //Εισαγωγή εξέτασης
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 250px;\"><h4>Επιλογή κέντρου</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" );
                                    out.println("<select name=\"centers\">");
                                    while (j < myJson.getJSONArray("centers").length()) { 
                                        x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                        out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                        j++;
                            }
                        out.println("</select></td>");
                        out.println("<br>\n" +
                                    "<button name=\"add_exam\"type=\"submit\" class=\"btn btn-success\">submit</button>" + 
                                    "</form>");
                            out.println("<br>" +
                                        "</b></div>");
                    
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>ID</th><th>Χρόνος</th><th>Εξεταστικό κέντρο</th><th>Διεύθυνση</th><th>Username</th><th>Αποτέλεσμα</th>"); 
                        out.println("<tr>");
                        myJson = log2(request.getParameter("centers"));
                        while (i < myJson.getJSONArray("results").length()) { 
                            int k = 0;
                            x = (JSONObject) myJson.getJSONArray("results").get(i);
                            out.println("<td>" + x.getString("id") + "</td>");
                            out.println("<td>" + x.getString("datetime") + "</td>");
                            out.println("<td>" + x.getString("center_name") + "</td>");
                            out.println("<td>" + x.getString("center_addr") + "</td>");
                            out.println("<td>" + x.getString("username") + "</td>");
                            out.println("<td>" + x.getString("result") + "</td>");
                            out.println("<td>");
                            while (k < myJson.getJSONArray("centers").length()) { 
                                x = (JSONObject) myJson.getJSONArray("centers").get(k);
                                out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                k++;
                            }
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");
                            out.println("</form></td></tr>");
                            i++;
                        }
                        out.println("</table>");

                    }
                    if ("log3".equals(request.getParameter("option"))) {
                        int i = 0,j = 0;
                        myJson = return_all_exams();

                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 250px;\"><h4>Επιλογή εξέτασης</h4>");
                        out.println("<form action=\"\" method=\"post\">\n");
                                    out.println("<select name=\"all_exams\">");
                                    while (j < myJson.getJSONArray("all_exams").length()) { 
                                        x = (JSONObject) myJson.getJSONArray("all_exams").get(j);
                                        out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("id") + "</option>");
                                        j++;
                            }
                        out.println("</select></td>");
                        out.println("<br>\n" +
                                    "<button name=\"add_exam\"type=\"submit\" class=\"btn btn-success\">submit</button>" + 
                                    "</form>");
                            out.println("<br>" +
                                        "</b></div>");
                    
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>ID</th><th>Χρόνος</th><th>Εξεταστικό κέντρο</th><th>Διεύθυνση</th><th>Username</th><th>Αποτέλεσμα</th>"); 
                        out.println("<tr>");
                        myJson = log3(request.getParameter("all_exams"));
                        while (i < myJson.getJSONArray("results").length()) {
                            int k = 0;
                            x = (JSONObject) myJson.getJSONArray("results").get(i);
                            out.println("<td>" + x.getString("id") + "</td>");
                            out.println("<td>" + x.getString("datetime") + "</td>");
                            out.println("<td>" + x.getString("center_name") + "</td>");
                            out.println("<td>" + x.getString("center_addr") + "</td>");
                            out.println("<td>" + x.getString("username") + "</td>");
                            out.println("<td>" + x.getString("result") + "</td>");
                            out.println("<td>");
                            while (k < myJson.getJSONArray("all_exams").length()) {
                                x = (JSONObject) myJson.getJSONArray("all_exams").get(k);
                                out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                k++;
                            }
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");
                            out.println("</form></td></tr>");
                            i++;
                        }
                        out.println("</table>");

                    }
                    if ("log4".equals(request.getParameter("option"))) {
                        int i = 0,j = 0;
                        myJson = return_all_users();
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 250px;\"><h4>Επιλογή εξεταζόμενου</h4>");
                        out.println("<form action=\"\" method=\"post\">\n");
                                    out.println("<select name=\"all_users\">");
                                    while (j < myJson.getJSONArray("all_users").length()) { 
                                        x = (JSONObject) myJson.getJSONArray("all_users").get(j);
                                        out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("name") + "</option>");
                                        j++;
                            }
                        out.println("</select></td>");
                        out.println("<br>\n" +
                                    "<button name=\"add_exam\"type=\"submit\" class=\"btn btn-success\">submit</button>" + 
                                    "</form>");
                            out.println("<br>" +
                                        "</b></div>");
                    
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>ID</th><th>Ερώτηση</th><th>Σωστή απάντηση</th><th>Απάντηση χρήστη</th><th>Χρόνος</th>"); 
                        out.println("<tr>");
                        myJson = log4(request.getParameter("all_users"));
                        while (i < myJson.getJSONArray("user").length()) { 
                            int k = 0;
                            x = (JSONObject) myJson.getJSONArray("user").get(i);
                            out.println("<td>" + x.getString("id") + "</td>");
                            out.println("<td>" + x.getString("question") + "</td>");
                            out.println("<td>" + x.getString("answer") + "</td>");
                            out.println("<td>" + x.getString("answer_user") + "</td>");
                            out.println("<td>" + x.getString("datetime") + "</td>");
                            out.println("<td>");
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");
                            out.println("</form></td></tr>");
                            i++;
                        }
                        out.println("</table>");

                    }
                    if ("exams".equals(request.getParameter("option"))) {
                        int j = 0;
                        myJson = centers();
                        //Εισαγωγή εξέτασης
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξέτασης</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Ημερομηνία & ώρα:<br>\n" +
                                    "<input required type=\"datetime-local\" name=\"datetime\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Εξεταστικό κέντρο:<br>\n");
                                    out.println("<select name=\"centers\">");
                                    while (j < myJson.getJSONArray("centers").length()) { 
                                        x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                        out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                        j++;
                            }
                            out.println("</select></td>");
                                    out.println("<br><br>\n" +
                                    "<button name=\"add_exam\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος.
                        if (request.getParameter("add_exam") != null)
                            out.println("<br><b>" + 
                                        add_exam(request.getParameter("datetime"), 
                                                   request.getParameter("centers")) + "</b></div>");
                    }
                    
                }  
            }
            //Αν δεν υπάρχει session ή έχει λήξει.
            else {
                String contextPath = "/WebQuiz/LoginPage";
                response.sendRedirect(contextPath);
                return;
            }
            out.println("</body>");
            out.println("</html>"); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
