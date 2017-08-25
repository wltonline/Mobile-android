package net.nineoneww.mobile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.api.res.Vote;

import java.util.ArrayList;

/**
 * Created by lilian on 2017/8/10.
 */

public class VoteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Vote> voteData;
    private Context context;
    private LayoutInflater layoutInflater;
    private static final int TYPE_ITEM = 0;

    private static final int TYPE_FOOTER = 1;
    //上拉加载更多
    static final int PULL_LOAD_MORE = 0;
    //正在加载更多
    static final int LOADING_MORE = 1;
    //没有更多
    static final int NO_MORE = 2;
    //脚布局当前的状态,默认为没有更多
    int footer_state = 1;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private VoteListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return voteData == null ? 0 : voteData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public VoteListAdapter(Context context, ArrayList<Vote> data) {
        this.voteData = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.content_vote_item,  parent, false);
            return new ItemViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.content_footer,  parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // 绑定数据
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.ivImg.setTag(voteData.get(position).getVoteImgUrl());//设置Tag
            itemViewHolder.tvTitle.setText(voteData.get(position).getVoteTitle());
            itemViewHolder.tvPubDate.setText(voteData.get(position).getVotePubDate());
            itemViewHolder.tvAuthor.setText(voteData.get(position).getVoteAuthor());
            itemViewHolder.tvPrice.setText(voteData.get(position).getVotePrice());

            //通过集合中的图片地址获得图片并且设置到view上
            getImage(this.context, voteData.get(position).getVoteImgUrl(), itemViewHolder.ivImg);
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }else if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
            if(position == 0){
                footerViewHolder.mProgressBar.setVisibility(View.GONE);
                footerViewHolder.tv_line1.setVisibility(View.GONE);
                footerViewHolder.tv_line2.setVisibility(View.GONE);
                footerViewHolder.tv_state.setText("");
            }
            switch (footer_state) {//根据状态来让脚布局发生改变
                case PULL_LOAD_MORE://上拉加载
                    footerViewHolder.mProgressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_state.setText(R.string.footer_pull_loading);
                    break;
                case LOADING_MORE:
                    footerViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_line1.setVisibility(View.GONE);
                    footerViewHolder.tv_line2.setVisibility(View.GONE);
                    footerViewHolder.tv_state.setText(R.string.footer_loading);
                    break;
                case NO_MORE:
                    footerViewHolder.mProgressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_line1.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_line2.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_state.setText(R.string.footer_baseline);
                    break;
            }
        }

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImg;
        private TextView tvTitle;
        private TextView tvPubDate;
        private TextView tvAuthor;
        private TextView tvPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.book_img);
            tvTitle = (TextView) itemView.findViewById(R.id.book_title);
            tvPubDate = (TextView) itemView.findViewById(R.id.book_publish_date);
            tvAuthor = (TextView) itemView.findViewById(R.id.book_author);
            tvPrice = (TextView) itemView.findViewById(R.id.book_price);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);
        }
    }

    public void getImage(Context context, String imgUrl, final ImageView imageView) {

        /**
         * 检测图片的Tag值 ,如果根请求的地址相同 才做图片的网络请求.
         */
        if (imageView.getTag().toString().equals(imgUrl)) {
            RequestQueue mQueue = Volley.newRequestQueue(context);
            ImageRequest imageRequest = new ImageRequest(imgUrl,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imageView.setImageBitmap(response);//将返回的Bitmap显示子ImageView上
                        }
                    }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            mQueue.add(imageRequest);
        }
    }

    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }
}
