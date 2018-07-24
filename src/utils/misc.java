/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author uidj5418
 */
public class misc {
    
    public static void printArrayList(ArrayList list){
        for( Object o : list ) {
            System.out.println(o);
        }
    }
    
    public static void printHashMap(HashMap map){
        for( Object key : map.keySet() ) {
            System.out.println(key);
        }
    }
    
}
