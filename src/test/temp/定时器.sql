

-- 创建定时任务（调用统计存储过程）

drop event if exists event_statistic_line_7dproportion;
CREATE EVENT event_statistic_line_7dproportion
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_line_7dproportion();

drop event if exists event_statistic_line_useavg;
CREATE EVENT event_statistic_line_useavg
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_line_useavg();

drop event if exists event_statistic_total_7dproportion;
CREATE EVENT event_statistic_total_7dproportion
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_total_7dproportion();

drop event if exists event_statistic_total_useavg;
CREATE EVENT event_statistic_total_useavg
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_total_useavg();

drop event if exists event_statistic_type_7dproportion;
CREATE EVENT event_statistic_type_7dproportion
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_type_7dproportion();

drop event if exists event_statistic_type_useavg;
CREATE EVENT event_statistic_type_useavg
ON SCHEDULE EVERY 10 MINUTE
STARTS '2017-01-01 01:00:00'
ON COMPLETION PRESERVE ENABLE
    DO
CALL proc_statistic_type_useavg();



-- 查看event是否开启
show variables like '%sche%';
--开启方法
set global event_scheduler =1;

-- 查看定时任务
select * from mysql.event;

-- 关闭事件任务
alter event event_statistic_line_7dproportion ON COMPLETION PRESERVE DISABLE;
alter event event_statistic_line_useavg ON COMPLETION PRESERVE DISABLE;
alter event event_statistic_total_7dproportion ON COMPLETION PRESERVE DISABLE;
alter event event_statistic_total_useavg ON COMPLETION PRESERVE DISABLE;
alter event event_statistic_type_7dproportion ON COMPLETION PRESERVE DISABLE;
alter event event_statistic_type_useavg ON COMPLETION PRESERVE DISABLE;

-- 开启事件任务
alter event event_statistic_line_7dproportion ON COMPLETION PRESERVE ENABLE;
alter event event_statistic_line_useavg ON COMPLETION PRESERVE ENABLE;
alter event event_statistic_total_7dproportion ON COMPLETION PRESERVE ENABLE;
alter event event_statistic_total_useavg ON COMPLETION PRESERVE ENABLE;
alter event event_statistic_type_7dproportion ON COMPLETION PRESERVE ENABLE;
alter event event_statistic_type_useavg ON COMPLETION PRESERVE ENABLE;

SELECT *
FROM ia_report_frequency;
