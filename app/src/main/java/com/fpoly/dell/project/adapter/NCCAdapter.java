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

import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class NCCAdapter extends BaseAdapter implements Filterable {
    private Filter nccFilter;
    private List<NCC> nccList;
    private  List<NCC> nccs;
    private Activity context;
    private LayoutInflater inflater;
    private NCCDao nccDao;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public NCCAdapter(List<NCC> nccList, Activity context) {
        super();
        this.nccList = nccList;
        this.nccs=nccList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nccDao = new NCCDao(context);
    }

    @Override
    public int getCount() {
        return nccList.size();
    }

    @Override
    public Object getItem(int i) {
        return nccList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtMaNCC, txtTenNCC, txtDiaChi, txtSdt;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        NCCAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new NCCAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.customncc, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtMaNCC = convertView.findViewById(R.id.tvtenncc);
            holder.txtTenNCC = convertView.findViewById(R.id.tvtenncc);
            holder.txtDiaChi = convertView.findViewById(R.id.tvdiachi);
            holder.txtSdt = convertView.findViewById(R.id.tvsdt);

            holder.imgDelete = convertView.findViewById(R.id.imgdeletesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn đại lí xóa không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            nccDao = new NCCDao(context);
                            nccDao.deleteNCC(nccList.get(i).getMaNCC());
                            nccList.remove(i);
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
            holder = (NCCAdapter.ViewHolder) convertView.getTag();
        NCC entry = nccList.get(i);
        holder.img.setImageResource(R.drawable.deli);
        holder.txtMaNCC.setText("Mã nhà cung cấp: " + entry.getMaNCC());
        holder.txtTenNCC.setText("Tên nhà cung cấp: " + entry.getTenNCC());
        holder.txtDiaChi.setText("Địa chỉ: " + entry.getDiaChi());
        holder.txtSdt.setText("Số điện thoại: " + entry.getSdt());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        nccList = nccs;
    }
    public Filter getFilter() {
        if (nccFilter == null)
            nccFilter = new NCCAdapter.CustomFilter();
        return nccFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = nccs;
                results.count = nccs.size();
            }
            else {
                List<NCC> lsHoaDon = new ArrayList<>();
                for (NCC p : nccList) {
                    if
                    (p.getMaNCC().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                nccList = (List<NCC>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
