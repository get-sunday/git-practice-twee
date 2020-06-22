package com.facotr.video.gitdemos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestViewActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 2;
    private List<String> datas = new ArrayList<>(12);
    GridViewAdapter adapter;
    public static final String content = "说明：“确认预约”提交后将形成订单，在产品详情页和预约明细内均可对订单继续操作完成交易，如有疑问可致电客服 400-8980-618";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list);

         ListView listView = findViewById(R.id.list_view);
         TextView textView = findViewById(R.id.textView);



        adapter = new GridViewAdapter();
//        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  if (position == datas.size() &&  datas.size() < 12){
                      onPick(12 -  datas.size());
                      Log.e("img","iamg show imageview" + datas.size());
                  }else {
                      //So a junk
                  }

            }
        });
        setSpannableString(content,textView);

    }

    //This is a junk foosd
    private void fixMethods(){

    }

    public static void setSpannableString(String text, TextView textView){
        String phoneNum = getPhoneNum(text);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);//设置电话号码字体颜色
            }

            @Override
            public void onClick(@NonNull View widget) {
                //电话号码点击事件
            }
        }, text.indexOf(phoneNum), text.indexOf(phoneNum) + 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 获取字符串里的里的手机号码
     * @param text 包含手机号的字符串
     * @return 手机号
     */
    public static String getPhoneNum(String text){
        String regex = "1[35789]\\d{9}";//正则规则有待优化
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }


    /**
     * 这些都是非常可怕的
     *
     * @param size
     */
    private void  onPick(int size){
        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setMaxSelectCount(size) // 图片的最大选择数量，小于等于0时，不限数量。
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this, REQUEST_CODE); // 打开相册
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);
            datas.addAll(images);
            adapter.notifyDataSetChanged();

        }
    }

    class GridViewAdapter extends BaseAdapter {

        private Context mContext;
        int type = 1 ;


        @Override
        public int getCount() {

            return datas.size() < 12 ? datas.size() + 1 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(TestViewActivity.this).inflate(R.layout.item, parent, false);
            ImageView ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            ImageView ivDeleate = (ImageView) convertView.findViewById(R.id.iv_del);
//                避免重复getview的问题出现
            if (datas.size() < 12){
                if (position < getCount() - 1) {
                    try {
                        Glide.with(ivPic.getContext()).load(datas.get(position)).into(ivPic);

                    } catch (Exception e) {
                    }
                    ivDeleate.setVisibility(View.VISIBLE);
                } else {
                    ivPic.setImageResource(R.mipmap.ic_launcher);
                    ivDeleate.setVisibility(View.GONE);
                }
                ivDeleate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Log.e("remove","onClick");
                        datas.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
            }else {
                Glide.with(ivPic.getContext()).load(datas.get(position)).into(ivPic);
            }

            return convertView;
        }
    }
}
