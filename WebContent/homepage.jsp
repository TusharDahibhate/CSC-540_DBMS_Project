<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WolfHospital</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
<script src="js/jquery.js"></script>
<style>
     #logout
     {
          height: 50px; width: 100px; background: #1a8180; position: fixed; top: 0px; color: white; font-size: 30px; right: 0px;
          line-height: 50px; vertical-align: middle; text-align: center; z-index: 3; cursor: pointer;
     }
     #logout:hover
     {
          font-size: 35px; background-color: 3cb3b2;
     }
     #welcomeBox
     {
          height: 50px; width: 200px; background: #1a8180; position: fixed; top: 0px; color: white; font-size: 30px;
          line-height: 50px; vertical-align: middle; text-align: center; z-index: 3;
     }
     #buttonbox
     {
          width: 230px; height: 230px; position: absolute; margin-left: -115px; margin-top: -130px; left: 50%; top: 50%;
     }
     .buttoner
     {
          width: 150px; height: 150px; border-radius: 75px; position: absolute; cursor: pointer;
          color: white; text-align: center; line-height: 150px; vertical-align: middle; font-size: 30px;
     }
     #oldChatbutton
     {
          background: #086060; z-index: 2;
     }
     #oldChatbutton:hover
     {
          font-size: 38px; background: #1a8180;
     }
     #newChatbutton
     {
          background: #3ca3a2;  right: 0px; bottom: 0px; z-index: 1;
     }
     #newChatbutton:hover
     {
          background: #5ec5c5; font-size: 38px;
     }
     #oldChatWindow, #newChatWindow
     {
          position: absolute; left: 50%; top: 50%; width: 400px; height: 400px;
          margin-left: -200px; margin-top: -200px; display: none; opacity: 0;
          color: #1a8180;
     }
     #closeOldWindow, #closeNewWindow
     {
          color: inherit; position: absolute; top: 0px; right: 0px; font-size: 30px; cursor: pointer; background: #ffffff;
          width: 70px; height: 30px; line-height: 30px; text-align: center; vertical-align: middle;
     }
     .nameBox
     {
          color: inherit; font-size: 25px; cursor: pointer;
          width: 200px; height: 40px; line-height: 40px; text-align: center; vertical-align: middle; margin-top: 10px;
          border: 3px solid #1a8180; border-radius: 7px; border-bottom-right-radius: 0px;
     }
     .nameBox:hover
     {
          color: white; background: #1a8180;
     }
     .nameBoxCont
     {
          position: absolute; top: 40px;  left: 50%; margin-left: -100px; max-height: 300px; overflow-y: scroll; width: 220px;
     }
     #toastMsg
     {
          height: 40px; background: #1a8180; color: white; position: absolute;  top: 75px; margin-left: -150px; width: 300px;
          border-radius: 8px; border-bottom-left-radius: 0px; left: 50%; line-height: 40px; text-align: center; vertical-align: middle;
          display: none; font-size: 25px;
     }
     #toastMsg2
     {
          height: 40px; background: #1a8180; color: white; position: absolute;  top: 75px; margin-left: -150px; width: 300px;
          border-radius: 8px; border-bottom-left-radius: 0px; left: 50%; line-height: 40px; text-align: center; vertical-align: middle;
          font-size: 35px; display: none; z-index: 5;
     }
     #notification
     {
          position: fixed; z-index: 10; width: 70px; height: 50px; right: 100px; cursor: pointer;
     }
     #notification:hover
     {
          background-color: 3cb3b2;
     }
     #popupbar
     {
          font-size: 20px; color: white; width: 23px; height: 23px; background: #8B0000; border-radius: 23px; line-height: 24px; vertical-align: middle;
          text-align: center; display: none;
     }
     #searchBar
     {
          width: 300px; height: 32px; position: absolute; z-index: 4; color: white; font-size: 20px; background-color: #2F4F4F; display: none;
          left: 50%; margin-left: -150px; top: 110px; text-align: center; line-height: 27px; vertical-align: bottom; border-bottom-right-radius: 8px;
     }
     #searchImage
     {
          width: 30px; height: 30px; margin-left: 30px; position: absolute; bottom: -1px;
     }
     #textField
     {
          background-color: inherit; width: 100px; height: 27px; position: absolute; left: 80px; bottom: 1px;
     }
     #ipfield
     {
          position: absolute; width: 140px; color: white; background-color: inherit; border: 0px; text-align: center; font-family: simplifica;
          font-size: 20px; letter-spacing: 1px; left: 0px; border-bottom: 1px solid white; outline: none;
     }
     #notifDisplay
     {
          background-color: #1a8180; position: fixed; width: 206px;  right: 40px; top: 52px;
          border-radius: 8px; border-top-left-radius: 0px; padding-bottom: 10px; display: none;
     }
     
     #titleBox{
     	position: absolute; top: 100px;  left: 50%; margin-left: -250px; max-height: 300px; overflow-y: scroll; width: 500px;
     	 height: 300px;
     }
	
	.dropbtn {
	  background-color: #1a8180;
	  color: white;
	  padding: 16px;
	  font-size: 16px;
	  border: none;
	  cursor: pointer;
	}
	
	.dropdown {
	  position: relative;
	  display: inline-block;
	  float: left;
	}
	
	#selectedFunc{
	float: right;
	margin-right: 200px;
	width: 100px;
	height: 50px;
	vertical-align: middle; text-align: center;
	line-height: 50px;
	color: #1a8180;
	}
	
	.functionalityClass{
	clear:both;
	top: 80px;
	position: absolute;
	
	}
	
	.dropdown-content {
	  display: none;
	  position: absolute;
	  background-color: #f9f9f9;
	  min-width: 160px;
	  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	  z-index: 1;
	}
	
	.dropdown-content a {
	  color: black;
	  padding: 12px 16px;
	  text-decoration: none;
	  display: block;
	}
	
	.dropdown-content a:hover {background-color: #f1f1f1}
	
	.dropdown:hover .dropdown-content {
	  display: block;
	}
	
	.dropdown:hover .dropbtn {
	  background-color: #3cb3b2;
	}
	
     ::-webkit-scrollbar {
          width: 5px;
     }

     ::-webkit-scrollbar-track {
          border-radius: 0px;
     }

     ::-webkit-scrollbar-thumb {
          border-radius: 0px;
          background: #1a8180;
     }
</style>
</head>
<body>
	<div class="header">WolfHospital: For all your late night howls!</div>
	<div id="welcomeBox">Welcome, D-Day User!</div>
	<div id="logout">Logout</div>
	
	<div id="titleBox">
		<div></div>
		<div class="dropdown">
			  <button class="dropbtn">Functionality: </button>
			  <div class="dropdown-content">
			  <a href="#" onclick="funcCalled(1);">Hospital</a>
			  <a href="#" onclick="funcCalled(2);">Patient</a>
			  <a href="#" onclick="funcCalled(3);">Operator</a>
			  <a href="#" onclick="funcCalled(4);">Doctor</a>
			  </div>
		</div>
		<div id="selectedFunc"></div>
		<div id="hospital" class="functionalityClass">
			> <a href="BedServlet">Bed Management</a><br/>
			> <a href="WardServlet">Ward Management</a><br/>
			> <a href="StaffServlet">Staff Management</a><br/>
		</div>
		<div id="patient" class="functionalityClass">
			 > <a href="PatientServlet">Patient Management</a><br/>
		</div>
		<div id="operator" class="functionalityClass">
			> <a href="BillingAccountServlet">Billing Account Management</a><br/>
			> <a href="CheckInServlet">Check-in Management</a><br/>
			> <a href="BedServlet">Assign bed to Patient</a><br/>
		</div>
		<div id="doctor" class="functionalityClass">
			> <a href="PatientServlet">Patient Management</a><br/>
			> <a href="TestReportServlet">Test Report Management</a><br/>
			> <a href="PrescriptionServlet">Prescription Management</a><br/>
			> <a href="TestServlet">Test Management</a><br/>
			> <a href="MedicalRecordServlet">Medical Record Management</a><br/>
		</div>
	</div>
	<div id="fetchedContent"></div>
	
</body>
<script>

	funcCalled(1);
	function funcCalled(arg){
		setAllNone();
		if(arg == 1){
			document.getElementById("hospital").style.display = "block";
			document.getElementById("selectedFunc").innerHTML = "Hospital";
		}
		if(arg == 2){
			document.getElementById("patient").style.display = "block";
			document.getElementById("selectedFunc").innerHTML = "Patient";
		}
		if(arg == 3){
			document.getElementById("operator").style.display = "block";
			document.getElementById("selectedFunc").innerHTML = "Operator";
		}
		if(arg == 4){
			document.getElementById("doctor").style.display = "block";
			document.getElementById("selectedFunc").innerHTML = "Doctor";
		}
	}
	
	function setAllNone(){
		//alert("called");
		document.getElementById("patient").style.display = "none";
		document.getElementById("hospital").style.display = "none";
		document.getElementById("doctor").style.display = "none";
		document.getElementById("operator").style.display = "none";
	}
</script>
</html>