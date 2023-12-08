package com.wallet.app.service;

import java.util.List;

import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;
import com.wallet.app.repository.TransactionRepository;
import com.wallet.app.repository.TransfertRepository;

public class TransfertService {
    TransactionRepository transactionRepository = new TransactionRepository();
    TransfertRepository transfertRepository = new TransfertRepository();

    public List<Transfert> getAllTransferts() {
        return transfertRepository.findAll();
    }
    
    public Transfert saveTransfert(String debtorId, String creditorId, Double amount) {
        transactionRepository.save(new Transaction("Transfert", amount, "DEBIT", debtorId));
        transactionRepository.save(new Transaction("Transfert", amount, "CREDIT", creditorId));
        Transfert transfert = new Transfert(debtorId, creditorId, amount);
        transfertRepository.save(transfert);
        return transfert;
    }
    
}
