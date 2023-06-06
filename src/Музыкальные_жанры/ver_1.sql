SELECT * FROM public.genre;

WITH RECURSIVE genres (id, name, parent_genre_id, path) as (
	SELECT * 
	FROM public.genre AS T1
	WHERE parent_genre_id IS NULL
	UNION
	SELECT T2.id, T2.name, T2.parent_genre_id, CONCAT(genres.path || " - " || T2.name AS varchar(50)) AS path
	FROM public.genre AS T2
	JOIN genres ON (genres.parent_genre_id = T2.id)
)
SELECT *
FROM genres