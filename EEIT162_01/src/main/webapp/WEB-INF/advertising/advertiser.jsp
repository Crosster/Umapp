<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Advertiser Page</title>
    <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
</head>
<body>
    <h1>Welcome to Advertiser Page</h1>
    
    <div id="login-form">
        <h2>Login</h2>
        <form>
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email"><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password"><br>
            <button type="button" id="login-button">Login</button>
        </form>
    </div>

    <div id="advertiser-info" style="display: none;">
        <h2>Advertiser Info</h2>
        <!-- Advertiser info will be displayed here -->
    </div>

    <script>
        $(document).ready(function() {
            $("#login-button").click(function() {
                $.ajax({
                    type: "POST",
                    url: "/login",
                    data: {
                        email: $("#email").val(),
                        password: $("#password").val()
                    },
                    success: function(advertiser) {
                        // Hide login form
                        $("#login-form").hide();
                        
                        // Display advertiser info
                        $("#advertiser-info").html(
                            "<p>Email: " + advertiser.email + "</p>" +
                            "<p>Brand: " + advertiser.brand + "</p>" +
                            "<p>Company Name: " + advertiser.companyName + "</p>"
                            // Continue adding other info as needed
                        ).show();
                    },
                    error: function() {
                        alert("Invalid email or password");
                    }
                });
            });
        });
    </script>
</body>
</html>
