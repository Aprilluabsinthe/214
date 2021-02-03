package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: dilu
 * Class Document
 * @brief: this class is designed to store url string and caculate cosine similarity
 * @member: HashMap to store word and frequency
 * @instances: has instance to calculate cosine similarity with another Document object
 * */
public class Document {
    private Map<String,Integer> urlMap;

    /**
     * class constructor
     * @param urlstring the original String url
    **/
    public Document(String urlstring){
        this.urlMap = new HashMap<>();
        URL url = null;
        try {
            url = new URL(urlstring);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Scanner in = null;
        try {
            in = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(in.hasNext()){
            String content = in.next();
            urlMap.put( content, urlMap.getOrDefault(content,0) + 1);
        }
        in.close();
    }

    /**
     * get private member UrlMap
     * @return the HashMap
     */
    public Map<String, Integer> getUrlMap() {
        Map<String, Integer> publicMap = new HashMap<>(urlMap);
        return publicMap;
    }

    /**
     * @methor consineSimilarity
     * @param urlCompare the new url to be calculated with
     * @return double cosine similarity
    * */
    public double cosineSimilarity(Document urlCompare){
        Map<String, Integer> urlCompareMap = urlCompare.getUrlMap();

        // calculate divider
        double divider = 0.0;
        for(Map.Entry<String,Integer> entry : urlCompareMap.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            if( urlMap.containsKey(key) ){
                divider += value * urlMap.get(key);
            }
        }

        // calculate nominator
        double norminizor = 0;
        double normOriginal = getSumSqrt(urlMap);
        double normCompare = getSumSqrt(urlCompareMap);
        norminizor = normOriginal * normCompare;

        // calculate results
        return (double)divider/norminizor;

    }

    /**
     * calculate norminator
     * @param map HashMap map, keys are String wors, values are Integer
     * @return double, sqrt(sum(ele^2))
     */
    public double getSumSqrt(Map<String, Integer> map){
        double norminator = 0;
        for(Integer value : urlMap.values()){
            norminator += value * value;
        }
        double sqrt = Math.sqrt(norminator);
        return sqrt;
    }

    @Override
    /** override toString method for Document
     **/
    public String toString(){
        return urlMap.toString();
    }

}
