package com.linkdev.linkdevelopment.utils;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class CombinedLiveData<A, B> extends MediatorLiveData<Pair<A, B>> {
    private A a;
    private B b;

    public CombinedLiveData(LiveData<A> ld1, LiveData<B> ld2) {
        setValue(Pair.create(a, b));

        addSource(ld1, (a) -> {
            if(a != null) {
                this.a = a;
            }
            setValue(Pair.create(a, b));
        });

        addSource(ld2, (b) -> {
            if(b != null) {
                this.b = b;
            }
            setValue(Pair.create(a, b));
        });
    }
}