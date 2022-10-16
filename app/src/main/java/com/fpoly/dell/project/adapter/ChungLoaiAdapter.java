package com.fpoly.dell.project.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project1.R;
import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.model.ChungLoai;

import java.util.ArrayList;
import java.util.List;

public class ChungLoaiAdapter extends BaseAdapter implements Filterable {
    private Filter chungloaiFilter;
    private List<ChungLoai> chungloailist;
    private List<ChungLoai> chungLoais;
    private final Activity context;
    private final LayoutInflater inflater;
    private  ChungLoaiDao chungLoaiDao;
    private DatabaseHelper databaseHelper;

    private Button btnHuy;
    private Button btnXoa;

    public ChungLoaiAdapter(List<ChungLoai> chungloaiList, Activity context) {
        this.chungloailist = chungloaiList;
        this.chungLoais=chungloaiList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        chungLoaiDao = new ChungLoaiDao(context);
    }
    @Override
    public int getCount() {
        return chungloailist.size();
    }

    @Override
    public Object getItem(int i) {
        return chungloailist.get(i);
    }

    @Override
    public long getItemId(int i) {
       return 0;
    }
static  class ViewHolder{
    ImageView img_avatar, imgdelete;
    TextView txtTenChungLoai,txtViTriChuong;
}

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            holder= new ViewHolder();
            convertView=inflater.inflate(R.layout.customchungloai,null);
            holder.img_avatar = convertView.findViewById(R.id.img_chungloai);
            holder.txtTenChungLoai = convertView.findViewById(R.id.tv_tenchungloai);
            holder.txtViTriChuong = convertView.findViewById(R.id.tv_vitrichuong);
            holder.imgdelete = convertView.findViewById(R.id.img_delete);
            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    final Dialog dialog = new Dialog(context);
//                    dialog.setContentView(R.layout.dialog_delete);
//                    dialog.setTitle("Bạn có muốn xóa không ?");
//                    btnXoa = dialog.findViewById(R.id.btnXoa);
//                    btnHuy = dialog.findViewById(R.id.btnHuy);
//                    dialog.show();
//
//                    btnXoa.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            chungLoaiDao.deleteChungloaiByID(chungloailist.get(i).getMachungloai());
//                            chungloailist.remove(i);
//                            notifyDataSetChanged();
//                            dialog.dismiss();
//                        }
//                    });
//                    btnHuy.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.dismiss();
//                        }
//                    });

                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            chungLoaiDao = new ChungLoaiDao(context);
                            chungLoaiDao.deleteChungloaiByID(chungloailist.get(i).getMachungloai());
                            chungloailist.remove(i);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }
            });
            convertView.setTag(holder);
        }else
            holder=(ViewHolder)convertView.getTag();
        ChungLoai entry = chungloailist.get(i);
        holder.img_avatar.setImageResource(R.drawable.iconanimals);
        holder.txtTenChungLoai.setText("Tên chủng loại: "+entry.getTenvatnuoi());
        holder.txtViTriChuong.setText("Vị trí: "+entry.getVitrichuong());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    public void resetData() {
        chungloailist = chungLoais;
    }
    public Filter getFilter() {
        if (chungloaiFilter == null)
            chungloaiFilter = new ChungLoaiAdapter.CustomFilter();
        return chungloaiFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = chungLoais;
                results.count = chungLoais.size();
            }
            else {
                List<ChungLoai> lsHoaDon = new ArrayList<>();
                for (ChungLoai p : chungloailist) {
                    if
                            (p.getTenvatnuoi().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsHoaDon.add(p);
                }
                results.values = lsHoaDon;
                results.count = lsHoaDon.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
// Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                chungloailist = (List<ChungLoai>) results.values;
                notifyDataSetChanged();
            }
        }
    }
    public void changeDataset(List<ChungLoai> items){
        this.chungloailist=items;
        notifyDataSetChanged();
    }
}
