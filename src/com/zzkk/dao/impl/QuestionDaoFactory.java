package com.zzkk.dao.impl;

import com.zzkk.dao.IQuestionDaoFactory;

public class QuestionDaoFactory implements IQuestionDaoFactory {
    @Override
    public QuestionContentDao createQuestionContentDao(int qid) {
        return new QuestionContentDao(qid);
    }

    @Override
    public QuestionListDao createQuestionListDao(int start, int total) {
        return new QuestionListDao(start ,total);
    }

    @Override
    public Test_dataDao creatTest_dataDao(int qid) {
        return new Test_dataDao(qid);
    }


}
