package com.fpoly.dell.project.adapter;

import android.app.Activity;
import android.content.Context;;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.dell.project.dao.BayDanDao;
import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class BayDanAdapter extends BaseAdapter implements Filterable{
    private Filter baydanFilter;
    private List<BayDan> bayDanList;
    private List<BayDan> bayDans;
    private Activity context;
    private LayoutInflater inflater;
    private BayDanDao bayDanDao;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnXoa;

    public BayDanAdapter(List<BayDan> bayDanList, Activity context) {
        super();
        this.bayDanList = bayDanList;
        this.bayDans=bayDanList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bayDanDao = new BayDanDao(context);
    }

    @Override
    public int getCount() {
        return bayDanList.size();
    }

    @Override
    public Object getItem(int i) {
        return bayDanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        ImageView img, imgDelete;
        TextView txtMaBayDan, txtSoBayDan, txtSoLuongVat;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custombaydan, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txtMaBayDan = convertView.findViewById(R.id.tvmabaydan);
            holder.txtSoBayDan = convertView.findViewById(R.id.tvsobaydan);
            holder.txtSoLuongVat = convertView.findViewById(R.id.tvsoluongvat);

            holder.imgDelete = convertView.findViewById(R.id.imgdeletesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            bayDanDao = new BayDanDao(context);
                            bayDanDao.deleteBayDan(bayDanList.get(i).getMaBayDan());
                            bayDanList.remove(i);
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
            holder = (BayDanAdapter.ViewHolder) convertView.getTag();
        BayDan entry = bayDanList.get(i);
        holder.img.setImageResource(R.drawable.pigsss);
        holder.txtMaBayDan.setText("Mã: " + entry.getMaBayDan());
        holder.txtSoBayDan.setText("Số chuồng: " + entry.getSoBayDan());
        holder.txtSoLuongVat.setText("Số lượng: " + entry.getSoLuongVat());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void resetData() {
        bayDanList = bayDans;
    }
    public Filter getFilter() {
        if (baydanFilter == null)
            baydanFilter = new BayDanAdapter.CustomFilter();
        return baydanFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = bayDans;
                results.count = bayDans.size();
            }
            else {
                List<BayDan> lsHoaDon = new ArrayList<>();
                for (BayDan p : bayDanList) {
                    if
                    (p.getMaBayDan().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                bayDanList = (List<BayDan>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
