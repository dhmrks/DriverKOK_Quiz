
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
@Path("all_exams")
public class AllExamsResource {

    @Context
    private UriInfo context;

    /**
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String Allexams() throws ClassNotFoundException, SQLException {
        DBManager db = DBManager.getInstance();
        return db.Allexams();
    }
}
