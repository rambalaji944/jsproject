<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Page</title>
    <style>
        body {
            background-color: blue;
            color: black;
            text-align: center;
            padding: 20px;
        }

        form {
            display: inline-block;
            text-align: left;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: blue;
            color: white;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Edit Page</h2>

    <form action="${pageContext.request.contextPath}/update" method="post">
        <!-- Display the updated data -->
        <label for="vendorName">Vendor Name:</label>
        <input type="text" id="vendorName" name="vendorName" value="${vendorName}">
        <br>

        <label for="BankAccountNum">Bank Account Number:</label>
        <input type="text" id="BankAccountNum" name="bankToEdit" value="${bankToEdit}" readonly>
        <br>

        <label for="BankName">Bank Name:</label>
        <input type="text" id="BankName" name="bankName" value="${bankName}">
        <br>

        <label for="AddressLine1">Address Line 1:</label>
        <input type="text" id="AddressLine1" name="addressLine1" value="${addressLine1}">
        <br>

        <label for="AddressLine2">Address Line 2:</label>
        <input type="text" id="AddressLine2" name="addressLine2" value="${addressLine2}">
        <br>

        <label for="City">City:</label>
        <input type="text" id="City" name="city" value="${city}">
        <br>

        <label for="Country">Country:</label>
        <input type="text" id="Country" name="country" value="${country}">
        <br>

        <label for="ZipCode">Zip Code:</label>
        <input type="text" id="ZipCode" name="zipCode" value="${zipCode}">
        <br>

        <input type="submit" value="Update">
    </form>

    <p>${message}</p> <!-- Display any additional message, e.g., success or error messages -->
</body>
</html>
