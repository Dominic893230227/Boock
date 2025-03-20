package com.boock.service.impl;

import com.boock.entity.po.Rank;
import com.boock.entity.po.User;
import com.boock.entity.po.UserLevel;
import com.boock.repository.UserLevelRepository;
import com.boock.service.UserLevelService;
import com.boock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserLevelServiceImpl implements UserLevelService {

    @Autowired
    private UserLevelRepository repo;
    @Autowired
    private UserService userService;
    @Override
    public void addUserLevel(UserLevel userLevel) {
        // 存一条数据
        repo.save(userLevel);
    }

    @Override
    public HashMap<String, Object> add50Exp(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        UserLevel userLevel = repo.findByUserId(userId);
        if (userLevel==null){
            User user = userService.loadUserInfo(userId);
            UserLevel newUL = UserLevel.builder().userId(userId).rank(Rank.F).exp(0).userName(user.getName()).build();
            repo.save(newUL);
            userLevel = newUL;
        }
        Integer exp = userLevel.getExp();
        if(getNextRank(userLevel.getRank())==null){
            userLevel.setExp(exp+50);
            map.put("msg","+50 Exp");
            repo.save(userLevel);
        }else{
            if(exp + 50>100*userLevel.getRank().getValue()){
                Rank nextLevel = getNextRank(userLevel.getRank());
                userLevel.setRank(nextLevel);
                userLevel.setExp(exp+50-100*userLevel.getRank().getValue());
                map.put("msg","+50 Exp,恭喜你已升至"+nextLevel+"级");
                repo.save(userLevel);
            }else{
                userLevel.setExp(exp+50);
                map.put("msg","+50 Exp");
                repo.save(userLevel);
            }
        }
        return map;
    }

    @Override
    public HashMap<String, Object> add20Exp(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        UserLevel userLevel = repo.findByUserId(userId);
        if (userLevel==null){
            User user = userService.loadUserInfo(userId);
            UserLevel newUL = UserLevel.builder().userId(userId).rank(Rank.F).exp(0).userName(user.getName()).build();
            repo.save(newUL);
            userLevel = newUL;
        }
        Integer exp = userLevel.getExp();
        if(getNextRank(userLevel.getRank())==null){
            userLevel.setExp(exp+20);
            map.put("msg","+20 Exp");
            repo.save(userLevel);
        }else{
            if(exp + 20>100*userLevel.getRank().getValue()){
                Rank nextLevel = getNextRank(userLevel.getRank());
                userLevel.setRank(nextLevel);
                userLevel.setExp(exp+20-100*userLevel.getRank().getValue());
                map.put("msg","+20 Exp,恭喜你已升至"+nextLevel+"级");
                repo.save(userLevel);
            }else{
                userLevel.setExp(exp+20);
                map.put("msg","+20 Exp");
                repo.save(userLevel);
            }
        }
        return map;
    }

    private Rank getNextRank(Rank currentRank) {
        Rank[] ranks = Rank.values();
        for (int i = 0; i < ranks.length - 1; i++) {
            if (ranks[i] == currentRank) {
                return ranks[i + 1];
            }
        }
        return null; // 如果已经是最高等级，则返回 null
    }
}
