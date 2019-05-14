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
@Path("add_exam_center")
public class AddExamCenterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddExamCenterResource
     */
    public AddExamCenterResource() {
    }

    /**
     * @param center_name
     * @param center_addr
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addExamCenter(@QueryParam ("n1") String center_name, @QueryParam ("n2") String center_addr) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.addExamCenter(center_name, center_addr);
    }
}
