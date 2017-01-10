CREATE PROCEDURE `proc_statistic_line_7dproportion`()
  BEGIN
    INSERT INTO ia_report_frequency
    -- 统计产品线下的7日内百分比
      SELECT
        date_sub(curdate(), INTERVAL 1 DAY) totaldate,
        NULL                                mdcode,
        T.lineId                            lineid,
        NULL                                devavg,
        NULL                                useavg,
        concat(cast(sum(
                        CASE WHEN T.useavg <= 7
                          THEN 1
                        ELSE 0 END
                    ) * 100 / count(1) AS DECIMAL(10, 1)),
               '%')                         7dproportion
      FROM
        (
          -- 按照型号分组的间隔天数
          SELECT
            log1.productcode,
            B.pid                                                                                        lineId,
            cast((to_days(max(log1.cdtime)) - to_days(min(log1.cdtime))) / C.days AS DECIMAL(10, 0)) + 1 useavg
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
                        -- 每个型号不同日期的总天数
                        SELECT
                          COUNT(M.d) days,
                          M.productcode
                        FROM (
                               -- 每个型号同一天的记录只取一条
                               SELECT DISTINCT
                                 to_days(log2.cdtime) d,
                                 log2.productcode
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
                               GROUP BY log2.productcode, to_days(log2.cdtime)
                             ) M
                        GROUP BY M.productcode
                      ) C
              ON log1.productcode = C.productcode
          WHERE A.uid IS NOT NULL
          GROUP BY log1.productcode
        ) T
      GROUP BY T.lineId;

  END