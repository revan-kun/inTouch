<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Uploading Form</title>
</head>
<body>
 	  
  	<form action="attachment"     enctype="multipart/form-data" method="post">
  		<label> Select a file to upload:</label>
 		<input type="file" name="file"  />
 	 	<br><label>Enter the name of image:</label>
 		<input  name="newFileName" /> 
 		<input type="submit" value="Upload File" />
 	</form>
 	

</body>
</html>