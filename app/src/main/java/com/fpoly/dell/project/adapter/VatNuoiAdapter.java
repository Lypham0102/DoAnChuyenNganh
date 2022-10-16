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
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class VatNuoiAdapter extends BaseAdapter implements Filterable {
    private Filter vatnuoiFilter;
    private List<VatNuoi> vatNuoiList;
    private  List<VatNuoi> vatNuois;
    private  Activity context;
    private  LayoutInflater inflater;
    private  VatNuoiDao vatNuoiDao;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public VatNuoiAdapter(List<VatNuoi> vatNuoiList, Activity context) {
        this.vatNuoiList = vatNuoiList;
        this.vatNuois=vatNuoiList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vatNuoiDao = new VatNuoiDao(context);
    }

    @Override
    public int getCount() {
        return vatNuoiList.size();
    }

    @Override
    public Object getItem(int i) {
        return vatNuoiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtTenVatNuoi, txtSoLuong, txtThucAn;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.customvatnuoi, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtTenVatNuoi = convertView.findViewById(R.id.tvnamevatnuoi);
            holder.txtSoLuong = convertView.findViewById(R.id.tvsoluong);
            holder.txtThucAn = convertView.findViewById(R.id.tvLoaithucan);

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
                    builder.setMessage("Bạn có muốn xóa không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            vatNuoiDao = new VatNuoiDao(context);
                            vatNuoiDao.deleteVatNuoi(vatNuoiList.get(i).getMavatnuoi());
                            vatNuoiList.remove(i);
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
            holder = (ViewHolder) convertView.getTag();
        VatNuoi entry = vatNuoiList.get(i);
        holder.img.setImageResource(R.drawable.vatnuoi1);
        holder.txtTenVatNuoi.setText("Tên: " + entry.getMachungloai());
        holder.txtSoLuong.setText("Số Lượng: " + entry.getSoluong());
        holder.txtThucAn.setText("Thức Ăn: " + entry.getLoaithucan());





        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        vatNuoiList = vatNuois;
    }
    public Filter getFilter() {
        if (vatnuoiFilter == null)
            vatnuoiFilter = new CustomFilter();
        return vatnuoiFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = vatNuois;
                results.count = vatNuois.size();
            }
            else {
                List<VatNuoi> lsHoaDon = new ArrayList<>();
                for (VatNuoi p : vatNuoiList) {
                    if
                            (p.getMachungloai().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                vatNuoiList = (List<VatNuoi>) results.values;
                notifyDataSetChanged();
            }
        }
    }

}
