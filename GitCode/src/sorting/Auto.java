/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

/**
 *
 * @author Martijn van der Bruggen (c) Hogeschool van Arnhem en Nijmegen
 *
 */
public class Auto implements Comparable {

    protected String eigenaar;
    protected String kleur;
    protected int pk;

    Auto(String e, String k, int p) {
        setEigenaar(e);
        setKleur(k);
        setPk(p);
    }

    public String getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(String eigenaar) {
        this.eigenaar = eigenaar;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    /*public int compareTo(Object x) {
     Auto a = (Auto) x;
     if (a.getEigenaar().length() > this.getEigenaar().length()) {
     return -1;
     } else if (a.getEigenaar().length() < this.getEigenaar().length()) {
     return +1;
     } else {
     return 0;
     }
     }
     */
    public int compareTo(Object x) {
        Auto a = (Auto) x;
        if (a.getPk() < this.getPk()) {
            return +1;
        } else if (a.getPk() > this.getPk()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Auto van " + eigenaar + " en is " + kleur + " met pks:" + Integer.toString(pk);
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
}
