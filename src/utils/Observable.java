/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.util.Vector;

/**
 *
 * @author uidj5418
 */

public class Observable {
    
    private boolean changed;
    private Vector<Observer> observerList;

    public Observable() {
        this.observerList = new Vector();
    }

    public synchronized void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    public synchronized void deleteObserver(Observer observer) {
        // compiled code
    }

    public void notifyObservers() {
        for ( Observer observer : this.observerList ) {
            observer.update();
        }
    }

    public void notifyObservers(Object o) {
        for ( Observer observer : this.observerList ) {
            observer.update(o);
        }
    }

    public synchronized void deleteObservers() {
        // compiled code
    }

    protected synchronized void setChanged() {
        // compiled code
    }

    protected synchronized void clearChanged() {
        // compiled code
    }

    public synchronized boolean hasChanged() {
        return true;
    }

    public synchronized int countObservers() {
        return 0;
    }
}
