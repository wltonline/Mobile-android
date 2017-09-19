package net.nineoneww.mobile.ui.view;

import net.nineoneww.mobile.model.entity.Vote;

import java.util.List;

/**
 * Created by lilian on 2017/8/29.
 */

public interface VoteListView  extends BaseView{
    void refresh(List<Vote> data);

    void loadMore(List<Vote> data);
}
