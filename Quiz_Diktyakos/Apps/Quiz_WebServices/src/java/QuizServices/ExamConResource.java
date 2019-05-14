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
@Path("exam_con")
public class ExamConResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamConfirmResource
     */
    public ExamConResource() {
    }

    /**
     * @param username
     * @return match_id ως key από την εξέταση που αντιστοιχεί στον user.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("text/plain")
    public int exam_con(@QueryParam ("n1") String username) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.exam_con(username);
    }
}
