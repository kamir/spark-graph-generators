
package de.bitocean.snc;

import java.io.File;
import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

public class SimpleApp {
    
  public static void main(String[] args) {
    
    String[] dfaResults = { "./data/in/b10.csv", 
                        "./data/in/b2.csv",
                        "./data/in/b9.csv",  
                        "./data/in/c10.csv",  
                        "./data/in/c2.csv", 
                        "./data/in/c9.csv"};  

    SparkConf conf = new SparkConf().setAppName("Network Generator");
    conf.setMaster("local");
    
    JavaSparkContext sc = new JavaSparkContext(conf);
    
    /**
     * 
     * Create a network for all time series buckets ...
     *
     */
    for( String file : dfaResults ) {
        
        File fn = new File( file );
        
        JavaRDD<String> dfaData = sc.textFile(file).cache();

        // split the string into ID and double[] to create a node-object
        JavaRDD<DFANetNode> nodesA = dfaData.map((String s) -> new DFANetNode(s)); 
        JavaRDD<DFANetNode> nodesB = dfaData.map((String s) -> new DFANetNode(s)); 

        // create all pairs
        JavaPairRDD<DFANetNode,DFANetNode> pairs = nodesB.cartesian(nodesA);

        // calc distance for all pairs
        JavaRDD<DFALink> links = pairs.map( (Tuple2<DFANetNode,DFANetNode> p) -> new DFALink(p) );

        // store distance matrix
        links.saveAsTextFile("./data/out/DCCA-net-" + fn.getName() );
    
    }
    
  }
}

