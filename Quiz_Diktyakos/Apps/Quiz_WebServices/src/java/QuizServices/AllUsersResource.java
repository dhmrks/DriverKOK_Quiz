/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizServices;

import java.sql.SQLException;
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
@Path("all_users")
public class AllUsersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of All_usersResource
     */
    public AllUsersResource() {
    }

    /**
     * Επιστρέφει όλους τους εξεταζόμενους που έχουν συμμετάσσχει σε εξέταση.
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String AllUsers() throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.AllUsers();
    }
}
