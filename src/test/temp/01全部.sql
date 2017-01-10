-- 7日占比

SELECT

  T.totaldate,
  sum(
      CASE WHEN T.useavg <= 7
        THEN 1
      ELSE 0 END
  )                                       part,
  count(1)                                total,
  cast(sum(
           CASE WHEN T.useavg <= 7
             THEN 1
           ELSE 0 END
       ) / count(1) AS DECIMAL(10, 2)) AS 7dproportion
FROM
  (
    -- 间隔天数
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
                    count(M.day) days,
                    M.lineId
                  FROM (
                         -- 每个产品线同一天的记录只取一条
                         SELECT DISTINCT
                           to_days(o2.cdtime) day,
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

