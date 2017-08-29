package net.nineoneww.mobile.api.res;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 2017/8/25.
 */

public class SopSurveysJson extends Base {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    static public class Data {
//        private List<Profiling> profiling;
        private List<Survey> research;

        public List<Survey> getResearch() {
            return research;
        }

        public List<Survey> getNotAnsweredResearch() {
            List<Survey> list = new ArrayList<Survey>();
            for (Survey item: research) {
                if(!item.isAnswered()){
                    list.add(item);
                }
            }

            return list;
        }


        public void setResearch(List<Survey> research) {
            this.research = research;
        }

//        public List<Profiling> getProfiling() {
//            return profiling;
//        }
//
//        public List<Profiling> getProfilingWithoutQ000() {
//            ArrayList<Profiling> profilingWithoutQ000 = new ArrayList<>();
//            for (Profiling p : profiling) {
//                if (!p.isQ000()) {
//                    profilingWithoutQ000.add(p);
//                }
//            }
//            return profilingWithoutQ000;
//        }
//
//        public void setProfiling(List<Profiling> profiling) {
//            this.profiling = profiling;
//        }

    }
}

