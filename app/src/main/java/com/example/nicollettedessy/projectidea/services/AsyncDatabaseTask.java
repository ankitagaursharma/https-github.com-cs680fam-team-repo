package com.example.nicollettedessy.projectidea.services;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by pierrethelusma on 4/12/18.
 */

public class AsyncDatabaseTask<T, V> extends AsyncTask<T, Void, V> {

    private final Context context;
    private final IAsyncDatabaseListener<V> listener;

    public AsyncDatabaseTask(Context context, IAsyncDatabaseListener<V> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected V doInBackground(T... params) {
        return (V)ApplicationDatabaseProvider.getApplicationDatabase(this.context).repository().getFood();
    }

    @Override
    protected void onPostExecute(V param)
    {
        listener.onDatabaseResponse(param);
    }
}
