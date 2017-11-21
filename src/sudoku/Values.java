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
public class Values implements Serializable {
    private char e[] = new char[9];
    public Values() {
        char i = '1';
        for(int k = 0; k < 9; ++k){
            e[k] = i++;
        }
    }
    public void setE(char[] e) {
        this.e = e;
    }
    public char[] getE() {
        return e;
    }
}
