-- 创建定时任务（调用统计存储过程）
CREATE  EVENT `proc_statistic_event` ON SCHEDULE
  EVERY 1 DAY STARTS '2017-01-01 01:00:00'
  ON COMPLETION PRESERVE ENABLE
DO BEGIN
  CALL proc_statistic_line_7dproportion();
  CALL proc_statistic_line_useavg();
  CALL proc_statistic_total_7dproportion();
  CALL proc_statistic_total_useavg();
  CALL proc_statistic_type_7dproportion();
  CALL proc_statistic_type_useavg();
END;

-- 查看定时任务
select * from mysql.event
