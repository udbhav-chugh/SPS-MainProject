package com.google.sps.servlets;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Survey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

class ProjectAgeCount{
    private final long ProjectID;
    private ArrayList<Integer> ageGroupCount;
    
    ProjectAgeCount(final long ProjectID){
        this.ProjectID= ProjectID;
        this.ageGroupCount = new ArrayList<Integer>();
        ageGroupCount = new ArrayList<Integer>();
        for(int i=0;i<4;i++)
            ageGroupCount.add(0);
    }

    void incrementAgeCount(int index ){
        if(index==-1)
            return;
        Integer value = this.ageGroupCount.get(index); // get value
        value = value + 1; // increment value
        this.ageGroupCount.set(index, value); // replace value
       
    }
   
}

/** Servlet responsible for listing tasks. */
@WebServlet("/age-count")
public class SurveyDataServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    long ProjectID= Long.parseLong(request.getParameter("productid"));
    ProjectAgeCount obj= getProjectAgeCountObject(ProjectID) ;

    
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(obj));
  }


  ProjectAgeCount getProjectAgeCountObject(final long ProjectID){
      //iterate over results and create ProjectVote object
      ProjectAgeCount ageCountObj= new ProjectAgeCount(ProjectID);

      PreparedQuery results= getQueryResults(ProjectID);

      for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        
        int posValue= (int) entity.getProperty("ageGroupCount");
        ageCountObj.incrementAgeCount(posValue);
        }

      return ageCountObj;


  }

  PreparedQuery getQueryResults(final long ProjectID){
      //build and prepare query results
        Filter projectIDFilter =
             new FilterPredicate("ProjectID", FilterOperator.EQUAL, ProjectID);
        Query query = new Query("Survey").setFilter(projectIDFilter);
   

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    return results;  
  }

}