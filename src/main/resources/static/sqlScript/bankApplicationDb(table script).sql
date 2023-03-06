drop table if exists customers cascade;
create table customers(

	cus_acc_num serial not null unique,
	cus_Name varchar(50) not null,
	cus_Balance numeric(11,2) default 0.00,
	acc_creation timestamp,
	last_Modified timestamp,
	
	primary key(cus_acc_num)

);

drop table if exists transactions cascade;
create table transactions(

	transaction_id serial not null unique,
	customerId serial not null unique,
	transaction_amount numeric(11,2),
	account_balance numeric(11,2),
	transaction_made_on timestamp not null,
	transaction_Type varchar(20) not null,
	description text,
	
	primary key(trans_Id),
	
	foreign key(customerId) references customers (cus_acc_num)

);
