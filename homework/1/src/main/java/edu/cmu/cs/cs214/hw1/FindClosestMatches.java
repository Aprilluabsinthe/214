package edu.cmu.cs.cs214.hw1;

import java.util.ArrayList;
import java.util.List;

/**
 * The closest match to each document in a set
 */
public class FindClosestMatches {
    private FindClosestMatch fcm;
    private List<Double> cossimList;
    private List<Integer> bestmatches;
    private double[][] cachedResults;

    public static final double SELF= -10.0;

    /**
     * default constructor
     */
    public FindClosestMatches(){
        this.fcm = new FindClosestMatch();
        this.cossimList = new ArrayList<Double>();
        this.bestmatches = new ArrayList<Integer>();
        this.cachedResults = new double[1][1];
    }

    /**
     * constructor with arguments
     * @param websites the websites to be copies
     */
    public FindClosestMatches(String[] websites){
        this.fcm = new FindClosestMatch(websites);
        this.cossimList = new ArrayList<Double>();
        this.bestmatches = new ArrayList<Integer>();
        this.cachedResults = new double[websites.length][websites.length];
    }

    /**
     * for visit list length in fcm
     * @return list length
     */
    public int getListLength(){
        return this.fcm.getListLength();
    }


    /**
     * give the index in list, return the String url
     * @param i index
     * @return url
     */
    public String getUrl(int i) {
        return this.fcm.getUrl(i);
    }

    /**
     * for visit private member cossinList
     * @param index index in cossimList
     * @return cossim cache
     */
    public double getCossim(int index) {
        return this.cossimList.get(index);
    }

    /**
     * for visit bestmatches map
     * @param index the given index
     * @return int bestmatches
     */
    public int getMacth(int index) {
        return this.bestmatches.get(index);
    }

    /**
     * use existes class
     * put cosine similarity information into map
     */
    public void putPair(){
        int numurl = getListLength();
        for(int i = 0 ; i < numurl ; i++){
            double cossimi = this.fcm.bestMatchForI(i);
            int[] index = this.fcm.getMinIndex();
            this.bestmatches.add(index[0],index[1]);
            this.cossimList.add(index[0],cossimi);
        }
    }

    /**
     * iterate way to calcute answer
     * with O(n*(n-1)/2)
     */
    public void iterCalc(){
        int numurl = getListLength();
        for(int i = 0 ; i < numurl ; i++){
            for(int j = 0 ; j < numurl ; j++){
                if( j < i ){
                    cachedResults[i][j] = cachedResults[j][i];
                }
                else if( j == i){
                    cachedResults[i][j] = SELF;
                }
                else if( j > i ){
                    double temp = fcm.cosSimBetween(i,j);
                    cachedResults[i][j] = temp;
                }
            }
        }
    }

    /**
     * get method for cachedResults
     * @return double[][]
     */
    public double[][] getCachedResults() {
        return cachedResults;
    }

    /**
     * main function
     * @param args argument
     */
    public static void main(String[] args){

        FindClosestMatches fcms = new FindClosestMatches(args);
        // method 1
        fcms.putPair();
        System.out.println( "\nuse existed class functions\n");
        for( int i = 0; i < fcms.getListLength(); i++){
            System.out.printf( "Best Match for url:%s is url2:%s with cos sim:%f\n", fcms.getUrl(i), fcms.getUrl(fcms.getMacth(i)), fcms.getCossim(i) );
        }

        // method 2

        fcms.iterCalc();
        System.out.println( "\nuse iteration\n");
        double[][] cached = fcms.getCachedResults();
        for( int i = 0; i < fcms.getListLength(); i++){
            double cossimi = 0.0;
            int bestPair = 0;
            for(int j = 0 ; j < fcms.getListLength();j++){
                if(cached[i][j] > cossimi){
                    cossimi = cached[i][j];
                    bestPair = j;
                }
            }
            System.out.printf( "Best Match for url:%s is url2:%s with cos sim:%f\n", fcms.getUrl(i), fcms.getUrl(bestPair), cossimi);
        }
    }

}
