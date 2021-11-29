SELECT query, cnt FROM 
(
SELECT COUNT(*) as cnt, query as query FROM queries
WHERE search_index = ($si) AND from = '$ip' AND platform = '$plt' 
GROUP BY query
) 
ORDER BY cnt DESC LIMIT 5