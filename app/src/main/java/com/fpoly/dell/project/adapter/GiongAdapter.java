package com.fpoly.dell.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fpoly.dell.project.dao.GiongDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.Giong;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class GiongAdapter extends BaseAdapter implements Filterable {

    private Filter giongFilter;
    private List<Giong> giongList;
    private  List<Giong> giongs;
    private Activity context;
    private LayoutInflater inflater;
    private GiongDao giongDao;
    private com.fpoly.dell.project.dao.database.DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public GiongAdapter(List<Giong> giongList, Activity context) {
        super();
        this.giongList = giongList;
        this.giongs=giongList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        giongDao = new GiongDao(context);
    }
    @Override
    public int getCount() {
        return giongList.size();
    }

    @Override
    public Object getItem(int i) {
        return giongList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtMaGiong, txtTenGiong, txtXuatXu;
    }
    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        GiongAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new GiongAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.customvatnuoi, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtMaGiong = convertView.findViewById(R.id.tvnamevatnuoi);
            holder.txtTenGiong = convertView.findViewById(R.id.tvsoluong);
            holder.txtXuatXu = convertView.findViewById(R.id.tvLoaithucan);

            holder.imgDelete = convertView.findViewById(R.id.imgdeletesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa giống này không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new com.fpoly.dell.project.dao.database.DatabaseHelper(context);
                            giongDao = new GiongDao(context);
                            giongDao.deleteGiong(giongList.get(i).getMaGiong());
                            giongList.remove(i);
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

        } else
            holder = (GiongAdapter.ViewHolder) convertView.getTag();
        Giong entry = giongList.get(i);
        holder.img.setImageResource(R.drawable.pork);
        holder.txtMaGiong.setText("Mã giống: " + entry.getMaGiong());
        holder.txtTenGiong.setText("Tên giống: " + entry.getTenGiong());
        holder.txtXuatXu.setText("Xuất xứ: " + entry.getXuatXu());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        giongList = giongs;
    }
    public Filter getFilter() {
        if (giongFilter == null)
            giongFilter = new GiongAdapter.CustomFilter();
        return giongFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = giongs;
                results.count = giongs.size();
            }
            else {
                List<Giong> lsHoaDon = new ArrayList<>();
                for (Giong p : giongList) {
                    if
                    (p.getTenGiong().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                giongList = (List<Giong>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
