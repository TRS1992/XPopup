package com.lxj.xpopup.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lxj.easyadapter.CommonAdapter;
import com.lxj.easyadapter.MultiItemTypeAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.R;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.Arrays;

/**
 * Description:
 * Create by dance, at 2018/12/12
 */
public class ListAttachPopupView extends AttachPopupView {
    RecyclerView recyclerView;

    public ListAttachPopupView(@NonNull Context context) {
        super(context);
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout._xpopup_attach_impl_list;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        recyclerView = findViewById(R.id.recyclerView);
        final CommonAdapter<String> adapter = new CommonAdapter<String>(R.layout._xpopup_adapter_text, Arrays.asList(datas)) {
            @Override
            protected void convert(@NonNull ViewHolder holder, @NonNull String s, int position) {
                holder.setText(R.id.tv_text, s);
                if (iconIds != null && iconIds.length > position) {
                    holder.setVisible(R.id.iv_image, true);
                    holder.setBackgroundRes(R.id.iv_image, iconIds[position]);
                }else {
                    holder.setVisible(R.id.iv_image, false);
                }
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.SimpleOnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (selectListener != null) {
                    selectListener.onSelect(position, adapter.getDatas().get(position));
                }
                dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    String[] datas;
    int[] iconIds;

    public void setStringData(String[] datas, int[] iconIds) {
        this.datas = datas;
        this.iconIds = iconIds;
    }

    public void setOffsetXAndY(int offsetX, int offsetY) {
        this.defaultOffsetX += offsetX;
        this.defaultOffsetY += offsetY;
    }

    private OnSelectListener selectListener;

    public void setOnSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
