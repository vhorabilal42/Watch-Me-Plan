<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import = "connection.DBConnection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WatchMePlan Book Event Ticket</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div class="container-xxl bg-white p-0">


		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
			<%int t  = (int)session.getAttribute("uuid");%>
		</div>
		<!-- Spinner End -->

		<!-- Navbar & Hero Start -->
		<div class="container-xxl position-relative p-0">

			<nav
				class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
				<a href="index.jsp" class="navbar-brand p-0"
					style="margin-top: 20px;">
					<h1 class="text-primary m-0">WatchMePlan</h1>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarCollapse">
					<div class="navbar-nav ms-auto py-0 pe-4"></div>
				</div>
			</nav>
		</div>
	</div>

	<div class="container-xxl py-5 px-0 wow fadeInUp" data-wow-delay="0.1s">

		<br><br><br><div class="col-md-6 bg-dark d-flex align-items-center"
			style="margin: auto">

			<div class="p-5 wow fadeInUp" data-wow-delay="0.2s">

			<%
				session = request.getSession(false);
				String usession = (String)session.getAttribute("uname");
				int eid = (int)session.getAttribute("esession");
				
			/* 	 = Integer.parseInt(eventid);
				out.print(usession + " " + eid);  */
				
				Connection conn = DBConnection.connect();
				PreparedStatement pstmt;
				String select = "Select EventName,Price from events where EventID=?";
				pstmt = conn.prepareStatement(select);
				pstmt.setInt(1,eid);

				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int price = Integer.parseInt(rs.getString("Price"));
				
				
				
				%>
				<h1 class="text-white mb-4">Book Tickets for <%=rs.getString("EventName")%></h1>

				<form action="makePayment" method="post">

					<table border="0" cellpadding="7" cellspacing="7">

						<tr>
							<td><label for="tPrice"
								style="font-size: 20px; color: white; font-weight: bold;">Price per ticket</label></td>
							<td><input type="text" name="tPrice" value="<%=rs.getString("Price")%>">
							</td>
						</tr>

						<tr>
							<td><label for="tQty"
								style="font-size: 20px; color: white; font-weight: bold;">No. of Tickets</label></td>
							<td><input type="text" name="tQty" id="tQty"></td>
						</tr>
									
<!-- 						<script>
							var qty = document.getElementById("tQty").value;
							var price = document.getElementById("tprice").value;
							var total = qty*price;
							document.getElementById("total").innerHTML = total ;
						</script>
 -->						
						
						<tr>
							<td><label for="payMethod"
								style="font-size: 20px; color: white; font-weight: bold;">How would you like to pay?</label></td>
							<td style="font-size:20px ;color: white;">
							<input type="radio" name="payment" value="cash">&nbsp;Cash&nbsp;&nbsp;
							<input type="radio" name="payment" value="upi">&nbsp;UPI
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Make Payment" class="btn btn-primary py-sm-3 px-sm-5 me-3 animated slideInLeft"></td>
						</tr>
						
					</table>
				</form>

			</div>
		</div>
	</div>

	<!-- Service End -->

	<!-- new footer start-->
	<div
		class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn"
		data-wow-delay="0.1s">
		<div class="container py-5">
			<div class="row g-5">
				<div class="col-lg-3 col-md-6">
					<h4
						class="section-title ff-secondary text-start text-primary fw-normal mb-4">Company</h4>
					<a class="btn btn-link" href="">About Us</a> <a
						class="btn btn-link" href="">Contact Us</a> <a
						class="btn btn-link" href="">Reservation</a> <a
						class="btn btn-link" href="">Privacy Policy</a> <a
						class="btn btn-link" href="">Terms & Condition</a>
				</div>
				<div class="col-lg-3 col-md-6">
					<h4
						class="section-title ff-secondary text-start text-primary fw-normal mb-4">Contact</h4>
					<p class="mb-2">
						<i class="fa fa-map-marker-alt me-3"></i>GLS University, Law
						Garden, Ahmedabad, 380006
					</p>
					<p class="mb-2">
						<i class="fa fa-phone-alt me-3"></i>+079 00000000
					</p>
					<p class="mb-2">
						<i class="fa fa-envelope me-3"></i>Contactus@glsuniversity.com
					</p>
					<div class="d-flex pt-2">
						<a class="btn btn-outline-light btn-social" href=""><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-outline-light btn-social" href=""><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-outline-light btn-social" href=""><i
							class="fab fa-youtube"></i></a> <a
							class="btn btn-outline-light btn-social" href=""><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<h4
						class="section-title ff-secondary text-start text-primary fw-normal mb-4">Contact
						Timings</h4>
					<h5 class="text-light fw-normal">Monday - Saturday</h5>
					<p>09AM - 04PM</p>
				</div>
				<div class="col-lg-3 col-md-6">
					<h4
						class="section-title ff-secondary text-start text-primary fw-normal mb-4">Newsletter</h4>
					<p>Join our WatchMePlan Family! Easiest way to keep in touch
						with us!</p>
					<div class="position-relative mx-auto" style="max-width: 400px;">
						<input class="form-control border-primary w-100 py-3 ps-4 pe-5"
							type="text" placeholder="Your email">
						<button type="button"
							class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="copyright">
				<div class="row">
					<div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
						&copy; <a class="border-bottom" href="#"
							style="color: darkgoldenrod;">WatchMePlan</a>, All Right
						Reserved. <br> <br>
						<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
						Designed By,<br> <span style="color: darkgoldenrod;">Shreya
							Acharya</span> (202201219010001)<br> <span
							style="color: darkgoldenrod;">Delcy Jivrajani</span>
						(202201219010010)<br> <span style="color: darkgoldenrod;">Faiz
							Mansuri</span> (202201219010015)<br> <span
							style="color: darkgoldenrod;">Uzzma Saiyed</span>
						(202201219010031)<br>
					</div>
					<div class="col-md-6 text-center text-md-end">
						<div class="footer-menu">
							<a href="">Home</a> <a href="">Cookies</a> <a href="">Help</a> <a
								href="">FQAs</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer End -->



	<!-- Back to Top -->
	<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top">
		<i class="bi bi-arrow-up"></i>
	</a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="lib/wow/wow.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/counterup/counterup.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="lib/tempusdominus/js/moment.min.js"></script>
	<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
	<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

	<!-- Template Javascript -->
	<script src="js/main.js"></script>
</body>
</html>




</body>
</html>