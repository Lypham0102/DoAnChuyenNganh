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
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiPhiAdapter extends BaseAdapter implements Filterable {

    private List<ChiPhi> arrChiPhi;
    private List<ChiPhi> arrSortChiPhi;
    private Filter chiphiFilter;
    private Activity context;
    private LayoutInflater inflater;
    private ChiPhiDao chiPhiDao;
    private DatabaseHelper databaseHelper;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private Button btnHuy;
    private Button btnXoa;
    public ChiPhiAdapter(Activity context, List<ChiPhi> arrChiPhi) {
        super();
        this.context = context;
        this.arrChiPhi = arrChiPhi;
        this.arrSortChiPhi=arrChiPhi;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        chiPhiDao = new ChiPhiDao(context);
    }



    @Override
    public int getCount() {
        return arrChiPhi.size();
    }

    @Override
    public Object getItem(int i) {
        return arrChiPhi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        ImageView img;
        TextView txttenthucan;
        TextView txtsoluong;
        TextView txtgiatien;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        NumberFormat numberFormat = new DecimalFormat("#,###,###");
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.customchiphi, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txttenthucan = convertView.findViewById(R.id.tvtenthucan);
            holder.txtsoluong = convertView.findViewById(R.id.tvSoluong);
            holder.txtgiatien = convertView.findViewById(R.id.tvgiatien);
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
                            chiPhiDao = new ChiPhiDao(context);
                            chiPhiDao.deleteChiPhi(arrChiPhi.get(position).getMachiphi());
                            arrChiPhi.remove(position);
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

            holder=(ViewHolder)convertView.getTag();
        ChiPhi _entry = arrChiPhi.get(position);
        holder.img.setImageResource(R.drawable.chiphi);
        holder.txttenthucan.setText("Tên: "+_entry.getTenthucan());
        holder.txtsoluong.setText("Số Lượng: "+_entry.getSoluong());
        holder.txtgiatien.setText("Giá Tiền: "+numberFormat.format(Long.valueOf(_entry.getGiatien()))+" vnđ");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrChiPhi = arrSortChiPhi;
    }
    public Filter getFilter() {
        if (chiphiFilter == null)
            chiphiFilter = new CustomFilter();
        return chiphiFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortChiPhi;
                results.count = arrSortChiPhi.size();
            }
            else {
                List<ChiPhi> lsHoaDon = new ArrayList<>();
                for (ChiPhi p : arrChiPhi) {
                    if
                            (p.getTenthucan().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                arrChiPhi = (List<ChiPhi>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
