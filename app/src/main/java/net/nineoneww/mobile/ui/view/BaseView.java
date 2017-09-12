package net.nineoneww.mobile.ui.view;

import android.view.View;

/**
 * Created by lilian on 2017/8/29.
 */

public interface BaseView {
    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener,int imageId);

    void showNetError(View.OnClickListener onClickListener);
}
