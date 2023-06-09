package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Evaluation;
import com.example.bookStore.example.vo.EvaluationBookShop;
import com.example.bookStore.example.vo.EvaluationUser;

import java.util.List;

public interface EvaluationService extends IService<Evaluation> {
    Boolean add(Evaluation evaluation);

    void deleteByIds(String ids);

    Boolean update(Evaluation evaluation);

    List<EvaluationBookShop> listByUserId();

    Page<EvaluationUser> pageByBookId(String bookId, Integer pageNum, Integer pageSize);

}
