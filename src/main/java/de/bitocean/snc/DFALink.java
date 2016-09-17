/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bitocean.snc;

import scala.Tuple2;

/**
 *
 * @author kamir
 */
class DFALink {

    double w[] = new double[3];
    double a_alphas[] = new double[3];
    double b_alphas[] = new double[3];
    
    String s = null;
    String t = null;
    
    public DFALink(DFANetNode a, DFANetNode b) {
        for( int i = 0; i < 3; i++ ) {
            
            double wa = a.alphas[i];
            double wb = b.alphas[i];
            
            a_alphas[i] = wa;
            b_alphas[i] = wb;
            
            w[i] = ( wa + wb ) * 0.5; 
            
//            if ( a.nodeid.equals(b.nodeid)) {
//            
//                System.out.println( i + " => " + w[i] + "wa=" + wa + "   wb=" + wb );
//                System.out.println( a );
//                System.out.println( b );
//                
//            }    
        }
        s = a.nodeid;
        t = b.nodeid;
    }

    DFALink(Tuple2<DFANetNode, DFANetNode> p) {
        this( p._1, p._2 );
    }
    
    public String toString() {
        return s + "\t" + t + "\t" + a_alphas[0] + "\t" + a_alphas[1] + "\t" + a_alphas[2] + "\t" + b_alphas[0] + "\t" + b_alphas[1] + "\t" + b_alphas[2] + "\t" + w[0] + "\t" + w[1] + "\t" + w[2]; 
    }
    
}
