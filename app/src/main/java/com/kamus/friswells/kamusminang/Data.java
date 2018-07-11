package com.kamus.friswells.kamusminang;

/**
 * Created by friswell on 30/05/2018.
 */

public class Data {

    private String b_indo, b_minang, b_ing, j_kata;

    public Data (String b_indo, String b_minang, String b_ing, String j_kata) {
        this.b_indo = b_indo;
        this.b_minang = b_minang;
        this.b_ing = b_ing;
        this.j_kata = j_kata;
    }

    public String getB_indo() {
        return b_indo;
    }

    public void setB_indo(String b_indo) {
        this.b_indo = b_indo;
    }

    public String getB_minang() {
        return b_minang;
    }

    public void setB_minang(String b_minang) {
        this.b_minang = b_minang;
    }

    public String getB_ingg() {
        return b_ing;
    }

    public void setB_ingg(String b_ing) {
        this.b_ing = b_ing;
    }

    public String getJ_kata() {
        return j_kata;
    }

    public void setJ_kata(String j_kata) {
        this.j_kata = j_kata;
    }
}