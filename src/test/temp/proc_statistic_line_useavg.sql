CREATE PROCEDURE `proc_statistic_line_useavg`()
  BEGIN
    INSERT INTO ia_report_frequency
    -- 统计产品线下的使用间隔时间
      SELECT
        date_sub(curdate(), INTERVAL 1 DAY)                                                          totaldate,
        NULL                                                                                         mdcode,
        B.pid                                                                                        lineId,
        NULL                                                                                         devavg,
        cast((to_days(max(log1.cdtime)) - to_days(min(log1.cdtime))) / C.days AS DECIMAL(10, 0)) + 1 useavg,
        NULL                                                                                         7dproportion
      FROM (
             -- 查询所有log日志
             SELECT
               o1.cdtime,
               o1.productcode,
               o1.machineid devId
             FROM operaterecord_data_80a0 o1
             WHERE o1.deleted = 0
                   AND DATE_FORMAT(o1.cdtime, '%Y-%m-%d') < curdate()
             UNION
             SELECT
               o2.cdtime,
               o2.productcode,
               o2.machineid devId
             FROM operaterecord_data_9000 o2
             WHERE o2.deleted = 0
                   AND DATE_FORMAT(o2.cdtime, '%Y-%m-%d') < curdate()
             UNION
             SELECT
               o3.cdtime,
               idi.mdcode productcode,
               o3.devid   devId
             FROM ia_onecup_finshlog o3
               LEFT JOIN ia_device_info idi
                 ON o3.devid = idi.produce_code
             WHERE DATE_FORMAT(o3.cdtime, '%Y-%m-%d') < curdate()
           ) log1
        -- 日志和用户关联
        LEFT JOIN (
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
          ON log1.devId = A.did
        -- 日志和产品线关联
        LEFT JOIN (
                    SELECT
                      m.dcode,
                      m.pid
                    FROM ia_productmodel m
                  ) B
          ON log1.productcode = B.dcode
        --
        LEFT JOIN (
                    -- 每个产品线不同日期的总天数
                    SELECT
                      COUNT(M.d) days,
                      M.lineId
                    FROM (
                           -- 每个产品线同一天的记录只取一条
                           SELECT DISTINCT
                             to_days(log2.cdtime) d,
                             im.pid               lineId
                           FROM (
                                  -- 查询所有log日志
                                  SELECT
                                    o1.cdtime,
                                    o1.productcode
                                  FROM operaterecord_data_80a0 o1
                                  WHERE o1.deleted = 0
                                        AND DATE_FORMAT(o1.cdtime, '%Y-%m-%d') < curdate()
                                  UNION
                                  SELECT
                                    o2.cdtime,
                                    o2.productcode
                                  FROM operaterecord_data_9000 o2
                                  WHERE o2.deleted = 0
                                        AND DATE_FORMAT(o2.cdtime, '%Y-%m-%d') < curdate()
                                  UNION
                                  SELECT
                                    o3.cdtime,
                                    idi.mdcode productcode
                                  FROM ia_onecup_finshlog o3
                                    LEFT JOIN ia_device_info idi
                                      ON o3.devid = idi.produce_code
                                  WHERE DATE_FORMAT(o3.cdtime, '%Y-%m-%d') < curdate()
                                ) log2
                             LEFT JOIN ia_productmodel im
                               ON log2.productcode = im.dcode
                           GROUP BY im.pid, to_days(log2.cdtime)
                         ) M
                    GROUP BY M.lineId
                  ) C
          ON B.pid = C.lineId
      WHERE A.uid IS NOT NULL
      GROUP BY B.pid;

  END