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
@Path("add_user")
public class AddUserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddCandidateResource
     */
    public AddUserResource() {
    }

    /**
     * Γίνετε εισαγωγή χρήστη μέσω φόρμας,παίρνει τα στοιχεία σαν παραμέτρους και τα εισάγει στην βάση.
     * @param username
     * @param password
     * @param full_name
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@QueryParam ("n1") String username, @QueryParam ("n2") String password, @QueryParam ("n3") String full_name) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.addUser(username, password, full_name);
    }
}
