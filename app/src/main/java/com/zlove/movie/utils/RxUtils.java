package com.zlove.movie.utils;

import io.reactivex.disposables.Disposable;

public class RxUtils {

    public static void unSubscribe(Disposable subscription) {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    public static void unSubscribe(Disposable... subscriptions) {
        for (Disposable subscription : subscriptions) {
            unSubscribe(subscription);
        }
    }
}
