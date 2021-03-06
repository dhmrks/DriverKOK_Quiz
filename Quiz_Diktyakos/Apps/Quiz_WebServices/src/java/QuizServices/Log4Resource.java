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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dimitris Rakas
 */
@Path("log4")
public class Log4Resource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Log4Resource
     */
    public Log4Resource() {
    }

    /**
     * Εμφάνιση των ερωτήσεων για κάθε εξεταζόμενο.
     * @param user_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String log4(@QueryParam ("n1") String user_id) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.log4(user_id);
    }
}
