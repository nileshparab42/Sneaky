package com.example.sneaky;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trendingFragment extends Fragment implements RecyclerSneakerInterface {

    CardView card;

    RecyclerView recyclerView;

    ArrayList<SneakerModel> arrSneaker = new ArrayList<>();

    RecyclerSneakerAdapter recyclerSneakerAdapter;

    FirebaseFirestore db,dw;

    LinearLayoutManager linearLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public trendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static trendingFragment newInstance(String param1, String param2) {
        trendingFragment fragment = new trendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        recyclerView = view.findViewById(R.id.RecyclerSneakersTrend);

        db = FirebaseFirestore.getInstance();

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerSneakerAdapter = new RecyclerSneakerAdapter(getContext(),arrSneaker,this);
        EventChangeListner();
        recyclerView.setAdapter(recyclerSneakerAdapter);

        return view;
    }

    private void EventChangeListner() {
        db.collection("Sneakers").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error!=null){
                            Log.d("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                arrSneaker.add(dc.getDocument().toObject(SneakerModel.class));
                            }
                            recyclerSneakerAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(),SneakerDetails.class);
        intent.putExtra("NAME",arrSneaker.get(position).getName());
        startActivity(intent);
    }
}