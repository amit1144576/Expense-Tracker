package com.example.barchart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder> {

    private List<Transactions> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransactionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_items, parent, false);
        return new TransactionsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsHolder holder, int position) {
        Transactions currentTransaction = transactions.get(position);
        holder.textViewAmount.setText(String.valueOf(currentTransaction.getTransactionAmount()));
        holder.textViewDescription.setText(currentTransaction.getDescription());
        holder.textViewDate.setText(currentTransaction.getTransCategory());

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    class TransactionsHolder extends RecyclerView.ViewHolder {
        private TextView textViewAmount;
        private TextView textViewDescription;
        private TextView textViewDate;

        public TransactionsHolder(@NonNull View itemView) {
            super(itemView);
            textViewAmount = itemView.findViewById(R.id.transaction_amount);
            textViewDescription = itemView.findViewById(R.id.transaction_description);
            textViewDate = itemView.findViewById(R.id.transaction_date);
        }
    }
}
