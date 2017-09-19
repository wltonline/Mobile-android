package net.nineoneww.mobile.model;

import java.io.Serializable;

/**
 * Created by lilian on 2017/8/8.
 */

public interface HomeItem extends Serializable {
    public String getItemTitle();
    public String getItemNumber();
    public String getItemLoi();
    public String getItemPoint();
    public String getFooter();
    public String getUrl();
    public boolean isSOPQuestionnaire();
    public boolean isProfileQuestionnaire();
    public boolean isRealtime();
    public boolean isQ000();
    public boolean isCookieQuestionnaire();
    public boolean isFacebookQuestionnaire();
    public boolean isGoogleQuestionnaire();
}
