package shreyas.musicsearchapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import shreyas.musicsearchapp.R;
import shreyas.musicsearchapp.listener.TrackOnClickListener;
import shreyas.musicsearchapp.model.TrackDetails;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  Recycler view adapter for displaying track lists with album name, album art and track name and artist
 *  in each row. View holder pattern is used with recycler view
 */

public class TrackResultsAdapter extends RecyclerView.Adapter<TrackResultsAdapter.ViewHolder>{

    private Context context;
    private TrackOnClickListener trackOnClickListener;
    private ArrayList<TrackDetails> trackDetailsArrayList;

    public TrackResultsAdapter(Context context, TrackOnClickListener trackOnClickListener) {
        this.context = context;
        this.trackOnClickListener = trackOnClickListener;
        this.trackDetailsArrayList = new ArrayList<>();
    }

    @Override
    public TrackResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.artist_track_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackResultsAdapter.ViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackOnClickListener.onItemClick(trackDetailsArrayList.get(position));
            }
        });
        Picasso.with(context).load(trackDetailsArrayList.get(position).getArtworkUrl100()).into(holder.albumArt);
        holder.trackName.setText(trackDetailsArrayList.get(position).getTrackName());
        holder.artistName.setText(trackDetailsArrayList.get(position).getArtistName());
        holder.albumName.setText(trackDetailsArrayList.get(position).getCollectionName());
    }

    @Override
    public int getItemCount() {
        return trackDetailsArrayList == null ? 0 : trackDetailsArrayList.size();
    }

    public void setTrackDetailsArrayListData(ArrayList<TrackDetails> trackDetailsArrayListData) {
        this.trackDetailsArrayList = trackDetailsArrayListData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView albumArt;
        public TextView trackName;
        public TextView artistName;
        public TextView albumName;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.single_track_view);
            albumArt = (ImageView) itemView.findViewById(R.id.artist_album_image);
            trackName = (TextView) itemView.findViewById(R.id.track_name);
            artistName = (TextView) itemView.findViewById(R.id.artist_name);
            albumName = (TextView) itemView.findViewById(R.id.album_name);
        }
    }
}
