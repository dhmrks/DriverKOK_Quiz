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
@Path("log2")
public class Log2Resource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Log2Resource
     */
    public Log2Resource() {
    }

    /**
     * Αποτελέσματα ανα εξεταστικό κέντρο.
     * @param center_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String log2(@QueryParam ("n1") String center_id) throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.log2(center_id);
    }
}