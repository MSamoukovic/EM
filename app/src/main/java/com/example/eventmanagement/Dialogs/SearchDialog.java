package com.example.eventmanagement.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.DateTime;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.example.eventmanagement.PreferenceManager;
import com.example.eventmanagement.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchDialog extends Dialog {
    private Context context;
    private ImageButton btnCloseDialog;
    private Button btnSubmit;
    private EditText edtEventName, edtPlace, edtStartDate, edtEndDate;
    private IApi apiInterface;
    private SearchEventRequestModel searchEventModel;
    private PreferenceManager preferenceManager;
    private boolean submitSearch;
    private int currentDay, currentYear, currentMonth;
    private String startDate, endDate;
    private DateTime dateTime;

    public SearchDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        apiInterface = Api.getAPI();
        preferenceManager = new PreferenceManager(context);
        submitSearch = false;
        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog);
        btnCloseDialog = findViewById(R.id.btnClose);
        btnSubmit = findViewById(R.id.btnSubmit);
        edtEventName = findViewById(R.id.edtEventName);
        edtPlace = findViewById(R.id.edtPlace);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtEndDate = findViewById(R.id.edtEndDate);
        dateTime = new DateTime();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSearch = true;
                createSearchEventModel();
                searchActiveEvents();
            }
        });

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog datePicker = new DateDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtStartDate.setText(dayOfMonth + "." + String.valueOf(month+1) + "." + year + ".");
                        startDate = dateTime.getDateForEventFiltering(edtStartDate.getText().toString());

                    }
                },Calendar.getInstance().get(Calendar.YEAR),currentMonth,currentDay);
                datePicker.show();
            }
        });

        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog datePicker = new DateDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtEndDate.setText(dayOfMonth + "." + String.valueOf(month+1) + "." + year + ".");
                        endDate = dateTime.getDateForEventFiltering(edtStartDate.getText().toString());
                    }
                },Calendar.getInstance().get(Calendar.YEAR),currentMonth,currentDay);
                datePicker.show();
            }
        });

    }

    private void createSearchEventModel(){
        searchEventModel = new SearchEventRequestModel();
        searchEventModel.setPageNum(1);
        searchEventModel.setPageSize(10);
    }

    public boolean isSubmitSearch(){
        return  submitSearch;
    }

    private void searchActiveEvents(){
        Call<SearchEventResponseModel> call = apiInterface.searchMyEvents(preferenceManager.getToken(), searchEventModel);
        call.enqueue(new Callback<SearchEventResponseModel>() {
            @Override
            public void onResponse(Call<SearchEventResponseModel> call, Response<SearchEventResponseModel> response) {
                if (response.isSuccessful())
                {
                    Constants.FILTERED_ACTIVE_EVENTS =  response.body().getEvents();
                    dismiss();
                }
                else if (response.code() == 500)
                {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchEventResponseModel> call, Throwable t) {
                Toast.makeText(context, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
