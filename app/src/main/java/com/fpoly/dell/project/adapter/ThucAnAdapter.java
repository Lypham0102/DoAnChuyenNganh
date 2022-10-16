package com.fpoly.dell.project.adapter;

import android.widget.BaseAdapter;
import android.widget.Filterable;
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
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project1.R;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project.model.ThucAn;



import java.util.ArrayList;
import java.util.List;

public class ThucAnAdapter extends BaseAdapter implements Filterable {
    private Filter thucanFilter;
    private List<ThucAn> thucAnList;
    private  List<ThucAn> thucAns;
    private  Activity context;
    private  LayoutInflater inflater;
    private ThucAnDao thucAnDao;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public ThucAnAdapter(List<ThucAn> thucAnList, Activity context) {
        super();
        this.thucAnList = thucAnList;
        this.thucAns=thucAnList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        thucAnDao = new ThucAnDao(context);
    }

    @Override
    public int getCount() {
        return thucAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return thucAnList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtMathucan,
            txtTenthucan,
                txtMaloai,
            txtSoLuong,
                txtDongia;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ThucAnAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ThucAnAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.customthucan, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtMathucan = convertView.findViewById(R.id.tvmathucan);
            holder.txtTenthucan = convertView.findViewById(R.id.tv_tenthucan);

            holder.txtSoLuong = convertView.findViewById(R.id.tvsoluong);

            //holder.txtDongia = convertView.findViewById(R.id.);

            holder.imgDelete = convertView.findViewById(R.id.imgdeletesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                  final Dialog  dialog = new Dialog(context);
//                    dialog.setContentView(R.layout.dialog_delete);
//                    dialog.setTitle("Bạn có muốn xóa không ?");
//                    btnXoa = dialog.findViewById(R.id.btnXoa);
//                    btnHuy = dialog.findViewById(R.id.btnHuy);
//                    dialog.show();
//
//                    btnXoa.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            vatNuoiDao.deleteVatNuoi(vatNuoiList.get(i).getMavatnuoi());
//                            vatNuoiList.remove(i);
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
                    builder.setMessage("Bạn có muốn xóa thức ăn này không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            thucAnDao = new ThucAnDao(context);
                            thucAnDao.deleteThucAn(thucAnList.get(i).getMathucan());
                            thucAnList.remove(i);
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
            holder = (ThucAnAdapter.ViewHolder) convertView.getTag();
        ThucAn entry = thucAnList.get(i);
        holder.img.setImageResource(R.drawable.thucpham);
        holder.txtMathucan.setText("Mã thức ăn: " + entry.getMathucan());
        holder.txtTenthucan.setText("Tên thức ăn: " + entry.getTenthucan());
        holder.txtMaloai.setText("Mã loại: " + entry.getMaloai());
        holder.txtSoLuong.setText("Số Lượng: " + entry.getSoluong());
        holder.txtDongia.setText("Đơn giá: " + entry.getDongia());
        //holder.txtThucAn.setText("Thức Ăn: " + entry.getMaloai());

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        thucAnList = thucAns;
    }
    public Filter getFilter() {
        if (thucanFilter == null)
            thucanFilter = new ThucAnAdapter.CustomFilter();
        return thucanFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = thucAns;
                results.count = thucAns.size();
            }
            else {
                List<ThucAn> lsHoaDon = new ArrayList<>();
                for (ThucAn p : thucAnList) {
                    if
                    (p.getMathucan().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                thucAnList = (List<ThucAn>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
