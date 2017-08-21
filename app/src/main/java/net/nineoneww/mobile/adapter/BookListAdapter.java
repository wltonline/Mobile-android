package net.nineoneww.mobile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.api.res.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 2017/8/10.
 */

public class BookListAdapter extends BaseAdapter {

    private List<Book> datas = new ArrayList<Book>();//书籍列表集合

    private Context context;
    private LayoutInflater layoutInflater;

    public BookListAdapter(Context context, List<Book> datas) {
        this.datas = datas;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Book getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.content_vote_list, null);//找到布局文件
            convertView.setTag(new BookListAdapter.ViewHolder(convertView));
        }
        initViews(getItem(position), (BookListAdapter.ViewHolder) convertView.getTag());

        return convertView;
    }

    private void initViews(Book data, BookListAdapter.ViewHolder holder) {//初始化数据

        /**
         * 第一次初始话的时候通过 要请求的Url地址 为每个图片设置一个Tag标记,
         * 然后在设置图片的时候判断Tag标记如果是才把图片设置到ImageView上,
         * 这做的原因是为了防止ListView 中的图片错位...
         */
//        String authors = "";
//        for(int i = 0; i < data.getBookAuthor().size(); i++){
//            authors += "&" + data.getBookAuthor().get(i);
//        }
        holder.ivImg.setTag(data.getBookImgUrl());//设置Tag
        holder.tvTitle.setText(data.getBookTitle());
        holder.tvPubDate.setText(data.getBookPubDate());
        holder.tvAuthor.setText(data.getBookAuthor());
        holder.tvPrice.setText(data.getBookPrice());

        //通过集合中的图片地址获得图片并且设置到view上
        getImage(this.context, data.getBookImgUrl(), holder.ivImg);

    }

    protected class ViewHolder {
        private ImageView ivImg;
        private TextView tvTitle;
        private TextView tvPubDate;
        private TextView tvAuthor;
        private TextView tvPrice;

        public ViewHolder(View view) {
            ivImg = (ImageView) view.findViewById(R.id.book_img);
            tvTitle = (TextView) view.findViewById(R.id.book_title);
            tvPubDate = (TextView) view.findViewById(R.id.book_publish_date);
            tvAuthor = (TextView) view.findViewById(R.id.book_author);
            tvPrice = (TextView) view.findViewById(R.id.book_price);
        }
    }

    public void getImage(Context context, String imgUrl,
                         final ImageView imageView) {

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
}
