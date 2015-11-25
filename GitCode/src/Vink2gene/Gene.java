/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vink2gene;

/**
 *
 * @author bgnmh
 */
public class Gene implements Comparable {

    private int id;

    public Gene(int newId) {
        setId(newId);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return Integer.toString(id);
    }

    public int compareTo(Object o) {
        if (this.id > ((Gene) o).getId()) {
            return -1;
        }
        if (this.id < ((Gene) o).getId()) {
            return +1;
        } else {
            return 0;
        }

    }

}
