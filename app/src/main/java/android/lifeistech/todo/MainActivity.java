package android.lifeistech.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Calendar;

import static android.text.InputType.TYPE_CLASS_DATETIME;
import static android.text.InputType.TYPE_DATETIME_VARIATION_DATE;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    EditText editText2;
    ArrayAdapter adapter;
    private PopupWindow mPopupWindow;
    private PopupWindow mPopupWindow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.day);

        super.onCreate(savedInstanceState);

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //setした日付を取得して表示
                                editText2.setText(String.format("%d / %02d / %02d", year, month + 1, dayOfMonth));
                                editText2.setInputType(TYPE_CLASS_DATETIME | TYPE_DATETIME_VARIATION_DATE);
                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();

                String item = (String) adapter.getItem(position);

                adapter.remove(item);
                adapter.add(item);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                mPopupWindow2 = new PopupWindow(MainActivity.this);

                // レイアウト設定
                View popupView = getLayoutInflater().inflate(R.layout.popout_layout_long, null);
                popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPopupWindow2.isShowing()) {
                            mPopupWindow2.dismiss();
                        }
                    }
                });
                mPopupWindow2.setContentView(popupView);

                // 背景設定
                mPopupWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background_long));

                // タップ時に他のViewでキャッチされないための設定
                mPopupWindow2.setOutsideTouchable(true);
                mPopupWindow2.setFocusable(true);

                // 表示サイズの設定 今回は幅300dp
                float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
                mPopupWindow2.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
                mPopupWindow2.setWidth((int) width);
                mPopupWindow2.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

                // 画面中央に表示
                mPopupWindow2.showAtLocation(listView, Gravity.CENTER, 0, 0);



/*

                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();

                String item = (String) adapter.getItem(position);

                adapter.remove(item);

*/

                return false;
            }
        });
    }

    public void add(View view) {
        mPopupWindow = new PopupWindow(MainActivity.this);
        String text;
        String day;

        day = editText2.getText().toString();
        text = editText.getText().toString();
        if (day.isEmpty()) {
            // レイアウト設定
            View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
            popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
            });
            mPopupWindow.setContentView(popupView);

            // 背景設定
            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

            // タップ時に他のViewでキャッチされないための設定
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);

            // 表示サイズの設定 今回は幅300dp
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
            mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setWidth((int) width);
            mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

            // 画面中央に表示
            mPopupWindow.showAtLocation(findViewById(R.id.button2), Gravity.CENTER, 0, 0);


            Toast.makeText(this, day + "   " + text + "...追加しました。", Toast.LENGTH_SHORT).show();
        } else if (text.isEmpty()) {
            // レイアウト設定
            View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
            popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
            });
            mPopupWindow.setContentView(popupView);

            // 背景設定
            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

            // タップ時に他のViewでキャッチされないための設定
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);

            // 表示サイズの設定 今回は幅300dp
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
            mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setWidth((int) width);
            mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

            // 画面中央に表示
            mPopupWindow.showAtLocation(findViewById(R.id.button2), Gravity.CENTER, 0, 0);


            Toast.makeText(this, day + "   " + text + "...追加しました。", Toast.LENGTH_SHORT).show();
        } else {
            adapter.add(day + "   " + text);
        }
    }

    public void remove(View view) {
        adapter.clear();
        Toast.makeText(this, "すべての項目を削除しました。", Toast.LENGTH_SHORT).show();
    }
    public void calendar(View view){
        Toast.makeText(this, "すべての項目を削除しました。", Toast.LENGTH_SHORT).show();

    }

}

