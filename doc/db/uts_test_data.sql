
-- 添加存储过程添加10000条用户测试数据
DROP PROCEDURE IF EXISTS create_uts_user_test_data_proc;
DELIMITER $$
SET AUTOCOMMIT = 0$$
CREATE  PROCEDURE create_uts_user_test_data_proc()
BEGIN
DECLARE v_cnt DECIMAL (10)  DEFAULT 0 ;
dd:LOOP
          INSERT INTO `uts_user`(`user_name`) VALUES (CONCAT('tony', v_cnt+1));
                  COMMIT;
                    SET v_cnt = v_cnt+1 ;
                           IF  v_cnt = 10000 THEN LEAVE dd;
                          END IF;
         END LOOP dd ;
END;$$
DELIMITER ;

-- 调用存储过程
call create_uts_user_test_data_proc;

-- 删除存储过程
DROP PROCEDURE IF EXISTS create_uts_user_test_data_proc;




-- 添加化标签测试数据
INSERT INTO `uts_tag`(`tag_name`) VALUES ('女性');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('男性');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('活跃');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('广东');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('沿海');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('东北');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('新增');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('广西');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('南海');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('男孩');
INSERT INTO `uts_tag`(`tag_name`) VALUES ('女孩');

commit;