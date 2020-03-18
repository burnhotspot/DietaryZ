package com.example.dietaryz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import java.sql.Time;
import java.time.Clock;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public String intent_reporter_name, intent_food_name, intent_food_group, intent_meal_type, intent_date, intent_time, intent_note;

    private String[] meal_type = {"Breakfast","Lunch","Dinner","Tea Time"};
    private String[] food_group = {"Dairy","Fruit","Meat","Bread"};

    private EditText rn, fn, note;
    private Spinner mt, fg; private int mt_row, fg_row;
    private DatePicker date;
    private TimePicker time;
    private Button review, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar current_date = Calendar.getInstance();
        int cyear = current_date.get(Calendar.YEAR);
        int cmonth = current_date.get(Calendar.MONTH);
        int cday = current_date.get(Calendar.DAY_OF_MONTH);

         rn = (EditText) findViewById(R.id.reporter_name);
         fn = (EditText) findViewById(R.id.food_name);
         note = (EditText) findViewById(R.id.note);
         mt = (Spinner) findViewById(R.id.meal_type);
         fg = (Spinner) findViewById(R.id.food_group);
         date = (DatePicker) findViewById(R.id.date);
         time = (TimePicker) findViewById(R.id.time);
         review = (Button) findViewById(R.id.review);
         back = (Button) findViewById(R.id.back);



        mt.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_list_item, meal_type));
        mt_row = mt.getSelectedItemPosition();

        fg.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_list_item, food_group));
        fg_row = fg.getSelectedItemPosition();

        date.init(cyear,cmonth,cday, null);

        review.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	view)	{
                Review_food();
            }
        });
    }


    private void Review_food(){
        intent_reporter_name = rn.getText().toString();
        intent_food_name = fn.getText().toString();
        intent_food_group = food_group[fg_row];
        intent_meal_type = meal_type[mt_row];
        intent_date = date.getDayOfMonth() + ":" + date.getMonth() + ":" + date.getYear();
        intent_time = time.getHour() + ":" + time.getMinute();
        intent_note = note.getText().toString();

        Intent i	=	new	Intent(this, Review.class);
        i.putExtra("reporter_name",	intent_reporter_name);
        i.putExtra("food_group", intent_food_group);
        i.putExtra("food_name", intent_food_name);
        i.putExtra("meal_type", intent_meal_type);
        i.putExtra("date", intent_date);
        i.putExtra("time", intent_time);
        i.putExtra("note", intent_note);

        startActivity(i);

    }
}
