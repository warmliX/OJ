package com.zzkk.dao;

import com.zzkk.dao.impl.QuestionContentDao;
import com.zzkk.dao.impl.QuestionListDao;
import com.zzkk.dao.impl.Test_dataDao;

public interface IQuestionDaoFactory {
    public QuestionContentDao createQuestionContentDao(int qid);

    public QuestionListDao createQuestionListDao(int start ,int total);

    public Test_dataDao creatTest_dataDao(int qid);
}
