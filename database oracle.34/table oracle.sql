create table SANBAY
(
	MASB VARCHAR2(50) not null
		constraint SANBAY_PK
			primary key,
	TENSB NVARCHAR2(50)
)
/

create table HANGMAYBAY
(
	MAHMB VARCHAR2(10) not null
		constraint HANGMAYBAY_PK
			primary key,
	TENHMB NVARCHAR2(50)
)
/

create table CHUYENBAY
(
	MACB VARCHAR2(10) not null
		constraint CHUYENBAY_PK
			primary key,
	MAHMB VARCHAR2(10)
		constraint CHUYENBAY_HANGMAYBAY_MAHMB_FK
			references HANGMAYBAY
				on delete set null,
	DIEMKHOIHANH NVARCHAR2(50),
	DIEMDEN NVARCHAR2(50),
	SANBAYDI VARCHAR2(10)
		constraint CHUYENBAY_SANBAY_MASB_FK
			references SANBAY
				on delete set null,
	SANBAYDEN VARCHAR2(10)
		constraint CHUYENBAY_SANBAY_MASB_FK_2
			references SANBAY
				on delete set null,
	SOGHEVIPTRONG NUMBER,
	SOGHETHUONGTRONG NUMBER,
	NGAYKHOIHANH TIMESTAMP(6),
	THOIGIANBAY NUMBER,
	GIAVE NUMBER
)
/

create table VE
(
	MAVE VARCHAR2(10) not null
		constraint VE_PK
			primary key,
	MACB VARCHAR2(10)
		constraint VE_CHUYENBAY_MACB_FK
			references CHUYENBAY
				on delete set null,
	LOAIVE NUMBER,
	TINHTRANG NUMBER,
	TILE NUMBER
)
/

create table DANGNHAP
(
	EMAIL NVARCHAR2(50) not null
		constraint DANGNHAP_PK
			primary key,
	PASS NVARCHAR2(50),
	"level" NUMBER
)
/

create table KHUYENMAI
(
	MAKM NVARCHAR2(10) default null not null
		constraint KHUYENMAI_PK
			primary key,
	TENKM NVARCHAR2(20),
	MOTA NVARCHAR2(50),
	GIAMGIA BINARY_DOUBLE,
	SOLUONGDISCOUNT NUMBER,
	HANSD DATE,
	TINHTRANG NUMBER,
	NGAYPHATHANH DATE
)
/

comment on column KHUYENMAI.MAKM is 'GENERATED ALWAYS AS IDENTITY'
/

create table KHACHHANG
(
	CMND VARCHAR2(9) not null
		constraint KHACHHANG_PK
			primary key,
	TENKH NVARCHAR2(20),
	EMAIL NVARCHAR2(20),
	TUOI NUMBER,
	DIACHI NVARCHAR2(50),
	LOAIHK NUMBER
)
/

create table CHITIETVE
(
	MAVE VARCHAR2(10) not null
		constraint CHITIETCHUYENBAY_PK
			primary key
		constraint CT_VE_FK
			references VE
				on delete set null,
	MACB VARCHAR2(10)
		constraint CT_VE_FK_CHUYENBAY_MACB_FK
			references CHUYENBAY
				on delete set null,
	CMND VARCHAR2(9)
		constraint CHITIETVE_KHACHHANG_CMND_FK
			references KHACHHANG
				on delete set null,
	GIA NUMBER,
	KHUYENMAI NVARCHAR2(10)
		constraint CHITIETVE_KHUYENMAI_MAKM_FK
			references KHUYENMAI
				on delete set null
)
/

