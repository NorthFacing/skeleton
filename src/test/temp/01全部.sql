-- 间隔天
SELECT
  date_sub(curdate(), INTERVAL 1 DAY)                              totaldate,
  NULL                                                             mdcode,
  NULL                                                             lineid,
  NULL                                                             devavg,
  cast((max(T.day) - min(T.day)) / count(1) AS DECIMAL(10, 0)) + 1 useavg,
  NULL                                                             7dproportion
FROM (
       SELECT
         DISTINCT to_days(o.cdtime) day
       FROM operaterecord_data_80a0 o
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
           ON o.machineid = A.did
       WHERE o.deleted = 0
             AND DATE_FORMAT(o.cdtime, '%Y-%m-%d') < curdate()
             AND A.uid IS NOT NULL
       GROUP BY to_days(o.cdtime)
     ) T;

-- 7日占比

SELECT
  T.totaldate,
  NULL        mdcode,
  NULL        lineid,
  NULL        devavg,
  NULL        useavg,
  concat(cast(sum(
                  CASE WHEN T.useavg <= 7
                    THEN 1
                  ELSE 0 END
              ) * 100 / count(1) AS DECIMAL(10, 1)),
         '%') 7dproportion
FROM
  (
    -- 按照产品线分组的间隔天数
    SELECT
      date_sub(curdate(), INTERVAL 1 DAY)                                                    totaldate,
      B.pid                                                                                  lineId,
      min(o.cdtime)                                                                          min_day,
      max(o.cdtime)                                                                          max_day,
      (to_days(max(o.cdtime)) - to_days(min(o.cdtime)))                                      '时间差',
      C.days                                                                                 '制作天数',
      cast((to_days(max(o.cdtime)) - to_days(min(o.cdtime))) / C.days AS DECIMAL(10, 0)) + 1 useavg
    FROM operaterecord_data_80a0 o
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
        ON o.machineid = A.did
      -- 日志和产品线关联
      LEFT JOIN (
                  SELECT
                    m.dcode,
                    m.pid
                  FROM ia_productmodel m
                ) B
        ON o.productcode = B.dcode
      --
      LEFT JOIN (
                  -- 每个产品线不同日期的总天数
                  SELECT
                    COUNT(M.d) days,
                    M.lineId
                  FROM (
                         -- 每个产品线同一天的记录只取一条
                         SELECT DISTINCT
                           to_days(o2.cdtime) d,
                           im.pid             lineId
                         FROM operaterecord_data_80a0 o2
                           LEFT JOIN ia_productmodel im
                             ON o2.productcode = im.dcode
                         GROUP BY im.pid, to_days(o2.cdtime)
                       ) M
                  GROUP BY M.lineId
                ) C
        ON B.pid = C.lineId
    WHERE o.deleted = 0
          AND DATE_FORMAT(o.cdtime, '%Y-%m-%d') < curdate()
          AND A.uid IS NOT NULL
    GROUP BY B.pid
  ) T;

