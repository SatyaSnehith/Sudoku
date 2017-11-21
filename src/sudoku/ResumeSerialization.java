/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.Serializable;

/**
 *
 * @author satyasnehith
 */
public class ResumeSerialization implements Serializable {
    private String fileName;
    private int n[][];
    private int mins, secs;
    public ResumeSerialization (String fileName, int n[][],int mins, int secs) {
        this.fileName = fileName;
        this.n = n;
        this.mins = mins;
        this.secs = secs;
    }
    public String getFileName() {
        return fileName;
    }
    public int[][] getN() {
        return n;
    }
    public int getMins() {
        return mins;
    }
    public int getSecs() {
        return secs;
    }
    public static void main(String[] args) {
        
    }
 }
