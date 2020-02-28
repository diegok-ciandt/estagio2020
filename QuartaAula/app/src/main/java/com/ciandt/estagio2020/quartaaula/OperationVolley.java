package com.ciandt.estagio2020.quartaaula;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OperationVolley {

    private RequestQueue queue;

    public void setup(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void request(String url,
                        Response.Listener<String> successListener,
                        Response.ErrorListener errorListener) {

        StringRequest request = new StringRequest(Request.Method.GET, url,
                successListener, errorListener);

        queue.add(request);

    }
}
