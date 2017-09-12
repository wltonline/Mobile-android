package net.nineoneww.mobile.ui.presenter;

import net.nineoneww.mobile.ui.view.BaseView;

/**
 * Created by lilian on 2017/8/30.
 */

public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}
