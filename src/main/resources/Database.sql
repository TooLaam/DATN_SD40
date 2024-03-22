CREATE DATABASE DATN_SD40


/*NHÂN VIÊN*/
-- Bảng chức vụ
CREATE TABLE chuc_vu (
                         id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                         ten			NVARCHAR(100) DEFAULT NULL,
                         ngay_tao	DATETIME NULL,
                         ngay_sua	DATETIME NULL,
                         trang_thai	INT
);

-- Bảng Nhân viên
CREATE TABLE nhan_vien (
                           id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                           ma          NVARCHAR(100) NOT NULL,
                           ho_ten		NVARCHAR(MAX) DEFAULT NULL,
                           ngay_sinh	DATETIME NULL,
                           ngay_tao	DATETIME NULL,
                           sdt			NVARCHAR(11),
                           gioi_tinh   INT,
                           dia_chi		NVARCHAR(MAX),
                           hinh_anh	NVARCHAR(MAX),
                           email		NVARCHAR(MAX),
                           tai_khoan	NVARCHAR(MAX),
                           mat_khau	NVARCHAR(MAX),
                           trang_thai	INT,
                           chuc_vu_id	BIGINT
                               REFERENCES chuc_vu(id),
);

/*KHÁCH HÀNG*/
--Hạng thành viên
CREATE TABLE hang_thanh_vien (
                                 id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                 ten			NVARCHAR(100) DEFAULT NULL,
                                 ngay_tao	DATETIME NULL,
                                 ngay_sua	DATETIME NULL,
                                 trang_thai	INT,
                                 dieu_kien   INT
);

--Bảng Tỉnh/thành phố

CREATE TABLE tinh_thanh_pho (
                                id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                ten			NVARCHAR(100) DEFAULT NULL,
                                ngay_tao	DATETIME NULL,
                                ngay_sua	DATETIME NULL,
                                trang_thai	INT
);

-- Bảng địa chỉ

CREATE TABLE dia_chi (
                         id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                         mo_ta			NVARCHAR(100) DEFAULT NULL,
                         ngay_tao	DATETIME NULL,
                         ngay_sua	DATETIME NULL,
                         trang_thai	INT,
                         tinh_thanh_pho_id	BIGINT
                             REFERENCES tinh_thanh_pho(id),
);

-- Bảng Khách hàng
CREATE TABLE khach_hang (
                            id			  BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                            ma            NVARCHAR(100) NOT NULL,
                            ho_ten		  NVARCHAR(MAX) DEFAULT NULL,
                            ngay_sinh  	  DATETIME NULL,
                            ngay_tao	  DATETIME NULL,
                            sdt			  NVARCHAR(11),
                            gioi_tinh     INT,
                            dia_chi		  NVARCHAR(MAX),
                            email		  NVARCHAR(MAX),
                            tai_khoan	  NVARCHAR(MAX),
                            mat_khau	  NVARCHAR(MAX),
                            trang_thai	  INT,
                            diem_tich_luy INT,
                            hang_thanh_vien_id	BIGINT
                                REFERENCES hang_thanh_vien(id),
);

--Bảng Địa chỉ chi tiết

CREATE TABLE dia_chi_chi_tiet (
                                  id	    BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,

                                  khach_hang_id	BIGINT
                                      REFERENCES khach_hang(id),
                                  dia_chi_id	BIGINT
                                      REFERENCES dia_chi(id),
                                  mac_dinh	INT



);

/*voucher*/
--Bảng voucher
CREATE TABLE voucher(
                        id					BIGINT IDENTITY(1,1) PRIMARY KEY,
                        ma_voucher			VARCHAR(50) NULL,
                        ten_voucher			NVARCHAR(50) NULL,
                        ngay_bat_dau		DATETIME NULL,
                        ngay_ket_thuc		DATETIME NULL,
                        ngay_tao			DATETIME NULL,
                        ngay_sua			DATETIME NULL,
                        so_luong	        INT NULL,
                        phan_tram_giam		int NULL,
                        giam_toi_da			MONEY NULL,
                        gia_tri_don_toi_thieu	MONEY NULL,
                        trang_thai			INT,
);


/*SẢN PHẨM*/

--Bảng thương hiệu

CREATE TABLE thuong_hieu (
                             id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                             ten			NVARCHAR(100) DEFAULT NULL,
                             hinh_anh    NVARCHAR(MAX),
                             ngay_tao	DATETIME NULL,
                             ngay_sua	DATETIME NULL,
                             trang_thai	INT
);

--Bảng Màu sắc

CREATE TABLE mau_sac (
                         id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                         ten			NVARCHAR(100) DEFAULT NULL,
                         hinh_anh    NVARCHAR(MAX),
                         ngay_tao	DATETIME NULL,
                         ngay_sua	DATETIME NULL,
                         trang_thai	INT
);

--Bảng Thể loại

CREATE TABLE the_loai (
                          id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                          ten			NVARCHAR(100) DEFAULT NULL,
                          ngay_tao	DATETIME NULL,
                          ngay_sua	DATETIME NULL,
                          trang_thai	INT
);


--Bảng Kích cỡ

CREATE TABLE kich_co (
                         id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                         ten			NVARCHAR(100) DEFAULT NULL,
                         ngay_tao	DATETIME NULL,
                         ngay_sua	DATETIME NULL,
                         trang_thai	INT
);

--Bảng giảm giá
CREATE TABLE giam_gia (
                          id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                          ten		NVARCHAR(MAX) NULL,
                          ngay_tao	DATETIME NULL,
                          ngay_sua	DATETIME NULL,
                          trang_thai	INT,
                          muc_giam    INT
);


-- Bảng Sản phẩm
CREATE TABLE san_pham (
                          id				BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                          ma			    AS 'PROD' + RIGHT('00000' + CAST(id AS VARCHAR(5)), 5) PERSISTED,
                          ten				NVARCHAR(255) NULL,
                          mo_ta			NVARCHAR(MAX) NULL,
                          ngay_tao		DATETIME NULL,
                          ngay_sua		DATETIME NULL,
                          hinh_anh_dai_dien VARCHAR(Max),
	nguoi_tao		NVARCHAR(100) NULL,
	trang_thai		INT,
	thuong_hieu_id	BIGINT
		REFERENCES thuong_hieu(id),
    the_loai_id  	BIGINT
		REFERENCES the_loai(id),
	 giam_gia_id  	BIGINT
		REFERENCES giam_gia(id),
);
alter table san_pham add so_luong_da_ban int


--Bảng chi tiết sản phẩm màu sắc

CREATE TABLE chi_tiet_san_pham_mau_sac (
                                           id			    BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                           trang_thai	    INT DEFAULT 0,
                                           ngay_tao		DATETIME NULL,
                                           ngay_sua		DATETIME NULL,
                                           hinh_anh        VARCHAR(MAX),
	gia_hien_hanh   MONEY NULL,
	san_pham_id     BIGINT
		REFERENCES san_pham(id),
	mau_sac_id	    BIGINT
		REFERENCES mau_sac(id)

);


--Tạo bảng Chi tiết sản phẩm
CREATE TABLE chi_tiet_san_pham (
                                   id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                   so_luong	INT NULL,
                                   trang_thai	INT DEFAULT 0,
                                   ngay_tao	DATETIME NULL,
                                   ngay_sua	DATETIME NULL,

                                   chi_tiet_san_pham_mau_sac_id BIGINT
                                       REFERENCES chi_tiet_san_pham_mau_sac(id),

                                   kich_co_id	BIGINT
                                       REFERENCES kich_co(id)

);

/*GIỎ HÀNG*/

--Bảng giỏ hàng

CREATE TABLE gio_hang (
                          id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                          ghi_chu 	NVARCHAR(MAX) NULL,
                          trang_thai	INT DEFAULT 0,
                          ngay_tao	DATETIME NULL,
                          ngay_sua	DATETIME NULL,

                          khach_hang_id BIGINT
                              REFERENCES khach_hang(id),

);

--Bảng chi tiết giỏ hàng
CREATE TABLE gio_hang_chi_tiet (
                                   id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,

                                   TrangThai	INT,
                                   so_luong    INT,
                                   gio_hang_id	BIGINT
                                       REFERENCES gio_hang(id),
                                   chi_tiet_san_pham_id	BIGINT
                                       REFERENCES chi_tiet_san_pham(id),


);

/*HÓA ĐƠN*/
CREATE TABLE phuong_thuc_thanh_toan (
                                        id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                        ten		NVARCHAR(MAX) NULL,
                                        ngay_tao	DATETIME NULL,
                                        ngay_sua	DATETIME NULL,
                                        trang_thai	INT,


);

--Bảng hóa đơn
CREATE TABLE hoa_don (
                         id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                         ma 	        AS 'PROD' + RIGHT('00000' + CAST(id AS VARCHAR(5)), 5) PERSISTED,
                         trang_thai	INT DEFAULT 0,
                         ngay_tao	DATETIME NULL,
                         ngay_thanh_toan	DATETIME NULL,
                         tong_tien   MONEY,
                         phan_tram_khuyen_mai INT,

                         nhan_vien_id BIGINT
                             REFERENCES nhan_vien(id),

                         khach_hang_id BIGINT
                             REFERENCES khach_hang(id),

                         voucher_id BIGINT
                             REFERENCES voucher(id),

);
alter table hoa_don add ghi_chu NVARCHAR(MAX)
alter table hoa_don add tong_tien_giam NVARCHAR(MAX)
alter table hoa_don add phuong_thuc_thanh_toan_id BIGINT REFERENCES phuong_thuc_thanh_toan(id)

--Bảng hóa đơn chi tiết
CREATE TABLE hoa_don_chi_tiet (
                                  id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,

                                  TrangThai	INT,
                                  so_luong    INT,
                                  gia_hien_hanh MONEY,
                                  gia_da_giam MONEY,
                                  hoa_don_id	BIGINT
                                      REFERENCES hoa_don(id),
                                  chi_tiet_san_pham_id	BIGINT
                                      REFERENCES chi_tiet_san_pham(id),

);

--Bảng hóa phương thức thanh toán



/*Giao hàng*/

-- Bảng trạng thái giao hàng
CREATE TABLE trang_thai_giao_hang (
                                      id			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                                      ten			NVARCHAR(100) DEFAULT NULL,
                                      ngay_tao	DATETIME NULL,
                                      ngay_sua	DATETIME NULL,
                                      trang_thai	INT
);

--Bảng Giao hàng

CREATE TABLE giao_hang (
                           id						BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
                           ma_giao_hang			NVARCHAR(100) DEFAULT NULL,
                           ngay_tao				DATETIME NULL,
                           chi_phi					MONEY NULL,
                           dia_chi    				NVARCHAR(MAX),
                           sdt                     NVARCHAR(11),

                           hoa_don_id BIGINT
                               REFERENCES hoa_don(id),

                           dia_chi_chi_tiet_id BIGINT
                               REFERENCES dia_chi_chi_tiet(id),
                           trang_thai_giao_hang_id	BIGINT
                               REFERENCES trang_thai_giao_hang(id),
);


--------------------------------------------------------------------
/*INSERT DỮ LIỆU*/

--Chúc vụ
INSERT INTO chuc_vu (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
('Quản lý', GETDATE(), GETDATE(), 0),
('Nhân viên', GETDATE(), GETDATE(), 0)

--Nhân viên
    INSERT INTO nhan_vien (ma, ho_ten, ngay_sinh, ngay_tao, sdt, gioi_tinh, dia_chi, hinh_anh, email, tai_khoan, mat_khau, trang_thai, chuc_vu_id)
VALUES
    ('NV001', 'Nguyễn Văn Nam', '1990-01-15', GETDATE(), '0987654321', 1, 'Số 123 Đường ABC, Quận 1', NULL, 'namnv@example.com', 'namnv', 'password', 1, 1),
    ('NV002', 'Trần Thị Hương', '1995-05-25', GETDATE(), '0901234567', 0, 'Số 456 Đường XYZ, Quận 2', NULL, 'huongtt@example.com', 'huongtt', 'password', 1, 2),
    ('NV003', 'Lê Văn Minh', '1988-10-08', GETDATE(), '0978123456', 1, 'Số 789 Đường DEF, Quận 3', NULL, 'minhlv@example.com', 'minhlv', 'password', 1, 2),
    ('NV004', 'Phạm Thị Thu', '1998-03-20', GETDATE(), '0912345678', 0, 'Số 101112 Đường GHI, Quận 4', NULL, 'thupt@example.com', 'thupt', 'password', 1, 2),
    ('NV005', 'Hoàng Văn Hùng', '1993-07-10', GETDATE(), '0965432109', 1, 'Số 131415 Đường JKL, Quận 5', NULL, 'hunghv@example.com', 'hunghv', 'password', 1, 1);


--Hạng thành viên
INSERT INTO hang_thanh_vien (ten, ngay_tao, ngay_sua, trang_thai, dieu_kien)
VALUES
('Bạc', GETDATE(), GETDATE(), 1, 100),
('Vàng', GETDATE(), GETDATE(), 1, 200),
('Bạch kim', GETDATE(), GETDATE(), 1, 300),
('Kim cương', GETDATE(), GETDATE(), 1, 400);

--Tỉnh/thành phố

INSERT INTO tinh_thanh_pho (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
('Hà Nội', GETDATE(), GETDATE(), 1),
('Hồ Chí Minh', GETDATE(), GETDATE(), 1),
('Đà Nẵng', GETDATE(), GETDATE(), 1),
('Hải Phòng', GETDATE(), GETDATE(), 1),
('Cần Thơ', GETDATE(), GETDATE(), 1);

--Địa chỉ
INSERT INTO dia_chi (mo_ta, ngay_tao, ngay_sua, trang_thai, tinh_thanh_pho_id)
VALUES
('Số 123 Đường ABC, Quận 1', GETDATE(), GETDATE(), 1, 1),
('Số 456 Đường XYZ, Quận 2', GETDATE(), GETDATE(), 1, 2),
('Số 789 Đường DEF, Quận 3', GETDATE(), GETDATE(), 1, 3),
('Số 101112 Đường GHI, Quận 4', GETDATE(), GETDATE(), 1, 4),
('Số 131415 Đường JKL, Quận 5', GETDATE(), GETDATE(), 1, 5);


--Khách hàng
INSERT INTO khach_hang (ma, ho_ten, ngay_sinh, ngay_tao, sdt, gioi_tinh, dia_chi, email, tai_khoan, mat_khau, trang_thai, diem_tich_luy, hang_thanh_vien_id)
VALUES
('KH001', 'Nguyễn Văn A', '1990-01-01', GETDATE(), '0987654321', 1, 'Số 123 Đường ABC, Quận 1', 'nguyenvana@example.com', 'nguyenvana', 'password', 1, 0, 1),
('KH002', 'Trần Thị B', '1995-05-05', GETDATE(), '0901234567', 0, 'Số 456 Đường XYZ, Quận 2', 'tranthib@example.com', 'tranthib', 'password', 1, 0, 2),
('KH003', 'Lê Văn C', '1988-10-10', GETDATE(), '0978123456', 1, 'Số 789 Đường DEF, Quận 3', 'levanc@example.com', 'levanc', 'password', 1, 0, 3),
('KH004', 'Phạm Thị D', '1998-03-15', GETDATE(), '0912345678', 0, 'Số 101112 Đường GHI, Quận 4', 'phamthid@example.com', 'phamthid', 'password', 1, 0, 4),
('KH005', 'Hoàng Văn E', '1993-07-20', GETDATE(), '0965432109', 1, 'Số 131415 Đường JKL, Quận 5', 'hoangvane@example.com', 'hoangvane', 'password', 1, 0, 1);

select * from khach_hang
-- địa chỉ chi tiết
    INSERT INTO dia_chi_chi_tiet (khach_hang_id, dia_chi_id, mac_dinh)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 1),
    (5, 5, 1);

--Đợt khuyến mãi


--Thương hiệu

INSERT INTO thuong_hieu (ten, hinh_anh, ngay_tao, ngay_sua, trang_thai)
VALUES
('Nike', 'nike_logo.jpg', GETDATE(), GETDATE(), 1),
('Adidas', 'adidas_logo.jpg', GETDATE(), GETDATE(), 1),
('Puma', 'puma_logo.jpg', GETDATE(), GETDATE(), 1),
('New Balance', 'newbalance_logo.jpg', GETDATE(), GETDATE(), 1),
('Under Armour', 'underarmour_logo.jpg', GETDATE(), GETDATE(), 1);


--Màu sắc

INSERT INTO mau_sac (ten, hinh_anh, ngay_tao, ngay_sua, trang_thai)
VALUES
('Đen', 'black_color.jpg', GETDATE(), GETDATE(), 1),
('Trắng', 'white_color.jpg', GETDATE(), GETDATE(), 1),
('Xanh', 'blue_color.jpg', GETDATE(), GETDATE(), 1),
('Đỏ', 'red_color.jpg', GETDATE(), GETDATE(), 1),
('Vàng', 'yellow_color.jpg', GETDATE(), GETDATE(), 1);


--thể loại

INSERT INTO the_loai (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
('Giày chạy bộ', GETDATE(), GETDATE(), 1),
('Giày bóng rổ', GETDATE(), GETDATE(), 1),
('Giày đá banh', GETDATE(), GETDATE(), 1),
('Giày tennis', GETDATE(), GETDATE(), 1),
('Giày leo núi', GETDATE(), GETDATE(), 1);


--Kích cỡ

INSERT INTO kich_co (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
('US 7', GETDATE(), GETDATE(), 1),
('US 8', GETDATE(), GETDATE(), 1),
('US 9', GETDATE(), GETDATE(), 1),
('US 10', GETDATE(), GETDATE(), 1),
('US 11', GETDATE(), GETDATE(), 1);

--Giảm giá

INSERT INTO giam_gia (ten, ngay_tao, ngay_sua, trang_thai, muc_giam)
VALUES
('Không', GETDATE(), GETDATE(), 1, 0),
('Giảm giá 10%', GETDATE(), GETDATE(), 1, 10),
('Giảm giá 20%', GETDATE(), GETDATE(), 1, 20),
('Giảm giá 30%', GETDATE(), GETDATE(), 1, 30),
('Giảm giá 50%', GETDATE(), GETDATE(), 1, 50),
('Giảm giá 70%', GETDATE(), GETDATE(), 1, 70);



--sản phẩm

INSERT INTO san_pham (ten, mo_ta, ngay_tao, ngay_sua, hinh_anh_dai_dien, nguoi_tao, trang_thai, thuong_hieu_id, the_loai_id, giam_gia_id)
VALUES
('Giày chạy Nike Air Zoom Pegasus', 'Giày chạy bộ cao cấp với công nghệ Zoom', GETDATE(), GETDATE(), 'nike_zoom_pegasus.jpg', 'Admin', 1, 1, 1, 1),
('Giày bóng rổ Adidas Pro Bounce 2019', 'Giày bóng rổ chuyên nghiệp với đế Boost', GETDATE(), GETDATE(), 'adidas_pro_bounce.jpg', 'Admin', 1, 2, 2,2),
('Giày đá banh Nike Mercurial Superfly', 'Giày đá banh siêu nhẹ và linh hoạt', GETDATE(), GETDATE(), 'nike_mercurial_superfly.jpg', 'Admin', 1, 3, 3,3),
('Giày tennis Adidas Adizero Ubersonic 3', 'Giày tennis chuyên nghiệp với đệm Boost', GETDATE(), GETDATE(), 'adidas_adizero_ubersonic.jpg', 'Admin', 1, 4, 4,4),
('Giày leo núi The North Face Ultra Fastpack IV Futurelight', 'Giày leo núi chống nước và thoáng khí', GETDATE(), GETDATE(), 'north_face_ultra_fastpack.jpg', 'Admin', 1, 5, 5,5);


--Chi tiết sản phẩm màu sắc

-- Đây là ví dụ, bạn cần thay đổi thông tin dựa trên sản phẩm cụ thể và các màu sắc có sẵn
INSERT INTO chi_tiet_san_pham_mau_sac (trang_thai, ngay_tao, ngay_sua, hinh_anh, gia_hien_hanh, san_pham_id, mau_sac_id)
VALUES
(1, GETDATE(), GETDATE(), 'black_shoe.jpg', 2000000, 1, 1),
(1, GETDATE(), GETDATE(), 'white_shoe.jpg', 2200000, 1, 2),
(1, GETDATE(), GETDATE(), 'blue_shoe.jpg', 2100000, 1, 3),
(1, GETDATE(), GETDATE(), 'red_shoe.jpg', 2300000, 1, 4),
(1, GETDATE(), GETDATE(), 'yellow_shoe.jpg', 2400000, 1, 5);


--chi tiết sản phẩm

INSERT INTO chi_tiet_san_pham (so_luong, trang_thai, ngay_tao, ngay_sua, chi_tiet_san_pham_mau_sac_id, kich_co_id)
VALUES
(100, 1, GETDATE(), GETDATE(), 1, 1),
(100, 1, GETDATE(), GETDATE(), 2, 2),
(100, 1, GETDATE(), GETDATE(), 3, 3),
(100, 1, GETDATE(), GETDATE(), 4, 4),
(100, 1, GETDATE(), GETDATE(), 5, 5);

--Voucher
INSERT INTO voucher (ma_voucher, ten_voucher, ngay_bat_dau, ngay_ket_thuc, ngay_tao, ngay_sua, so_luong, phan_tram_giam, giam_toi_da, gia_tri_don_toi_thieu, trang_thai)
VALUES
('VOUCHER001', N'Voucher Giảm 10%', '2024-03-20', '2024-04-20', '2024-03-20', '2024-03-20', 100, 10, 100000, 500000, 1),
('VOUCHER002', N'Voucher Giảm 20%', '2024-03-21', '2024-04-21', '2024-03-21', '2024-03-21', 150, 20, 150000, 750000, 1),
('VOUCHER003', N'Voucher Giảm 30%', '2024-03-22', '2024-04-22', '2024-03-22', '2024-03-22', 200, 30, 200000, 1000000, 1),
('VOUCHER004', N'Voucher Giảm 40%', '2024-03-23', '2024-04-23', '2024-03-23', '2024-03-23', 250, 40, 250000, 1250000, 1),
('VOUCHER005', N'Voucher Giảm 50%', '2024-03-24', '2024-04-24', '2024-03-24', '2024-03-24', 300, 50, 300000, 1500000, 1);


--giỏ hàng
INSERT INTO gio_hang (ghi_chu, trang_thai, ngay_tao, ngay_sua, khach_hang_id)
VALUES
('Giỏ hàng của Nguyễn Văn A', 1, GETDATE(), GETDATE(), 1),
('Giỏ hàng của Trần Thị B', 1, GETDATE(), GETDATE(), 2),
('Giỏ hàng của Lê Văn C', 1, GETDATE(), GETDATE(), 3),
('Giỏ hàng của Phạm Thị D', 1, GETDATE(), GETDATE(), 4),
('Giỏ hàng của Hoàng Văn E', 1, GETDATE(), GETDATE(), 5);


--giỏ hàng chi tiết
-- Đây là ví dụ, bạn cần thay đổi thông tin dựa trên thông tin giỏ hàng và sản phẩm cụ thể
INSERT INTO gio_hang_chi_tiet (gio_hang_id, chi_tiet_san_pham_id, TrangThai, so_luong)
VALUES
(6, 1, 1, 2),
(2, 2, 1, 1),
(3, 3, 1, 3),
(4, 4, 1, 1),
(5, 5, 1, 2);
select * from gio_hang

--hóa đơn

    INSERT INTO hoa_don (ngay_tao, ngay_thanh_toan, tong_tien, phan_tram_khuyen_mai, nhan_vien_id, khach_hang_id, voucher_id)
VALUES
    (GETDATE(), GETDATE(), 1500000, 10, 1, 1, 1),
    (GETDATE(), GETDATE(), 2000000, 20, 2, 2, 2),
    (GETDATE(), GETDATE(), 2500000, 30, 3, 3, 3),
    (GETDATE(), GETDATE(), 3000000, 15, 4, 4, 1),
    (GETDATE(), GETDATE(), 3500000, 25, 5, 5, 2);

select * from hoa_don
--hóa đơn chi tiết

    INSERT INTO hoa_don_chi_tiet (hoa_don_id, chi_tiet_san_pham_id, TrangThai, so_luong, gia_hien_hanh, gia_da_giam)
VALUES
    (1, 1, 1, 2, 200000, 180000),
    (1, 2, 1, 1, 300000, 240000),
    (1, 3, 1, 3, 400000, 280000),
    (2, 4, 1, 1, 500000, 375000),
    (3, 5, 1, 2, 600000, 450000);



