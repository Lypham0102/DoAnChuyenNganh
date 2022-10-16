package com.fpoly.dell.project.adapter;

import android.app.Activity;
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

import com.fpoly.dell.project.dao.BayChuongDao;
import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.BayChuong;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project.ui.BayChuongActivity;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BayChuongAdapter extends BaseAdapter implements Filterable {
    private List<BayChuong> arrBayChuong;
    private List<BayChuong> arrSortBayChuong;
    private Filter baychuongFilter;
    private Activity context;
    private LayoutInflater inflater;
    private BayChuongDao bayChuongDao;
    private DatabaseHelper databaseHelper;

    private Button btnHuy;
    private Button btnXoa;
    public BayChuongAdapter(Activity context, List<BayChuong> arrBayChuong) {
        super();
        this.context = context;
        this.arrBayChuong = arrBayChuong;
        this.arrSortBayChuong=arrBayChuong;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bayChuongDao = new BayChuongDao(context);
    }
    @Override
    public int getCount() {
        return arrBayChuong.size();
    }

    @Override
    public Object getItem(int i) {
        return arrBayChuong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        ImageView img;
        TextView txtmabaychuong;
        TextView txtsobachuong;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        BayChuongAdapter.ViewHolder holder;
        NumberFormat numberFormat = new DecimalFormat("#,###,###");
        if (convertView == null) {
            holder = new BayChuongAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.custombaychuong, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtmabaychuong = convertView.findViewById(R.id.tvtenthucan);
            holder.txtsobachuong = convertView.findViewById(R.id.tvSoluong);

            holder.imgDelete = convertView.findViewById(R.id.imgdelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
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
//                            chiPhiDao.deleteChiPhi(arrChiPhi.get(position).getMachiphi());
//                            arrChiPhi.remove(position);
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
                            bayChuongDao = new BayChuongDao(context);
                            bayChuongDao.deleteBayChuongByID(arrBayChuong.get(position).getMabaychuong());
                            arrBayChuong.remove(position);
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

        }
        else

            holder=(BayChuongAdapter.ViewHolder)convertView.getTag();
        BayChuong _entry = arrBayChuong.get(position);
        holder.img.setImageResource(R.drawable.chiphi);
        holder.txtmabaychuong.setText("Tên: "+_entry.getMabaychuong());
        holder.txtsobachuong.setText("Số Lượng: "+_entry.getSochuong());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrBayChuong = arrSortBayChuong;
    }
    public Filter getFilter() {
        if (baychuongFilter == null)
            baychuongFilter = new BayChuongAdapter.CustomFilter();
        return baychuongFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortBayChuong;
                results.count = arrSortBayChuong.size();
            }
            else {
                List<BayChuong> lsHoaDon = new ArrayList<>();
                for (BayChuong p : arrBayChuong) {
                    if
                    (p.getMabaychuong().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                arrBayChuong = (List<BayChuong>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}

