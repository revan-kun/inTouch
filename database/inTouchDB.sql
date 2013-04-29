USE [master]
GO
/****** Object:  Database [inTouchDB]    Script Date: 04/29/2013 00:22:50 ******/
CREATE DATABASE [inTouchDB] ON  PRIMARY 
( NAME = N'inTouchDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'inTouchDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [inTouchDB] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [inTouchDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [inTouchDB] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [inTouchDB] SET ANSI_NULLS OFF
GO
ALTER DATABASE [inTouchDB] SET ANSI_PADDING OFF
GO
ALTER DATABASE [inTouchDB] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [inTouchDB] SET ARITHABORT OFF
GO
ALTER DATABASE [inTouchDB] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [inTouchDB] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [inTouchDB] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [inTouchDB] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [inTouchDB] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [inTouchDB] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [inTouchDB] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [inTouchDB] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [inTouchDB] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [inTouchDB] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [inTouchDB] SET  DISABLE_BROKER
GO
ALTER DATABASE [inTouchDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [inTouchDB] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [inTouchDB] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [inTouchDB] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [inTouchDB] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [inTouchDB] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [inTouchDB] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [inTouchDB] SET  READ_WRITE
GO
ALTER DATABASE [inTouchDB] SET RECOVERY SIMPLE
GO
ALTER DATABASE [inTouchDB] SET  MULTI_USER
GO
ALTER DATABASE [inTouchDB] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [inTouchDB] SET DB_CHAINING OFF
GO
USE [inTouchDB]
GO
/****** Object:  User [admin]    Script Date: 04/29/2013 00:22:50 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type](
	[type] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Type] PRIMARY KEY CLUSTERED 
(
	[type] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Technology_Level]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Technology_Level](
	[level] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Technology_Level] PRIMARY KEY CLUSTERED 
(
	[level] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sex]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sex](
	[sex] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Sex] PRIMARY KEY CLUSTERED 
(
	[sex] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Sex] ([sex]) VALUES (N'FEMALE')
INSERT [dbo].[Sex] ([sex]) VALUES (N'MALE')
/****** Object:  Table [dbo].[Role]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[role] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Role] ([role]) VALUES (N'DEVELOPER')
INSERT [dbo].[Role] ([role]) VALUES (N'MANAGER')
INSERT [dbo].[Role] ([role]) VALUES (N'TESTER')
/****** Object:  Table [dbo].[ProjectStatus]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProjectStatus](
	[status] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_ProjectStatus] PRIMARY KEY CLUSTERED 
(
	[status] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ProjectStatus] ([status]) VALUES (N'ABANDONED')
INSERT [dbo].[ProjectStatus] ([status]) VALUES (N'CLOSED')
INSERT [dbo].[ProjectStatus] ([status]) VALUES (N'FROZEN')
INSERT [dbo].[ProjectStatus] ([status]) VALUES (N'OPEN')
/****** Object:  Table [dbo].[Technologies]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Technologies](
	[technology] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Technologies] PRIMARY KEY CLUSTERED 
(
	[technology] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Programming_Level]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Programming_Level](
	[level] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Programming_Level] PRIMARY KEY CLUSTERED 
(
	[level] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Programming_Level] ([level]) VALUES (N'BEGINNER')
INSERT [dbo].[Programming_Level] ([level]) VALUES (N'GODLIKE')
/****** Object:  Table [dbo].[Programming_Languages]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Programming_Languages](
	[language] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Programming_Languages] PRIMARY KEY CLUSTERED 
(
	[language] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Programming_Languages] ([language]) VALUES (N'English')
INSERT [dbo].[Programming_Languages] ([language]) VALUES (N'Russian')
INSERT [dbo].[Programming_Languages] ([language]) VALUES (N'Ukrainian')
/****** Object:  Table [dbo].[Level]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Level](
	[qlevel] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Level] PRIMARY KEY CLUSTERED 
(
	[qlevel] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Level] ([qlevel]) VALUES (N'JUNIOR')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'MIDDLE')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'SENIOR')
/****** Object:  Table [dbo].[Languages]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Languages](
	[language] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Languages] PRIMARY KEY CLUSTERED 
(
	[language] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LanguageLevel]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LanguageLevel](
	[level] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_LanguageLevel] PRIMARY KEY CLUSTERED 
(
	[level] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Member]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member](
	[login] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[name] [nvarchar](255) NULL,
	[surname] [nvarchar](255) NULL,
	[birthday] [date] NULL,
	[registration] [date] NULL,
	[sex] [nvarchar](25) NULL,
	[qlevel] [nvarchar](25) NULL,
	[experience] [float] NULL,
	[photo_link] [nvarchar](255) NULL,
	[additional_info] [nvarchar](max) NULL,
	[role] [nvarchar](25) NULL,
 CONSTRAINT [PK_Member_1] PRIMARY KEY CLUSTERED 
(
	[login] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'carlos@gmail.com', N'2222', N'Robert', N'Carlos', CAST(0x44130B00 AS Date), CAST(0xBD330B00 AS Date), N'MALE', N'SENIOR', 4, N'.\resources\photos\carlos89.jpg', NULL, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'dobkin@epam.com', N'1111', N'Arkadiy', N'Dobkin', CAST(0x30EC0A00 AS Date), CAST(0xC4370B00 AS Date), N'MALE', N'SENIOR', 20, N'.\resources\photos\dobkin.jpg', NULL, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'franko77@ukr.net', N'3333qwerty', N'Ivan', N'Franko', CAST(0x3E030B00 AS Date), CAST(0x11370B00 AS Date), N'MALE', N'SENIOR', 5, N'.\resources\photos\franko77.jpg', NULL, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'grispin@gmail.com', N'123qwerty', N'John', N'Grispin', CAST(0x23140B00 AS Date), CAST(0xF3340B00 AS Date), N'MALE', N'SENIOR', 12, N'.\resources\photos\grispin.jpg', NULL, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'smith@epam.com', N'4a6sdf6sdf88dfgh9', N'Josef', N'Smith', CAST(0x39030B00 AS Date), CAST(0x48350B00 AS Date), N'MALE', N'JUNIOR', 0.5, N'.\resources\photos\mr_smith.jpg', NULL, N'TESTER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [role]) VALUES (N'willson@gmail.com', N'qwerty123', N'Jennifer', N'Willson', CAST(0xEA120B00 AS Date), CAST(0x6E320B00 AS Date), N'FEMALE', N'MIDDLE', 1.5, N'.\resources\photos\willey1988.jpg', NULL, N'TESTER')
/****** Object:  Table [dbo].[Project]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[created] [date] NOT NULL,
	[estimated_completion] [date] NULL,
	[completed] [date] NULL,
	[description] [nvarchar](max) NULL,
	[customer] [nvarchar](255) NULL,
	[status] [nvarchar](25) NULL,
 CONSTRAINT [PK_Project] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Project] ON
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (2, N'Indigo', CAST(0x24360B00 AS Date), CAST(0x91370B00 AS Date), NULL, N'Indigo project - dummy decription', N'Google', N'OPEN')
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (4, N'Juno', CAST(0x61360B00 AS Date), CAST(0xC9370B00 AS Date), NULL, N'Dummy description for Juno project', N'Apple', N'OPEN')
SET IDENTITY_INSERT [dbo].[Project] OFF
/****** Object:  Table [dbo].[Skills]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skills](
	[name] [nvarchar](50) NOT NULL,
	[type] [nvarchar](25) NULL,
 CONSTRAINT [PK_Skills_1] PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Technologies_Skills]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Technologies_Skills](
	[member_id] [nvarchar](255) NOT NULL,
	[technology] [nvarchar](50) NOT NULL,
	[experience] [float] NULL,
	[description] [nvarchar](max) NULL,
	[skill_level] [nvarchar](25) NULL,
 CONSTRAINT [PK_Technologies_Skills] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[technology] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teams]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teams](
	[project_id] [bigint] NOT NULL,
	[member_id] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Teams] PRIMARY KEY CLUSTERED 
(
	[project_id] ASC,
	[member_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (2, N'carlos@gmail.com')
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (2, N'dobkin@epam.com')
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (2, N'franko77@ukr.net')
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (4, N'grispin@gmail.com')
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (4, N'smith@epam.com')
INSERT [dbo].[Teams] ([project_id], [member_id]) VALUES (4, N'willson@gmail.com')
/****** Object:  Table [dbo].[Project_History]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project_History](
	[member_id] [nvarchar](255) NOT NULL,
	[project_id] [bigint] NOT NULL,
 CONSTRAINT [PK_Project_History] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[project_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Programming_Skills]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Programming_Skills](
	[member_id] [nvarchar](255) NOT NULL,
	[programming_language] [nvarchar](25) NOT NULL,
	[experience] [nchar](10) NULL,
	[description] [nvarchar](max) NULL,
	[programming_level] [nvarchar](25) NULL,
 CONSTRAINT [PK_Programming_Skills] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[programming_language] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberSkills]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberSkills](
	[member_id] [nvarchar](255) NOT NULL,
	[skill_id] [bigint] IDENTITY(1,1) NOT NULL,
	[skill_name] [nvarchar](50) NULL,
	[experience] [float] NULL,
	[description] [nvarchar](max) NULL,
	[level] [nvarchar](25) NULL,
 CONSTRAINT [PK_MemberSkills] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[skill_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Language_Skills]    Script Date: 04/29/2013 00:22:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Language_Skills](
	[member_id] [nvarchar](255) NOT NULL,
	[language] [nvarchar](25) NOT NULL,
	[description] [nvarchar](max) NULL,
	[level] [nvarchar](25) NULL,
 CONSTRAINT [PK_Language_Skills] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[language] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Member_Level]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Level] FOREIGN KEY([qlevel])
REFERENCES [dbo].[Level] ([qlevel])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Level]
GO
/****** Object:  ForeignKey [FK_Member_Role]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Role] FOREIGN KEY([role])
REFERENCES [dbo].[Role] ([role])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Role]
GO
/****** Object:  ForeignKey [FK_Member_Sex]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Sex] FOREIGN KEY([sex])
REFERENCES [dbo].[Sex] ([sex])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Sex]
GO
/****** Object:  ForeignKey [FK_Project_ProjectStatus]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Project]  WITH CHECK ADD  CONSTRAINT [FK_Project_ProjectStatus] FOREIGN KEY([status])
REFERENCES [dbo].[ProjectStatus] ([status])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Project] CHECK CONSTRAINT [FK_Project_ProjectStatus]
GO
/****** Object:  ForeignKey [FK_Skills_Type]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Skills]  WITH CHECK ADD  CONSTRAINT [FK_Skills_Type] FOREIGN KEY([type])
REFERENCES [dbo].[Type] ([type])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Skills] CHECK CONSTRAINT [FK_Skills_Type]
GO
/****** Object:  ForeignKey [FK_Technologies_Skills_Member]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Technologies_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Technologies_Skills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Technologies_Skills] CHECK CONSTRAINT [FK_Technologies_Skills_Member]
GO
/****** Object:  ForeignKey [FK_Technologies_Skills_Technologies]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Technologies_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Technologies_Skills_Technologies] FOREIGN KEY([technology])
REFERENCES [dbo].[Technologies] ([technology])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Technologies_Skills] CHECK CONSTRAINT [FK_Technologies_Skills_Technologies]
GO
/****** Object:  ForeignKey [FK_Technologies_Skills_Technology_Level]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Technologies_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Technologies_Skills_Technology_Level] FOREIGN KEY([skill_level])
REFERENCES [dbo].[Technology_Level] ([level])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Technologies_Skills] CHECK CONSTRAINT [FK_Technologies_Skills_Technology_Level]
GO
/****** Object:  ForeignKey [FK_Teams_Member1]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Member1] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Member1]
GO
/****** Object:  ForeignKey [FK_Teams_Project]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Project]
GO
/****** Object:  ForeignKey [FK_Project_History_Member]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Member]
GO
/****** Object:  ForeignKey [FK_Project_History_Project]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Project]
GO
/****** Object:  ForeignKey [FK_Programming_Skills_Member]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Programming_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Programming_Skills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Programming_Skills] CHECK CONSTRAINT [FK_Programming_Skills_Member]
GO
/****** Object:  ForeignKey [FK_Programming_Skills_Programming_Languages]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Programming_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Programming_Skills_Programming_Languages] FOREIGN KEY([programming_language])
REFERENCES [dbo].[Programming_Languages] ([language])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Programming_Skills] CHECK CONSTRAINT [FK_Programming_Skills_Programming_Languages]
GO
/****** Object:  ForeignKey [FK_Programming_Skills_Programming_Level]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Programming_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Programming_Skills_Programming_Level] FOREIGN KEY([programming_level])
REFERENCES [dbo].[Programming_Level] ([level])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Programming_Skills] CHECK CONSTRAINT [FK_Programming_Skills_Programming_Level]
GO
/****** Object:  ForeignKey [FK_MemberSkills_Member]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[MemberSkills]  WITH CHECK ADD  CONSTRAINT [FK_MemberSkills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MemberSkills] CHECK CONSTRAINT [FK_MemberSkills_Member]
GO
/****** Object:  ForeignKey [FK_MemberSkills_Skills]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[MemberSkills]  WITH CHECK ADD  CONSTRAINT [FK_MemberSkills_Skills] FOREIGN KEY([skill_name])
REFERENCES [dbo].[Skills] ([name])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[MemberSkills] CHECK CONSTRAINT [FK_MemberSkills_Skills]
GO
/****** Object:  ForeignKey [FK_Language_Skills_LanguageLevel]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Language_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Language_Skills_LanguageLevel] FOREIGN KEY([level])
REFERENCES [dbo].[LanguageLevel] ([level])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Language_Skills] CHECK CONSTRAINT [FK_Language_Skills_LanguageLevel]
GO
/****** Object:  ForeignKey [FK_Language_Skills_Languages]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Language_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Language_Skills_Languages] FOREIGN KEY([language])
REFERENCES [dbo].[Languages] ([language])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Language_Skills] CHECK CONSTRAINT [FK_Language_Skills_Languages]
GO
/****** Object:  ForeignKey [FK_Language_Skills_Member]    Script Date: 04/29/2013 00:22:51 ******/
ALTER TABLE [dbo].[Language_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Language_Skills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Language_Skills] CHECK CONSTRAINT [FK_Language_Skills_Member]
GO
