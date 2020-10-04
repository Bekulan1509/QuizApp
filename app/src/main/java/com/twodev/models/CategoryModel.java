
package com.twodev.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("trivia_categories")
    @Expose
    private List<TriviaCategory> triviaCategories = null;

    public List<TriviaCategory> getTriviaCategories() {
        return triviaCategories;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "triviaCategories=" + triviaCategories +
                '}';
    }
}
