package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Evaluation;
//import com.example.petstore.example.vo.EvaluationBookShop;
//import com.example.petstore.example.vo.EvaluationUser;

import java.util.List;

public interface EvaluationService extends IService<Evaluation> {
    Boolean add(Evaluation evaluation);

    void deleteByIds(String ids);

    Boolean update(Evaluation evaluation);

//    List<EvaluationBookShop> listByUserId();
//
//    Page<EvaluationUser> pageByBookId(String bookId, Integer pageNum, Integer pageSize);

}
