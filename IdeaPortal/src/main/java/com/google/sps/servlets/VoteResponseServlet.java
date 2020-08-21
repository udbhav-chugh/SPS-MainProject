package com.google.sps.servlets;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Vote;
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

class ProjectVote{
    private final long ProjectID;
    private long upvotes;
    private long downvotes;

    ProjectVote(final long ProjectID){
        this.ProjectID= ProjectID;
        this.upvotes=0;
        this.downvotes=0;
    }

    void incrementUpvote(){
        this.upvotes+= 1;
    }
    void incrementDownvote(){
        this.downvotes+=1;
    }

    long getUpvotes(){
        return this.upvotes;
    }
    long getDownvotes(){
        return this.downvotes;
    }
}

/** Servlet responsible for listing tasks. */
@WebServlet("/vote-response")
public class VoteResponseServlet extends HttpServlet {
    private static DatastoreService datastore;

    public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    long ProjectID= Long.parseLong(request.getParameter("productid"));
    ProjectVote obj= getProjectVoteObject(ProjectID) ;
    
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(obj));
  }

  private void temp_func(final long ProjectID){
      Entity employee = new Entity("Vote");
        employee.setProperty("ProjectID", ProjectID);
        employee.setProperty("userId", 5555);
        employee.setProperty("voteValue", 1);
        
        datastore.put(employee);

        Entity emp = new Entity("Vote");
        emp.setProperty("ProjectID", ProjectID);
        emp.setProperty("userId", 5255);
        emp.setProperty("voteValue", 1);
        
        datastore.put(emp);

        
        Entity emp1 = new Entity("Vote");
        emp1.setProperty("ProjectID", ProjectID);
        emp1.setProperty("userId", 5255);
        emp1.setProperty("voteValue", 1);
        
        datastore.put(emp1);

        Entity emp2 = new Entity("Vote");
        emp2.setProperty("ProjectID", ProjectID);
        emp2.setProperty("userId", 5255);
        emp2.setProperty("voteValue", 1);
        
        datastore.put(emp2);

        Entity emp3 = new Entity("Vote");
        emp3.setProperty("ProjectID", ProjectID);
        emp3.setProperty("userId", 5255);
        emp3.setProperty("voteValue", -1);
        
        datastore.put(emp3);

        
}
  ProjectVote getProjectVoteObject(final long ProjectID){
      //iterate over results and create ProjectVote object
      temp_func(ProjectID);
      ProjectVote voteObj= new ProjectVote(ProjectID);

      PreparedQuery results= getQueryResults(ProjectID);

      for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        
        long voteValue= (long) entity.getProperty("voteValue");
       
        if(voteValue==1)
            voteObj.incrementUpvote();
       
        if(voteValue==-1)
            voteObj.incrementDownvote();

         }

      return voteObj;

  }

  PreparedQuery getQueryResults(final long ProjectID){
      //build and prepare query results
        Filter projectIDFilter =
             new FilterPredicate("ProjectID", FilterOperator.EQUAL, ProjectID);
        Query query = new Query("Vote").setFilter(projectIDFilter);
   

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    return results;  
  }

}