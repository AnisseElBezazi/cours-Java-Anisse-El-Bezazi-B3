package com.iim.iimcoursspring.entity;

public class Cible {

    public int XCible;
    public int moveRange;
    public int PostionCibleTir;

    public Cible(int XCibleParam, int moveRangeParam) {
        this.XCible = XCibleParam;
        this.moveRange = moveRangeParam;
    }

    public int positionCible() {
        int min = -moveRange;
        int max = moveRange;
        int deplacement = (int) (Math.random() * ((max - min) + 1)) + min;
        this.PostionCibleTir = this.XCible + deplacement;
        return this.PostionCibleTir;
    }

}
