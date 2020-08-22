package com.google.sps.servlets;
import com.google.sps.data.Comment;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import java.util.Arrays;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ideaComments")
public class ideaComments extends HttpServlet { 

  private static DatastoreService datastore;
  public static long productID;
  @Override
  public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  /*post function to read input of the form and add the comment to the database*/
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Entity user = LoginServlet.getUser();

    long commentAuthorId = user.getKey().getId();

    Filter productFilter = new FilterPredicate("productID", FilterOperator.EQUAL, productID);
    Filter authorFilter = new FilterPredicate("commentAuthorId", FilterOperator.EQUAL, commentAuthorId);

    CompositeFilter filter = CompositeFilterOperator.and(productFilter, authorFilter);
    Query query = new Query("Comment").setFilter(filter);
    List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
    Entity taskEntity = new Entity("Comment");
    if(results.size()>0){
        taskEntity = results.get(0);
    }
    String text = request.getParameter("text");
    String suggestion = request.getParameter("suggestion");
    List<String> suggestionKeywords = new ArrayList<String>();
    suggestionKeywords.add("Groundbreaking");
    suggestionKeywords.add("Future");
    long timestamp = System.currentTimeMillis();
    double sentimentAnalysisScore = 9.5;

    taskEntity.setProperty("productID",productID);
    taskEntity.setProperty("commentAuthorId",commentAuthorId);
    taskEntity.setProperty("text",text);
    taskEntity.setProperty("suggestion", suggestion);
    taskEntity.setProperty("suggestionKeywords", suggestionKeywords);
    taskEntity.setProperty("timestamp", timestamp);
    taskEntity.setProperty("sentimentAnalysisScore",sentimentAnalysisScore);

    datastore.put(taskEntity);

    response.sendRedirect("/IdeaPage.html");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    productID= Long.parseLong(request.getParameter("productid"));

    Filter ProductIDFilter = new FilterPredicate("productID", FilterOperator.EQUAL, productID);
    Query query = new Query("Comment").setFilter(ProductIDFilter);
    PreparedQuery results = datastore.prepare(query);

    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
        long commentAuthorId = (long) entity.getProperty("commentAuthorId");
        String text = (String) entity.getProperty("text");
        String suggestion = (String) entity.getProperty("suggestion");
        List<String> suggestionKeywords = new ArrayList<String>();
        suggestionKeywords = (List<String> )entity.getProperty("suggestionKeywords");
        long timestamp = (long) entity.getProperty("timestamp");
        double sentimentAnalysisScore = (double) entity.getProperty("sentimentAnalysisScore");
        
        Comment finalComment = new Comment(productID, commentAuthorId, text, suggestion, suggestionKeywords, timestamp, sentimentAnalysisScore);
        comments.add(finalComment);

    }
    Gson gson = new Gson();
    response.setContentType("application/json");
    response.getWriter().println(gson.toJson(comments));
  } 

}



