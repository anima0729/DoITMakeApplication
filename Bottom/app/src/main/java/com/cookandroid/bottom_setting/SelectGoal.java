package com.cookandroid.bottom_setting;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SelectGoal extends AppCompatActivity {


    Calendar myCalendar1 = Calendar.getInstance(); //끝나는 날짜
    //똑같이 날짜 받아오기
    DatePickerDialog.OnDateSetListener myDatePicker1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar1.set(Calendar.YEAR, year);
            myCalendar1.set(Calendar.MONTH, month);
            myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    Calendar myCalendar2 = Calendar.getInstance(); //끝나는 날짜
    //똑같이 날짜 받아오기
    DatePickerDialog.OnDateSetListener myDatePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, month);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectgoal);
        final EditText goal=(EditText)findViewById(R.id.goal);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = (Switch) findViewById(R.id.switch2);
        final TextView starttime = (TextView) findViewById(R.id.starttime);
        final EditText editstrattime = (EditText) findViewById(R.id.editstarttime);
        final TextView endtime = (TextView) findViewById(R.id.endtime);
        final EditText editendtime = (EditText) findViewById(R.id.editendtime);
        final EditText editstartdate = (EditText) findViewById(R.id.editstartdate);
        final EditText editenddate = (EditText) findViewById(R.id.editenddate);
        TextView category=(TextView)findViewById(R.id.category);
        TextView textView=(TextView)findViewById(R.id.textView2);
        Button save=(Button)findViewById(R.id.save);
        Button cancle=(Button)findViewById(R.id.cancel);



        //시작 날짜 클릭 시
        editstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SelectGoal.this, myDatePicker1, myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH), myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //끝 날짜 클릭 시
        editenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SelectGoal.this, myDatePicker2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editstrattime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //캘린더 객체에 시간이랑 분 받아옴
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(SelectGoal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        editstrattime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        editendtime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(SelectGoal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        editendtime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        //목표 입력 안 한 경우 토스트 메세지 출력
        //저장 버튼 눌렀을 때


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String g=goal.getText().toString();
                if ((g.equals(""))){
                    Toast.makeText(getApplicationContext(),"목표를 입력하지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                }
            }
        });
        //취소 버튼 눌렀을 때
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        //스위치
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ //스위치 체크한 경우, 보이게
                    starttime.setVisibility(View.VISIBLE);
                    endtime.setVisibility(View.VISIBLE);
                    editstrattime.setVisibility(View.VISIBLE);
                    editendtime.setVisibility(View.VISIBLE);
                }
                else{ //안보이게
                    starttime.setVisibility(View.INVISIBLE);
                    editstrattime.setVisibility(View.INVISIBLE);
                    endtime.setVisibility(View.INVISIBLE);
                    editendtime.setVisibility(View.INVISIBLE);
                }
            }
        });

        //카테고리 클릭 시 메뉴 생성
        registerForContextMenu(textView);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                view.showContextMenu();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText editstartdate = (EditText) findViewById(R.id.editstartdate);
        editstartdate.setText(sdf.format(myCalendar1.getTime()));
    }

    private void updateLabel2() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText editenddate = (EditText) findViewById(R.id.editenddate);
        editenddate.setText(sdf.format(myCalendar2.getTime()));
    }





    @Override //카테고리 선택시 메뉴 팝업 생성
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menu.setHeaderTitle("Category");
        menuInflater.inflate(R.menu.status_menu, menu);
    }

    @Override //메뉴 아이템 선택 시 이벤트
    public boolean onContextItemSelected(MenuItem item) {
        TextView category=(TextView)findViewById(R.id.category);
        switch( item.getItemId() )
        {
            case R.id.menu_1:
                category.setText("취미");
                break;

            case R.id.menu_2:
                category.setText("학업");

                break;
            case R.id.menu_3:
                category.setText("직장");
                break;
        }
        return super.onContextItemSelected(item);
    }
}
