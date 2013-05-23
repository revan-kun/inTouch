<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>In Touch</title>
	<link id="favicon" rel="shortcut icon" href="img/green.ico" />
	
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
	<link type="text/css" rel="stylesheet" href="css/datepicker.css" />
	<link type="text/css" rel="stylesheet" href="css/createProjectStyle.css" />
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	
	<style>
		.datepicker {
			z-index: 1151;
		}
	</style>
</head>

<body>

	<div class="modal" id="createProject">
		<div class="modal-body">
			<div class="alert alert-success" align="center">
				Fill up the creation form below to proceed
			</div>

			<form class="form-horizontal" id="createProjectHere" method='post' action='createProject'>
				<fieldset>
					<legend>Create new project </legend>

					<div class="control-group">
						<label class="control-label">Project Name</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">
									<i class="icon-folder-open"></i>
								</span> 
								<input type="text" class="input-xlarge" id="projectName" name="projectName" placeholder="project name...">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Customer</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">
									<i class="icon-briefcase"></i>
								</span> 
								<input type="text" class="input-xlarge" id="projectCustomer" name="projectCustomer" placeholder="customer...">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Estimated Completion</label>
						<div class="controls">
							<div class="input-append date" id="projectEstimatedCompletion" data-date="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd" />">
								<input class="span2" id="projectEstimatedCompletion"
									name="projectEstimatedCompletion" size="16" type="text"
									placeholder="yyyy-MM-dd" readonly> 
								<span class="add-on">
									<i class="icon-calendar"></i>
								</span>
							</div>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Description</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">
									<i class="icon-pencil"></i>
								</span>
								<textarea id="projectDescription" name="projectDescription" class="span3" rows="4" cols="80"
									placeholder="description..."></textarea>
							</div>
						</div>
					</div>



					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-success">
								<i class="icon-edit icon-white"></i>&nbsp;Sign UP
							</button>
							<button type="reset" class="btn">
								<i class="icon-repeat icon-black"></i>&nbsp;Clear
							</button>
						</div>
					</div>

				</fieldset>
			</form>
		</div>

		<div class="modal-footer">
			<a href="home" class="btn btn-danger">
				<i class="icon-white icon-remove"></i> Close
			</a>
		</div>
	</div>

	<script>
		$(function() {

			var nowTemp = new Date();
			var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
					nowTemp.getDate(), 0, 0, 0, 0);

			$('#projectEstimatedCompletion').datepicker({
				format : 'yyyy-mm-dd',
				onRender : function(date) {
					return date.valueOf() < now.valueOf() ? 'disabled' : '';
				}
			});

			var checkout = $('#projectEstimatedCompletion').datepicker({
				onRender : function(date) {
					return date.valueOf() < now.valueOf() ? 'disabled' : '';
				}
			}).on('changeDate', function(ev) {
				checkout.hide();
			}).data('datepicker');

		});
	</script>
</body>
</html>