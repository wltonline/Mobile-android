package net.nineoneww.mobile.api;

import java.util.List;
import java.util.Map;

/**
 * Created by lilian on 2017/8/25.
 */

public class Base {
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Meta {
        private static final String TERMS_OF_USE_AGREEMENT_ERROR = "TERMS_OF_USE_AGREEMENT_ERROR";
        private String code;
        private String type;
        private Map<String, List<String>> errors;

        public String getLoginError() {
            if (errors == null || errors.size() == 0) {
                return "";
            }
            String str = "";
            Map.Entry<String, List<String>> elm = errors.entrySet().iterator().next();
            for (String val : elm.getValue()) {
                str += val + "\n";
            }
            return str.trim();
        }

        public String getAllErros() {
            if (errors == null || errors.size() == 0) {
                return "";
            }
            String str = "";
            for (Map.Entry<String, List<String>> entry :
                    errors.entrySet()) {
                for (String error : entry.getValue()
                        ) {
                    str += entry.getKey() + ": " + error + "\n";
                }
            }

            return str.trim();
        }

        public boolean is200() {
            return code.equals("200");
        }

        public boolean is201() {
            return code.equals("201");
        }

        public boolean is400() {
            //mainly used for validation error
            return code.equals("400");
        }

        public boolean isNoUserAgreemnt() {
            //mainly used for invalid token
            return code.equals("403") && type.equals(TERMS_OF_USE_AGREEMENT_ERROR) ;
        }

        public boolean is403() {
            //mainly used for invalid token
            return code.equals("403");
        }

        public boolean is404() {
            return code.equals("404");
        }


        public String getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
