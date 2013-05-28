<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InTouch</title>
<link id="favicon" rel="shortcut icon" href="img/red.ico" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>

<style type="text/css">
img.center { display: block; margin-left: auto; margin-right: auto; }
div.modal{
height: 520px;
width: 600px;

}
.modal-body{
max-height: 480px;
}
</style>

</head>
<body>

	<div class="modal" >
		<div class="modal-body" >

			<div class="alert alert-error" align="center">Sorry, but your
				account not found.</div>



			<div class="well span5" style="margin-left: 80px;" >

				<a class="thumbnail"> 
				<span  style='height: 300px; width: 450px;'></span>
				<img src="./img/wrong-password.jpg" style=" height: 300px; width: 450px; "/>
				</a>
			</div>



		</div>
		<div class="modal-footer">
			<a href="home" class="btn btn-info"><i
				class="icon-white icon-home"></i> Home</a> 
				<a href="registration"
				class="btn btn-success"><i class="icon-white icon-edit"></i> Sign up</a>
		</div>
	</div>

</body>
</html>