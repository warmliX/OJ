package com.zzkk.dao.impl;

import com.zzkk.dao.IRecordDaoFactory;

public class RecordDaoFactory implements IRecordDaoFactory {
    @Override
    public RecordDao createRecordDao(String email ,int qid ,int state ,int language ,int codelength) {
        return new RecordDao(email ,qid ,state ,language ,codelength);
    }

    @Override
    public RefurbishDao createRefurbishDao(String email, int currentPage, int total) {
        return new RefurbishDao(email ,currentPage ,total);
    }
}
