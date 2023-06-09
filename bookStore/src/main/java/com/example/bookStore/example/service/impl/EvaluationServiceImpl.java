package com.example.bookStore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.common.utils.DateTool;
import com.example.bookStore.common.utils.JwtUtil;
import com.example.bookStore.example.entiey.Evaluation;
import com.example.bookStore.example.mapper.EvaluationMapper;
import com.example.bookStore.example.service.EvaluationService;
import com.example.bookStore.example.vo.EvaluationBookShop;
import com.example.bookStore.example.vo.EvaluationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Evaluation evaluation) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        evaluation.setUserId(userId);
        evaluation.setCreateTime(DateTool.getCurrTime());
        this.save(evaluation);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean update(Evaluation evaluation) {
        this.updateById(evaluation);
        return true;
    }

    @Override
    public List<EvaluationBookShop> listByUserId() {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        return baseMapper.listByUserId(userId);
    }

    @Override
    public Page<EvaluationUser> pageByBookId(String bookId, Integer pageNum, Integer pageSize) {
        Page<EvaluationUser> page = new Page<>(pageNum, pageSize);
        return baseMapper.pageByBookId(page, bookId);
    }

}
