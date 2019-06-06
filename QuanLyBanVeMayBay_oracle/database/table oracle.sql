-- auto-generated definition
create table VE
(
    MAVE      VARCHAR2(10) not null
        constraint VE_PK
            primary key,
    MACB      VARCHAR2(10)
        constraint VE_CHUYENBAY_MACB_FK
            references CHUYENBAY
                on delete set null,
    LOAIVE    NUMBER,
    TINHTRANG NUMBER
)

-- auto-generated definition
create table SANBAY
(
    MASB  VARCHAR2(50) not null
        constraint SANBAY_PK
            primary key,
    TENSB NVARCHAR2(50)
)


-- auto-generated definition
create table HANGMAYBAY
(
    MAHMB  VARCHAR2(10) not null
        constraint HANGMAYBAY_PK
            primary key,
    TENHMB NVARCHAR2(50)
)


-- auto-generated definition
create table DANGNHAP
(
    EMAIL   NVARCHAR2(50) not null
        constraint DANGNHAP_PK
            primary key,
    PASS    NVARCHAR2(50),
    "level" NUMBER
)


-- auto-generated definition
create table CHUYENBAY
(
    MACB             VARCHAR2(10) not null
        constraint CHUYENBAY_PK
            primary key,
    MAHMB            VARCHAR2(10)
        constraint CHUYENBAY_HANGMAYBAY_MAHMB_FK
            references HANGMAYBAY
                on delete set null,
    DIEMKHOIHANH     NVARCHAR2(50),
    DIEMDEN          NVARCHAR2(50),
    SANBAYDI         VARCHAR2(10)
        constraint CHUYENBAY_SANBAY_MASB_FK
            references SANBAY
                on delete set null,
    SANBAYDEN        VARCHAR2(10)
        constraint CHUYENBAY_SANBAY_MASB_FK_2
            references SANBAY
                on delete set null,
    SOGHEVIPTRONG    NUMBER,
    SOGHETHUONGTRONG NUMBER,
    NGAYKHOIHANH     DATE,
    THOIGIANBAY      NUMBER,
    GIOKHOIHANH      TIMESTAMP(6),
    GIAVE            NUMBER
)


-- auto-generated definition
create table CHITIETCHUYENBAY
(
    MAVE   VARCHAR2(10) not null
        constraint CHITIETCHUYENBAY_PK
            primary key
        constraint CT_VE_FK
            references VE
                on delete set null,
    MACB   VARCHAR2(10)
        constraint CT_VE_FK_CHUYENBAY_MACB_FK
            references CHUYENBAY
                on delete set null,
    TENHK  NVARCHAR2(50),
    CMND   VARCHAR2(12),
    SDT    VARCHAR2(11),
    EMAIL  NVARCHAR2(50),
    LOAIHK NUMBER,
    GIA    NUMBER,
    DIACHI NVARCHAR2(100)
)


