 --주문 테이블
     create table orderInfo
  (  num NUMBER(6) PRIMARY KEY,
     userid VARCHAR2(10),
     gCode varchar2(20) not null,
     gName varchar2(50) not null,
     gPrice NUMBER(6) not null,
     gSize CHAR(1) not null,
     gColor VARCHAR2(10) not null,
     gAmount NUMBER(2) not null,
     gImage varchar2(20) not null,
     orderName VARCHAR2(10) NOT NULL,
     post VARCHAR2(5) NOT NULL,
     addr1 VARCHAR2(500) NOT NULL,
     addr2 VARCHAR2(500) NOT NULL,
     phone VARCHAR2(11) NOT NULL,
     payMethod VARCHAR2(50) NOT NULL,
     orderDay DATE  DEFAULT SYSDATE
  );   
  alter table orderInfo
  add CONSTRAINT orderInfo_userid_fk FOREIGN KEY(userid)
   REFERENCES member(userid) ON DELETE CASCADE;
   
   
  alter table orderInfo
  add CONSTRAINT orderInfo_gCode_fk FOREIGN KEY(gCode)
   REFERENCES goods(gCode) ON DELETE CASCADE; 
   
   create sequence orderInfo_seq;   