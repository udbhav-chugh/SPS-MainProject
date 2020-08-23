package com.google.sps.servlets;
import com.google.sps.data.Comment;
import com.google.sps.servlets.TFIDFCalculator;

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
import java.util.Iterator; 
import java.util.Map; 
import java.util.Set; 
import java.util.SortedMap; 
import java.util.TreeMap; 

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ideaSuggestions")
public class ideaSuggestions extends HttpServlet { 

  private static DatastoreService datastore;
  public static long productID;
  @Override
  public void init(){
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    productID= Long.parseLong(request.getParameter("productid"));

    Filter ProductIDFilter = new FilterPredicate("productID", FilterOperator.EQUAL, productID);
    Query query = new Query("Comment").setFilter(ProductIDFilter);
    PreparedQuery results = datastore.prepare(query);
    List<ArrayList<String>> docs = new ArrayList<ArrayList<String>>();
    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
        long commentAuthorId = (long) entity.getProperty("commentAuthorId");
        String text = (String) entity.getProperty("text");
        String suggestion = (String) entity.getProperty("suggestion");
        String suggestion_words[]= suggestion.split(" ");
        List<String> d= Arrays.asList(suggestion_words);
        ArrayList<String> doc = new ArrayList<String>(d);
        docs.add(doc);
        long timestamp = (long) entity.getProperty("timestamp");
        double sentimentAnalysisScore = (double) entity.getProperty("sentimentAnalysisScore");
        
        Comment finalComment = new Comment(productID, commentAuthorId, text, suggestion, timestamp, sentimentAnalysisScore);
        comments.add(finalComment);

    }
    ArrayList<ArrayList<String>> documents= new ArrayList<ArrayList<String>>(docs);
    
    TFIDFCalculator calculator = new TFIDFCalculator(documents);
    ArrayList<ArrayList<Double>> tfidf = calculator.tfidfDocumentsVector();

    SortedMap<Double, String> scoreMap = new TreeMap<Double, String>();
    for(int i=0;i<tfidf.size();i++){
        for(int j=0;j<tfidf.get(i).size();j++){
            scoreMap.put(tfidf.get(i).get(j),documents.get(i).get(j));
        }
    }
    Set s = scoreMap.entrySet();
    Iterator it = s.iterator(); 
  
    Integer count =0;
    ArrayList<String> keyWords = new ArrayList<String>();
    while (it.hasNext() && count<5) { 
        Map.Entry m = (Map.Entry)it.next(); 

        Double key = (Double)m.getKey(); 
        for(int i=0;i<tfidf.size();i++){
            for(int j=0;j<tfidf.get(i).size();j++){
                if(Double.compare(key,tfidf.get(i).get(j))==0){
                    count+=1;
                    keyWords.add(documents.get(i).get(j));
                }
            }
        }
    } 

    Gson gson = new Gson();
    response.setContentType("application/json");
    response.getWriter().println(gson.toJson(keyWords));
  } 

}



