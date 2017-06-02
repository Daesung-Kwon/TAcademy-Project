package com.leisurekr.leisuresportskorea.shop_detail;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 2..
 */

public class ParentData {
    private String programName;
    public ArrayList<ChildData> child;

    public ParentData(String name) {
        this.programName= name;
        child = new ArrayList<>();
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ArrayList<ChildData> getChild() {
        return child;
    }

    public void setChild(ArrayList<ChildData> child) {
        this.child = child;
    }
}
