# 根据要求写 SQL

## 描述

用户浏览视频日志：user_behavior

|字段|描述|
|:---:|:---:|
|date||
|user_id||
|video_id||
|start_time||
|end_time||

视频信息：video_info

|字段|描述|
|:---:|:---:|
|video_id||
|video_duration||

用户信息：user_info

|字段|描述|
|:---:|:---:|
|user_id||
|gender||

### 问题一：

某一天（如20200310），观看不同视频个数最多的前 5 名 user_id

```sql
SELECT user_id 
FROM (
    SELECT user_id, RANK() OVER (ORDER BY COUNT(DISTINCT video_id) DESC) AS rk
    FROM user_behavior
    WHERE date = '20200310'
    GROUP BY user_id
) t
WHERE rk <= 5;
```

### 问题二：

观看超过 50 个不同视频的女性用户中，完整观看率最高的 10 个 user_id

```sql
SELECT user_id
FROM(
    SELECT
        user_id,
        RANK() OVER(ORDER BY SUM(complete_flag) / COUNT(video_id) DESC) AS rk
    FROM (
        SELECT 
            t3.user_id, 
            t3.video_id, 
            CASE WHEN (t3.end_time - t3.start_time) = t4.video_duration THEN 1 ELSE 0 END AS complete_flag
        FROM
            (SELECT t1.* FROM user_behavior t1 LEFT JOIN user_info t2 ON t1.user_id = t2.user_id WHERE t2.gender = '女') t3 
            LEFT JOIN video_info t4 ON t3.video_id = t4.video_id) t5
    GROUP BY user_id 
    HAVING COUNT(DISTINCT video_id) >= 50) t6
) t7 
WHERE rk <= 10;
```
