/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dimitris Rakas
 */
@Path("examq")
public class ExamQResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamResource
     */
    public ExamQResource() {
    }

/**
     * Επιστρέφει 5 τυχαίες ερωτήσεις από την βάση.
     * @return ένα String που περιέχει τις ερωτήσεις σε μορφη json array. Σε περίπτωση σφάλματος επιστρέφεται μήνυμα. 
     * Μορφή json :
     * { "questions": 
     *      [{"id": Κωδικός ερώτησης,
     *        "question": Ερώτηση,
     *        "answer1": Απάντηση 1,
     *        "answer2": Απάντηση 2,
     *        "answer3": Απάντηση 3,
     *        "answer4": Απάντηση 4,
     *        "answer": Σωστή απάντηση },
     *        ... ,
     *       {"id": Κωδικός ερώτησης,
     *        "question": Ερώτηση,
     *        "answer1": Απάντηση 1,
     *        "answer2": Απάντηση 2,
     *        "answer3": Απάντηση 3,
     *        "answer4": Απάντηση 4,
     *        "answer": Σωστή απάντηση }
     *      ]
     * }
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String examq() throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.examq();
    }
}
