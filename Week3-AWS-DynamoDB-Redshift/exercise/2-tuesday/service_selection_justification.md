\## Part A

&#x20;

Scenario 1: RDS because it needs a relational database because it is has ACID consistency. The fixed schema refers to RDS's schema rigidity, which prevents malformed data across tables. RDS also allows for relationship support, and different columns from different tables are related through foreign keys.



Scenario 2: DynamoDB because it allows for write scalability: data is partitioned without cross table join logic or referential integrity, which makes it easier to absorb large amounts of data. Since the data is dynamic, there is schema flexibility and each row in a table can have different attributes. Since lookups are by primary key, reads and writes are fast on a massive scale.



Scenario 3: Redshift because column oriented storage allows for quicker queries because you don't have to look at other fields in a row to get the information that you need. The different sales channel over multiple years hints at parallel processing. The billions of rows over various years means queries are quick unlike OLTP (operational) databases like RDS, which are quick for smaller queries. 



\## Part B

&#x20;

1\. If you select a relational database for Scenario 2, it will make look up transactions take more time. The operational and financial risk is higher when using a non-key value pair database as more transactions means more lookups means more storage in the cloud which ultimately means more money.



2\. It stores entire files. When there is no related data amongst different files and file security is important.

