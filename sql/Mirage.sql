set names utf8;
set foreign_key_checks=0;

drop database if exists Mirage;
create database if not exists Mirage;

use Mirage;


create table user_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) unique not null comment "ユーザーID",
password varchar(16) not null comment "パスワード",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
sex tinyint not null default 0 comment "性別:男=0,女=1",
email varchar(32) not null comment "メールアドレス",
status tinyint not null comment "管理者フラグ:一般ユーザー=0,管理者=1",
logined tinyint not null comment "ログインフラグ:未ログイン=0,ログイン済=1",
regist_date datetime not null comment "登録日",
update_date datetime not null comment "更新日"
);

insert into user_info values
(1,"guest","guest","ゲスト","ユーザー","げすと","ゆーざー",0,"guest@gmail.com",0,0,now(),now()),
(2,"admin","admin","管理者","ユーザー","かんりしゃ","ゆーざー",0,"admin@gmail.com",1,0,now(),now());


create table product_info(
id int primary key not null auto_increment comment "ID",
product_id int unique not null comment "商品ID",
product_name varchar(100) unique not null comment "商品名",

product_description varchar(255) not null comment "商品詳細",
product_for_sex tinyint not null default 0 comment "男性用=0,女性用=1",
category_id int not null comment "カテゴリID",
price int comment "価格",
image_file_path varchar(100) comment "画像ファイルパス",
image_file_name varchar(50) comment "画像ファイル名",
release_date datetime not null comment "発売年月",

status tinyint not null default 0 comment "ステータス:無効=0,有効=1",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(category_id) references m_category(category_id)
);

insert into product_info values
(1,1,"FRAGRANCE","detail",0,2,200,"./images","sample.jpg",now(),0,now(),now()),


(2,2,"FRAGRANCE2","detail",0,2,300,"./images","sample.jpg",now(),0,now(),now()),
(3,3,"FRAGRANCE3","detail",1,2,400,"./images","sample.jpg",now(),0,now(),now()),
(4,4,"FRAGRANCE4","detail",1,2,500,"./images","sample.jpg",now(),0,now(),now()),

(5,5,"JEWELRY","detail",0,3,200,"./images","sample.jpg",now(),0,now(),now()),
(6,6,"JEWELRY2","detail",0,3,300,"./images","sample.jpg",now(),0,now(),now()),
(7,7,"JEWELRY3","detail",1,3,400,"./images","sample.jpg",now(),0,now(),now()),
(8,8,"JEWELRY4","detail",1,3,250,"./images","sample.jpg",now(),0,now(),now()),

(9,9,"ACCESORIES","detail",0,4,200,"./images","sample.jpg",now(),0,now(),now()),
(10,10,"ACCESORIES2","detail",0,4,300,"./images","sample.jpg",now(),0,now(),now()),
(11,11,"ACCESORIES3","detail",1,4,400,"./images","sample.jpg",now(),0,now(),now()),
(12,12,"ACCESORIES4","detail",1,4,500,"./images","sample.jpg",now(),0,now(),now());




create table cart_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
temp_user_id varchar(16) comment "仮ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
);


create table purchase_history_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
destination_id int not null comment "宛先情報ID",
regist_date datetime not null comment "登録日",
update_date datetime not null comment "更新日",
delete_flg tinyint not null comment "購入履歴表示フラグ:表示=0,非表示=1",
foreign key(user_id) references user_info(user_id),
foreign key(product_id) references product_info(product_id)
);


create table destination_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
email varchar(32) not null comment "メールアドレス",
tel_number varchar(13) not null comment "電話番号",
user_address varchar(50) not null comment "住所",
regist_date datetime not null comment "登録日",
update_date datetime not null comment "更新日"
);

insert into destination_info values
(1,"guest","ゲスト","ユーザー","げすと","ゆーざー","guest@gmail.com","080-1234-5678","東京都千代田区三番町１－１ KY三番町ビル１F",now(),"0000-00-00 00:00:00");


create table m_category(
id int primary key not null auto_increment comment "ID",
category_id int not null unique comment "カテゴリID",
category_name varchar(20) not null unique comment "カテゴリ名",
category_description varchar(100) comment "カテゴリ詳細",
insert_date datetime not null comment "登録日",
update_date datetime comment "更新日"
);

insert into m_category values
(1,1,"全てのカテゴリ","FRAGRANCE,JEWELRY,パズル・クイズ全てのカテゴリが対象となります",now(),null),
(2,2,"FRAGRANCE","FRAGRANCEに関するカテゴリが対象となります",now(),null),
(3,3,"JEWELRY","JEWELRYACCESORIESに関するカテゴリが対象となります",now(),null),
(4,4,"ACCESORIES","ACCESORIESに関するカテゴリが対象となります",now(),null);
