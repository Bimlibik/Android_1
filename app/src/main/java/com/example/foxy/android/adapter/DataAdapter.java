package com.example.foxy.android.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.foxy.android.R;
import com.example.foxy.android.models.Lesson;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    //    конструктор адаптера
    public DataAdapter(Context context, List<Lesson> lessonList) {
        this.inflater = LayoutInflater.from(context);
        this.lessonList = lessonList;
    }

    //    вызывается, когда ViewHolder должен быть инициализирован
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.lesson_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.titleLesson.setText(lesson.getTitleLesson());
        holder.image.setImageResource(lesson.getImageLesson());
    }

    //    возвращает количество элементов, присутствующих в данных
    @Override
    public int getItemCount() {
        return lessonList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;
        protected TextView titleLesson;

        //внутри данного контруктора инициализируем все вью, входящие в RV
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.lesson_list_item_image);
            titleLesson = itemView.findViewById(R.id.lesson_list_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public ItemClickListener getClickListener() {
        return clickListener;
    }

    String getItem(int id) {
        return lessonList.get(id).getTitleLesson();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}



