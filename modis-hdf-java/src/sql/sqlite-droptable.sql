select kk ,count(databaseKey) from (
select substr(databaseKey,2,7)||statie as kk,databaseKey from puncte
) as dd group by kk