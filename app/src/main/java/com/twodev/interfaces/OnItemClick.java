package com.twodev.interfaces;

import com.twodev.models.Result;

public interface OnItemClick {
    void onClick(int position);
    void result(Result result);
    void count(float count);
}
