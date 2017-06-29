package com.leisurekr.leisuresportskorea.shop_detail;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 2..
 */

public class ParentData {
    public String category;
    public ArrayList<ChildData> child;

    public ParentData() {}
    public ParentData(String category) {
        this.category= category;
        child = new ArrayList<>();
    }


    public String getProgramName() {
        return category;
    }

    public void setProgramName(String category) {
        this.category = category;
    }

    public ArrayList<ChildData> getChild() {
        return child;
    }

    public void setChild(ArrayList<ChildData> child) {
        this.child = child;
    }
}
