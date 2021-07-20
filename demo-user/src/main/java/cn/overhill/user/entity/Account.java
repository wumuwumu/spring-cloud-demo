package cn.overhill.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_account")
public class Account {

    private Long id;

    private Long userId;


    private Integer account;
}
