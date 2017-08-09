package net.nineoneww.mobile.api.res;


import com.google.gson.annotations.SerializedName;

import net.nineoneww.mobile.util.Constant;

import java.io.Serializable;

/**
 * Created by lilian on 2017/8/8.
 */

public class Survey implements HomeItem {
    private static final String SURVEY_PREFIX = "r";
    private String surveyId;
    private String quotaId;
    private String cpi;
    private String ir;
    private String loi;
    private String isAnswered;
    private String isClosed;
    private String title;
    private String url;
    private String isFixedLoi;
    private String isNotifiable;
    private String date;
    private ExtraInfo extraInfo;
    private BlockedDevices blockedDevices;

    @Override
    public String getItemTitle() {
        return title;
    }

    @Override
    public String getItemNumber() {
        return SURVEY_PREFIX + surveyId;
    }

    @Override
    public String getItemLoi() {
        return loi;
    }

    @Override
    public String getItemPoint() {
        return extraInfo.getPoint().getComplete();
    }

    @Override
    public String getFooter() {
        return getExtraInfo().getContent();
    }

    @Override
    public boolean isSOPQuestionnaire() {
        return true;
    }

    @Override
    public boolean isProfileQuestionnaire() {
        return false;
    }

    @Override
    public boolean isQ000() {
        return false;
    }

    @Override
    public boolean isCookieQuestionnaire() {
        return false;
    }

    @Override
    public boolean isFacebookQuestionnaire() {
        return false;
    }

    @Override
    public boolean isGoogleQuestionnaire() {
        return false;
    }

    @Override
    public boolean isRealtime() {
        if (isNotifiable == null || isFixedLoi == null) return false;
        return isNotifiable.equals(Constant.NOTIFIABLE) && isFixedLoi.equals(Constant.FIXED_LOI);
    }

    static public class Date implements Serializable {
        private String startAt;
        private String endAt;

        public String getStartAt() {
            return startAt;
        }

        public void setStartAt(String startAt) {
            this.startAt = startAt;
        }

        public String getEndAt() {
            return endAt;
        }

        public void setEndAt(String endAt) {
            this.endAt = endAt;
        }
    }

    static public class Point implements Serializable {
        private String complete;
        private String screenout;
        private String quotafull;

        public String getComplete() {
            return complete;
        }

        public void setComplete(String complete) {
            this.complete = complete;
        }

        public String getScreenout() {
            return screenout;
        }

        public void setScreenout(String screenout) {
            this.screenout = screenout;
        }

        public String getQuotafull() {
            return quotafull;
        }

        public void setQuotafull(String quotafull) {
            this.quotafull = quotafull;
        }
    }

    static public class ExtraInfo implements Serializable {
        private Date date;
        private Point point;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String content;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    static public class BlockedDevices implements Serializable {
        @SerializedName("PC")
        private int pc;
        @SerializedName("TABLET")
        private int tablet;
        @SerializedName("MOBILE")
        private int mobile;

        public boolean isPcBlocked() {
            return pc == 1;
        }

        public boolean isMobileBlocked() {
            return mobile == 1;
        }

        public boolean isTabletBlocked() {
            return tablet == 1;
        }
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getCpi() {
        return cpi;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getIr() {
        return ir;
    }

    public void setIr(String ir) {
        this.ir = ir;
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public String getIsAnswered() {
        return isAnswered;
    }

    public boolean isAnswered() {
        if (isAnswered == null) return false;
        return isAnswered.equals(Constant.IS_ANSWERED);
    }

    public void setIsAnswered(String isAnswered) {
        this.isAnswered = isAnswered;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsFixedLoi() {
        return isFixedLoi;
    }

    public void setIsFixedLoi(String isFixed_loi) {
        this.isFixedLoi = isFixed_loi;
    }

    public String getIsNotifiable() {
        return isNotifiable;
    }

    public void setIsNotifiable(String isNotifiable) {
        this.isNotifiable = isNotifiable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ExtraInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    public BlockedDevices getBlockedDevices() {
        return blockedDevices;
    }
}
