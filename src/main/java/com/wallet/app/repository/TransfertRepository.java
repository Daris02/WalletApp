package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Transfert;

public class TransfertRepository implements Crud<Transfert> {

    @Override
    public Transfert getById(String id) {
        return (Transfert) AutoCrud.findById(id, "transfert");
    }

    @Override
    public List<Transfert> findAll() {
        List<Transfert> listTransferts = new ArrayList<>();
        for (Object object : AutoCrud.findAll("transfert")) {
            listTransferts.add((Transfert)object);
        }
        return listTransferts;
    }

    @Override
    public List<Transfert> saveAll(List<Transfert> toSave) {
        List<Transfert> saveAll = new ArrayList<>();
        for (Transfert transfert : toSave) {
            save(transfert);
            saveAll.add(getById(transfert.getId()));
        }
        return saveAll;
    }

    @Override
    public Transfert save(Transfert toSave) {
        AutoCrud.save(toSave);
        return getById(toSave.getId());
    }

}
