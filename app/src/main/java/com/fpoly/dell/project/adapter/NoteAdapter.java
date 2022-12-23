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

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.NoteDao;
import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project.model.Note;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter implements Filterable {
    private List<Note> arrNote;
    private List<Note> arrSortNote;
    private Filter noteFilter;
    private Activity context;
    private LayoutInflater inflater;
    private NoteDao noteDao;
    private DatabaseHelper databaseHelper;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private Button btnHuy;
    private Button btnXoa;
    public NoteAdapter(Activity context, List<Note> arrNote) {
        super();
        this.context = context;
        this.arrNote = arrNote;
        this.arrSortNote=arrNote;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        noteDao = new NoteDao(context);
    }



    @Override
    public int getCount() {
        return arrNote.size();
    }

    @Override
    public Object getItem(int i) {
        return arrNote.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        ImageView img;
        TextView txttennote;
        TextView txtngaynote;
        TextView txtnoidung;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        NoteAdapter.ViewHolder holder;
        NumberFormat numberFormat = new DecimalFormat("#,###,###");
        if (convertView == null) {
            holder = new NoteAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.customchiphi, null);
            holder.img = convertView.findViewById(R.id.imgavatar);
            holder.txttennote = convertView.findViewById(R.id.tvtenthucan);
            holder.txtngaynote = convertView.findViewById(R.id.tvSoluong);
            holder.txtnoidung = convertView.findViewById(R.id.tvgiatien);
            holder.imgDelete = convertView.findViewById(R.id.imgdelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa note này không?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper = new DatabaseHelper(context);
                            noteDao = new NoteDao(context);
                            noteDao.deleteNote(arrNote.get(position).getManote());
                            arrNote.remove(position);
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

            holder=(NoteAdapter.ViewHolder)convertView.getTag();
        Note _entry = arrNote.get(position);
        holder.img.setImageResource(R.drawable.pencil);
        holder.txttennote.setText("Mã note: "+_entry.getManote());
        holder.txtngaynote.setText("Tên note: "+_entry.getTennote());
        holder.txtnoidung.setText("Nội dung: "+_entry.getNoidung());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrNote = arrSortNote;
    }
    public Filter getFilter() {
        if (noteFilter == null)
            noteFilter = new NoteAdapter.CustomFilter();
        return noteFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortNote;
                results.count = arrSortNote.size();
            }
            else {
                List<Note> lsHoaDon = new ArrayList<>();
                for (Note p : arrNote) {
                    if
                    (p.getTennote().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                arrNote = (List<Note>) results.values;
                notifyDataSetChanged();
            }
        }
    }

}
