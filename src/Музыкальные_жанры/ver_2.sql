SELECT id, name, parent_genre_id
	FROM public.genre;

WITH RECURSIVE r AS (
	SELECT id, name, parent_genre_id, CONCAT (name, ' ') as track
	FROM public.genre
  	WHERE parent_genre_id IS NULL
--  	WHERE id = 1
	
	UNION
	
	SELECT public.genre.id, public.genre.name, public.genre.parent_genre_id, concat(r.track, public.genre.name) as track
	FROM public.genre
	JOIN r 
		ON public.genre.parent_genre_id = r.id
)
SELECT *
FROM r
---------------

SELECT *
FROM public.track;


SELECT *
FROM public.track_genre;

WITH tracks AS (
	SELECT 
	FROM 
)

SELECT id AS track_id, AS genre_id, name AS track_name, AS genre_name
FROM public.track
JOIN ALL
SELECT 
FROM 



