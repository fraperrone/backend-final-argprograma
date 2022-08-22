
package com.portfolio.fpr.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int  htm;
    private int cs;
    private int js;
    private int gi;
    private int en;
    
    public Skills(){
        
    }

    public Skills(int htm, int cs, int js, int gi, int en) {
        this.htm = htm;
        this.cs = cs;
        this.js = js;
        this.gi = gi;
        this.en = en;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHtm() {
        return htm;
    }

    public void setHtm(int htm) {
        this.htm = htm;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public int getJs() {
        return js;
    }

    public void setJs(int js) {
        this.js = js;
    }

    public int getGi() {
        return gi;
    }

    public void setGi(int gi) {
        this.gi = gi;
    }

    public int getEn() {
        return en;
    }

    public void setEn(int en) {
        this.en = en;
    }

    
}
