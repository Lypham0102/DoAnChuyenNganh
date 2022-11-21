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

import com.fpoly.dell.project.dao.TrangThietBiDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.TrangThietBi;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class TrangThietBiAdapter extends BaseAdapter implements Filterable {

    private Filter trangthietbiFilter;
    private List<TrangThietBi> trangThietBiList;
    private  List<TrangThietBi> trangThietBis;
    private Activity context;
    private LayoutInflater inflater;
    private TrangThietBiDao trangThietBiDao;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public TrangThietBiAdapter(List<TrangThietBi> trangThietBiList, Activity context) {
        super();
        this.trangThietBiList = trangThietBiList;
        this.trangThietBis=trangThietBiList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        trangThietBiDao = new TrangThietBiDao(context);
    }

    @Override
    public int getCount() {
        return trangThietBiList.size();
    }

    @Override
    public Object getItem(int i) {
        return trangThietBiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtMaTrangThietBi, txtTenTrangThietBi, txtGiaTTT;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        TrangThietBiAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new TrangThietBiAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.customvatnuoi, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtMaTrangThietBi = convertView.findViewById(R.id.tvnamevatnuoi);
            holder.txtTenTrangThietBi = convertView.findViewById(R.id.tvsoluong);
            holder.txtGiaTTT = convertView.findViewById(R.id.tvLoaithucan);

            holder.imgDelete = convertView.findViewById(R.id.imgdeletesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa trang thiết bị này không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            trangThietBiDao = new TrangThietBiDao(context);
                            trangThietBiDao.deleteTrangThietBi(trangThietBiList.get(i).getMaTrangThietBi());
                            trangThietBiList.remove(i);
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
            holder = (TrangThietBiAdapter.ViewHolder) convertView.getTag();
        TrangThietBi entry = trangThietBiList.get(i);
        holder.img.setImageResource(R.drawable.vatnuoi1);
        holder.txtMaTrangThietBi.setText("Mã trang thiết bị: " + entry.getMaTrangThietBi());
        holder.txtTenTrangThietBi.setText("Tên trang thiết bị: " + entry.getTenTrangThietBi());
        holder.txtGiaTTT.setText("Đơn giá: " + entry.getGiaTTT());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        trangThietBiList = trangThietBis;
    }
    public Filter getFilter() {
        if (trangthietbiFilter == null)
            trangthietbiFilter = new TrangThietBiAdapter.CustomFilter();
        return trangthietbiFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = trangThietBis;
                results.count = trangThietBis.size();
            }
            else {
                List<TrangThietBi> lsHoaDon = new ArrayList<>();
                for (TrangThietBi p : trangThietBiList) {
                    if
                    (p.getMaTrangThietBi().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                trangThietBiList = (List<TrangThietBi>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
