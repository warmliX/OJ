package com.zzkk.dao;

import com.zzkk.dao.impl.RecordDao;
import com.zzkk.dao.impl.RefurbishDao;

public interface IRecordDaoFactory {
    public RecordDao createRecordDao(String email ,int qid ,int state ,int language ,int codelength);

    public RefurbishDao createRefurbishDao(String email ,int currentPage ,int total);
}
