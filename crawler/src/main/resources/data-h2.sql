INSERT INTO culture (id,culture_name)
SELECT 1, 'musical'
FROM dual
WHERE NOT EXISTS (
SELECT *  FROM culture
WHERE  id = 1 and culture_name = 'musical'
);
INSERT INTO culture (id,culture_name)
SELECT 2, 'concert'
FROM dual
WHERE NOT EXISTS (
SELECT *  FROM culture
WHERE  id = 2 and culture_name = 'concert'
);
INSERT INTO culture (id,culture_name)
SELECT 3, 'play'
FROM dual
WHERE NOT EXISTS (
SELECT *  FROM culture
WHERE  id = 3 and culture_name = 'play'
);
INSERT INTO culture (id,culture_name)
SELECT 4, 'exhibition'
FROM dual
WHERE NOT EXISTS (
SELECT *  FROM culture
WHERE  id = 4 and culture_name = 'exhibition'
);
INSERT INTO culture (id,culture_name)
SELECT 5, 'etc'
FROM dual
WHERE NOT EXISTS (
SELECT *  FROM culture
WHERE  id = 5 and culture_name = 'etc'
);