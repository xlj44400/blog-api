SELECT e.entry_id,
       e.title,
/*[# th:if="!${excludeContent}"]*/
       e.content,
/*[/]*/
       e.created_by,
       e.created_date,
       e.last_modified_by,
       e.last_modified_date
FROM entry AS e
WHERE e.entry_id IN (/*[# mb:p="entryIds"]*/ 600 /*[/]*/)
ORDER BY e.last_modified_date DESC