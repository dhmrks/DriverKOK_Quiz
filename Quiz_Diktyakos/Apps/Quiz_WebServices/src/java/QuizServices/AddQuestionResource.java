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
@Path("add_question")
public class AddQuestionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddQuestionResource
     */
    public AddQuestionResource() {
    }

    /**
     * @param question
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4
     * @param answer
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String add_question(@QueryParam ("n1") String question, @QueryParam ("n2") String answer1, @QueryParam ("n3") String answer2, @QueryParam ("n4") String answer3, @QueryParam ("n5") String answer4, @QueryParam ("n6") String answer) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.add_question(question,answer1,answer2,answer3,answer4,answer);
    }
}
