package com.example.eventmanagement.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Activities.EventsBaseActivity;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.DateTime;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Models.EventTopicModel;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.example.eventmanagement.PreferenceManager;
import com.example.eventmanagement.R;
import com.example.eventmanagement.SpinnerClass;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDialog extends Dialog {
    private Context context;
    private ImageButton btnCloseDialog;
    private Button btnSubmit;
    private EditText edtEventName, edtStartDate, edtEndDate;
    private IApi apiInterface;
    private SearchEventRequestModel searchEventModel;
    private PreferenceManager preferenceManager;
    private boolean submitSearch;
    private int currentDay, currentYear, currentMonth;
    private String startDate, endDate;
    private DateTime dateTime;
    private int currentPage;
    private Integer eventTopicId;
    private SpinnerClass spinner;

    private Spinner spinnerTopics;

    public SearchDialog(@NonNull Context context, int currentPage) {
        super(context);
        this.context = context;
        this.currentPage = currentPage;
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
        spinnerTopics = findViewById(R.id.spinnerTopics);
        edtEventName = findViewById(R.id.edtEventName);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtEndDate = findViewById(R.id.edtEndDate);
        dateTime = new DateTime();
        getAllEventTopics();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSearch = true;
                if (currentPage == 0)
                    createSearchEventModel(Constants.EVENT_STATUS_ACTIVE);
                else
                    createSearchEventModel(Constants.EVENT_STATUS_NOT_STARTED);
                searchEvents();
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
                        edtStartDate.setText(dayOfMonth + "." + String.valueOf(month + 1) + "." + year + ".");
                        startDate = dateTime.getDateForEventFiltering(edtStartDate.getText().toString());
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), currentMonth, currentDay);
                datePicker.show();
            }
        });

        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog datePicker = new DateDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtEndDate.setText(dayOfMonth + "." + String.valueOf(month + 1) + "." + year + ".");
                        endDate = dateTime.getDateForEventFiltering(edtStartDate.getText().toString());
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), currentMonth, currentDay);
                datePicker.show();
            }
        });

        spinnerTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String eventTopicName = spinnerTopics.getSelectedItem().toString();
                eventTopicId = spinner.getEventTopicId(eventTopicName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void createSearchEventModel(int eventStatus) {
        searchEventModel = new SearchEventRequestModel();
        searchEventModel.setPageNum(1);
        searchEventModel.setPageSize(10);
        searchEventModel.setName(edtEventName.getText().toString());
        searchEventModel.setFrom(startDate);
        searchEventModel.setTo(endDate);
        searchEventModel.setEventStatus(eventStatus);
        searchEventModel.setFkEventTopicId(eventTopicId);
    }

    public boolean isSubmitSearch() {
        return submitSearch;
    }

    private void getAllEventTopics() {
        Call<List<EventTopicModel>> call = apiInterface.getAllEventTopics(preferenceManager.getToken());
        call.enqueue(new Callback<List<EventTopicModel>>() {
            @Override
            public void onResponse(Call<List<EventTopicModel>> call, Response<List<EventTopicModel>> response) {
                if (response.isSuccessful()) {
                    Constants.ALL_EVENT_TOPICS = response.body();
                    spinner = new SpinnerClass(context, Constants.ALL_EVENT_TOPICS);
                    spinnerTopics.setAdapter(spinner.getEventTopicsAdapter());
                }
            }

            @Override
            public void onFailure(Call<List<EventTopicModel>> call, Throwable t) {
                Toast.makeText(context, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    private void searchEvents() {
        Call<SearchEventResponseModel> call = apiInterface.searchMyEvents(preferenceManager.getToken(), searchEventModel);
        call.enqueue(new Callback<SearchEventResponseModel>() {
            @Override
            public void onResponse(Call<SearchEventResponseModel> call, Response<SearchEventResponseModel> response) {
                if (response.isSuccessful()) {
                    if (searchEventModel.getEventStatus() == Constants.EVENT_STATUS_ACTIVE)
                        Constants.FILTERED_ACTIVE_EVENTS = response.body().getEvents();
                    else
                        Constants.FILTERED_NOT_STARTED_EVENTS = response.body().getEvents();
                    dismiss();
                } else if (response.code() == 500) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                } else {
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
