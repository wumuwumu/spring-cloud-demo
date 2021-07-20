package cn.overhill.user.service.impl;

import cn.overhill.user.entity.Account;
import cn.overhill.user.infra.mapper.AccountMapper;
import cn.overhill.user.service.AccountService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private int account = 100;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void reduce(int money) {
      log.info("减少账号{}",money);
      Account account = accountMapper.selectById(1);
      account.setAccount(account.getAccount()-money);
      accountMapper.updateById(account);
      log.info("账号余额{}",account);
    }

    @Override
    public int get() {
        Account account = accountMapper.selectById(1);

        return account.getAccount();
    }
}
