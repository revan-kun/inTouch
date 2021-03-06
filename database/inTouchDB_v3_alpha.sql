USE [master]
GO
/****** Object:  Database [inTouchDB]    Script Date: 05/17/2013 22:37:44 ******/
CREATE DATABASE [inTouchDB] ON  PRIMARY 
( NAME = N'inTouchDB', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'inTouchDB_log', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
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
/****** Object:  User [admin]    Script Date: 05/17/2013 22:37:44 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 05/17/2013 22:37:45 ******/
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
INSERT [dbo].[Type] ([type]) VALUES (N'LANGUAGE')
INSERT [dbo].[Type] ([type]) VALUES (N'PROGRAMMING')
INSERT [dbo].[Type] ([type]) VALUES (N'TECHNOLOGY')
/****** Object:  Table [dbo].[Skill_Level]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skill_Level](
	[level] [int] NOT NULL,
 CONSTRAINT [PK_Skill_Level] PRIMARY KEY CLUSTERED 
(
	[level] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Skill_Level] ([level]) VALUES (1)
INSERT [dbo].[Skill_Level] ([level]) VALUES (2)
INSERT [dbo].[Skill_Level] ([level]) VALUES (3)
INSERT [dbo].[Skill_Level] ([level]) VALUES (4)
INSERT [dbo].[Skill_Level] ([level]) VALUES (5)
INSERT [dbo].[Skill_Level] ([level]) VALUES (6)
INSERT [dbo].[Skill_Level] ([level]) VALUES (7)
INSERT [dbo].[Skill_Level] ([level]) VALUES (8)
INSERT [dbo].[Skill_Level] ([level]) VALUES (9)
INSERT [dbo].[Skill_Level] ([level]) VALUES (10)
/****** Object:  Table [dbo].[Sex]    Script Date: 05/17/2013 22:37:45 ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 05/17/2013 22:37:45 ******/
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
/****** Object:  Table [dbo].[Project_Status]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project_Status](
	[status] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_ProjectStatus] PRIMARY KEY CLUSTERED 
(
	[status] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Project_Status] ([status]) VALUES (N'ABANDONED')
INSERT [dbo].[Project_Status] ([status]) VALUES (N'CLOSED')
INSERT [dbo].[Project_Status] ([status]) VALUES (N'FROZEN')
INSERT [dbo].[Project_Status] ([status]) VALUES (N'OPEN')
/****** Object:  Table [dbo].[Likes]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Likes](
	[like_value] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Likes] PRIMARY KEY CLUSTERED 
(
	[like_value] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Likes] ([like_value]) VALUES (N'DISLIKE')
INSERT [dbo].[Likes] ([like_value]) VALUES (N'DONT_CARE')
INSERT [dbo].[Likes] ([like_value]) VALUES (N'LIKE')
/****** Object:  Table [dbo].[Level]    Script Date: 05/17/2013 22:37:45 ******/
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
/****** Object:  Table [dbo].[Member]    Script Date: 05/17/2013 22:37:45 ******/
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
	[rating] [int] NULL,
	[role] [nvarchar](25) NULL,
	[like_id] [bigint] NULL,
 CONSTRAINT [PK_Member_1] PRIMARY KEY CLUSTERED 
(
	[login] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'carlos@gmail.com', N'2222', N'Robert', N'Carlos', CAST(0x44130B00 AS Date), CAST(0xBD330B00 AS Date), N'MALE', N'SENIOR', 4, N'.\resources\photos\carlos89.jpg', NULL, NULL, N'DEVELOPER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'dobkin@epam.com', N'1111', N'Arkadiy', N'Dobkin', CAST(0x30EC0A00 AS Date), CAST(0xC4370B00 AS Date), N'MALE', N'SENIOR', 20, N'.\resources\photos\dobkin.jpg', NULL, NULL, N'MANAGER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'franko77@ukr.net', N'3333qwerty', N'Ivan', N'Franko', CAST(0x3E030B00 AS Date), CAST(0x11370B00 AS Date), N'MALE', N'SENIOR', 5, N'.\resources\photos\franko77.jpg', NULL, NULL, N'DEVELOPER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'grispin@gmail.com', N'123qwerty', N'John', N'Grispin', CAST(0x23140B00 AS Date), CAST(0xF3340B00 AS Date), N'MALE', N'SENIOR', 12, N'.\resources\photos\grispin.jpg', NULL, NULL, N'MANAGER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'rttttttttt@asdasd.asd', N'rttttttttt@asdasd.asd', N'asdasd', N'asdasd', NULL, NULL, NULL, NULL, 0, NULL, N'memberAdditionalInfo', 0, N'DEVELOPER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'smith@epam.com', N'4a6sdf6sdf88dfgh9', N'Josef', N'Smith', CAST(0x39030B00 AS Date), CAST(0x48350B00 AS Date), N'MALE', N'JUNIOR', 0.5, N'.\resources\photos\mr_smith.jpg', NULL, NULL, N'TESTER', NULL)
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role], [like_id]) VALUES (N'willson@gmail.com', N'qwerty123', N'Jennifer', N'Willson', CAST(0xEA120B00 AS Date), CAST(0x6E320B00 AS Date), N'FEMALE', N'MIDDLE', 1.5, N'.\resources\photos\willey1988.jpg', NULL, NULL, N'TESTER', NULL)
/****** Object:  Table [dbo].[Project]    Script Date: 05/17/2013 22:37:45 ******/
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
/****** Object:  Table [dbo].[Skills]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skills](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[type] [nvarchar](25) NULL,
 CONSTRAINT [PK_Skills] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Skills] ON
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (6, N'ENGLISH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (7, N'CHINESE', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (8, N'JAVA', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (9, N'C#', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (10, N'HIBERNATE', N'TECHNOLOGY')
SET IDENTITY_INSERT [dbo].[Skills] OFF
/****** Object:  Table [dbo].[Teams]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teams](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[project_id] [bigint] NOT NULL,
	[member_id] [nvarchar](255) NOT NULL,
	[enter_date] [date] NULL,
	[enter_time] [time](0) NULL,
 CONSTRAINT [PK_Teams_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Teams] ON
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (1, 2, N'carlos@gmail.com', NULL, NULL)
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (2, 2, N'dobkin@epam.com', NULL, NULL)
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (3, 2, N'franko77@ukr.net', NULL, NULL)
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (4, 4, N'grispin@gmail.com', NULL, NULL)
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (5, 4, N'smith@epam.com', NULL, NULL)
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (6, 4, N'willson@gmail.com', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Teams] OFF
/****** Object:  Table [dbo].[MemberLikes]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberLikes](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[owner_login] [nvarchar](255) NULL,
	[liker_login] [nvarchar](255) NULL,
	[like] [nvarchar](25) NULL,
 CONSTRAINT [PK_MemberLikes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Member_Skills]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member_Skills](
	[member_id] [nvarchar](255) NOT NULL,
	[skill_id] [bigint] NOT NULL,
	[experience] [float] NULL,
	[description] [nvarchar](max) NULL,
	[self_assessed_level] [int] NULL,
 CONSTRAINT [PK_MemberSkills] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[skill_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'carlos@gmail.com', 6, 2, N'Hibernate is the best:)', 7)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'carlos@gmail.com', 7, 5, N'I love Chinese culture', 9)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'dobkin@epam.com', 8, 20, N'My favorite programming language is Java', 10)
/****** Object:  Table [dbo].[Project_History]    Script Date: 05/17/2013 22:37:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project_History](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[member_id] [nvarchar](255) NOT NULL,
	[project_id] [bigint] NOT NULL,
	[enter_date] [date] NULL,
	[enter_time] [time](0) NULL,
	[leaving_date] [date] NULL,
	[leaving_time] [time](0) NULL,
 CONSTRAINT [PK_Project_History_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Project_History] ON
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (1, N'carlos@gmail.com', 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (2, N'dobkin@epam.com', 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (3, N'franko77@ukr.net', 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (4, N'grispin@gmail.com', 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (5, N'smith@epam.com', 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (6, N'willson@gmail.com', 2, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Project_History] OFF
/****** Object:  ForeignKey [FK_Member_Level]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Level] FOREIGN KEY([qlevel])
REFERENCES [dbo].[Level] ([qlevel])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Level]
GO
/****** Object:  ForeignKey [FK_Member_Role]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Role] FOREIGN KEY([role])
REFERENCES [dbo].[Role] ([role])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Role]
GO
/****** Object:  ForeignKey [FK_Member_Sex]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Sex] FOREIGN KEY([sex])
REFERENCES [dbo].[Sex] ([sex])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Sex]
GO
/****** Object:  ForeignKey [FK_Project_ProjectStatus]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Project]  WITH CHECK ADD  CONSTRAINT [FK_Project_ProjectStatus] FOREIGN KEY([status])
REFERENCES [dbo].[Project_Status] ([status])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Project] CHECK CONSTRAINT [FK_Project_ProjectStatus]
GO
/****** Object:  ForeignKey [FK_Skills_Type]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Skills]  WITH CHECK ADD  CONSTRAINT [FK_Skills_Type] FOREIGN KEY([type])
REFERENCES [dbo].[Type] ([type])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Skills] CHECK CONSTRAINT [FK_Skills_Type]
GO
/****** Object:  ForeignKey [FK_Teams_Member1]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Member1] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Member1]
GO
/****** Object:  ForeignKey [FK_Teams_Project]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Project]
GO
/****** Object:  ForeignKey [FK_MemberLikes_Likes]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[MemberLikes]  WITH CHECK ADD  CONSTRAINT [FK_MemberLikes_Likes] FOREIGN KEY([like])
REFERENCES [dbo].[Likes] ([like_value])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[MemberLikes] CHECK CONSTRAINT [FK_MemberLikes_Likes]
GO
/****** Object:  ForeignKey [FK_MemberLikes_Member]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[MemberLikes]  WITH CHECK ADD  CONSTRAINT [FK_MemberLikes_Member] FOREIGN KEY([owner_login])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MemberLikes] CHECK CONSTRAINT [FK_MemberLikes_Member]
GO
/****** Object:  ForeignKey [FK_MemberLikes_Member1]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[MemberLikes]  WITH CHECK ADD  CONSTRAINT [FK_MemberLikes_Member1] FOREIGN KEY([liker_login])
REFERENCES [dbo].[Member] ([login])
GO
ALTER TABLE [dbo].[MemberLikes] CHECK CONSTRAINT [FK_MemberLikes_Member1]
GO
/****** Object:  ForeignKey [FK_Member_Skills_Skill_Level]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Member_Skills_Skill_Level] FOREIGN KEY([self_assessed_level])
REFERENCES [dbo].[Skill_Level] ([level])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_Member_Skills_Skill_Level]
GO
/****** Object:  ForeignKey [FK_Member_Skills_Skills]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Member_Skills_Skills] FOREIGN KEY([skill_id])
REFERENCES [dbo].[Skills] ([id])
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_Member_Skills_Skills]
GO
/****** Object:  ForeignKey [FK_MemberSkills_Member]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_MemberSkills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_MemberSkills_Member]
GO
/****** Object:  ForeignKey [FK_Project_History_Member]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Member]
GO
/****** Object:  ForeignKey [FK_Project_History_Project]    Script Date: 05/17/2013 22:37:45 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Project]
GO
