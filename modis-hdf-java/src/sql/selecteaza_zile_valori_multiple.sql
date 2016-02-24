select kk,masdey from (
	select kk ,count(databaseKey) as masdey from (
		select substr(databaseKey,2,7)||statie as kk,databaseKey,opticalDepthLandAndOcean,angstromExponentLand from puncte where opticalDepthLandAndOcean > 0 
	) as dd  group by kk 
) as msk where masdey > 4