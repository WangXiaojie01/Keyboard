package com.example.keyboard;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.keyboard.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView textview;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private EditText edittext_num;
    private Button test_btn;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = this;

        textview = (TextView)findViewById(R.id.textView1);
        textview1 = (TextView)findViewById(R.id.textView2);
        textview2 = (TextView)findViewById(R.id.textView3);
        textview3 = (TextView)findViewById(R.id.textView4);
        textview4 = (TextView)findViewById(R.id.textView5);
        edittext_num = (EditText)findViewById(R.id.editText1);
        test_btn =(Button)findViewById(R.id.btn_click_one);
        test_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View arg0) {
                                            Display display = getWindowManager().getDefaultDisplay();
                                            Point point = new Point();
                                            display.getSize(point);

                                            String str = "屏幕大小为（" + point.x + ", " + point.y + ")";
                                            textview.setText(str);
                                           /* Rect r = new Rect();
                                            getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

                                            View view = getWindow().getDecorView().getRootView();
                                            String str1 = "view 为（" + view.getWidth() + ", " + view.getHeight() + ")";
                                            textview1.setText(str1);

                                            String str2 = "r 为（" + r.bottom + ", " + r.top + ", " + r.left + ", " + r.right + "),(" + r.width() + ", " + r.height() + ")";
                                            textview2.setText(str2);

                                            Rect keyboardRec = new Rect();
                                            keyboardRec.top = view.getHeight() - r.bottom > 0 ? r.bottom : 0;
                                            keyboardRec.bottom = keyboardRec.top > 0 ? view.getBottom() : 0;
                                            keyboardRec.left = 0;
                                            keyboardRec.right = keyboardRec.top > 0 ? view.getWidth() : 0;

                                            String str3 = "键盘大小为（" + keyboardRec.left + ", " + keyboardRec.top + ", " + keyboardRec.width() + ", " + keyboardRec.height() + ")";

                                            //String str3 = "键盘大小为（" + 0 + ", " + r.bottom + ", " + view.getWidth() + ", " + (view.getHeight() - r.bottom) + ")";

                                            textview3.setText(str3);

                                            View decorView = getWindow().getDecorView();
                                            String str4 = "decorView is (" + decorView.getWidth() + ", " + decorView.getHeight() + ")";
                                            textview4.setText(str4);*/

                                            /* 键盘Rect获取的最终实现 */
                                            View view = getWindow().getDecorView();//.getRootView();
                                            Rect r = new Rect();
                                            getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                                            Rect keyboardRec = new Rect();

                                            keyboardRec.top = view.getHeight() - r.bottom > 0 ? r.bottom : 0;
                                            keyboardRec.bottom = keyboardRec.top > 0 ? view.getBottom() : 0;
                                            keyboardRec.left = 0;
                                            keyboardRec.right = keyboardRec.top > 0 ? view.getWidth() : 0;

                                            String str3 = "键盘大小为（" + keyboardRec.left + ", " + keyboardRec.top + ", " + keyboardRec.width() + ", " + keyboardRec.height() + ")";
                                            textview3.setText(str3);


                                        }
                                    });

        //edittext_num.setText("1234567");

        edittext_num.addTextChangedListener(new TextWatcher() {
            //在字符串s中,从start开始的长度为count的子串正在替换长度为before的子串
            //不要在此改变EditText的值!!!!!!!!!
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                textview.setText("文本框输入:"+edittext_num.getText().toString());
            }
            //在字符串s中,从start开始的长度为count的子串将要被长度为after的子串替代
            //不要在此改变EditText的值!!!!!!!!!!!
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            //字符串内容变动后触发,此处可以更改EditText的内容----但是注意不要死循环
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                String ts=s.toString();
                if (ts.length()<3) //长度小于3,则不处理
                    return;
                if (ts.substring(ts.length()-3).compareTo("123")==0)  //发现末尾"123"则删除
                {
                    edittext_num.setText(ts.substring(0, ts.length()-3)); //删除"123"
                    edittext_num.setSelection(ts.length()-3); //设定光标位置----否则光标回到行首
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}