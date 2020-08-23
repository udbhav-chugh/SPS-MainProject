package com.google.sps.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList; // import the ArrayList class



public class TFIDFCalculator {
    /**Set of terms present across all documents**/
     private Set<String> hash_Set = new HashSet<String>(); 

    /** documents with string that will generate the terms for hash_set**/
     private ArrayList<ArrayList<String>> docs;

   /**
   * @param documents list of list of strings
   */
     public TFIDFCalculator(ArrayList<ArrayList<String>> documents) {
         docs= documents;
         for(ArrayList<String> doc: documents){
             for(String word: doc){
                 hash_Set.add(word);
             }
         }
    }
/**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf(ArrayList<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

 /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(ArrayList<ArrayList<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
     
    public double tfIdf(ArrayList<String> doc, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    /**
    * @return nested list of list storing tfidf value of each term in a document
    */
    public ArrayList<ArrayList<Double>> tfidfDocumentsVector(){
        ArrayList<ArrayList<Double>> docs_vectors= new ArrayList<ArrayList<Double>>();
        for(ArrayList<String> doc: docs){
            ArrayList<Double> doc_vec= new ArrayList<Double>();
            for(String word:hash_Set){
                doc_vec.add(tfIdf(doc, word));
            }
            docs_vectors.add(doc_vec);

        }
        return docs_vectors;
    }

    public  ArrayList<ArrayList<String>> tfidfWords(){
        ArrayList<ArrayList<String>> docs_vectors= new ArrayList<ArrayList<String>>();
        for(ArrayList<String> doc: docs){
            ArrayList<String> doc_vec= new ArrayList<String>();
            for(String word:hash_Set){
                doc_vec.add(word);
            }
            docs_vectors.add(doc_vec);
        }
        return docs_vectors;
    }




    


}



