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
@Path("users_re")
public class UsersReResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersResponsibleResource
     */
    public UsersReResource() {
    }

    /**
     * @param center_id
     * @param user_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String usersRe(@QueryParam ("n1") String center_id, @QueryParam ("n2") String user_id) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.usersRe(center_id, user_id);
    }
}
