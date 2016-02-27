package com.ifpb.AppAndroid.textwatcher.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import com.ifpb.AppAndroid.textwatcher.activity.MainActivity;
import com.ifpb.AppAndroid.textwatcher.util.HttpService;
import com.ifpb.AppAndroid.textwatcher.util.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarNome extends AsyncTask<JSONObject,Void,Response> {

    @Override

    protected Response doInBackground(JSONObject... jsons) {

        Response response = null;

        JSONObject json = jsons[0];
        Log.i("EditTextListener", "doInBackground (JSON):" + json);

        try {

            response = HttpService.sendJSONPostResquest("get-byname", json);

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override

    protected void onPostExecute(Response response) {

        String nome = null;
        List<String> name = new ArrayList<String>();
        int i = 0;

        try {

            JSONArray jsonArray = new JSONArray(response.getContentValue());

            while(jsonArray.getString(i)!= null) {
                JSONObject jo = new JSONObject(jsonArray.getString(i));
                nome = jo.getString("fullName");
                name.add(nome);
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MainActivity.atualizarLista(name);

    }
}