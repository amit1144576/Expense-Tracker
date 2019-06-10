package com.ISEE2019_CODEPROS_team.MoneyCTRL.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ISEE2019_CODEPROS_team.MoneyCTRL.Model.Transactions;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter <TransactionsAdapter.TransactionsHolder> {

    private List<Transactions> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransactionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_list, parent, false);
        return new TransactionsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsHolder holder, int position) {
        Transactions currentTransaction = transactions.get(position);
        holder.textViewDescription.setText(currentTransaction.getDescription());
        holder.textViewDate.setText(currentTransaction.getTransDate());
        // this is changed into string type in order to test purpose
        // holder.textViewAmount.setText(String.valueOf(currentTransaction.getTransactionAmount()));
        holder.textViewAmount.setText(currentTransaction.getTransactionAmount());
        holder.textViewCategory.setText(currentTransaction.getTransCategory());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transactions> transactions){
        this.transactions = transactions;
        notifyDataSetChanged();
    }


    class TransactionsHolder extends RecyclerView.ViewHolder{
        private TextView textViewAmount;
        private TextView textViewDate;
        private TextView textViewDescription;
        private TextView textViewCategory;

        public TransactionsHolder(@NonNull View itemView) {
            super(itemView);
            textViewAmount = itemView.findViewById(R.id.trans_amount);
            textViewDate = itemView.findViewById(R.id.trans_date);
            textViewDescription = itemView.findViewById(R.id.trans_description);
            textViewCategory = itemView.findViewById(R.id.trans_category);
        }
    }
}
