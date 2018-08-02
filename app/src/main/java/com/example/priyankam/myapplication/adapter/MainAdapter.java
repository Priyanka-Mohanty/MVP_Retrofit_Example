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
import com.example.priyankam.myapplication.database.MainDatabase;
import com.example.priyankam.myapplication.model.GetDataService;
import com.example.priyankam.myapplication.model.ResultObject;
import com.example.priyankam.myapplication.model.ResultStatusPost;
import com.example.priyankam.myapplication.network.RetrofitClientInstanceGet;
import com.example.priyankam.myapplication.network.RetrofitClientInstancePost;
import com.example.priyankam.myapplication.utils.ConstantValues;
import com.example.priyankam.myapplication.view.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // viewHolder.textStatus.setText(itemStatus);
        if (itemStatus.equals("On")) {
            viewHolder.toggleButton.setChecked(true);
            // viewHolder.toggleButton.setChecked(true);
        } else {
            viewHolder.toggleButton.setChecked(false);
        }
        viewHolder.textStatus.setText("");
        viewHolder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = items.get(position).getUrl().toString();
                if (items.get(position).getStatus().equals("On")) {

                    viewHolder.toggleButton.setChecked(true);
                    checkState = true;

                } else {
                    viewHolder.toggleButton.setChecked(false);
                    checkState = false;
                }
                String siteID = items.get(position).getSiteID();


                callCheck(viewHolder.toggleButton, viewHolder.textStatus, url, siteID);
            }
        });

    }

    private void callCheck(ToggleButton toggleButton, TextView textStatus, String url, String siteID) {
        if (!checkState) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder
                    .setMessage("Sure you want to enable?")
                    .setCancelable(false)
                    .setPositiveButton(
                            "YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    checkState = false;
                                    toggleButton.setChecked(false);
                                    textStatus.setText("Request Pending");
                                    sendPost(toggleButton, textStatus, url, siteID, checkState);
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
                                    checkState = false;
                                    toggleButton.setChecked(false);
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
                                    toggleButton.setChecked(true);
                                    textStatus.setText("Request Pending");
                                    sendPost(toggleButton, textStatus, url, siteID, checkState);
                                    checkState = true;
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
                                    toggleButton.setChecked(true);
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void sendPost(ToggleButton toggleButton, TextView textStatus, String url, String siteID, Boolean Status) {
        //String Url = "https://ptsv2.com/t/5q9xm-1533097794/post";
        // String Url = "http://10.1.1.206/Projects/php_upload/techMPost.php";
        String status;
        if (Status == true) {
            status = "On";
        } else {
            status = "Off";
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uuid", siteID);
        map.put("app_key", status);


        JSONObject jsonObject = new JSONObject(map);
        System.out.println("jsonObject=" + jsonObject);

        ConstantValues constantValues = new ConstantValues();
        String parentUrl = constantValues.postParentUrl;
        url = constantValues.postUrl;
        //GetDataService service = RetrofitClientInstancePost.getRetrofitInstance(context).create(GetDataService.class);
        GetDataService service = RetrofitClientInstancePost.getApiService(parentUrl);

        Call<ResultStatusPost> call = service.savePost(url, jsonObject.toString());

        call.enqueue(new Callback<ResultStatusPost>() {
            @Override
            public void onResponse(Call<ResultStatusPost> call, Response<ResultStatusPost> response) {
                Log.d("URL", "URL = " + call.request().url().toString()); // here
                if (response.isSuccessful()) {
                    showResponse(toggleButton, textStatus, response.body().toString());
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

    public void showResponse(ToggleButton toggleButton, TextView textStatus, String response) {
        Log.d(response, response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            String siteID = jsonObject.getString("siteId"); // get the name from data.
            String status = jsonObject.getString("status"); // get the name from data.
            ResultStatusPost resultStatusPost = new ResultStatusPost(siteID, status);
            Log.d("response", resultStatusPost.getSiteID().toString());
            if (resultStatusPost.getStatus().equals("On")) {
                toggleButton.setChecked(true);
                checkState = true;
            } else {
                toggleButton.setChecked(false);
                checkState = false;
            }
            textStatus.setText("");

            updateStatusInDataBase(siteID, status);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateStatusInDataBase(String siteID, String status) {
        MainDatabase mainDatabase = new MainDatabase(context);
        mainDatabase.open();
        mainDatabase.updateStatusInMasterTable(siteID, status);
        mainDatabase.close();

        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getDataFromDatabase();

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
