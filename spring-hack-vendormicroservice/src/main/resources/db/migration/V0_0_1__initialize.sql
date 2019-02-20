CREATE TABLE vendor(vendor_id bigint not null,vendor_address varchar,vendor_contact_no bigint,vendor_email varchar,vendor_name varchar,vendor_username varchar,PRIMARY KEY(vendor_id));

insert into vendor values(111,'V_Address1',1345667,'test@gmail.com','V_Name1','V_UserName');
insert into vendor values(222,'V_Address2',1345668,'test2@gmail.com','V_Name2','V_UserName1');
insert into vendor values(333,'V_Address3',1345669,'test3@gmail.com','V_Name3','V_UserName2');
insert into vendor values(444,'V_Address4',1345670,'test4@gmail.com','V_Name4','V_UserName3');