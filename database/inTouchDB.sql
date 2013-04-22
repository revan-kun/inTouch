USE [master]
GO
/****** Object:  Database [inTouchDB]    Script Date: 04/21/2013 12:32:28 ******/
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
/****** Object:  Table [dbo].[Level]    Script Date: 04/21/2013 12:32:29 ******/
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
/****** Object:  Table [dbo].[Sex]    Script Date: 04/21/2013 12:32:29 ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 04/21/2013 12:32:29 ******/
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
/****** Object:  Table [dbo].[ProjectStatus]    Script Date: 04/21/2013 12:32:29 ******/
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
/****** Object:  Table [dbo].[Project]    Script Date: 04/21/2013 12:32:29 ******/
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
	[status] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Project] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Project] ON
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (2, N'Indigo', CAST(0x24360B00 AS Date), CAST(0x91370B00 AS Date), NULL, N'Indigo project - dummy decription', N'Google', N'Open')
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (4, N'Juno', CAST(0x61360B00 AS Date), CAST(0xC9370B00 AS Date), NULL, N'Dummy description for Juno project', N'Apple', N'Open')
SET IDENTITY_INSERT [dbo].[Project] OFF
/****** Object:  Table [dbo].[Member]    Script Date: 04/21/2013 12:32:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member](
	[email] [nvarchar](255) NOT NULL,
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
	[role] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Member] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'carlos@gmail.com', N'carlos89', N'2222', N'Robert', N'Carlos', CAST(0x44130B00 AS Date), CAST(0xBD330B00 AS Date), N'Male', N'Senior', 4, N'.\resources\photos\carlos89.jpg', N'Developer')
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'dobkin@epam.com', N'dobkin', N'1111', N'Arkadiy', N'Dobkin', CAST(0x30EC0A00 AS Date), CAST(0xC4370B00 AS Date), N'Male', N'Senior', 20, N'.\resources\photos\dobkin.jpg', N'Manager')
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'franko77@ukr.net', N'franko77', N'3333qwerty', N'Ivan', N'Franko', CAST(0x3E030B00 AS Date), CAST(0x11370B00 AS Date), N'Male', N'Senior', 5, N'.\resources\photos\franko77.jpg', N'Developer')
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'grispin@gmail.com', N'grispin', N'123qwerty',  N'John', N'Grispin', CAST(0x23140B00 AS Date), CAST(0xF3340B00 AS Date), N'Male', N'Senior', 12, N'.\resources\photos\grispin.jpg', N'Manager')
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'smith@epam.com', N'mr_smith', N'4a6sdf6sdf88dfgh9', N'Josef', N'Smith', CAST(0x39030B00 AS Date), CAST(0x48350B00 AS Date), N'Male', N'Junior', 0.5, N'.\resources\photos\mr_smith.jpg', N'Tester')
INSERT [dbo].[Member] ([email], [login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [role]) VALUES (N'willson@gmail.com', N'willey1988', N'qwerty123', N'Jennifer', N'Willson', CAST(0xEA120B00 AS Date), CAST(0x6E320B00 AS Date), N'Female', N'Middle', 1.5, N'.\resources\photos\willey1988.jpg', N'Tester')
/****** Object:  Table [dbo].[Teams]    Script Date: 04/21/2013 12:32:29 ******/
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
/****** Object:  Table [dbo].[Skill]    Script Date: 04/21/2013 12:32:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skill](
	[member_id] [nvarchar](255) NOT NULL,
	[skill] [nvarchar](25) NOT NULL,
 CONSTRAINT [PK_Skill] PRIMARY KEY CLUSTERED 
(
	[member_id] ASC,
	[skill] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Project_ProjectStatus]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Project]  WITH CHECK ADD  CONSTRAINT [FK_Project_ProjectStatus] FOREIGN KEY([status])
REFERENCES [dbo].[ProjectStatus] ([status])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project] CHECK CONSTRAINT [FK_Project_ProjectStatus]
GO
/****** Object:  ForeignKey [FK_Member_Level]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Level] FOREIGN KEY([qlevel])
REFERENCES [dbo].[Level] ([qlevel])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Level]
GO
/****** Object:  ForeignKey [FK_Member_Role]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Role] FOREIGN KEY([role])
REFERENCES [dbo].[Role] ([role])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Role]
GO
/****** Object:  ForeignKey [FK_Member_Sex]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Sex] FOREIGN KEY([sex])
REFERENCES [dbo].[Sex] ([sex])
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Sex]
GO
/****** Object:  ForeignKey [FK_Teams_Member]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([email])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Member]
GO
/****** Object:  ForeignKey [FK_Teams_Project]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Project]
GO
/****** Object:  ForeignKey [FK_Skill_Member]    Script Date: 04/21/2013 12:32:29 ******/
ALTER TABLE [dbo].[Skill]  WITH CHECK ADD  CONSTRAINT [FK_Skill_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([email])
GO
ALTER TABLE [dbo].[Skill] CHECK CONSTRAINT [FK_Skill_Member]
GO
