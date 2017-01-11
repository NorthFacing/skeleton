DROP PROCEDURE IF EXISTS proc_statistic_total_useavg;

CREATE PROCEDURE proc_statistic_total_useavg()
  BEGIN
    INSERT INTO ia_report_frequency
    -- 统计全部设备的使用间隔时间
      SELECT
        date_sub(curdate(), INTERVAL 1 DAY)                          totaldate,
        NULL                                                         mdcode,
        NULL                                                         lineid,
        NULL                                                         devavg,
        cast((max(T.d) - min(T.d)) / count(1) AS DECIMAL(10, 0)) + 1 useavg,
        NULL                                                         7dproportion
      FROM (
             SELECT DISTINCT to_days(O.cdtime) d
             FROM (
                    -- 查询所有log日志
                    SELECT
                      o1.cdtime,
                      o1.machineid devId
                    FROM operaterecord_data_80a0 o1
                    WHERE o1.deleted = 0
                          AND DATE_FORMAT(o1.cdtime, '%Y-%m-%d') < curdate()
                    UNION
                    SELECT
                      o2.cdtime,
                      o2.machineid devId
                    FROM operaterecord_data_9000 o2
                    WHERE o2.deleted = 0
                          AND DATE_FORMAT(o2.cdtime, '%Y-%m-%d') < curdate()
                    UNION
                    SELECT
                      o3.cdtime,
                      o3.devid devId
                    FROM ia_onecup_finshlog o3
                    WHERE DATE_FORMAT(o3.cdtime, '%Y-%m-%d') < curdate()
                  ) O
               LEFT JOIN (
                           -- 关联用户信息
                           SELECT DISTINCT
                             ud.did,
                             ud.uid
                           FROM ia_user_dev ud
                             -- 设备和用户关联
                             LEFT JOIN ia_appusers u
                               ON ud.uid = u.id
                           WHERE ud.deleted = 0
                                 AND u.deleted = 0
                           GROUP BY ud.did
                         ) A
                 ON O.devId = A.did
             WHERE A.uid IS NOT NULL
           ) T;

  END
