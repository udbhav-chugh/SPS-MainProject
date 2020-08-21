package com.google.sps.servlets;
import com.google.sps.data.ProductIdea;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ideaPage")
public class ideaPageServlet extends HttpServlet { 

  private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    long ProductID= Long.parseLong(request.getParameter("ProductID"));

    Filter ProductIDFilter = new FilterPredicate("ProductID", FilterOperator.EQUAL, ProductID);
    Query query = new Query("ProductIdea").setFilter(ProductIDFilter);
    PreparedQuery results = datastore.prepare(query);

    List<ProductIdea> productIdeas = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long authorId = (long) entity.getProperty("authorId");
      String title = (String) entity.getProperty("title");
      long timestamp = (long) entity.getProperty("timestamp");
      ProductIdea.category categories = (ProductIdea.category) entity.getProperty("categories");
      String imageUrl = (String) entity.getProperty("imageUrl");
      String videoUrl = (String) entity.getProperty("videoUrl");
      String description = (String) entity.getProperty("description");
      
      ProductIdea productidea = new ProductIdea(ProductID, title, authorId, timestamp, categories, imageUrl, videoUrl, description);
      productIdeas.add(productidea);
    }
    
    Gson gson = new Gson();

    response.setContentType("application/json");
    response.getWriter().println(gson.toJson(productIdeas));
  } 

}



