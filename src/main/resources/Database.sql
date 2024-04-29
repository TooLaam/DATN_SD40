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
                            trang_thai	  INT
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
                          so_luong_da_ban int,
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
                         ma 	        VARCHAR(max),
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
	ghi_chu NVARCHAR(MAX),
	ten_nguoi_nhan NVARCHAR(MAX),
	sdt_nguoi_nhan NVARCHAR(MAX),
	dia_chi_nguoi_nhan NVARCHAR(MAX),
	tong_tien_giam NVARCHAR(MAX),
	phuong_thuc_thanh_toan_id BIGINT REFERENCES phuong_thuc_thanh_toan(id),
	tong_tien_san_pham_chua_giam NVARCHAR(MAX),
	phi_ship Money

);
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



--------------------------------------------------------------------
/*INSERT DỮ LIỆU*/

--Phương thức thanh toán
INSERT INTO phuong_thuc_thanh_toan(ten, ngay_tao,ngay_sua,trang_thai)
VALUES
(N'Thanh toán khi nhận hàng', GETDATE(), GETDATE(), 0),
(N'VNPAY', GETDATE(), GETDATE(), 0)

--Chúc vụ
    INSERT INTO chuc_vu (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
    (N'Quản lý', GETDATE(), GETDATE(), 0),
    (N'Nhân viên', GETDATE(), GETDATE(), 0)
delete from nhan_vien
--Nhân viên
    INSERT INTO nhan_vien (ma, ho_ten, ngay_sinh, ngay_tao, sdt, gioi_tinh, dia_chi, hinh_anh, email, tai_khoan, mat_khau, trang_thai, chuc_vu_id)
VALUES
    ('NV001', N'Nguyễn Văn Nam', '1990-01-15', GETDATE(), '0987654321', 1, N'Số 123 Đường ABC, Quận 1', NULL, 'namnv@example.com', 'namnv', 'password', 0, 1),
    ('NV002', N'Trần Thị Hương', '1995-05-25', GETDATE(), '0901234567', 0, N'Số 456 Đường XYZ, Quận 2', NULL, 'huongtt@example.com', 'huongtt', 'password', 0, 2),
    ('NV003', N'Lê Văn Minh', '1988-10-08', GETDATE(), '0978123456', 1, N'Số 789 Đường DEF, Quận 3', NULL, 'minhlv@example.com', 'minhlv', 'password', 0, 2),
    ('NV004', N'Phạm Thị Thu', '1998-03-20', GETDATE(), '0912345678', 0, N'Số 101112 Đường GHI, Quận 4', NULL, 'thupt@example.com', 'thupt', 'password', 0, 2),
    ('NV005', N'Hoàng Văn Hùng', '1993-07-10', GETDATE(), '0965432109', 1, N'Số 131415 Đường JKL, Quận 5', NULL, 'hunghv@example.com', 'hunghv', 'password', 0, 1);



--Tỉnh/thành phố

INSERT INTO tinh_thanh_pho (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
(N'Hà Nội', GETDATE(), GETDATE(), 1),(N'Hồ Chí Minh', GETDATE(), GETDATE(), 1),(N'Hải Phòng', GETDATE(), GETDATE(), 1), (N'Đà Nẵng', GETDATE(), GETDATE(), 1), (N'Cần Thơ', GETDATE(), GETDATE(), 1),    -- 5
(N'Hà Giang', GETDATE(), GETDATE(), 1), (N'Cao Bằng', GETDATE(), GETDATE(), 1), (N'Bắc Kạn', GETDATE(), GETDATE(), 1), (N'Tuyên Quang', GETDATE(), GETDATE(), 1),
(N'Lào Cai', GETDATE(), GETDATE(), 1),   (N'Điện Biên', GETDATE(), GETDATE(), 1), (N'Lai Châu', GETDATE(), GETDATE(), 1),  (N'Sơn La', GETDATE(), GETDATE(), 1),
(N'Yên Bái', GETDATE(), GETDATE(), 1),  (N'Hòa Bình', GETDATE(), GETDATE(), 1), (N'Thái Nguyên', GETDATE(), GETDATE(), 1),(N'Lạng Sơn', GETDATE(), GETDATE(), 1),
(N'Quảng Ninh', GETDATE(), GETDATE(), 1), (N'Bắc Giang', GETDATE(), GETDATE(), 1), (N'Phú Thọ', GETDATE(), GETDATE(), 1), (N'Vĩnh Phúc', GETDATE(), GETDATE(), 1),
(N'Bắc Ninh', GETDATE(), GETDATE(), 1),  (N'Thái Bình', GETDATE(), GETDATE(), 1),  (N'Hà Nam', GETDATE(), GETDATE(), 1), (N'Nam Định', GETDATE(), GETDATE(), 1),
(N'Ninh Bình', GETDATE(), GETDATE(), 1),  (N'Thanh Hóa', GETDATE(), GETDATE(), 1), (N'Hà Tĩnh', GETDATE(), GETDATE(), 1), (N'Quảng Bình', GETDATE(), GETDATE(), 1),
(N'Quảng Trị', GETDATE(), GETDATE(), 1), (N'Thừa Thiên Huế', GETDATE(), GETDATE(), 1),(N'Quảng Nam', GETDATE(), GETDATE(), 1),  (N'Quảng Ngãi', GETDATE(), GETDATE(), 1),
(N'Bình Định', GETDATE(), GETDATE(), 1), (N'Phú Yên', GETDATE(), GETDATE(), 1), (N'Khánh Hòa', GETDATE(), GETDATE(), 1), (N'Ninh Thuận', GETDATE(), GETDATE(), 1),
(N'Bình Thuận', GETDATE(), GETDATE(), 1),(N'Kon Tum', GETDATE(), GETDATE(), 1),(N'Gia Lai', GETDATE(), GETDATE(), 1), (N'Đắk Lắk', GETDATE(), GETDATE(), 1),
(N'Đắk Nông', GETDATE(), GETDATE(), 1),  (N'Lâm Đồng', GETDATE(), GETDATE(), 1), (N'Bình Phước', GETDATE(), GETDATE(), 1), (N'Bình Dương', GETDATE(), GETDATE(), 1),
(N'Đồng Nai', GETDATE(), GETDATE(), 1),  (N'Bà Rịa - Vũng Tàu', GETDATE(), GETDATE(), 1),(N'Long An', GETDATE(), GETDATE(), 1),(N'Tiền Giang', GETDATE(), GETDATE(), 1),
(N'Bến Tre', GETDATE(), GETDATE(), 1), (N'Trà Vinh', GETDATE(), GETDATE(), 1), (N'Vĩnh Long', GETDATE(), GETDATE(), 1), (N'An Giang', GETDATE(), GETDATE(), 1),
(N'Kiên Giang', GETDATE(), GETDATE(), 1), (N'Cần Thơ', GETDATE(), GETDATE(), 1), (N'Hậu Giang', GETDATE(), GETDATE(), 1), (N'Sóc Trăng', GETDATE(), GETDATE(), 1),
(N'Bạc Liêu', GETDATE(), GETDATE(), 1),  (N'Cà Mau', GETDATE(), GETDATE(), 1);

--Địa chỉ
INSERT INTO dia_chi (mo_ta, ngay_tao, ngay_sua, trang_thai, tinh_thanh_pho_id)
VALUES
(N'Số 123 Đường ABC, Quận 1', GETDATE(), GETDATE(), 0, 1),
(N'Số 456 Đường XYZ, Quận 2', GETDATE(), GETDATE(), 0, 2),
(N'Số 789 Đường DEF, Quận 3', GETDATE(), GETDATE(), 0, 3),
(N'Số 101112 Đường GHI, Quận 4', GETDATE(), GETDATE(), 0, 4),
(N'Số 131415 Đường JKL, Quận 5', GETDATE(), GETDATE(), 0, 5);


--Khách hàng
INSERT INTO khach_hang (ma, ho_ten, ngay_sinh, ngay_tao, sdt, gioi_tinh, dia_chi, email, tai_khoan, mat_khau, trang_thai)
VALUES
('KH001', N'Nguyễn Văn A', '1990-01-01', GETDATE(), '0987654321', 1, N'Số 123 Đường ABC, Quận 1', 'nguyenvana@example.com', 'nguyenvana', 'password', 0),
('KH002', N'Trần Thị B', '1995-05-05', GETDATE(), '0901234567', 0, N'Số 456 Đường XYZ, Quận 2', 'tranthib@example.com', 'tranthib', 'password', 0),
('KH003', N'Lê Văn C', '1988-10-10', GETDATE(), '0978123456', 1, N'Số 789 Đường DEF, Quận 3', 'levanc@example.com', 'levanc', 'password', 0),
('KH004', N'Phạm Thị D', '1998-03-15', GETDATE(), '0912345678', 0, N'Số 101112 Đường GHI, Quận 4', 'phamthid@example.com', 'phamthid', 'password', 0),
('KH005', N'Hoàng Văn E', '1993-07-20', GETDATE(), '0965432109', 1, N'Số 131415 Đường JKL, Quận 5', 'hoangvane@example.com', 'hoangvane', 'password', 0);

-- địa chỉ chi tiết
INSERT INTO dia_chi_chi_tiet (khach_hang_id, dia_chi_id, mac_dinh)
VALUES
(1, 2, 1),
(2, 3, 1),
(3, 4, 1),
(4, 5, 1),
(5, 6, 1);



--Thương hiệu

INSERT INTO thuong_hieu (ten, hinh_anh, ngay_tao, ngay_sua, trang_thai)
VALUES
(N'Nike', 'nike_logo.jpg', GETDATE(), GETDATE(), 0),
(N'Adidas', 'adidas_logo.jpg', GETDATE(), GETDATE(), 0),
(N'Puma', 'puma_logo.jpg', GETDATE(), GETDATE(), 0),
(N'New Balance', 'newbalance_logo.jpg', GETDATE(), GETDATE(), 0),
(N'Under Armour', 'underarmour_logo.jpg', GETDATE(), GETDATE(), 0);


--Màu sắc

INSERT INTO mau_sac (ten, hinh_anh, ngay_tao, ngay_sua, trang_thai)
VALUES
(N'Màu đen', 'black_color.jpg', GETDATE(), GETDATE(), 0),
(N'Màu trắng', 'white_color.jpg', GETDATE(), GETDATE(), 0),
(N'Màu xanh', 'blue_color.jpg', GETDATE(), GETDATE(), 0),
(N'Màu đỏ', 'red_color.jpg', GETDATE(), GETDATE(), 0),
(N'Màu vàng', 'yellow_color.jpg', GETDATE(), GETDATE(), 0);


--thể loại

INSERT INTO the_loai (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
(N'Giày chạy bộ', GETDATE(), GETDATE(), 0),
(N'Giày bóng rổ', GETDATE(), GETDATE(), 0),
(N'Giày đá banh', GETDATE(), GETDATE(), 0),
(N'Giày tennis', GETDATE(), GETDATE(), 0),
(N'Giày leo núi', GETDATE(), GETDATE(), 0);


--Kích cỡ

INSERT INTO kich_co (ten, ngay_tao, ngay_sua, trang_thai)
VALUES
('US 7', GETDATE(), GETDATE(), 0),
('US 8', GETDATE(), GETDATE(), 0),
('US 9', GETDATE(), GETDATE(), 0),
('US 10', GETDATE(), GETDATE(), 0),
('US 11', GETDATE(), GETDATE(), 0);

--Giảm giá

INSERT INTO giam_gia (ten, ngay_tao, ngay_sua, trang_thai, muc_giam)
VALUES
('Không', GETDATE(), GETDATE(), 0, 0),
('Giảm giá 5%', GETDATE(), GETDATE(), 0, 5),
('Giảm giá 10%', GETDATE(), GETDATE(), 0, 10),
('Giảm giá 15%', GETDATE(), GETDATE(), 0, 15),
('Giảm giá 20%', GETDATE(), GETDATE(), 0, 20),
('Giảm giá 25%', GETDATE(), GETDATE(), 0, 25);



--sản phẩm

INSERT INTO san_pham (ten, mo_ta, ngay_tao, ngay_sua, hinh_anh_dai_dien, nguoi_tao, trang_thai, thuong_hieu_id, the_loai_id, giam_gia_id)
VALUES
(N'Giày chạy Nike Air Zoom Pegasus', N'Giày chạy bộ cao cấp với công nghệ Zoom', GETDATE(), GETDATE(), 'nike_zoom_pegasus.jpg', 'Admin', 0, 11, 6, 1),
(N'Giày bóng rổ Adidas Pro Bounce 2019', N'Giày bóng rổ chuyên nghiệp với đế Boost', GETDATE(), GETDATE(), 'adidas_pro_bounce.jpg', 'Admin', 0, 12, 7,2),
(N'Giày đá banh Nike Mercurial Superfly', N'Giày đá banh siêu nhẹ và linh hoạt', GETDATE(), GETDATE(), 'nike_mercurial_superfly.jpg', 'Admin', 0, 13, 8,3),
(N'Giày tennis Adidas Adizero Ubersonic 3', N'Giày tennis chuyên nghiệp với đệm Boost', GETDATE(), GETDATE(), 'adidas_adizero_ubersonic.jpg', 'Admin', 0, 14, 9,4),
(N'Giày leo núi The North Face Ultra Fastpack IV Futurelight', N'Giày leo núi chống nước và thoáng khí', GETDATE(), GETDATE(), 'north_face_ultra_fastpack.jpg', 'Admin', 0, 15, 10,5);


--Chi tiết sản phẩm màu sắc

INSERT INTO chi_tiet_san_pham_mau_sac (trang_thai, ngay_tao, ngay_sua, hinh_anh, gia_hien_hanh, san_pham_id, mau_sac_id)
VALUES
(0, GETDATE(), GETDATE(), 'black_shoe.jpg', 1000000, 1, 6),
(0, GETDATE(), GETDATE(), 'white_shoe.jpg', 3200000, 2, 7),
(0, GETDATE(), GETDATE(), 'blue_shoe.jpg', 5100000, 3, 8),
(0, GETDATE(), GETDATE(), 'red_shoe.jpg', 7300000, 4, 9),
(0, GETDATE(), GETDATE(), 'yellow_shoe.jpg', 2400000, 5, 10);


--chi tiết sản phẩm

INSERT INTO chi_tiet_san_pham (so_luong, trang_thai, ngay_tao, ngay_sua, chi_tiet_san_pham_mau_sac_id, kich_co_id)
VALUES
(100, 0, GETDATE(), GETDATE(), 1, 1),
(100, 0, GETDATE(), GETDATE(), 2, 2),
(100, 0, GETDATE(), GETDATE(), 3, 3),
(100, 0, GETDATE(), GETDATE(), 4, 4),
(100, 0, GETDATE(), GETDATE(), 5, 5);

--Voucher
INSERT INTO voucher (ma_voucher, ten_voucher, ngay_bat_dau, ngay_ket_thuc, ngay_tao, ngay_sua, so_luong, phan_tram_giam, giam_toi_da, gia_tri_don_toi_thieu, trang_thai)
VALUES
(N'không', N'Không', '2024-03-20', '2024-05-20', '2024-03-20', '2024-03-20', 100, 0, 100000, 500000, 0),
('VOUCHER001', N'Voucher Giảm 5%', '2024-03-20', '2024-05-20', '2024-03-20', '2024-03-20', 100, 5, 100000, 500000, 0),
('VOUCHER002', N'Voucher Giảm 10%', '2024-03-21', '2024-05-21', '2024-03-21', '2024-03-21', 150, 10, 150000, 750000, 0),
('VOUCHER003', N'Voucher Giảm 15%', '2024-03-22', '2024-05-22', '2024-03-22', '2024-03-22', 200, 15, 200000, 1000000, 0),
('VOUCHER004', N'Voucher Giảm 20%', '2024-03-23', '2024-05-23', '2024-03-23', '2024-03-23', 250, 20, 250000, 1250000, 0),
('VOUCHER005', N'Voucher Giảm 25%', '2024-03-24', '2024-05-24', '2024-03-24', '2024-03-24', 300, 25, 300000, 1500000, 0);


--giỏ hàng
INSERT INTO gio_hang (ghi_chu, trang_thai, ngay_tao, ngay_sua, khach_hang_id)
VALUES
(N'Giỏ hàng của Nguyễn Văn A', 0, GETDATE(), GETDATE(), 1),
(N'Giỏ hàng của Trần Thị B', 0, GETDATE(), GETDATE(), 2),
(N'Giỏ hàng của Lê Văn C', 0, GETDATE(), GETDATE(), 3),
(N'Giỏ hàng của Phạm Thị D', 0, GETDATE(), GETDATE(), 4),
(N'Giỏ hàng của Hoàng Văn E', 0, GETDATE(), GETDATE(), 5);


--giỏ hàng chi tiết
-- Đây là ví dụ, bạn cần thay đổi thông tin dựa trên thông tin giỏ hàng và sản phẩm cụ thể
INSERT INTO gio_hang_chi_tiet (gio_hang_id, chi_tiet_san_pham_id, TrangThai, so_luong)
VALUES
(1, 6, 0, 2),
(2, 2, 0, 1),
(3, 3, 0, 3),
(4, 4, 0, 1),
(5, 5, 0, 2);


--hóa đơn

INSERT INTO hoa_don (ma,trang_thai, ngay_tao, ngay_thanh_toan, tong_tien, phan_tram_khuyen_mai, nhan_vien_id, khach_hang_id, voucher_id, ghi_chu, ten_nguoi_nhan, sdt_nguoi_nhan, dia_chi_nguoi_nhan, tong_tien_giam, phuong_thuc_thanh_toan_id, tong_tien_san_pham_chua_giam, phi_ship)
VALUES
('HD0001',0, '2024-01-05', null, 200000, 5,null , 1, 1, N'Ghi chú 1', N'Nguyễn Văn A', '0987654321', N'123 Đường ABC, Quận XYZ', 190000, 1, 210000, 10000),
('HD0002',1, '2024-02-10', null, 250000, 0, null, 1, 2, N'Ghi chú 2', N'Trần Thị B', '0123456789', N'456 Đường DEF, Quận UVW', 250000, 1, 250000, 0),
('HD0003',2, '2024-03-15', null, 300000, 15, null, 1, 3, N'Ghi chú 3', N'Lê Văn C', '0123789456', N'789 Đường GHI, Quận STU', 255000, 1, 350000, 50000),
('HD0004',3, '2024-04-20', null, 180000, 0, null, 1, 4, N'Ghi chú 4', N'Phạm Thị D', '0369874512', N'987 Đường KLM, Quận OPQ', 180000, 1, 200000, 20000),
('HD0005',4, '2024-05-25', '2024-05-30', 220000, 10, null, 1, 5, N'Ghi chú 5', N'Trần Văn E', '0542167893', N'654 Đường XYZ, Quận RST', 198000, 1, 250000, 30000),
('HD0006',5, '2024-06-30', null, 280000, 20, null, 1, 1, N'Ghi chú 6', N'Lê Thị F', '0789456321', N'321 Đường MNO, Quận JKL', 224000, 1, 350000, 50000),
('HD0007',0, '2024-07-05', null, 320000, 0, null, 1, 2, N'Ghi chú 7', N'Nguyễn Văn G', '0321654987', N'963 Đường UVW, Quận GHI', 320000, 1, 320000, 0),
('HD0008',1, '2024-08-10', null, 270000, 8, null, 1, 3, N'Ghi chú 8', N'Trần Thị H', '0246813579', N'159 Đường QRS, Quận DEF', 248400, 1, 300000, 20000),
('HD0009',2, '2024-09-15', null, 350000, 15, null, 1, 4, N'Ghi chú 9', N'Lê Văn I', '0369258147', N'741 Đường WXY, Quận BCD', 297500, 1, 400000, 52500),
('HD00010',3, '2024-10-20', null, 210000, 0, null, 1, 5, N'Ghi chú 10', N'Phạm Thị K', '0369258741', N'147 Đường STU, Quận EFG', 210000, 1, 220000, 10000),
('HD00011',4, '2024-11-01', '2024-11-05', 280000, 10, null, 1, 1, N'Ghi chú 11', N'Nguyễn Văn L', '0987654321', N'123 Đường ABC, Quận XYZ', 252000, 1, 310000, 30000),
('HD00012',5, '2024-12-05', null, 320000, 0, null, 1, 2, N'Ghi chú 12', N'Trần Thị M', '0123456789', N'456 Đường DEF, Quận UVW', 320000, 1, 320000, 0),
('HD00013',0, '2025-01-10', null, 350000, 15, null, 1, 3, N'Ghi chú 13', N'Lê Văn N', '0123789456', N'789 Đường GHI, Quận STU', 297500, 1, 400000, 52500),
('HD00014',1, '2025-02-15', null, 240000, 0, null, 1, 4, N'Ghi chú 14', N'Phạm Thị O', '0369874512', N'987 Đường KLM, Quận OPQ', 240000, 1, 260000, 20000),
('HD00015',2, '2025-03-20', null, 280000, 10, null, 1, 5, N'Ghi chú 15', N'Trần Văn P', '0542167893', N'654 Đường XYZ, Quận RST', 252000, 1, 320000, 40000),
('HD00016',3, '2025-04-25', null, 330000, 20, null, 1, 1, N'Ghi chú 16', N'Lê Thị Q', '0789456321', N'321 Đường MNO, Quận JKL', 264000, 1, 400000, 60000),
('HD00017',4, '2025-05-30', '2025-05-05', 360000, 0, null, 1, 2, N'Ghi chú 17', N'Nguyễn Văn R', '0321654987', N'963 Đường UVW, Quận GHI', 360000, 1, 360000, 0),
('HD00018',5, '2025-06-05', null, 290000, 8, null, 1, 3, N'Ghi chú 18', N'Trần Thị S', '0246813579', N'159 Đường QRS, Quận DEF', 266800, 1, 350000, 30000),
('HD00019',0, '2025-07-10', null, 380000, 15, null, 1, 4, N'Ghi chú 19', N'Lê Văn T', '0369258147', N'741 Đường WXY, Quận BCD', 323000, 1, 450000, 57000),
('HD00020',1, '2025-08-15', null, 220000, 0, null, 1,5, N'Ghi chú 20', N'Phạm Thị U', '0369258741', N'147 Đường STU, Quận EFG', 220000, 1, 230000, 10000);
--hóa đơn chi tiết

INSERT INTO hoa_don_chi_tiet (hoa_don_id, chi_tiet_san_pham_id, TrangThai, so_luong, gia_hien_hanh, gia_da_giam)
VALUES
(28, 2, 0, 2, 200000, 180000),
(29, 2, 0, 1, 500000, 375000),
(30, 3, 0, 1, 500000, 375000),
(31, 4, 0, 1, 300000, 240000),
(32, 5, 0, 1, 500000, 375000),
(33, 6, 0, 1, 500000, 375000),
(34, 2, 0, 1, 500000, 375000),
(35, 3, 0, 3, 400000, 280000),
(36, 3, 0, 1, 500000, 375000),
(37, 4, 0, 1, 500000, 375000),
(38, 5, 0, 1, 500000, 375000),
(39, 6, 0, 1, 500000, 375000),
(40, 2, 0, 1, 500000, 375000),
(41, 3, 0, 1, 500000, 375000),
(42, 4, 0, 1, 500000, 375000),
(23, 5, 0, 1, 500000, 375000),
(24, 6, 0, 1, 500000, 375000),
(25, 2, 0, 1, 500000, 375000),
(26, 3, 0, 1, 500000, 375000),
(27, 4, 0, 1, 500000, 375000),
(28, 5, 0, 1, 500000, 375000),
(29, 6, 0, 1, 500000, 375000),
(30, 2, 0, 1, 500000, 375000),
(31, 3, 0, 1, 500000, 375000),
(32, 4, 0, 1, 500000, 375000),
(33, 5, 0, 2, 600000, 450000);

