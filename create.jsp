<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <style>
           body {
            background-color: rgb(0, 0, 64);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        form {
            display: inline-block;
            text-align: left;
            background-color: white;
            padding: 10px; 
            border-radius: 10px;
            border: 1px solid white;
            width: 299px; 
            margin: auto;
        }

        #res {
            font-family: Arial, Helvetica, sans-serif;
            color: red; /* Changed color to red */
            text-align: center;
            margin-top: 10px;
        }

        input {
            width: 100%;
            padding: 8px; /* Adjusted padding */
            margin-bottom: 8px; /* Adjusted margin */
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: blue;
            color: white;
            cursor: pointer;
        
        }
    </style>
</head>
<body background="green">
    <form action="callprg1" method="post">
        <div id="res"><b>Register Details</b></div>
    
        <div>
            <label for="vendorName">Vendor Name:</label>
            <input type="text" name="vendorName">
        </div>
        <div>
            <label for="BankAccountNum">Bank Account Number:</label>
            <input type="number" name="BankAccountNum">
        </div>
        <div>
            <label for="BankName">Bank Name:</label>
            <input type="text" name="BankName">
        </div>
        <div>
            <label for="AddressLine1">Address Line 1:</label>
            <input type="text" name="AddressLine1">
        </div>
        <div>
            <label for="AddressLine2">Address Line 2:</label>
            <input type="text" name="AddressLine2">
        </div>
        <div>
            <label for="City">City:</label>
            <input type="text" name="City">
        </div>
        <div>
            <label for="Country">Country:</label>
            <input type="text" name="Country">
        </div>
        <div>
            <label for="ZipCode">Zip Code:</label>
            <input type="number" name="ZipCode">
        </div>
        <div>
            <input type="submit">
        </div>
    </form>
</body>
</html>
