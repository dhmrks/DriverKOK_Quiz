/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizServices;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Dimitris Rakas
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(QuizServices.AddExamCenterResource.class);
        resources.add(QuizServices.AddExamResource.class);
        resources.add(QuizServices.AddQuestionResource.class);
        resources.add(QuizServices.AddResultResource.class);
        resources.add(QuizServices.AddUserResource.class);
        resources.add(QuizServices.AllExamsResource.class);
        resources.add(QuizServices.AllUsersResource.class);
        resources.add(QuizServices.AnswerQuestionResource.class);
        resources.add(QuizServices.CentersAvaResource.class);
        resources.add(QuizServices.CentersResource.class);
        resources.add(QuizServices.ExamConResource.class);
        resources.add(QuizServices.ExamEnableResource.class);
        resources.add(QuizServices.ExamInfoResource.class);
        resources.add(QuizServices.ExamQResource.class);
        resources.add(QuizServices.Log1Resource.class);
        resources.add(QuizServices.Log2Resource.class);
        resources.add(QuizServices.Log3Resource.class);
        resources.add(QuizServices.Log4Resource.class);
        resources.add(QuizServices.LoginResource.class);
        resources.add(QuizServices.UsersAvaMResource.class);
        resources.add(QuizServices.UsersAvaReResource.class);
        resources.add(QuizServices.UsersMatchResource.class);
        resources.add(QuizServices.UsersReResource.class);
    }
    
}
