package com.example.priyankam.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.priyankam.myapplication.R;
import com.example.priyankam.myapplication.model.GetDataService;
import com.example.priyankam.myapplication.model.ResultObject;
import com.example.priyankam.myapplication.model.ResultStatusPost;
import com.example.priyankam.myapplication.network.RetrofitClientInstanceGet;
import com.example.priyankam.myapplication.network.RetrofitClientInstancePost;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

import static android.content.ContentValues.TAG;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ResultObject> items;
    private Context context;
    Boolean checkState;

    public MainAdapter(Context context, List<ResultObject> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_main_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String itemName = items.get(position).getSiteName();
        final String itemLoad = items.get(position).getLoadType();
        final String itemStatus = items.get(position).getStatus();
        viewHolder.textName.setText(itemName);
        viewHolder.textLoad.setText(itemLoad);
        viewHolder.textStatus.setText(itemStatus);
        if (itemStatus.equals("On")) {
            viewHolder.toggleButton.toggle();
            checkState = true;
            // viewHolder.toggleButton.setChecked(true);
        } else {
            viewHolder.toggleButton.setChecked(false);
            checkState = false;
        }

        viewHolder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCheck(viewHolder.toggleButton);
            }
        });

    }

    private void callCheck(ToggleButton toggleButton) {
        if (checkState) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder
                    .setMessage(
                            "Sure you want to enable?. ")
                    .setCancelable(false)
                    .setPositiveButton(
                            "YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    sendPost();
                                    dialog.cancel();
                                }
                            })

                    .setNegativeButton(
                            "NO",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    dialog.cancel();
                                    checkState = true;
                                    toggleButton.toggle();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            alertDialogBuilder
                    .setMessage(
                            "Sure you want to disable?")
                    .setCancelable(true)
                    .setPositiveButton(
                            "YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    sendPost();
                                    dialog.cancel();
                                }
                            })

                    .setNegativeButton(
                            "NO",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    dialog.cancel();
                                    checkState = true;
                                    toggleButton.toggle();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void sendPost() {
        //String Url = "https://ptsv2.com/t/5q9xm-1533097794/post";
       // String Url = "http://10.1.1.206/Projects/php_upload/techMPost.php";
        GetDataService service = RetrofitClientInstancePost.getRetrofitInstance(context).create(GetDataService.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("siteID", "123");
        map.put("status", "dsds");


        JSONObject jsonObject = new JSONObject(map);
        System.out.println("jsonObject=" + jsonObject);

        Call<ResultStatusPost> call = service.savePost(jsonObject.toString());

        call.enqueue(new Callback<ResultStatusPost>() {
            @Override
            public void onResponse(Call<ResultStatusPost> call, Response<ResultStatusPost> response) {
                Log.d("URL", "URL = " + call.request().url().toString()); // here
                if (response.isSuccessful()) {
                   // showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResultStatusPost> call, Throwable t) {
                Log.d("URL", "URL = " + call.request().url().toString()); // here
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        Log.d(response, response);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textLoad;
        TextView textStatus;
        ToggleButton toggleButton;

        ViewHolder(View view) {
            super(view);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textLoad = (TextView) itemView.findViewById(R.id.textLoad);
            textStatus = (TextView) itemView.findViewById(R.id.textStatus);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.toggleButton);

        }
    }
}
