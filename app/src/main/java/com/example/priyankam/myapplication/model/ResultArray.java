
package com.example.priyankam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultArray {


    @SerializedName("result")
    @Expose
    private List<ResultObject> result = null;

    public List<ResultObject> getResult() {
        return result;
    }

    public void setResult(List<ResultObject> result) {
        this.result = result;
    }


}
