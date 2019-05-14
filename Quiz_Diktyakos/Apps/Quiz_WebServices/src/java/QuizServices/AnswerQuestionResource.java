/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dimitris Rakas
 */
@Path("answer_question")
public class AnswerQuestionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnswerQuestionResource
     */
    public AnswerQuestionResource() {
    }

    /**
     * @param match_id
     * @param id
     * @param answer
     * @param user_answer
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String answerQuestion(@QueryParam ("n1") String match_id, @QueryParam ("n2") String id, @QueryParam ("n3") String answer, @QueryParam ("n4") String user_answer) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.answerQuestion(match_id, id, answer, user_answer);
    }
}
