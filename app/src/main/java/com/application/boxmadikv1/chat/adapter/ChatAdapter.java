package com.application.boxmadikv1.chat.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.chat.adapter.holder.ChatHolder;
import com.application.boxmadikv1.chat.entidad.MensajesUi;

import java.util.Collections;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {


    public static final String TAG = ChatAdapter.class.getSimpleName();

    private List<MensajesUi> mensajesUiList;

    private int SELF = 100;
    private String userId;
    private RecyclerView recycler;


    public ChatAdapter(List<MensajesUi> mensajesUiList) {
        this.mensajesUiList = mensajesUiList;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view type is to identify where to render the chat message
        // left or right
        View itemView;
        if (viewType == SELF) {
            Log.d(TAG, "viewType == SELF");
            // self message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_self, parent, false);
        } else {
            Log.d(TAG, "else == else");
            // others message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_other, parent, false);
        }
        return new ChatHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        MensajesUi mensajesUi = mensajesUiList.get(position);
        holder.bind(mensajesUi);
    }

    @Override
    public int getItemCount() {
        if (mensajesUiList == null) return 0;
        return mensajesUiList.size();

    }

    @Override
    public int getItemViewType(int position) {
        MensajesUi message = mensajesUiList.get(position);
        if (message.getCodigoUsuario().equals(userId)) {
            return SELF;
        }
        return position;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void mostrarLista(List<MensajesUi> mensajesUiList) {
        if (mensajesUiList != null && !mensajesUiList.isEmpty()) {
            Log.d(TAG, "mensajesUiList!=null");
            this.mensajesUiList.clear();
            Collections.reverse(mensajesUiList);
            this.mensajesUiList.addAll(mensajesUiList);
            notifyDataSetChanged();
            scrollToLastItem();
        } else {
            Log.d(TAG, "casoContrarioMostrarLista");
        }


    }

    public void agregarItem(MensajesUi mensajesUi) {
        if (mensajesUi != null) {
            this.mensajesUiList.add(mensajesUi);
            notifyItemInserted(getItemCount() - 1);
            scrollToLastItem();
        }

    }


    public void eliminarItem(MensajesUi mensajesUi) {
        int position = this.mensajesUiList.indexOf(mensajesUi);
        if (position != -1) {
            this.mensajesUiList.remove(mensajesUi);
            notifyItemRemoved(position);
        }
    }

    public void setRecyclerView(RecyclerView recycler) {
        this.recycler = recycler;
    }

    /*public void setRecyclerView(RecyclerView recycler) {
        this.recycler = recycler;
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int messagesCount = getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();

                Log.d(TAG, "messagesCount: " + messagesCount);
                Log.d(TAG, "itemCount: " + itemCount);
                Log.d(TAG, "positionStart: " + positionStart);
                Log.d(TAG, "lastVisiblePosition: " + lastVisiblePosition);
                Log.d(TAG, "lastPosition: " + lastPosition);
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (messagesCount - 1) &&
                                lastVisiblePosition == (positionStart - 2))) {
                    scrollToLastItem();
                } else {
                    if (itemCount == 1) {
                        Log.d(TAG, "onNewMessageAddedToTheBottom: " );
                        //listener.onNewMessageAddedToTheBottom();
                    }
                }
            }
        });
        recycler.addOnScrollListener(mScrollListener);

    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int visibleItemCount = mLinearLayoutManager.getChildCount();
            int totalItemCount = mLinearLayoutManager.getItemCount();
            int pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                //End of list
              //  onBottomReachedListener.onBottomReached();
                Log.d(TAG, "onBottomReached: " );
            } else {
              //  onBottomReachedListener.onNotBottom();
                Log.d(TAG, "onNotBottom: " );
            }
        }
    };
*/


    public void scrollToLastItem() {
        recycler.scrollToPosition(getItemCount() - 1);
    }

    public void mostrarListaAdd(List<MensajesUi> mensajesUiList) {
        if (mensajesUiList != null && !mensajesUiList.isEmpty()) {
            Collections.reverse(mensajesUiList);
            this.mensajesUiList.addAll(0,mensajesUiList);
            notifyItemRangeInserted(0, mensajesUiList.size());
        }

    }



}
