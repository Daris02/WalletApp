package com.wallet.app.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.wallet.app.model.Balance;
import com.wallet.app.repository.BalanceRepository;

public class BalanceService {
    private BalanceRepository balanceRepo = new BalanceRepository();

    public Balance getById(String id) {
        return balanceRepo.getById(id);
    }

    public List<Balance> getAll() {
        return balanceRepo.findAll();
    }

    public Balance save(Balance toSave) {
        return balanceRepo.save(toSave);
    }

    public void removeById(String id) {
        balanceRepo.removeById(id);
    }

    public List<Balance> getBalancesHistory(String id) {
        return balanceRepo.getBalanceHistory(id, null, null);
    }
    
    public List<Balance> getBalancesHistoryWithDate(String id, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        Timestamp start = Timestamp.valueOf(startDatetime);
        Timestamp end = Timestamp.valueOf(endDatetime);
        return balanceRepo.getBalanceHistory(id, start, end);
    }

    public Balance getBalanceNow(String id) {
        return balanceRepo.getBalanceNow(id);
    }
}
