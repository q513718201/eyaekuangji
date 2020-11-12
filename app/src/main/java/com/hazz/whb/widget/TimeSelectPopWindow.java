package com.hazz.whb.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hazz.whb.R;
import com.hazz.whb.base.BasePopWindow;
import com.hazz.whb.mvp.model.bean.TimeData;
import com.hazz.whb.ui.adapter.TimeAdapter;

import java.util.List;

public class TimeSelectPopWindow extends BasePopWindow {
    private List<TimeData> timeData;
    private Context context;
    private View conentView;
    private RecyclerView rvData;
    private RelativeLayout view;

    public TimeSelectPopWindow(Context mContext, List<TimeData> timeData, RelativeLayout view) {
        super(mContext);
        this.context = mContext;
        this.timeData = timeData;
        this.view = view;
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.fenhong_popwnd_time_select, null);
        rvData = conentView.findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        rvData.setAdapter(new TimeAdapter(R.layout.item_time,timeData));
        rvData.addItemDecoration(new RewardItemDeco(10,0,0,0,0));
        setContentView(conentView);
        if(null != view){
            setWidth(view.getWidth());
        }else{
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        }
        // 设置SelectPicPopupWindow弹出窗体的高
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        // 设置弹出窗体可点击
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
    }
}
