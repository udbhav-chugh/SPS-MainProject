package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

/** Servlet responsible for listing tasks. */
@WebServlet("/sentiment-score")
public class SentimentScoreServlet extends HttpServlet {

    private static DatastoreService datastore;

    public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    long ProjectID= Long.parseLong(request.getParameter("productid"));
    List<Comment> comments= getListofCommentObject(ProjectID);

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }

private List<Comment> getListofCommentObject(final long ProjectID){
    
  
    PreparedQuery results= getQueryResults(ProjectID);

    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        
       
        long commentAuthorId= (long) entity.getProperty("commentAuthorId");
        
        String suggestion = (String) entity.getProperty("suggestion");
       
        String str[] =   ((String)entity.getProperty("suggestionKeywords")).split(" ");
        List<String> suggestionKeywords = new ArrayList<String>();
	    suggestionKeywords = Arrays.asList(str);

        long timestamp = (long) entity.getProperty("timestamp");
        
        String text = (String) entity.getProperty("text");
        
        double sentimantAnalysisScore = (double) entity.getProperty("sentimantAnalysisScore");

        /** the sentiment analysis score will be calculated and stored when the comment is posted **/
        
        //sentimantAnalysisScore= (sentimantAnalysisScore==0?getSentimentScore(text):sentimantAnalysisScore);


        Comment comment_obj = new Comment(ProjectID,commentAuthorId,text,suggestion,
            suggestionKeywords,timestamp,sentimantAnalysisScore);
        

        comments.add(comment_obj);

    }
    return comments;
}

private PreparedQuery getQueryResults(final long ProjectID){


      //build and prepare query results
        Filter projectIDFilter =
             new FilterPredicate("ProjectID", FilterOperator.EQUAL, ProjectID);
        Query query = new Query("Comment").setFilter(projectIDFilter);
   

        //DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
       
        PreparedQuery results = datastore.prepare(query);
        return results;  
  }

  
}



