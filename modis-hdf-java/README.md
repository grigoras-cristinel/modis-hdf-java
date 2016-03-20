# modis-hdf-java
Modis and HDF data extraction tools

Cerinte
- java 8
- HDFView, hdfjava libs
- maven

###Utilizare

**mvn -P Interface exec:exec** 
Porneste interfata SWING pentru extragere de date punctuale.

**mvn -P brokerhdf04  exec:java** Porneste un file broker pentru M?D04 pentru utilizarea in cluster. 
Un protocol simplu pentru sincronizare prelucrarii in paralel a fisierelor in clusterul rocks.
 
 **mvn -P runextracthdf04  exec:java** Clientul, pentru filebroker sincronizat, ce filtreaza si extrage punctele din hdf. Ruleaza multiprocess
 pe nodurile din cluster sau pe aceeasi masina. 
 
 **mvn -P filebroker04  exec:java** File broker pentru fisierele shp pentru datele selectate din hdf.
Sincronizarea pentru cluster. 

**mvn -P runshape04  exec:java** Construieste shp din punctele alese, este client pentru filebroker04 

 **mvn -P filebroker06  exec:java** File broker pentru fisierele shp pentru datele selectate din hdf.
Sincronizarea pentru cluster. 

**mvn -P runshape06  exec:java** Construieste shp din punctele alese, este client pentru filebroker04