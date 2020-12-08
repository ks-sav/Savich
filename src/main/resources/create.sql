create table if not exists "Staff" (
    id int primary key,
    name text,
    unionMember boolean
);
create table if not exists "Puiple" (
    id int primary key,
    name text,
    nClass text,
    freeMeals boolean
);

create table if not exists "Order" (
    id int primary key,
    customerId int,
    date timestamp,
    status text,
    totalCost real
);

create table if not exists "FoodItem" (
    id int primary key,
    itemName text,
    price real,
    description text,
    category_id int,
    inStock boolean
);

create table if not exists "FoodCategory" (
    id int primary key,
    categoryName text
);

create table if not exists "ComboMeals" (
    id int primary key,
    comboId int,
    name text,
    foodId int
);