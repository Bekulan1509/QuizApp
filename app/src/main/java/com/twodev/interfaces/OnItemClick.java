package com.twodev.interfaces;

import com.twodev.models.Result;

public interface OnItemClick {
    void onClick(int position);
    void count(float count);
    void resultModel(Result result);
}
