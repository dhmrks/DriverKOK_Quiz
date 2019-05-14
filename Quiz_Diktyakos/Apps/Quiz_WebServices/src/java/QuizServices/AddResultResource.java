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
@Path("add_result")
public class AddResultResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddResultResource
     */
    public AddResultResource() {
    }

    /**
     * @param match_id
     * @param result
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addResult(@QueryParam ("n1") String match_id, @QueryParam ("n2") String result) throws ClassNotFoundException, SQLException {        DBManager db = DBManager.getInstance();
        return db.addResult(match_id, result);
    }
}
