CREATE TABLE "User" (
	"userKey"	INTEGER NOT NULL UNIQUE,	--使用者鍵值
	"userName"	TEXT NOT NULL,				--使用者名稱
	"password"	TEXT NOT NULL,				--密碼
	"userDisplayName"	TEXT NOT NULL,		--使用者顯示名稱
	joinDate date,							--入職日期
	"departmentKey"	INTEGER, 				--所屬部門代碼
	employeeId INTEGER, 					--員工編號
	birthday date, 							--生日
	email TEXT, 							--電子郵件
	tel TEXT,								--連絡電話
	roleKey INTEGER,						--角色鍵值
	createdTime datetime, 					--資料建立時間
	updatedTime datetime, 					--資料更新時間
	PRIMARY KEY("userKey" AUTOINCREMENT)
);

CREATE TABLE "Function" (
	"functionKey" INTEGER NOT NULL UNIQUE,
	functionName TEXT NOT NULL,								--功能名稱
	upwardFunctionKey INTEGER,								--上層功能鍵值
	"isOpen" BOOLEAN NOT NULL CHECK ("isOpen" IN (0, 1)),	--是否啟用
	createdTime datetime,									--建立時間
	updatedTime datetime,									--更新時間
	PRIMARY KEY("functionKey" AUTOINCREMENT)
);

CREATE TABLE "Role" (
	"roleKey" INTEGER NOT NULL UNIQUE,						--角色鍵值
	roleName TEXT NOT NULL,									--角色名稱
	upwardRoleKey INTEGER,									--上層角色鍵值
	"isOpen" BOOLEAN NOT NULL CHECK ("isOpen" IN (0, 1)),	--是否啟用
	createdTime datetime,									--建立時間
	updatedTime datetime,									--更新時間
	PRIMARY KEY("roleKey" AUTOINCREMENT)
);

CREATE TABLE "RoleFuntion" (
	"roleKey" INTEGER NOT NULL,											--角色鍵值
	"functionKey" INTEGER NOT NULL,										--功能鍵值
	queryGranted BOOLEAN NOT NULL CHECK ("queryGranted" IN (0, 1)),		--查詢權限
	createGranted BOOLEAN NOT NULL CHECK ("createGranted" IN (0, 1)),	--新增權限
	updateGranted BOOLEAN NOT NULL CHECK ("modifyGranted" IN (0, 1)),	--修改權限
	deleteGranted BOOLEAN NOT NULL CHECK ("deleteGranted" IN (0, 1)),	--刪除權限
	updatedTime datetime,												--更新時間
	PRIMARY KEY("roleKey","functionKey")
);