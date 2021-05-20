package com.example.eventmanagement.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.DateTime;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Models.EventTopicModel;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.example.eventmanagement.PreferenceManager;
import com.example.eventmanagement.R;
import com.example.eventmanagement.SpinnerClass;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDialog extends Dialog {
    private Context context;
    private ImageButton btnCloseDialog;
    private Button btnSubmit;
    private EditText edtEventName, edtDateRange;
    private IApi apiInterface;
    private SearchEventRequestModel searchEventModel;
    private PreferenceManager preferenceManager;
    private boolean submitSearch;
    private DateTime dateTime;
    private int currentPage;
    private Integer eventTopicId;
    private SpinnerClass spinner;
    private FragmentManager fragmentManager;
    private MaterialDatePicker<Pair<Long, Long>> picker;
    private String fromDate, toDate;
    private Spinner spinnerTopics;

    public SearchDialog(@NonNull Context context, int currentPage, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.currentPage = currentPage;
        apiInterface = Api.getAPI();
        preferenceManager = new PreferenceManager(context);
        submitSearch = false;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog);
        btnCloseDialog = findViewById(R.id.btnClose);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinnerTopics = findViewById(R.id.spinnerTopics);
        edtEventName = findViewById(R.id.edtEventName);
        edtDateRange = findViewById(R.id.edtDateRange);
        dateTime = new DateTime();

        if (currentPage == 0) {
            edtDateRange.setVisibility(View.GONE);
        }

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setCalendarConstraints(limitRange().build());
        builder.setTheme(R.style.CustomMaterialCalendarTheme);
        picker = builder.build();
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

        edtDateRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!picker.isVisible()) {
                    picker.show(fragmentManager, picker.toString());
                }
            }
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                String from = dateTime.convertLongToString(selection.first);
                String to = dateTime.convertLongToString(selection.second);
                fromDate = dateTime.getDateForEventFiltering(from);
                toDate = dateTime.getDateForEventFiltering(to);
            }
        });

        picker.addOnNegativeButtonClickListener(v -> {
            edtDateRange.setText(null);
        });

        spinnerTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String eventTopicName = spinnerTopics.getSelectedItem().toString();
                if (spinner.getEventTopicId(eventTopicName) == 0)
                    eventTopicId = null;
                else
                    eventTopicId = spinner.getEventTopicId(eventTopicName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private CalendarConstraints.Builder limitRange() {
        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();
        Calendar calendarEnd = Calendar.getInstance();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int endMonth = 12;
        int endDate = 31;
        calendarEnd.set(year + 1, endMonth - 1, endDate);

        long minDate = System.currentTimeMillis();
        ;
        long maxDate = calendarEnd.getTimeInMillis();

        constraintsBuilderRange.setStart(minDate);
        constraintsBuilderRange.setEnd(maxDate);
        constraintsBuilderRange.setValidator(new DatePickerRangeValidator(minDate, maxDate));

        return constraintsBuilderRange;
    }

    private void createSearchEventModel(int eventStatus) {
        searchEventModel = new SearchEventRequestModel();
        searchEventModel.setPageNum(1);
        searchEventModel.setPageSize(10);
        searchEventModel.setName(edtEventName.getText().toString());
        searchEventModel.setFrom(fromDate);
        searchEventModel.setTo(toDate);
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
