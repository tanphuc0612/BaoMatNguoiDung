connect QT/1;

SELECT * FROM QT.THANHVIEN;

INSERT INTO QT.THANHVIEN(MaTV, Ten, Phai, QueQuan, NgaySinh, QuocTich,dcthuongtru,DCTamTru,NoiCongTac, TrangThai, LyDo, MaDV, Username,DuocBau) 
VALUES (null, 'Nguyen Thi Thuy', 'Nu','Binh Duong', TO_DATE('2/1/1999','DD/MM/YYYY'),'VietNam','Binh Duong','Binh Duong','BinhPhuoc', 1,null,1,'test',1);

SELECT QT.F_DECRYPT_thanhvien_username(MATV) FROM QT.THANHVIEN;