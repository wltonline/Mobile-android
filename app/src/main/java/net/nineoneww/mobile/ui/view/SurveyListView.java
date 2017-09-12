package net.nineoneww.mobile.ui.view;

import net.nineoneww.mobile.api.res.HomeItem;

import java.util.List;

/**
 * Created by lilian on 2017/8/29.
 */

public interface SurveyListView extends BaseView{
    void refresh(List<HomeItem> data);

    void loadMore(List<HomeItem> data);
}
