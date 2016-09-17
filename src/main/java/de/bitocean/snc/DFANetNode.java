/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bitocean.snc;

public class DFANetNode {

    public DFANetNode(String line) {
        String[] seg = line.split("\t");
        nodeid = seg[0];
        alphas = new double[3];
        alphas[0] = Double.parseDouble(seg[2]);
        alphas[1] = Double.parseDouble(seg[3]);
        alphas[2] = Double.parseDouble(seg[4]);
    }

    String nodeid = null;
    double[] alphas = null;

    public String toString() {
        return nodeid + "\t" + alphas[0] + "\t" + alphas[1] + "\t" + alphas[2];
    }

}
