USE [master]
GO
/****** Object:  Database [inTouchDB]    Script Date: 05/28/2013 22:00:38 ******/
CREATE DATABASE [inTouchDB] ON  PRIMARY 
( NAME = N'inTouchDB', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'inTouchDB_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\inTouchDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
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
/****** Object:  User [admin]    Script Date: 05/28/2013 22:00:38 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Skill_Level]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Sex]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Project_Status]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Likes]    Script Date: 05/28/2013 22:00:39 ******/
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
/****** Object:  Table [dbo].[Level]    Script Date: 05/28/2013 22:00:39 ******/
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
INSERT [dbo].[Level] ([qlevel]) VALUES (N'GODLIKE')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'JODA')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'JUNIOR')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'MIDDLE')
INSERT [dbo].[Level] ([qlevel]) VALUES (N'SENIOR')
/****** Object:  Table [dbo].[Member]    Script Date: 05/28/2013 22:00:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member](
	[login] [nvarchar](50) NOT NULL,
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
 CONSTRAINT [PK_Member_1] PRIMARY KEY CLUSTERED 
(
	[login] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'dartik@gmail.com', N'123456', N'Darth', N'Vader', CAST(0x88FA0A00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JODA', 20, N'dartik@gmail.com.', N'Let the dark side of the force be with you', 0, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'iruna.kov@gmail.com', N'1234567', N'Iryna', N'Kovalyovska', CAST(0x50190B00 AS Date), CAST(0x27370B00 AS Date), N'FEMALE', N'JUNIOR', 1, N'developer.jpg', NULL, 50, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'last''@gmail.com', N'123456', N'first', N'last', NULL, CAST(0x27370B00 AS Date), N'MALE', NULL, 0, N'manager.jpg', NULL, 0, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'lava647@gmail.com', N'111111', N'Olya', N'Lavinska', CAST(0xC7180B00 AS Date), CAST(0x27370B00 AS Date), N'FEMALE', N'JUNIOR', 0, N'lava647@gmail.com.jpg', N'I like to be QA very much...', -1, N'TESTER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'lihos.tor@gmail.com', N'123456st', N'Natalia', N'Stosyk', CAST(0x1D160B00 AS Date), CAST(0x27370B00 AS Date), N'FEMALE', N'JUNIOR', 0.6, N'qa.jpg', NULL, 0, N'TESTER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'molodec@email.ua', N'123456', N'Sergiy', N'Kononchuk', CAST(0xEE180B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 1, N'developer.jpg', N'Hello world)))', 26, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'oksanavolyn@gmail.com', N'123456', N'Oksana', N'Nytrebych', CAST(0x03130B00 AS Date), CAST(0x27370B00 AS Date), N'FEMALE', N'JUNIOR', 1, N'oksanavolyn@gmail.com.gif', NULL, 0, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'ol30.05ka@gmail.com', N'Q3KEI33M71', N'Olya', N'Luzhetska', CAST(0xA0170B00 AS Date), CAST(0x27370B00 AS Date), N'FEMALE', N'JUNIOR', 0.2, N'ol30.05ka@gmail.com.PNG', NULL, 0, N'TESTER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'ovchar.andr@gmail.com', N'123456', N'Ovchar', N'Andriy', CAST(0xA5170B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 2, N'ovchar.andr@gmail.com.jpg', N'By world)))', 24, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'petruk@mail.ru', N'123456', N'Kolya', N'Petriv', CAST(0x12370B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 5, N'developer.jpg', NULL, -1, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'roman.fylypets@gmal.com', N'qwerty', N'Roman', N'Fylypets', CAST(0x9B190B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 1, N'roman.fylypets@gmal.com.jpg', NULL, -20, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'rwerewolf@gmail.com', N'123456', N'Roman', N'Markhyvka', CAST(0xEE1A0B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 1, N'rwerewolf@gmail.com.jpg', N'zZz...', 1, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'senicker@gmail.com', N'karasj', N'Nick', N'Seniuk', CAST(0xD1170B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 1, N'senicker@gmail.com.jpg', NULL, 0, N'DEVELOPER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'serge.pasternak@hotmail.co.uk', N'starwars', N'Serge', N'Pasternak', CAST(0xC8150B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 1, N'manager.jpg', N'I may look simple but i want you to know
I’ve been to college,
I’m full of knowledge', 80, N'MANAGER')
INSERT [dbo].[Member] ([login], [password], [name], [surname], [birthday], [registration], [sex], [qlevel], [experience], [photo_link], [additional_info], [rating], [role]) VALUES (N'zatorsky.danylo@gmail.com', N'1234567', N'Danylo', N'Zatorsky', CAST(0x77180B00 AS Date), CAST(0x27370B00 AS Date), N'MALE', N'JUNIOR', 0.5, N'zatorsky.danylo@gmail.com.jpg', N'I like java:)', 74, N'DEVELOPER')
/****** Object:  Table [dbo].[Project]    Script Date: 05/28/2013 22:00:39 ******/
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
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (7, N'Death Star', CAST(0x27370B00 AS Date), CAST(0xE04D0B00 AS Date), NULL, N'A Death Star was a moon-sized Imperial military battlestation armed with a planet-destroying superlaser.
A Death Star was a moon-sized Imperial military battlestation armed with a planet-destroying s', N'Empire', N'OPEN')
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (8, N'BugTrace', CAST(0x27370B00 AS Date), CAST(0x29370B00 AS Date), NULL, N'Issue tracking system that  manages and maintains project’s former and current issue events, as needed by an organization.', N'<epam>', N'OPEN')
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (10, N'Make-IT-Easy', CAST(0x27370B00 AS Date), CAST(0x29370B00 AS Date), NULL, N'Excelent system for managing project`s information', N'<epam>', N'OPEN')
INSERT [dbo].[Project] ([id], [name], [created], [estimated_completion], [completed], [description], [customer], [status]) VALUES (11, N'inTouch', CAST(0x27370B00 AS Date), CAST(0x2A370B00 AS Date), NULL, N'InTouch is social networking service, that helps you to maintain communication with your teammates', N'<epam>', N'OPEN')
SET IDENTITY_INSERT [dbo].[Project] OFF
/****** Object:  Table [dbo].[Skills]    Script Date: 05/28/2013 22:00:39 ******/
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
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (11, N'RUSSIAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (12, N'DUTCH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (13, N'UKRANIAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (14, N'ITALYAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (15, N'GERMAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (16, N'CZECH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (17, N'JAPANESE', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (18, N'GREEK', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (19, N'POLISH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (20, N'FRANCH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (21, N'SPANISH', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (22, N'C', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (23, N'C++', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (24, N'PASCAL', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (25, N'DELPHI', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (26, N'PHP', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (27, N'PERL', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (28, N'RUBY', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (29, N'JavaScript', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (30, N'FOXPRO', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (31, N'Objective-C', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (32, N'ASSEMBLER', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (33, N'BUSH', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (34, N'SCALA', N'PROGRAMMING')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (35, N'jQuery', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (36, N'AJAX', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (37, N'SPRING', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (38, N'.NET', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (39, N'BOOTSTRAP', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (40, N'XML/XSLT', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (41, N'JSON', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (42, N'SWING', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (43, N'AWT', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (44, N'GUAVA', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (45, N'MAVEN', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (46, N'ANT', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (47, N'LOG4J', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (48, N'SLF4J', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (49, N'JUNIT', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (50, N'SQL', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (51, N'JPA', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (52, N'JSTL', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (53, N'J2EE', N'TECHNOLOGY')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (54, N'RUSSIAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (55, N'RUSSIAN', N'LANGUAGE')
INSERT [dbo].[Skills] ([id], [name], [type]) VALUES (56, N'RUSSIAN', N'LANGUAGE')
SET IDENTITY_INSERT [dbo].[Skills] OFF
/****** Object:  Table [dbo].[Teams]    Script Date: 05/28/2013 22:00:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teams](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[project_id] [bigint] NOT NULL,
	[member_id] [nvarchar](50) NOT NULL,
	[enter_date] [date] NULL,
	[enter_time] [time](0) NULL,
 CONSTRAINT [PK_Teams_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Teams] ON
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (12, 8, N'rwerewolf@gmail.com', CAST(0x27370B00 AS Date), CAST(0x00BAF60000000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (13, 8, N'lava647@gmail.com', CAST(0x27370B00 AS Date), CAST(0x005D070100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (14, 8, N'oksanavolyn@gmail.com', CAST(0x27370B00 AS Date), CAST(0x00540B0100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (16, 10, N'roman.fylypets@gmal.com', CAST(0x27370B00 AS Date), CAST(0x00EC100100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (17, 10, N'lihos.tor@gmail.com', CAST(0x27370B00 AS Date), CAST(0x007D170100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (18, 10, N'ol30.05ka@gmail.com', CAST(0x27370B00 AS Date), CAST(0x008C170100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (19, 10, N'senicker@gmail.com', CAST(0x27370B00 AS Date), CAST(0x00C6170100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (20, 11, N'serge.pasternak@hotmail.co.uk', CAST(0x27370B00 AS Date), CAST(0x001D1E0100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (21, 11, N'molodec@email.ua', CAST(0x27370B00 AS Date), CAST(0x00261E0100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (22, 11, N'ovchar.andr@gmail.com', CAST(0x27370B00 AS Date), CAST(0x002A1E0100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (23, 11, N'zatorsky.danylo@gmail.com', CAST(0x27370B00 AS Date), CAST(0x002D1E0100000000 AS Time))
INSERT [dbo].[Teams] ([id], [project_id], [member_id], [enter_date], [enter_time]) VALUES (24, 11, N'iruna.kov@gmail.com', CAST(0x27370B00 AS Date), CAST(0x00321E0100000000 AS Time))
SET IDENTITY_INSERT [dbo].[Teams] OFF
/****** Object:  Table [dbo].[Member_Skills]    Script Date: 05/28/2013 22:00:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member_Skills](
	[member_id] [nvarchar](50) NOT NULL,
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
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 30, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'iruna.kov@gmail.com', 50, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 22, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 50, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lava647@gmail.com', 53, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 9, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 26, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 29, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 38, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'lihos.tor@gmail.com', 50, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 30, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 39, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'molodec@email.ua', 53, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 23, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 36, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'oksanavolyn@gmail.com', 50, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 9, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 23, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 29, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 38, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 39, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ol30.05ka@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 22, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 23, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 29, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 32, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 36, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 39, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'ovchar.andr@gmail.com', 52, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 15, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 23, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 29, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 36, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 41, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'roman.fylypets@gmal.com', 53, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 22, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 23, NULL, NULL, NULL)
GO
print 'Processed 100 total records'
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 32, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 50, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 52, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'rwerewolf@gmail.com', 53, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 9, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 32, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 38, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 39, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 41, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'senicker@gmail.com', 53, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 15, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 22, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 24, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 28, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 29, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 32, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 35, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 36, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 39, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 41, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 42, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 43, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'serge.pasternak@hotmail.co.uk', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 6, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 8, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 11, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 13, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 25, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 30, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 40, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 41, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 42, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 47, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 49, NULL, NULL, NULL)
INSERT [dbo].[Member_Skills] ([member_id], [skill_id], [experience], [description], [self_assessed_level]) VALUES (N'zatorsky.danylo@gmail.com', 52, NULL, NULL, NULL)
/****** Object:  Table [dbo].[Member_Likes]    Script Date: 05/28/2013 22:00:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member_Likes](
	[owner_login] [nvarchar](50) NOT NULL,
	[liker_login] [nvarchar](50) NOT NULL,
	[like] [nvarchar](25) NULL,
 CONSTRAINT [PK_Member_Likes] PRIMARY KEY CLUSTERED 
(
	[owner_login] ASC,
	[liker_login] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'lava647@gmail.com', N'rwerewolf@gmail.com', N'DISLIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'molodec@email.ua', N'iruna.kov@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'molodec@email.ua', N'rwerewolf@gmail.com', N'DISLIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'ovchar.andr@gmail.com', N'dartik@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'ovchar.andr@gmail.com', N'iruna.kov@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'ovchar.andr@gmail.com', N'molodec@email.ua', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'ovchar.andr@gmail.com', N'rwerewolf@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'ovchar.andr@gmail.com', N'serge.pasternak@hotmail.co.uk', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'petruk@mail.ru', N'dartik@gmail.com', N'DISLIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'roman.fylypets@gmal.com', N'senicker@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'rwerewolf@gmail.com', N'oksanavolyn@gmail.com', N'LIKE')
INSERT [dbo].[Member_Likes] ([owner_login], [liker_login], [like]) VALUES (N'zatorsky.danylo@gmail.com', N'molodec@email.ua', N'DONT_CARE')
/****** Object:  Table [dbo].[Project_History]    Script Date: 05/28/2013 22:00:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project_History](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[member_id] [nvarchar](50) NOT NULL,
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
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (9, N'petruk@mail.ru', 7, CAST(0x27370B00 AS Date), CAST(0x00B9250100000000 AS Time), CAST(0x27370B00 AS Date), CAST(0x00B9250100000000 AS Time))
INSERT [dbo].[Project_History] ([id], [member_id], [project_id], [enter_date], [enter_time], [leaving_date], [leaving_time]) VALUES (19, N'dartik@gmail.com', 7, CAST(0x27370B00 AS Date), CAST(0x0009310100000000 AS Time), CAST(0x27370B00 AS Date), CAST(0x0009310100000000 AS Time))
SET IDENTITY_INSERT [dbo].[Project_History] OFF
/****** Object:  ForeignKey [FK_Member_Level]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Level] FOREIGN KEY([qlevel])
REFERENCES [dbo].[Level] ([qlevel])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Level]
GO
/****** Object:  ForeignKey [FK_Member_Role]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Role] FOREIGN KEY([role])
REFERENCES [dbo].[Role] ([role])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Role]
GO
/****** Object:  ForeignKey [FK_Member_Sex]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FK_Member_Sex] FOREIGN KEY([sex])
REFERENCES [dbo].[Sex] ([sex])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FK_Member_Sex]
GO
/****** Object:  ForeignKey [FK_Project_ProjectStatus]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Project]  WITH CHECK ADD  CONSTRAINT [FK_Project_ProjectStatus] FOREIGN KEY([status])
REFERENCES [dbo].[Project_Status] ([status])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Project] CHECK CONSTRAINT [FK_Project_ProjectStatus]
GO
/****** Object:  ForeignKey [FK_Skills_Type]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Skills]  WITH CHECK ADD  CONSTRAINT [FK_Skills_Type] FOREIGN KEY([type])
REFERENCES [dbo].[Type] ([type])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Skills] CHECK CONSTRAINT [FK_Skills_Type]
GO
/****** Object:  ForeignKey [FK_Teams_Member1]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Member1] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Member1]
GO
/****** Object:  ForeignKey [FK_Teams_Project]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Project]
GO
/****** Object:  ForeignKey [FK_Member_Skills_Member]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Member_Skills_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_Member_Skills_Member]
GO
/****** Object:  ForeignKey [FK_Member_Skills_Skill_Level]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Member_Skills_Skill_Level] FOREIGN KEY([self_assessed_level])
REFERENCES [dbo].[Skill_Level] ([level])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_Member_Skills_Skill_Level]
GO
/****** Object:  ForeignKey [FK_Member_Skills_Skills]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Skills]  WITH CHECK ADD  CONSTRAINT [FK_Member_Skills_Skills] FOREIGN KEY([skill_id])
REFERENCES [dbo].[Skills] ([id])
GO
ALTER TABLE [dbo].[Member_Skills] CHECK CONSTRAINT [FK_Member_Skills_Skills]
GO
/****** Object:  ForeignKey [FK_Member_Likes_Member]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Likes]  WITH CHECK ADD  CONSTRAINT [FK_Member_Likes_Member] FOREIGN KEY([owner_login])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Member_Likes] CHECK CONSTRAINT [FK_Member_Likes_Member]
GO
/****** Object:  ForeignKey [FK_Member_Likes_Member1]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Likes]  WITH CHECK ADD  CONSTRAINT [FK_Member_Likes_Member1] FOREIGN KEY([liker_login])
REFERENCES [dbo].[Member] ([login])
GO
ALTER TABLE [dbo].[Member_Likes] CHECK CONSTRAINT [FK_Member_Likes_Member1]
GO
/****** Object:  ForeignKey [FK_MemberLikes_Likes]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Member_Likes]  WITH CHECK ADD  CONSTRAINT [FK_MemberLikes_Likes] FOREIGN KEY([like])
REFERENCES [dbo].[Likes] ([like_value])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Member_Likes] CHECK CONSTRAINT [FK_MemberLikes_Likes]
GO
/****** Object:  ForeignKey [FK_Project_History_Member]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[Member] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Member]
GO
/****** Object:  ForeignKey [FK_Project_History_Project]    Script Date: 05/28/2013 22:00:39 ******/
ALTER TABLE [dbo].[Project_History]  WITH CHECK ADD  CONSTRAINT [FK_Project_History_Project] FOREIGN KEY([project_id])
REFERENCES [dbo].[Project] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Project_History] CHECK CONSTRAINT [FK_Project_History_Project]
GO
